import com.squareup.kotlinpoet.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import model.AbiElementJson
import model.AbiRoot
import kotlin.reflect.KClass

fun generateWrapper(abi: String) {
    val moshi = Moshi.Builder().build()

    val jsonAdapter = moshi.adapter(AbiRoot::class.java)
    val abiRoot = jsonAdapter.fromJson(abi)

    val testClass = ClassName("", "TestWrapper")
    val kotlinFile = KotlinFile.builder("", "TestWrapper")

    abiRoot?.abi?.filter { it.type == "function" }?.forEach { function ->
        val f = FunSpec.builder(function.name)
        function.inputs.forEachIndexed { index, parameter ->
            val name = if (parameter.name.isNullOrEmpty()) "arg${index + 1}" else parameter.name
            if (parameter.type.contains("[]")) {
                val p = parameter.type.removeSuffix("[]")
                val tvn = ParameterizedTypeName.get(Solidity.ArrayOfStatic::class, getTypeWithName(p))
                f.addParameter(ParameterSpec.builder(name, tvn).build())
            } else {
                f.addParameter(name, getTypeWithName(parameter.type))
            }
        }
        kotlinFile.addFun(f.build())
    }
    kotlinFile.build().writeTo(System.out)
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