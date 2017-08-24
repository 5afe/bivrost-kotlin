import com.squareup.kotlinpoet.*
import java.math.BigInteger
import kotlin.reflect.KClass

class SolidityTypeGenerator {
    private val types = listOf(
            Solidity.UInt8::class,
            Solidity.UInt256::class
    )

    fun generateUInts() {
        val kotlinFile = KotlinFile.builder("", "")
        (8..256 step 8).forEach {
            val uintClass = TypeSpec.classBuilder("UInt$it")
                    .superclass(Solidity.UInt::class)
                    .addSuperclassConstructorParameter("%1L, %2L", "value", it)
                    .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                            ParameterSpec.builder("value", BigInteger::class).build()).build())
                    .build()
            kotlinFile.addType(uintClass)
        }

        kotlinFile.build().writeTo(System.out)
    }

    fun generateInts() {
        val kotlinFile = KotlinFile.builder("", "")
        (8..256 step 8).forEach {
            val uintClass = TypeSpec.classBuilder("Int$it")
                    .superclass(Solidity.Int::class)
                    .addSuperclassConstructorParameter("%1L, %2L", "value", it)
                    .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                            ParameterSpec.builder("value", BigInteger::class).build()).build())
                    .build()
            kotlinFile.addType(uintClass)
        }

        kotlinFile.build().writeTo(System.out)
    }

    fun generateStaticBytes() {
        val kotlinFile = KotlinFile.builder("", "")
        (1..32).forEach {
            val staticBytesClass = TypeSpec.classBuilder("Bytes$it")
                    .superclass(Solidity.StaticBytes::class)
                    .addSuperclassConstructorParameter("%1L, %2L", "byteArray", it)
                    .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                            ParameterSpec.builder("byteArray", ByteArray::class).build()).build())
                    .build()
            kotlinFile.addType(staticBytesClass)
        }

        kotlinFile.build().writeTo(System.out)
    }

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
