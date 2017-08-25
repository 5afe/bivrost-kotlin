import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi
import model.AbiRoot
import model.Solidity
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

    private fun getTypeWithName(name: String): KClass<*> {
        return when (name) {
            "bytes" -> SolidityBase.Bytes::class
            else -> throw Exception()
        }
    }
}
