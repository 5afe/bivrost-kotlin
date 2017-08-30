import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi
import model.AbiElementJson
import model.AbiRoot
import model.OutputJson
import model.Solidity
import utils.generateSolidityMethodId
import java.math.BigInteger
import kotlin.reflect.KClass

class AbiParser {
    private val jsonAdapter = Moshi.Builder().build().adapter(AbiRoot::class.java)

    companion object {
        const val DECODER_FUN_ARG_NAME = "data"
        const val DECODER_VAR_PARTITIONS_NAME = "partitions"
        const val DECODER_VAR_LOCATION_ARG_PREFIX = "locationArg" //locationArg0, locationArg1...
        const val DECODER_VAR_ARG_PREFIX = "arg" //arg0, arg1...
    }

    fun generateWrapper(abi: String) {
        val abiRoot = jsonAdapter.fromJson(abi) ?: return

        val kotlinClass = TypeSpec.classBuilder(abiRoot.contractName)
        val kotlinFile = KotlinFile.builder("", abiRoot.contractName)

        val companionWithEncoder = generateEncoder(abiRoot)
        val decoder = generateDecoder(abiRoot)

        kotlinClass.addType(companionWithEncoder.first)
        kotlinClass.addType(companionWithEncoder.second)
        kotlinClass.addType(decoder)
        kotlinFile.addType(kotlinClass.build()).build().writeTo(System.out)
    }

    fun generateEncoder(abiRoot: AbiRoot): Pair<TypeSpec, TypeSpec> {
        val companionObject = TypeSpec.companionObjectBuilder()
        val encoderObject = TypeSpec.objectBuilder("Encoder")

        abiRoot.abi.filter { it.type == "function" }.forEach { function ->
            val funSpec = FunSpec.builder(function.name)
            function.inputs.forEachIndexed { index, parameter ->
                val name = if (parameter.name.isEmpty()) "arg${index + 1}" else parameter.name
                Solidity.map[parameter.type]?.let {
                    funSpec.addParameter(name, it)
                }
            }

            val funWithParams = funSpec.build()
            val finalFun = funWithParams.toBuilder().returns(String::class)
            val methodId = "${function.name}(${function.inputs.joinToString(",") { it.type }})".generateSolidityMethodId()
            val constName = "${function.name.toUpperCase()}_METHOD_ID"
            companionObject.addProperty(PropertySpec.builder(constName, String::class, KModifier.CONST).initializer("\"$methodId\"").build())
            finalFun.addStatement("return \"0x\" + ${constName +
                    if (funWithParams.parameters.isNotEmpty()) {
                        " + SolidityBase.encodeFunctionArguments(${funWithParams.parameters.joinToString { it.name }})"
                    } else ""}")

            encoderObject.addFun(finalFun.build())
        }

        return companionObject.build() to encoderObject.build()
    }

    fun generateDecoder(abiRoot: AbiRoot): TypeSpec {
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
            val abiRawType = solidityRawType(output.type)
            if (output.type.contains("[]")) {
                val kotlinType = typeMapper[abiRawType]
                val parameterizedTypeName = ParameterizedTypeName.get(List::class, kotlinType!!)
                returnContainerConstructor.addParameter(name, parameterizedTypeName)
                returnContainerBuilder.addProperty(PropertySpec.builder(name, parameterizedTypeName).initializer(name).build())
            } else {
                returnContainerConstructor.addParameter(name, typeMapper[abiRawType]!!)
                returnContainerBuilder.addProperty(PropertySpec.builder(name, typeMapper[abiRawType]!!).initializer(name).build())
            }
        }

        return returnContainerBuilder.primaryConstructor(returnContainerConstructor.build()).build()
    }

    private fun generateStaticArgDecoding(outputs: List<OutputJson>, function: FunSpec.Builder, locationArgs: MutableCollection<Pair<String, String>>) {
        function.addStatement("")
        function.addComment("Decode arguments")
        outputs.forEachIndexed { index, outputJson ->
            val abiRawType = solidityRawType(outputJson.type)
            if (isStaticType(outputJson.type)) {
                function.addStatement("val $DECODER_VAR_ARG_PREFIX$index = %1T.${typeDecoderMap[abiRawType]}($DECODER_VAR_PARTITIONS_NAME[$index])", SolidityBase::class)
            } else {
                locationArgs.add("$DECODER_VAR_LOCATION_ARG_PREFIX$index" to outputJson.type)
                function.addStatement("val $DECODER_VAR_LOCATION_ARG_PREFIX$index = %1T($DECODER_VAR_PARTITIONS_NAME[$index], 16)", BigInteger::class)
            }
        }
    }

    private fun generateDynamicArgDecoding(locationArgs: List<Pair<String, String>>, function: FunSpec.Builder) {
        (0 until locationArgs.size).forEach {
            val locationReference = locationArgs[it].first
            val type = locationArgs[it].second
            val dynamicValName = locationReference.removePrefix("location").decapitalize()
            val upperLimit = if (it == locationArgs.size - 1) "data.length" else "${locationArgs[it + 1].first}.intValueExact() * 2)"
            if (isBytes(type)) {
                function.addStatement("val $dynamicValName = %1T.decodeBytes(data.substring(${locationArgs[it].first}.intValueExact() * 2, $upperLimit))", SolidityBase::class)
            } else if (isArray(type)) {
                val abiRawType = solidityRawType(type)
                function.addStatement("val $dynamicValName = %1T.decodeArray(data.substring(${locationArgs[it].first}.intValueExact() * 2, $upperLimit), %1T::${typeDecoderMap[abiRawType]})", SolidityBase::class)
            }
        }
    }

    private fun solidityRawType(type: String) = type.replace(Regex(pattern = "[^A-Za-z]+"), "")

    private val typeDecoderMap = mapOf(
            "uint" to "decodeUInt",
            "int" to "decodeInt",
            "address" to "decodeUInt",
            "bool" to "decodeBool",
            "bytes" to "decodeStaticBytes"
    )

    private val typeMapper = mapOf<String, KClass<*>>(
            "uint" to BigInteger::class,
            "int" to BigInteger::class,
            "address" to BigInteger::class,
            "bool" to Boolean::class,
            "bytes" to ByteArray::class,
            "bytesN" to ByteArray::class
    )

    private fun isStaticType(type: String) = !isDynamicType(type)

    private fun isDynamicType(type: String) = isBytes(type) || isArray(type) || isString(type)

    private fun isBytes(type: String) = type == "bytes"

    private fun isArray(type: String) = type.contains(Regex(pattern = "\\[[0-9]*]"))

    private fun isString(type: String) = type == "string"
}
