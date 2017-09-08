package pm.gnosis

import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi
import pm.gnosis.model.*
import pm.gnosis.utils.generateSolidityMethodId
import java.io.File
import java.math.BigDecimal
import java.math.BigInteger

class AbiParser {
    companion object {
        private const val DECODER_FUN_ARG_NAME = "data"
        private const val DECODER_VAR_PARTITIONS_NAME = "partitions"
        private const val DECODER_VAR_LOCATION_ARG_PREFIX = "locationArg" //locationArg0, locationArg1...
        private const val DECODER_VAR_ARG_PREFIX = "arg" //arg0, arg1...

        fun generateWrapper(packageName: String, abi: String, output: File) {
            val jsonAdapter = Moshi.Builder().build().adapter(AbiRoot::class.java)

            val abiRoot = jsonAdapter.fromJson(abi) ?: return

            val kotlinClass = TypeSpec.classBuilder(abiRoot.contractName)
            val kotlinFile = KotlinFile.builder(packageName, abiRoot.contractName)

            val companionWithEncoder = generateEncoder(abiRoot)
            val decoder = generateDecoder(abiRoot)

            kotlinClass.addType(companionWithEncoder.first)
            kotlinClass.addType(companionWithEncoder.second)
            kotlinClass.addType(decoder)
            val build = kotlinFile.addType(kotlinClass.build()).build()
            output.mkdirs()
            build.writeTo(output)
        }


        private fun generateEncoder(abiRoot: AbiRoot): Pair<TypeSpec, TypeSpec> {
            val companionObject = TypeSpec.companionObjectBuilder()
            val encoderObject = TypeSpec.objectBuilder("Encoder")

            abiRoot.abi.filter { it.type == "function" }.forEach { function ->
                val funSpec = FunSpec.builder(function.name)
                function.inputs.forEachIndexed { index, parameter ->
                    val name = if (parameter.name.isEmpty()) "arg${index + 1}" else parameter.name
                    Solidity.map[parameter.type]?.let {
                        funSpec.addParameter(name, ClassName.bestGuess(it))
                    }
                }

                val funWithParams = funSpec.build()
                val finalFun = funWithParams.toBuilder().returns(String::class)
                val methodId = "${function.name}(${function.inputs.joinToString(",") { it.type }})".generateSolidityMethodId()
                val constName = "${function.name.toUpperCase()}_METHOD_ID"
                companionObject.addProperty(PropertySpec.builder(constName, String::class, KModifier.CONST).initializer("\"$methodId\"").build())
                finalFun.addStatement("return \"0x\" + ${constName +
                        if (funWithParams.parameters.isNotEmpty()) {
                            " + pm.gnosis.model.SolidityBase.encodeFunctionArguments(${funWithParams.parameters.joinToString { it.name }})"
                        } else ""}")

                encoderObject.addFun(finalFun.build())
            }

            return companionObject.build() to encoderObject.build()
        }

        private fun generateDecoder(abiRoot: AbiRoot): TypeSpec {
            val decoderObject = TypeSpec.objectBuilder("Decoder")

            abiRoot.abi.filter { it.type == "function" }.filter { it.outputs.isNotEmpty() }.forEach { function ->
                val funSpecBuilder = FunSpec.builder("decode${function.name.capitalize()}").addParameter(DECODER_FUN_ARG_NAME, String::class)
                val dataClass = generateDataClassResult(function)

                //Set function return
                val typeName = ClassName("", dataClass.name!!)
                funSpecBuilder.returns(typeName)

                funSpecBuilder.addStatement("val $DECODER_VAR_PARTITIONS_NAME = %1T.partitionData($DECODER_FUN_ARG_NAME)", SolidityBase::class)

                //Generate decodings
                val locationArgs = ArrayList<Pair<String, String>>()
                generateStaticArgDecoding(function.outputs, funSpecBuilder, locationArgs)
                generateDynamicArgDecoding(locationArgs, funSpecBuilder)

                funSpecBuilder.addStatement("")
                funSpecBuilder.addStatement("return ${dataClass.name}(${(0 until function.outputs.size).joinToString(", ") { "arg$it" }})")

                decoderObject.addType(dataClass)
                decoderObject.addFun(funSpecBuilder.build())
            }

            return decoderObject.build()
        }

        private fun generateDataClassResult(function: AbiElementJson): TypeSpec {
            val returnContainerBuilder = TypeSpec.classBuilder("${function.name.capitalize()}Result").addModifiers(KModifier.DATA)
            val returnContainerConstructor = FunSpec.constructorBuilder()

            function.outputs.forEachIndexed { index, output ->
                val name = if (output.name.isEmpty()) "output$index" else output.name.toLowerCase()
                val className = ClassName.bestGuess(Solidity.map[output.type]!!)
                returnContainerConstructor.addParameter(name, className)
                returnContainerBuilder.addProperty(PropertySpec.builder(name, className).initializer(name).build())

            }

            return returnContainerBuilder.primaryConstructor(returnContainerConstructor.build()).build()
        }

        private fun generateStaticArgDecoding(outputs: List<OutputJson>, function: FunSpec.Builder, locationArgs: MutableCollection<Pair<String, String>>) {
            function.addStatement("")
            function.addComment("Decode arguments")
            outputs.forEachIndexed { index, outputJson ->
                val className = ClassName.bestGuess(Solidity.map[outputJson.type]!!)
                when {
                    isSolidityStaticType(outputJson.type) -> function.addStatement("val $DECODER_VAR_ARG_PREFIX$index = %1T.decode($DECODER_VAR_PARTITIONS_NAME[$index])", className)
                    else -> {
                        locationArgs.add("$DECODER_VAR_LOCATION_ARG_PREFIX$index" to outputJson.type)
                        function.addStatement("val $DECODER_VAR_LOCATION_ARG_PREFIX$index = %1T($DECODER_VAR_PARTITIONS_NAME[$index], 16)", BigInteger::class)
                    }
                }
            }
        }

        private fun generateDynamicArgDecoding(locationArgs: List<Pair<String, String>>, function: FunSpec.Builder) {
            (0 until locationArgs.size).forEach {
                val locationReference = locationArgs[it].first
                val className = ClassName.bestGuess(Solidity.map[locationArgs[it].second]!!)
                val dynamicValName = locationReference.removePrefix("location").decapitalize()
                val upperLimit = if (it == locationArgs.size - 1) "data.length" else "%2T(${locationArgs[it + 1].first}).intValueExact() * 2"

                function.addStatement("val $dynamicValName = %1T.decode(data.substring(%2T(${locationArgs[it].first}).intValueExact() * 2, $upperLimit))", className, BigDecimal::class)
            }
        }

        private fun isSolidityStaticType(type: String) = !isSolidityDynamicType(type)

        private fun isSolidityDynamicType(type: String) = isSolidityBytes(type) || isSolidityArray(type) || isSolidityString(type)

        private fun isSolidityBytes(type: String) = type == "bytes"

        private fun isSolidityArray(type: String) = type.contains(Regex(pattern = "\\[[0-9]*]"))

        private fun isSolidityString(type: String) = type == "string"
    }
}
