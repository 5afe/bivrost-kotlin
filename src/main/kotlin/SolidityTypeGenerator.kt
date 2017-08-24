import com.squareup.kotlinpoet.*
import kotlin.reflect.KClass

class SolidityTypeGenerator {
    private val types = listOf(
            Solidity.UInt8::class,
            Solidity.UInt256::class
    )

    fun generateArrays() {
        val kotlinFile = KotlinFile.builder("", "")
        val solidityGeneratedObject = TypeSpec.objectBuilder("SolidityGenerated")

        types.forEach {
            solidityGeneratedObject.addType(generateArrayClass(it)).build()
        }

        kotlinFile.addType(solidityGeneratedObject.build()).build().writeTo(System.out)
    }

    private fun <T : Solidity.Type> generateArrayClass(clazz: KClass<T>): TypeSpec {
        val parameterizedType = ParameterizedTypeName.get(Solidity.ArrayOfStatic::class, clazz)
        return TypeSpec.classBuilder("ArrayOf${clazz.simpleName}")
                .superclass(parameterizedType)
                .addSuperclassConstructorParameter("*items")
                .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                        ParameterSpec.builder("items", clazz, KModifier.VARARG).build()).build())
                .build()

    }
}
