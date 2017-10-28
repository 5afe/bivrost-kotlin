package pm.gnosis

import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi
import org.bouncycastle.crypto.digests.KeccakDigest
import pm.gnosis.model.*
import pm.gnosis.utils.generateSolidityMethodId
import pm.gnosis.utils.toHex
import java.io.File
import java.util.regex.Pattern

class AbiParser {

    internal interface TypeHolder {
        fun toTypeName(): TypeName

        fun isDynamic(): Boolean

        fun hash(): String
    }

    internal class SimpleTypeHolder(private val className: ClassName, private val dynamic: Boolean) : TypeHolder {
        override fun toTypeName() = className

        override fun isDynamic() = dynamic

        override fun hash() = generateHash(listOf(className.toString()))
    }

    internal abstract class CollectionTypeHolder(val listType: ClassName, val itemType: TypeHolder) : TypeHolder {
        override fun hash() = generateHash(listOf(listType.toString(), itemType.hash()))
    }

    internal class ArrayTypeHolder(arraysMap: ArraysMap, itemType: TypeHolder, val capacity: Int) : CollectionTypeHolder(arraysMap.get(capacity), itemType) {

        override fun toTypeName() = ParameterizedTypeName.get(listType, itemType.toTypeName())

        override fun isDynamic() = capacity > 0 && itemType.isDynamic()
    }

    internal class VectorTypeHolder(itemType: TypeHolder) : CollectionTypeHolder(SolidityBase.Vector::class.asClassName(), itemType) {

        override fun toTypeName() = ParameterizedTypeName.get(listType, itemType.toTypeName())

        override fun isDynamic() = true
    }

    internal class TupleTypeHolder(index: Int, val entries: List<Pair<String, TypeHolder>>) : TypeHolder {

        val name = "Tuple" + numberToLetter(index)

        override fun toTypeName() = ClassName("", name)

        override fun hash() = generateHash(entries.map { "${it.first}:${it.second.hash()} " })

        override fun isDynamic() = entries.any { it.second.isDynamic() }
    }

    class ArraysMap(basePackageName: String, private val map: MutableMap<Int, ClassName> = HashMap()) {

        private val arraysPackageName = basePackageName + ".arrays"

        fun get(capacity: Int) = map.getOrPut(capacity, { ClassName(arraysPackageName, "Array" + capacity) })

        fun generate(output: File) {
            map.forEach {
                val capacity = it.key
                val arrayType = it.value

                val kotlinFile = KotlinFile.builder(arraysPackageName, arrayType.simpleName())

                val typeVariable = TypeVariableName.Companion.invoke("T", SolidityBase.Type::class)
                val itemsType = ParameterizedTypeName.get(List::class.asClassName(), typeVariable)

                val parameterizedClassType = ParameterizedTypeName.get(arrayType, typeVariable)
                val parameterizedItemDecoderType = ParameterizedTypeName.get(SolidityBase.TypeDecoder::class.asClassName(), typeVariable)
                val isDynamicBlock = CodeBlock.of("return " + if (capacity > 0) "$VARIABLE_NAME_ITEM_DECODER.isDynamic()" else "false")
                val decodeBlock =
                        CodeBlock.builder()
                                .addStatement("return %1T(%2T.decodeList(%3L, %4L, %5L))", arrayType, SolidityBase::class, "source", capacity, VARIABLE_NAME_ITEM_DECODER)
                                .build()
                val decoderBuilder = GeneratorUtils.generateDecoderBuilder(parameterizedClassType, decodeBlock, isDynamicBlock)
                        .addTypeVariable(typeVariable)
                        .addProperty(PropertySpec.builder(VARIABLE_NAME_ITEM_DECODER, parameterizedItemDecoderType).initializer(VARIABLE_NAME_ITEM_DECODER).build())
                        .primaryConstructor(FunSpec.constructorBuilder().addParameter(VARIABLE_NAME_ITEM_DECODER, parameterizedItemDecoderType).build())

                val kotlinClass = TypeSpec.classBuilder(arrayType)
                        .superclass(ParameterizedTypeName.get(SolidityBase.Array::class.asClassName(), typeVariable))
                        .addSuperclassConstructorParameter("items, %L", capacity)
                        .addTypeVariable(typeVariable)
                        .primaryConstructor(
                                FunSpec.constructorBuilder()
                                        .addParameter(ParameterSpec.builder("items", itemsType).build())
                                        .build()
                        )
                        .addType(decoderBuilder.build())
                val build = kotlinFile.addType(kotlinClass.build()).indent(INDENTATION).build()
                output.mkdirs()
                build.writeTo(output)
            }
        }

        companion object {
            const val VARIABLE_NAME_ITEM_DECODER = "itemDecoder"
        }
    }

    internal class GeneratorContext(val root: AbiRoot, val arrays: ArraysMap, val tuples: MutableMap<String, TupleTypeHolder> = HashMap())

    companion object {
        private const val DECODER_FUN_ARG_NAME = "data"
        private const val DECODER_VAR_PARTITIONS_NAME = "source"
        private const val DECODER_VAR_ARG_PREFIX = "arg" //arg0, arg1...
        private const val INDENTATION = "    "
        private const val TUPLE_TYPE_NAME = "tuple"
        private val TUPLE_NAME_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
        private val TYPE_PATTERN = Pattern.compile("^(\\w+)((?>\\[\\d*])*)$")
        private val ARRAY_DEF_PATTERN = Pattern.compile("^\\[[0-9]*]")

        private fun numberToLetter(index: Int): String {
            val builder = StringBuilder()
            var i = index
            do {
                builder.append(TUPLE_NAME_ALPHABET[i % TUPLE_NAME_ALPHABET.size])
                i /= TUPLE_NAME_ALPHABET.size
            } while (i > 0)
            return builder.toString()
        }

        private fun generateHash(parts: List<String>): String {
            val digest = KeccakDigest()
            parts.forEach {
                val bytes = it.toByteArray()
                digest.update(bytes, 0, bytes.size)
            }
            val hash = ByteArray(digest.digestSize)
            digest.doFinal(hash, 0)
            return hash.toHex()
        }

        fun generateWrapper(packageName: String, abi: String, output: File, arraysMap: ArraysMap) {
            val jsonAdapter = Moshi.Builder().build().adapter(AbiRoot::class.java)

            val abiRoot = jsonAdapter.fromJson(abi) ?: return

            val kotlinClass = TypeSpec.classBuilder(abiRoot.contractName)
            val kotlinFile = KotlinFile.builder(packageName, abiRoot.contractName)

            val context = GeneratorContext(abiRoot, arraysMap)
            kotlinClass.addTypes(generateFunctionObjects(context))
            kotlinClass.addTypes(generateTupleObjects(context))

            val build = kotlinFile.addType(kotlinClass.build()).indent(INDENTATION).build()
            output.mkdirs()
            build.writeTo(output)
        }

        private fun checkType(type: String): String {
            return Solidity.aliases.getOrElse(type, { type })
        }

        internal fun mapType(parameter: ParameterJson, context: GeneratorContext): TypeHolder {
            val matcher = TYPE_PATTERN.matcher(parameter.type)
            if (!matcher.find()) {
                throw IllegalArgumentException("Invalid parameter definition: ${parameter.type}!")
            }
            val arrayType = matcher.group(1)
            val baseType = generateElementaryType(arrayType) ?: generateTuple(arrayType, parameter, context) ?: throw IllegalArgumentException("Unknown parameter ${parameter.type}!")
            val arrayDef = matcher.group(2)
            if (arrayType.length < parameter.type.length && arrayDef.isNullOrBlank()) {
                throw IllegalArgumentException("Invalid parameter definition: ${parameter.type}!")
            }
            return parseArrayDefinition(arrayDef, baseType, context)
        }

        private fun generateElementaryType(type: String): TypeHolder? {
            val baseType = Solidity.types[checkType(type)] ?: return null
            return SimpleTypeHolder(ClassName.bestGuess(baseType), isSolidityDynamicType(type))
        }

        private fun generateTuple(type: String, parameters: ParameterJson, context: GeneratorContext): TypeHolder? {
            if (type != TUPLE_TYPE_NAME || parameters.components == null) {
                return null
            }
            val entries = parameters.components.mapIndexed { index, param ->
                Pair(if (param.name.isEmpty()) "param$index" else param.name.toLowerCase(), mapType(param, context))
            }
            val tupleTypeHolder = TupleTypeHolder(context.tuples.size, entries)
            return context.tuples.getOrPut(tupleTypeHolder.hash(), { tupleTypeHolder })
        }

        private fun parseArrayDefinition(arrayDef: String, innerType: TypeHolder, context: GeneratorContext): TypeHolder {
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
                ArrayTypeHolder(context.arrays, innerType, arraySize)
            }
            return parseArrayDefinition(arrayDef.removePrefix(match), collectionTypeHolder, context)
        }

        private fun generateFunctionObjects(context: GeneratorContext) =
                context.root.abi
                        .filter { it.type == "function" }
                        .groupBy { it.name }
                        .flatMap { (_, value) -> value.map { Pair(it, value.size > 1) } }
                        .map { (functionJson, useMethodId) ->

                            //Add method id
                            val methodId = "${functionJson.name}${generateMethodSignature(functionJson.inputs)}".generateSolidityMethodId()
                            val baseName = functionJson.name.capitalize()
                            val name = if (useMethodId) "${baseName}_$methodId" else baseName
                            val functionObject = TypeSpec.objectBuilder(name)

                            functionObject.addProperty(PropertySpec.builder("METHOD_ID", String::class, KModifier.CONST).initializer("\"$methodId\"").build())
                            functionObject.addFun(generateFunctionEncoder(functionJson, context))
                            if (functionJson.outputs.isNotEmpty()) {
                                val returnHolder = generateParameterHolder("Return", functionJson.outputs, context)
                                functionObject.addFun(generateParameterDecoder("decode", functionJson.outputs, returnHolder.name!!, context))
                                functionObject.addType(returnHolder)
                            }

                            if (functionJson.inputs.isNotEmpty()) {
                                val argumentsHolder = generateParameterHolder("Arguments", functionJson.inputs, context)
                                functionObject.addFun(generateParameterDecoder("decodeArguments", functionJson.inputs, argumentsHolder.name!!, context))
                                functionObject.addType(argumentsHolder)
                            }

                            functionObject.build()
                        }.toList()

        private fun generateMethodSignature(parameters: List<ParameterJson>): String =
                "(${parameters.joinToString(",") {
                    if (it.type.startsWith(TUPLE_TYPE_NAME)) {
                        it.components ?: return@joinToString ""
                        generateMethodSignature(it.components) + it.type.removePrefix(TUPLE_TYPE_NAME)
                    } else {
                        checkType(it.type)
                    }
                }})"

        private fun generateTupleObjects(context: GeneratorContext) =
                context.tuples.map {
                    val decoderTypeName = ClassName.bestGuess("Decoder")
                    val typeHolder = it.value

                    val builder = TypeSpec.classBuilder(typeHolder.name)
                    val constructor = FunSpec.constructorBuilder()

                    typeHolder.entries.forEach { (name, holder) ->
                        val className = holder.toTypeName()
                        constructor.addParameter(name, className)
                        builder.addProperty(PropertySpec.builder(name, className).initializer(name).build())
                    }

                    //Generate decodings
                    val decodeBlockBuilder = CodeBlock.builder()
                    val locationArgs = ArrayList<Pair<String, TypeHolder>>()
                    generateStaticArgDecoding(typeHolder, decodeBlockBuilder, locationArgs)
                    generateDynamicArgDecoding(locationArgs, decodeBlockBuilder)
                    decodeBlockBuilder.addStatement("return %1N(${(0 until typeHolder.entries.size).joinToString(", ") { "arg$it" }})", typeHolder.name)

                    builder
                            .addModifiers(KModifier.DATA)
                            .addSuperinterface(if (typeHolder.isDynamic()) SolidityBase.DynamicType::class else SolidityBase.StaticType::class)
                            .addFun(generateTupleEncoder(typeHolder))
                            .addType(GeneratorUtils.generateDecoder(typeHolder.name, decodeBlockBuilder.build(), typeHolder.isDynamic(), DECODER_VAR_PARTITIONS_NAME))
                            .companionObject(GeneratorUtils.generateDecoderCompanion(
                                    decoderTypeName,
                                    CodeBlock.of("%1T()", decoderTypeName)))
                    builder.primaryConstructor(constructor.build()).build()
                }.toList()


        private fun generateStaticArgDecoding(typeHolder: TupleTypeHolder, function: CodeBlock.Builder, locationArgs: MutableCollection<Pair<String, TypeHolder>>) {
            typeHolder.entries.forEachIndexed { index, info ->
                addDecoderStatementForType(function, locationArgs, index, info.second)
            }
        }


        private fun generateTupleEncoder(typeHolder: TupleTypeHolder) =
                FunSpec.builder("encode")
                        .returns(String::class)
                        .addModifiers(KModifier.OVERRIDE)
                        .addStatement("return %T.encodeFunctionArguments(${typeHolder.entries.map { it.first }.joinToString { it }})", SolidityBase::class)
                        .build()

        private fun generateFunctionEncoder(functionJson: AbiElementJson, context: GeneratorContext): FunSpec {
            val funSpec = FunSpec.builder("encode")
            functionJson.inputs.forEachIndexed { index, parameter ->
                val name = if (parameter.name.isEmpty()) "arg${index + 1}" else parameter.name
                val type = mapType(parameter, context)
                funSpec.addParameter(name, type.toTypeName())
            }

            val funWithParams = funSpec.build()
            val finalFun = funWithParams.toBuilder().returns(String::class)
            finalFun.addStatement("return \"0x\" + METHOD_ID${if (funWithParams.parameters.isNotEmpty()) {
                " + pm.gnosis.model.SolidityBase.encodeFunctionArguments(${funWithParams.parameters.joinToString { it.name }})"
            } else ""}")

            return finalFun.build()
        }

        private fun generateParameterDecoder(functionName: String, parameters: List<ParameterJson>, dataClassName: String, context: GeneratorContext): FunSpec {
            val funSpecBuilder = FunSpec.builder(functionName).addParameter(DECODER_FUN_ARG_NAME, String::class)

            //Set function return
            val typeName = ClassName("", dataClassName)
            funSpecBuilder.returns(typeName)

            funSpecBuilder.addStatement("val $DECODER_VAR_PARTITIONS_NAME = %1T.of($DECODER_FUN_ARG_NAME)", SolidityBase.PartitionData::class)
            funSpecBuilder.addStatement("")
            funSpecBuilder.addComment("Add decoders")

            val codeBlock = CodeBlock.builder()

            //Generate decodings
            val locationArgs = ArrayList<Pair<String, TypeHolder>>()
            generateStaticArgDecoding(parameters, codeBlock, locationArgs, context)
            generateDynamicArgDecoding(locationArgs, codeBlock)

            funSpecBuilder.addCode(codeBlock.build())

            funSpecBuilder.addStatement("")
            funSpecBuilder.addStatement("return $dataClassName(${(0 until parameters.size).joinToString(", ") { "arg$it" }})")

            return funSpecBuilder.build()
        }

        private fun generateParameterHolder(holderName: String, parameters: List<ParameterJson>, context: GeneratorContext): TypeSpec {
            val returnContainerBuilder = TypeSpec.classBuilder(holderName).addModifiers(KModifier.DATA)
            val returnContainerConstructor = FunSpec.constructorBuilder()

            parameters.forEachIndexed { index, parameterJson ->
                val name = if (parameterJson.name.isEmpty()) "param$index" else parameterJson.name.toLowerCase()
                val className = mapType(parameterJson, context).toTypeName()
                returnContainerConstructor.addParameter(name, className)
                returnContainerBuilder.addProperty(PropertySpec.builder(name, className).initializer(name).build())
            }

            return returnContainerBuilder.primaryConstructor(returnContainerConstructor.build()).build()
        }

        private fun generateStaticArgDecoding(parameters: List<ParameterJson>, function: CodeBlock.Builder, locationArgs: MutableCollection<Pair<String, TypeHolder>>,
                                              context: GeneratorContext) {
            parameters.forEachIndexed { index, outputJson ->
                val className = mapType(outputJson, context)
                addDecoderStatementForType(function, locationArgs, index, className)
            }
        }

        private fun addDecoderStatementForType(function: CodeBlock.Builder, locationArgs: MutableCollection<Pair<String, TypeHolder>>, index: Int, className: TypeHolder) {
            when {
                isSolidityDynamicType(className) -> {
                    locationArgs.add("$index" to className)
                    function.addStatement("$DECODER_VAR_PARTITIONS_NAME.consume()")
                }
                isSolidityArray(className) -> {
                    val format = buildArrayDecoder(className, forceStatic = true)
                    function.addStatement("val $DECODER_VAR_ARG_PREFIX$index = ${format.first}.decode(%L)", *format.second.toTypedArray(), DECODER_VAR_PARTITIONS_NAME)
                }
                else -> function.addStatement("val $DECODER_VAR_ARG_PREFIX$index = %1T.DECODER.decode($DECODER_VAR_PARTITIONS_NAME)", className.toTypeName())
            }
        }

        private fun generateDynamicArgDecoding(locationArgs: List<Pair<String, TypeHolder>>, function: CodeBlock.Builder) {
            locationArgs.forEach { (argIndex, type) ->
                val dynamicValName = "$DECODER_VAR_ARG_PREFIX$argIndex"

                if (isSolidityArray(type)) {
                    val format = buildArrayDecoder(type)
                    function.addStatement("val $dynamicValName = ${format.first}.decode(%L)", *format.second.toTypedArray(), DECODER_VAR_PARTITIONS_NAME)
                } else {
                    function.addStatement("val $dynamicValName = %1T.DECODER.decode(%2L)", type.toTypeName(), DECODER_VAR_PARTITIONS_NAME)
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
                return Pair("%T.Decoder(${codeInfo.first})", codeInfo.second)
            }
            val types = ArrayList<TypeName>()
            types.add(className.toTypeName())
            return Pair("%T.DECODER", types)
        }

        private fun isSolidityDynamicType(type: String) = SolidityBase.dynamicTypes.contains(type.toLowerCase())

        private fun isSolidityDynamicType(type: TypeHolder) = type.isDynamic()

        private fun isSolidityArray(type: TypeHolder) = type is CollectionTypeHolder
    }
}
