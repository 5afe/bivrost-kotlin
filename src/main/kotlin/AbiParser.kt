import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi
import model.AbiRoot
import model.Solidity
import utils.generateSolidityMethodId
import java.math.BigInteger
import kotlin.reflect.KClass

class AbiParser {
    private val jsonAdapter = Moshi.Builder().build().adapter(AbiRoot::class.java)

    fun generateEncoder(abi: String) {
        val abiRoot = jsonAdapter.fromJson(abi) ?: return

        val kotlinClass = TypeSpec.classBuilder(abiRoot.contractName)
        val kotlinFile = KotlinFile.builder("", abiRoot.contractName)
        val companionObject = TypeSpec.companionObjectBuilder()

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

            kotlinClass.addFun(finalFun.build())
        }
        kotlinClass.addType(companionObject.build())
        kotlinFile.addType(kotlinClass.build()).build().writeTo(System.out)
    }

    fun generateDecoder(abi: String) {
        val abiRoot = jsonAdapter.fromJson(abi) ?: return

        val kotlinClass = TypeSpec.classBuilder(abiRoot.contractName)
        val kotlinFile = KotlinFile.builder("", abiRoot.contractName)
        val companionObject = TypeSpec.companionObjectBuilder()

        abiRoot.abi.filter { it.type == "function" }.filter { it.outputs.isNotEmpty() }.forEach { function ->
            val funSpecBuilder = FunSpec.builder("decode${function.name.capitalize()}")
                    .addParameter("data", String::class)
            val returnContainerBuilder = TypeSpec.classBuilder("${function.name.capitalize()}Result")
                    .addModifiers(KModifier.DATA)

            function.outputs.forEachIndexed { index, output ->
                val name = if (output.name.isEmpty()) "output$index" else output.name.toLowerCase()
                val abiRawType = output.type.replace(Regex(pattern = "[^A-Za-z]+"), "")
                if (output.type.contains("[]")) {
                    val kotlinType = typeMapper[abiRawType]
                    val parameterizedTypeName = ParameterizedTypeName.get(List::class, kotlinType!!)
                    returnContainerBuilder.addProperty(name, parameterizedTypeName)
                } else {
                    returnContainerBuilder.addProperty(name, typeMapper[abiRawType]!!)
                }
            }

            val returnContainer = returnContainerBuilder.build()
            val typeName = ClassName("", returnContainer.name!!)
            funSpecBuilder.returns(typeName)

            funSpecBuilder.addStatement("val partitions = SolidityBase.partitionData(data)")

            val locationArgs = ArrayList<Pair<String, String>>()

            funSpecBuilder.addStatement("")
            funSpecBuilder.addComment("Decode arguments")
            function.outputs.forEachIndexed { index, outputJson ->
                val abiRawType = outputJson.type.replace(Regex(pattern = "[^A-Za-z]+"), "")
                if (isStaticType(outputJson.type)) {
                    funSpecBuilder.addStatement("val arg$index = ${typeDecoderMap[abiRawType]}(partitions[$index])")
                } else {
                    locationArgs.add("locationArg$index" to abiRawType)
                    funSpecBuilder.addStatement("val locationArg$index = %1T(partitions[$index], 16)", BigInteger::class)
                }
            }

            (0 until locationArgs.size).forEach {
                val locationReference = locationArgs[it].first
                val dynamicValName = locationReference.removePrefix("location").decapitalize()
                val decoderFunction = if (locationArgs[it].second == "bytes") "SolidityBase.decodeBytes" else "SolidityBase.decodeArray"

                funSpecBuilder.addStatement(if (it == locationArgs.size - 1) {
                    "val $dynamicValName = $decoderFunction(data.substring(${locationArgs[it].first}.intValueExact() * 2, data.length))"
                } else {
                    "val $dynamicValName = $decoderFunction(data.substring(${locationArgs[it].first}.intValueExact() * 2, ${locationArgs[it + 1].first}.intValueExact() * 2))"
                })
            }

            funSpecBuilder.addStatement("")
            funSpecBuilder.addStatement("return ${returnContainer.name}(${(0 until function.outputs.size).map { "arg$it" }.joinToString(", ")})")

            kotlinFile.addType(returnContainer)
            kotlinFile.addFun(funSpecBuilder.build())
        }

        kotlinFile.build().writeTo(System.out)
    }

    val typeDecoderMap = mapOf(
            "uint" to "SolidityBase.decodeUInt",
            "int" to "SolidityBase.decodeInt",
            "address" to "SolidityBase.decodeUInt",
            "bool" to "SolidityBase.decodeBool",
            "bytes" to "SolidityBase.decodeStaticBytes"
    )

    val typeMapper = mapOf<String, KClass<*>>(
            "uint" to BigInteger::class,
            "int" to BigInteger::class,
            "address" to BigInteger::class,
            "bool" to Boolean::class,
            "bytes" to ByteArray::class,
            "bytesN" to ByteArray::class
    )

    fun isStaticType(type: String) = type != "bytes" && !type.endsWith("[]")
}
