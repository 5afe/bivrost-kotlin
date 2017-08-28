import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi
import model.AbiRoot
import model.Solidity
import utils.generateSolidityMethodId
import java.math.BigInteger
import java.util.regex.Pattern
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

        abiRoot.abi.filter { it.type == "function" }.forEach { function ->
            if (function.outputs.isNotEmpty()) {
                val funSpec = FunSpec.builder("decode${function.name.capitalize()}")

                val returnContainer = TypeSpec.classBuilder("${function.name.toLowerCase()}Result")
                        .addModifiers(KModifier.DATA)

                function.outputs.forEachIndexed { index, output ->
                    val name = if (output.name.isEmpty()) "output$index" else output.name.toLowerCase()
                    val abiRawType = output.type.replace(Regex(pattern = "[^A-Za-z]+"), "")
                    if (output.type.contains("[]")) {
                        val kotlinType = typeMapper[abiRawType]
                        val parameterizedTypeName = ParameterizedTypeName.get(List::class, kotlinType!!)
                        returnContainer.addProperty(name, parameterizedTypeName)
                    } else {
                        println("$abiRawType  ${output.type}")
                        returnContainer.addProperty(name, typeMapper[abiRawType]!!)
                    }
                }
                kotlinFile.addType(returnContainer.build())
            }
        }
        kotlinFile.build().writeTo(System.out)
    }

    val typeDecoderMap = mapOf<String, (String) -> Any>(
            "uint" to SolidityBase::decodeUInt,
            "int" to SolidityBase::decodeInt,
            "address" to SolidityBase::decodeUInt,
            "bool" to SolidityBase::decodeBool,
            "bytes" to SolidityBase::decodeBytes,
            "bytesN" to SolidityBase::decodeStaticBytes
    )

    val typeMapper = mapOf<String, KClass<*>>(
            "uint" to BigInteger::class,
            "int" to BigInteger::class,
            "address" to BigInteger::class,
            "bool" to Boolean::class,
            "bytes" to ByteArray::class,
            "bytesN" to ByteArray::class
    )
}
