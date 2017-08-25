import com.squareup.kotlinpoet.*
import java.math.BigInteger

class SolidityTypeGenerator {
    fun generate() {
        val kotlinFile = KotlinFile.builder("", "")
        val solidityGeneratedObject = TypeSpec.objectBuilder("Solidity")

        //Generate types
        val uInts = generateUInts()
        val ints = generateInts()
        val staticBytes = generateStaticBytes()
        val address = generateAddress()
        val bool = generateBool()

        val arraysOfInt = ints.map { ClassName("", it.name!!) }.map { generateArrayClass(it) }.toList()
        val arraysOfUInt = uInts.map { ClassName("", it.name!!) }.map { generateArrayClass(it) }.toList()
        val arraysOfStaticBytes = staticBytes.map { ClassName("", it.name!!) }.map { generateArrayClass(it) }.toList()
        val arrayOfAddress = generateArrayClass(ClassName("", address.name!!))
        val arrayOfBool = generateArrayClass(ClassName("", bool.name!!))

        //Add types to object
        solidityGeneratedObject.addTypes(uInts)
        solidityGeneratedObject.addTypes(ints)
        solidityGeneratedObject.addTypes(staticBytes)
        solidityGeneratedObject.addTypes(arraysOfInt)
        solidityGeneratedObject.addTypes(arraysOfUInt)
        solidityGeneratedObject.addTypes(arraysOfStaticBytes)
        solidityGeneratedObject.addType(address)
        solidityGeneratedObject.addType(arrayOfAddress)
        solidityGeneratedObject.addType(bool)
        solidityGeneratedObject.addType(arrayOfBool)

        //Generate map
        val mapContent = (uInts + ints + staticBytes + arraysOfInt + arraysOfUInt + arraysOfStaticBytes + address + arrayOfAddress + bool + arrayOfBool)
                .map { it.name?.toLowerCase() to it.name }
                .map {
                    if (it.first!!.startsWith("arrayof")) {
                        it.first!!.removePrefix("arrayof") + "[]"
                    } else {
                        it.first!!.toLowerCase()
                    } to it.second
                }.joinToString(",\n") { "\"${it.first}\" to ${it.second}::class" }

        val mapBlock = CodeBlock.builder().add(CodeBlock.of("mapOf(\n$mapContent)"))

        //Add map to object
        val mapClassName = ClassName("kotlin.collections", "Map")
        val stringClassName = ClassName("kotlin", "String")
        val kClassName = ClassName("kotlin.reflect", "KClass")
        val kclassType = ParameterizedTypeName.get(kClassName, WildcardTypeName.subtypeOf(Any::class))
        val mapType = ParameterizedTypeName.get(mapClassName, stringClassName, kclassType)
        solidityGeneratedObject.addProperty(PropertySpec.builder("map", mapType).initializer(mapBlock.build()).build())

        //Write object file
        kotlinFile.addType(solidityGeneratedObject.build()).build().writeTo(System.out)
    }

    private fun generateUInts(): List<TypeSpec> = (8..256 step 8).map {
        TypeSpec.classBuilder("UInt$it")
                .superclass(SolidityBase.UInt::class)
                .addSuperclassConstructorParameter("%1L, %2L", "value", it)
                .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                        ParameterSpec.builder("value", BigInteger::class).build()).build())
                .build()
    }.toList()

    private fun generateAddress(): TypeSpec =
            TypeSpec.classBuilder("Address")
                    .superclass(SolidityBase.UInt::class)
                    .addSuperclassConstructorParameter("%1L, %2L", "value", "160")
                    .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                            ParameterSpec.builder("value", BigInteger::class).build()).build())
                    .build()

    private fun generateBool(): TypeSpec =
            TypeSpec.classBuilder("Bool")
                    .superclass(SolidityBase.UInt::class)
                    .addSuperclassConstructorParameter("if (%1L) %2T.ONE else %2T.ZERO, %3L", "value", BigInteger::class, "8")
                    .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                            ParameterSpec.builder("value", Boolean::class).build()).build())
                    .build()

    private fun generateInts() = (8..256 step 8).map {
        TypeSpec.classBuilder("Int$it")
                .superclass(SolidityBase.Int::class)
                .addSuperclassConstructorParameter("%1L, %2L", "value", it)
                .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                        ParameterSpec.builder("value", BigInteger::class).build()).build())
                .build()
    }.toList()

    private fun generateStaticBytes() = (1..32).map {
        TypeSpec.classBuilder("Bytes$it")
                .superclass(SolidityBase.StaticBytes::class)
                .addSuperclassConstructorParameter("%1L, %2L", "byteArray", it)
                .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                        ParameterSpec.builder("byteArray", ByteArray::class).build()).build())
                .build()
    }.toList()

    private fun generateArrayClass(className: ClassName): TypeSpec {
        val arrayOfStaticBase = ClassName("", SolidityBase.ArrayOfStatic::class.qualifiedName!!)
        val parameterizedType = ParameterizedTypeName.get(arrayOfStaticBase, className)
        return TypeSpec.classBuilder("ArrayOf${className.simpleName()}")
                .superclass(parameterizedType)
                .addSuperclassConstructorParameter("*items")
                .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                        ParameterSpec.builder("items", className, KModifier.VARARG).build()).build())
                .build()
    }
}
