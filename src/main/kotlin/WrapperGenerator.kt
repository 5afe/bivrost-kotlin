import com.squareup.kotlinpoet.*
import com.squareup.moshi.Moshi
import model.AbiRoot
import kotlin.reflect.KClass

fun generateWrapper(abi: String) {
    val moshi = Moshi.Builder().build()

    val jsonAdapter = moshi.adapter(AbiRoot::class.java)
    val abiRoot = jsonAdapter.fromJson(abi) ?: return

    val kotlinClass = TypeSpec.classBuilder(abiRoot.contractName)
    val kotlinFile = KotlinFile.builder("", abiRoot.contractName)
    val companionObject = TypeSpec.companionObjectBuilder()

    abiRoot.abi.filter { it.type == "function" }.forEach { function ->
        val funSpec = FunSpec.builder(function.name)
        function.inputs.forEachIndexed { index, parameter ->
            val name = if (parameter.name.isEmpty()) "arg${index + 1}" else parameter.name
            if (parameter.type.contains("[]")) { //TODO: Fixed size array
                val p = parameter.type.removeSuffix("[]")
                val tvn = ParameterizedTypeName.get(Solidity.ArrayOfStatic::class, getTypeWithName(p))
                funSpec.addParameter(ParameterSpec.builder(name, tvn).build())
            } else {
                funSpec.addParameter(name, getTypeWithName(parameter.type))
            }
        }

        val funWithParams = funSpec.build()
        val finalFun = funWithParams.toBuilder().returns(String::class)
        val methodId = Solidity.getMethodId("${function.name}(${function.inputs.joinToString(",") { it.type }})")
        val constName = "${function.name.toUpperCase()}_METHOD_ID"
        companionObject.addProperty(PropertySpec.builder(constName, String::class, KModifier.CONST).initializer("\"$methodId\"").build())
        finalFun.addStatement("return \"0x\" + ${constName +
                if (funWithParams.parameters.isNotEmpty()) {
                    " + Solidity.encodeFunctionArguments(${funWithParams.parameters.joinToString { it.name }})"
                } else ""}")

        kotlinClass.addFun(finalFun.build())
    }
    kotlinClass.addType(companionObject.build())
    kotlinFile.addType(kotlinClass.build()).build().writeTo(System.out)
}

fun getTypeWithName(name: String): KClass<*> {
    return when (name) {
        "uint256" -> Solidity.UInt256::class
        "address" -> Solidity.Address::class
        "bool" -> Solidity.Boolean::class
        "bytes" -> Solidity.Bytes::class
        else -> throw Exception()
    }
}