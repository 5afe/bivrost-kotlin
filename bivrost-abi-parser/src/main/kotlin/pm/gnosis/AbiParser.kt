package pm.gnosis

import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi
import pm.gnosis.model.*
import pm.gnosis.utils.generateSolidityMethodId
import java.io.File
import java.math.BigInteger
import java.util.regex.Pattern

class AbiParser {

    internal interface TypeHolder {
        fun toTypeName(): TypeName

        fun isDynamic(): Boolean
    }

    internal class SimpleTypeHolder(private val className: ClassName, private val dynamic: Boolean) : TypeHolder {
        override fun toTypeName(): TypeName {
            return className
        }

        override fun isDynamic(): Boolean {
            return dynamic
        }
    }

    internal abstract class CollectionTypeHolder(val listType: ClassName, val itemType: TypeHolder): TypeHolder

    internal class ArrayTypeHolder(itemType: TypeHolder, val capacity: Int) : CollectionTypeHolder(getListType(itemType), itemType) {

        override fun toTypeName(): TypeName {
            return ParameterizedTypeName.get(listType, itemType.toTypeName())
        }

        override fun isDynamic(): Boolean {
            return itemType.isDynamic()
        }

        companion object {
            private fun getListType(itemType: TypeHolder): ClassName {
                return if (itemType.isDynamic()) {
                    SolidityBase.ArrayDT::class.asClassName()
                } else {
                    SolidityBase.ArrayST::class.asClassName()
                }
            }
        }
    }

    internal class VectorTypeHolder(itemType: TypeHolder) : CollectionTypeHolder(getListType(itemType), itemType) {

        override fun toTypeName(): TypeName {
            return ParameterizedTypeName.get(listType, itemType.toTypeName())
        }

        override fun isDynamic(): Boolean {
            return true
        }

        companion object {
            private fun getListType(itemType: TypeHolder): ClassName {
                return if (itemType.isDynamic()) {
                    SolidityBase.VectorDT::class.asClassName()
                } else {
                    SolidityBase.VectorST::class.asClassName()
                }
            }
        }
    }

    companion object {
        private const val DECODER_FUN_ARG_NAME = "data"
        private const val DECODER_VAR_PARTITIONS_NAME = "partitions"
        private const val DECODER_VAR_LOCATION_ARG_PREFIX = "locationArg" //locationArg0, locationArg1...
        private const val DECODER_VAR_ARG_PREFIX = "arg" //arg0, arg1...
        private const val INDENTATION = "    "
        private val TYPE_PATTERN = Pattern.compile("^(\\w+)((?>\\[\\d*])*)$")
        private val ARRAY_DEF_PATTERN = Pattern.compile("^\\[[0-9]*]")

        fun generateWrapper(packageName: String, abi: String, output: File) {
            val jsonAdapter = Moshi.Builder().build().adapter(AbiRoot::class.java)

            val abiRoot = jsonAdapter.fromJson(abi) ?: return

            val kotlinClass = TypeSpec.classBuilder(abiRoot.contractName)
            val kotlinFile = KotlinFile.builder(packageName, abiRoot.contractName)

            kotlinClass.addTypes(generateFunctionObjects(abiRoot))
            val build = kotlinFile.addType(kotlinClass.build()).indent(INDENTATION).build()
            output.mkdirs()
            build.writeTo(output)
        }

        private fun checkType(type: String): String {
            return Solidity.aliases.getOrElse(type, { type })
        }

        internal fun mapType(type: String): TypeHolder {
            // uint[5][]
            val matcher = TYPE_PATTERN.matcher(type)
            if (!matcher.find()) {
                throw IllegalArgumentException("Invalid type definition: $type!")
            }
            val arrayType = matcher.group(1)
            val baseType = Solidity.types[checkType(arrayType)] ?: throw IllegalArgumentException("Unknown type $type!")
            val arrayDef = matcher.group(2)
            if (arrayType.length < type.length && arrayDef.isNullOrBlank()) {
                throw IllegalArgumentException("Invalid type definition: $type!")
            }
            return parseArrayDefinition(arrayDef, SimpleTypeHolder(ClassName.bestGuess(baseType), isSolidityDynamicType(arrayType)))
        }

        private fun parseArrayDefinition(arrayDef: String, innerType: TypeHolder): TypeHolder {
            if (arrayDef.isBlank()) {
                return innerType
            }
            val matcher = ARRAY_DEF_PATTERN.matcher(arrayDef)
            if (!matcher.find()) {
                throw IllegalArgumentException("Illegal array definition $arrayDef!")
            }
            val match = matcher.group() ?: throw IllegalArgumentException()
            val arraySizeDef = match.substring(1, match.length - 1)
            val arraySize = if (arraySizeDef.isBlank()) -1 else arraySizeDef.toInt()
            val collectionTypeHolder = if (arraySizeDef.isBlank()) {
                VectorTypeHolder(innerType)
            } else {
                ArrayTypeHolder(innerType, arraySize)
            }
            return parseArrayDefinition(arrayDef.removePrefix(match), collectionTypeHolder)
        }

        private fun generateFunctionObjects(abiRoot: AbiRoot) =
                abiRoot.abi.filter { it.type == "function" }.map { functionJson ->
                    val functionObject = TypeSpec.objectBuilder(functionJson.name.capitalize())

                    //Add method id
                    val methodId = "${functionJson.name}(${functionJson.inputs.joinToString(",") { checkType(it.type) }})".generateSolidityMethodId()
                    functionObject.addProperty(PropertySpec.builder("METHOD_ID", String::class, KModifier.CONST).initializer("\"$methodId\"").build())
                    functionObject.addFun(generateFunctionEncoder(functionJson))
                    if (functionJson.outputs.isNotEmpty()) {
                        val returnHolder = generateParameterHolder("Return", functionJson.outputs)
                        functionObject.addFun(generateParameterDecoder("decode", functionJson.outputs, returnHolder.name!!))
                        functionObject.addType(returnHolder)
                    }

                    if (functionJson.inputs.isNotEmpty()) {
                        val argumentsHolder = generateParameterHolder("Arguments", functionJson.inputs)
                        functionObject.addFun(generateParameterDecoder("decodeArguments", functionJson.inputs, argumentsHolder.name!!))
                        functionObject.addType(argumentsHolder)
                    }

                    functionObject.build()
                }.toList()

        private fun generateCheck(type: TypeHolder, name: String): CodeBlock? {
            if (type is CollectionTypeHolder) {
                val builder = CodeBlock.builder()
                if (type is ArrayTypeHolder) {
                    builder.addStatement("%L.checkCapacity(%L)", name, type.capacity)
                }

                if (type.itemType is CollectionTypeHolder) {
                    generateCheck(type.itemType, "it")?.let {
                        builder
                                .beginControlFlow("%L.items.forEach", name)
                                .add(it)
                                .endControlFlow()
                    }
                }
                return builder.build()
            }
            return null
        }

        private fun generateFunctionEncoder(functionJson: AbiElementJson): FunSpec {
            val funSpec = FunSpec.builder("encode")
            functionJson.inputs.forEachIndexed { index, parameter ->
                val name = if (parameter.name.isEmpty()) "arg${index + 1}" else parameter.name
                val type = mapType(parameter.type)
                funSpec.addParameter(name, type.toTypeName())
                generateCheck(type, name)?.let { funSpec.addCode(it) }
            }

            val funWithParams = funSpec.build()
            val finalFun = funWithParams.toBuilder().returns(String::class)
            finalFun.addStatement("return \"0x\" + METHOD_ID${if (funWithParams.parameters.isNotEmpty()) {
                " + pm.gnosis.model.SolidityBase.encodeFunctionArguments(${funWithParams.parameters.joinToString { it.name }})"
            } else ""}")

            return finalFun.build()
        }

        private fun generateParameterDecoder(functionName: String, parameters: List<ParameterJson>, dataClassName: String): FunSpec {
            val funSpecBuilder = FunSpec.builder(functionName).addParameter(DECODER_FUN_ARG_NAME, String::class)

            //Set function return
            val typeName = ClassName("", dataClassName)
            funSpecBuilder.returns(typeName)

            funSpecBuilder.addStatement("val $DECODER_VAR_PARTITIONS_NAME = %1T.of($DECODER_FUN_ARG_NAME)", SolidityBase.PartitionData::class)

            //Generate decodings
            val locationArgs = ArrayList<Pair<String, String>>()
            generateStaticArgDecoding(parameters, funSpecBuilder, locationArgs)
            generateDynamicArgDecoding(locationArgs, funSpecBuilder)

            funSpecBuilder.addStatement("")
            funSpecBuilder.addStatement("return $dataClassName(${(0 until parameters.size).joinToString(", ") { "arg$it" }})")

            return funSpecBuilder.build()
        }

        private fun generateParameterHolder(holderName: String, parameters: List<ParameterJson>): TypeSpec {
            val returnContainerBuilder = TypeSpec.classBuilder(holderName).addModifiers(KModifier.DATA)
            val returnContainerConstructor = FunSpec.constructorBuilder()

            parameters.forEachIndexed { index, parameterJson ->
                val name = if (parameterJson.name.isEmpty()) "param$index" else parameterJson.name.toLowerCase()
                val className = mapType(parameterJson.type).toTypeName()
                returnContainerConstructor.addParameter(name, className)
                returnContainerBuilder.addProperty(PropertySpec.builder(name, className).initializer(name).build())
            }

            return returnContainerBuilder.primaryConstructor(returnContainerConstructor.build()).build()
        }

        private fun generateStaticArgDecoding(parameters: List<ParameterJson>, function: FunSpec.Builder, locationArgs: MutableCollection<Pair<String, String>>) {
            function.addStatement("")
            function.addComment("Decode arguments")
            parameters.forEachIndexed { index, outputJson ->
                val className = mapType(outputJson.type)
                when {
                    isSolidityDynamicType(outputJson.type) -> {
                        locationArgs.add("$DECODER_VAR_LOCATION_ARG_PREFIX$index" to outputJson.type)
                        function.addStatement("val $DECODER_VAR_LOCATION_ARG_PREFIX$index = %1T($DECODER_VAR_PARTITIONS_NAME.consume(), 16)", BigInteger::class)
                    }
                    isSolidityArray(outputJson.type) -> {
                        val format = buildArrayDecoder(className, forceStatic = true)
                        function.addStatement("val $DECODER_VAR_LOCATION_ARG_PREFIX$index = ${format.first}.decode(%L)", *format.second.toTypedArray(), "partitions")
                    }
                    else -> function.addStatement("val $DECODER_VAR_ARG_PREFIX$index = %1T.DECODER.decode($DECODER_VAR_PARTITIONS_NAME)", className.toTypeName())
                }
            }
        }

        private fun generateDynamicArgDecoding(locationArgs: List<Pair<String, String>>, function: FunSpec.Builder) {
            (0 until locationArgs.size).forEach {
                val locationReference = locationArgs[it].first
                val type = locationArgs[it].second
                val className = mapType(type)
                val dynamicValName = locationReference.removePrefix("location").decapitalize()

                if (isSolidityArray(type)) {
                    val format = buildArrayDecoder(className)
                    function.addStatement("val $dynamicValName = ${format.first}.decode(%L)", *format.second.toTypedArray(), "partitions")
                } else {
                    function.addStatement("val $dynamicValName = %1T.DECODER.decode(%2L)", className.toTypeName(), "partitions")
                }
            }
        }

        private fun buildArrayDecoder(className: TypeHolder, forceStatic: Boolean = false): Pair<String, MutableList<TypeName>> {
            if (forceStatic && (
                    (className is VectorTypeHolder) ||
                    (className is ArrayTypeHolder && className.isDynamic()) ||
                            className.toTypeName() == Solidity.Bytes::class.asClassName() || className.toTypeName() == Solidity.String::class.asClassName())) {
                throw IllegalArgumentException("No dynamic types allowed!")
            }
            if (className is CollectionTypeHolder) {
                val childClass = className.itemType
                val codeInfo = buildArrayDecoder(childClass, forceStatic)
                codeInfo.second.add(0, className.listType)
                val capacityParam = if (className is ArrayTypeHolder) ", ${className.capacity}" else ""
                return Pair("%T.Decoder(${codeInfo.first}$capacityParam)", codeInfo.second)
            }
            val types = ArrayList<TypeName>()
            types.add(className.toTypeName())
            return Pair("%T.DECODER", types)
        }

        private fun isSolidityStaticType(type: String) = !isSolidityDynamicType(type)

        private fun isSolidityDynamicType(type: String) = isSolidityBytesType(type) || isSolidityStringType(type) || isSolidityDynamicArray(type)

        private fun isSolidityBytesType(type: String) = type.contains("bytes")

        private fun isSolidityStringType(type: String) = type.contains("string")

        private fun isSolidityDynamicArray(type: String) = type.contains("[]")

        private fun isSolidityArray(type: String) = type.contains(Regex(pattern = "\\[[0-9]*]"))
    }
}
