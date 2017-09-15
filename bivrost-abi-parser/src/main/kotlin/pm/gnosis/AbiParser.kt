package pm.gnosis

import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi
import pm.gnosis.model.*
import pm.gnosis.utils.generateSolidityMethodId
import java.io.File
import java.math.BigInteger
import java.util.regex.Pattern

class AbiParser {
    companion object {
        private const val DECODER_FUN_ARG_NAME = "data"
        private const val DECODER_VAR_PARTITIONS_NAME = "partitions"
        private const val DECODER_VAR_LOCATION_ARG_PREFIX = "locationArg" //locationArg0, locationArg1...
        private const val DECODER_VAR_ARG_PREFIX = "arg" //arg0, arg1...
        private const val INDENTATION = "    "
        private val TYPE_PATTERN = Pattern.compile("^(\\w+)((?>\\[\\d*])*)")

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

        internal fun mapType(type: String): TypeName {
            // uint[5][]
            val matcher = TYPE_PATTERN.matcher(type)
            matcher.find()
            val baseType = Solidity.types[checkType(matcher.group(1))] ?: throw IllegalArgumentException("Unknown type $type!")
            val arrayDef = matcher.group(2)
            return parseArrayDefinition(arrayDef, ClassName.bestGuess(baseType))
        }

        private fun parseArrayDefinition(arrayDef: String, innerType: TypeName): TypeName {
            if (arrayDef.isBlank()) {
                return innerType
            }
            val length = arrayDef.length
            if (length < 2 || arrayDef[0] != '[') {
                // Missing opening bracket or too short
                throw IllegalArgumentException("Illegal array definition $arrayDef!")
            }

            var closingBracketIndex = -1
            for (i in 1 until length) {
                val c = arrayDef[i]
                if (c == ']') {
                    closingBracketIndex = i
                    break
                } else if (!c.isDigit()) {
                    // Size of array not numeric
                    throw IllegalArgumentException("Illegal array definition $arrayDef!")
                }
            }
            if (closingBracketIndex < 1) {
                // Missing closing bracket
                throw IllegalArgumentException("Illegal array definition $arrayDef!")
            }
            val arraySizeDef = arrayDef.substring(1, closingBracketIndex)
            val arrayType = if (arraySizeDef.isBlank()) SolidityBase.DynamicArray::class else SolidityBase.FixedArray::class
            System.out.print("${arrayDef.substring(Math.min(length, closingBracketIndex + 1))}: ${Math.min(length, closingBracketIndex)}")
            return parseArrayDefinition(arrayDef.substring(Math.min(length, closingBracketIndex + 1)), ParameterizedTypeName.get(arrayType.asTypeName(), innerType))
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

        private fun generateFunctionEncoder(functionJson: AbiElementJson): FunSpec {
            val funSpec = FunSpec.builder("encode")
            functionJson.inputs.forEachIndexed { index, parameter ->
                val name = if (parameter.name.isEmpty()) "arg${index + 1}" else parameter.name
                funSpec.addParameter(name, mapType(parameter.type))
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
                val className = mapType(parameterJson.type)
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
                    isSolidityStaticType(outputJson.type) -> function.addStatement("val $DECODER_VAR_ARG_PREFIX$index = %1T.DECODER.decode($DECODER_VAR_PARTITIONS_NAME)", className)
                    else -> {
                        locationArgs.add("$DECODER_VAR_LOCATION_ARG_PREFIX$index" to outputJson.type)
                        function.addStatement("val $DECODER_VAR_LOCATION_ARG_PREFIX$index = %1T($DECODER_VAR_PARTITIONS_NAME.consume(), 16)", BigInteger::class)
                    }
                }
            }
        }

        private fun generateDynamicArgDecoding(locationArgs: List<Pair<String, String>>, function: FunSpec.Builder) {
            (0 until locationArgs.size).forEach {
                val locationReference = locationArgs[it].first
                val className = mapType(locationArgs[it].second)
                val dynamicValName = locationReference.removePrefix("location").decapitalize()

                function.addStatement("val $dynamicValName = %1T.DECODER.decode(%2L)", className, "partitions")
            }
        }

        private fun isSolidityStaticType(type: String) = !isSolidityDynamicType(type)

        private fun isSolidityDynamicType(type: String) = isSolidityBytes(type) || isSolidityArray(type) || isSolidityString(type)

        private fun isSolidityBytes(type: String) = type == "bytes"

        private fun isSolidityArray(type: String) = type.contains(Regex(pattern = "\\[[0-9]*]"))

        private fun isSolidityString(type: String) = type == "string"
    }
}
