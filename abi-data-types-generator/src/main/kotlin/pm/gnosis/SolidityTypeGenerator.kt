@file:JvmName("SolidityTypeGenerator")

package pm.gnosis

import com.squareup.kotlinpoet.*
import pm.gnosis.model.SolidityBase
import java.io.File
import java.math.BigDecimal
import java.math.BigInteger

fun main(vararg args: String) {
    args.forEach { println(it.split(File.separator).last()) }
    if (args.isNotEmpty()) {
        generate(args[0], args[1])
    }
}

fun generate(path: String, packageName: String) {
    val fileName = "Solidity"

    val modelPackageName = "$packageName.model"
    val kotlinFile = KotlinFile.builder(modelPackageName, fileName)
    val solidityGeneratedObject = TypeSpec.objectBuilder(fileName)

    kotlinFile.addStaticImport("pm.gnosis.utils", "padEndMultiple", "toHex", "hexToByteArray")
    solidityGeneratedObject.addKdoc("Generated code. Do not modify\n")

    //Generate types
    val uInts = generateUInts()
    val ints = generateInts()
    val staticBytes = generateStaticBytes()
    val address = generateAddress()
    val bool = generateBool()
    val dynamicBytes = generateDynamicBytes()

    //Arrays of dynamic types not supported (only static types)
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
    solidityGeneratedObject.addType(dynamicBytes)

    //Generate map
    val mapContent = (uInts + ints + staticBytes + arraysOfInt + arraysOfUInt + arraysOfStaticBytes + address +
            arrayOfAddress + bool + arrayOfBool + dynamicBytes)
            .map { it.name?.toLowerCase() to it.name }
            .map {
                if (it.first!!.startsWith("arrayof")) {
                    it.first!!.removePrefix("arrayof") + "[]"
                } else {
                    it.first!!.toLowerCase()
                } to it.second
            }.joinToString(",\n") { "\"${it.first}\" to \"$modelPackageName.$fileName.${it.second}\"" }

    val mapBlock = CodeBlock.builder().add(CodeBlock.of("mapOf(\n$mapContent)"))

    //Add map to object
    val mapClassName = ClassName("kotlin.collections", "Map")
    val stringClassName = ClassName("kotlin", "String")
    val mapType = ParameterizedTypeName.get(mapClassName, stringClassName, stringClassName)
    solidityGeneratedObject.addProperty(PropertySpec.builder("map", mapType).initializer(mapBlock.build()).build())

    //Write object file
    kotlinFile.addType(solidityGeneratedObject.build()).build().writeTo(File(path.removeSuffix(modelPackageName)))
}

private fun generateDecoderCompanion(name: String, decodeCode: CodeBlock): TypeSpec {
    val className = ClassName("", name)
    return TypeSpec.companionObjectBuilder()
            .addSuperinterface(ParameterizedTypeName.get(SolidityBase.Type.Decoder::class.asClassName(), className))
            .addFun(generateDecodeFunction(decodeCode, className))
            .build()
}

private fun generateDecodeFunction(decodeCode: CodeBlock, className: ClassName): FunSpec {
    return FunSpec.builder("decode")
            .addParameter("source", String::class)
            .addModifiers(KModifier.OVERRIDE)
            .returns(className)
            .addCode(decodeCode)
            .build()
}

private fun generateUInts(): List<TypeSpec> = (8..256 step 8).map {
    val name = "UInt$it"
    TypeSpec.classBuilder(name)
            .superclass(SolidityBase.UInt::class)
            .addSuperclassConstructorParameter("%1L, %2L", "value", it)
            .companionObject(generateDecoderCompanion(name,
                    CodeBlock.builder()
                            .addStatement("return %1N(%2T(%3L, 16))", name, BigInteger::class, "source")
                            .build())
            )
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("value", BigInteger::class).build()).build())
            .build()
}.toList()

private fun generateAddress(): TypeSpec {
    val name = "Address"
    return TypeSpec.classBuilder(name)
            .superclass(SolidityBase.UInt::class)
            .addSuperclassConstructorParameter("%1L, %2L", "value", "160")
            .companionObject(generateDecoderCompanion(name,
                    CodeBlock.builder()
                            .addStatement("return %1N(%2T(%3L, 16))", name, BigInteger::class, "source")
                            .build())
            )
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("value", BigInteger::class).build()).build())
            .build()
}

private fun generateBool(): TypeSpec {
    val name = "Bool"
    return TypeSpec.classBuilder(name)
            .superclass(SolidityBase.UInt::class)
            .addSuperclassConstructorParameter("if (%1L) %2T.ONE else %2T.ZERO, %3L", "value", BigInteger::class, "8")
            .companionObject(generateDecoderCompanion(name,
                    CodeBlock.builder()
                            .addStatement("return %1N(%2T(%3L, 16) != %2T.ZERO)", name, BigInteger::class, "source")
                            .build())
            )
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("value", Boolean::class).build()).build())
            .build()
}

private fun generateInts() = (8..256 step 8).map {
    val name = "Int$it"
    TypeSpec.classBuilder(name)
            .superclass(SolidityBase.Int::class)
            .addSuperclassConstructorParameter("%1L, %2L", "value", it)
            .companionObject(generateDecoderCompanion(name,
                    CodeBlock.builder()
                            .addStatement("return %1N(%2T(%3L, 16))", name, BigInteger::class, "source")
                            .build())
            )
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("value", BigInteger::class).build()).build())
            .build()
}.toList()

private fun generateStaticBytes() = (1..32).map {
    val name = "Bytes$it"
    TypeSpec.classBuilder(name)
            .superclass(SolidityBase.StaticBytes::class)
            .addSuperclassConstructorParameter("%1L, %2L", "byteArray", it)
            .companionObject(generateDecoderCompanion(name,
                    CodeBlock.builder()
                            .addStatement("return %1N(%2L.substring(0, %3L * 2).hexToByteArray())", name, "source", it)
                            .build())
            )
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("byteArray", ByteArray::class).build()).build())
            .build()
}.toList()

private fun generateArrayClass(className: ClassName): TypeSpec {
    val arrayOfStaticBase = ClassName("", SolidityBase.ArrayOfStatic::class.qualifiedName!!)
    val parameterizedType = ParameterizedTypeName.get(arrayOfStaticBase, className)
    val listEntryTypeName = ParameterizedTypeName.get(List::class.asClassName(), className)
    val entryTypeName = className.simpleName()
    val name = "ArrayOf$entryTypeName"
    return TypeSpec.classBuilder(name)
            .superclass(parameterizedType)
            .addSuperclassConstructorParameter("items")
            .companionObject(generateDecoderCompanion(name,
                    CodeBlock.builder()
                            .addStatement("val partitions = %1T.partitionData(%2L)", SolidityBase::class, "source")
                            .addStatement("val contentSize = %1T(partitions[0]).intValueExact() * 2", BigDecimal::class)
                            .addStatement("if (contentSize == 0) return %1L(ArrayList())", name)
                            .addStatement("return %1L((1 until partitions.size).map { %2L.decode(partitions[it]) }.toList())", name, entryTypeName)
                            .build())
            )
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("items", listEntryTypeName).build()).build())
            .build()
}

private fun generateDynamicBytes() =
        TypeSpec.classBuilder("Bytes")
                .addSuperinterface(SolidityBase.DynamicType::class)
                .primaryConstructor(FunSpec.constructorBuilder()
                        .addParameter("bytes", ByteArray::class)
                        .build())
                .addProperty(PropertySpec.builder("bytes", ByteArray::class).initializer("bytes").build())
                .addInitializerBlock(CodeBlock.builder()
                        .addStatement("if (%1T(bytes.size.toString(10)) > %1T.valueOf(2).pow(256)) throw %2T()", BigInteger::class, Exception::class)
                        .build())
                .addFun(FunSpec.builder("encode")
                        .addModifiers(KModifier.OVERRIDE)
                        .returns(String::class)
                        .addCode(CodeBlock.builder()
                                .addStatement("val parts = encodeParts()")
                                .addStatement("return parts.static + parts.dynamic")
                                .build())
                        .build())
                .addFun(FunSpec.builder("encodeParts")
                        .addModifiers(KModifier.OVERRIDE)
                        .returns(SolidityBase.DynamicType.Parts::class)
                        .addCode(CodeBlock.builder()
                                .addStatement("val length = bytes.size.toString(16).padStart(64, '0')")
                                .addStatement("val contents = bytes.toHex().padEndMultiple(64, '0')")
                                .addStatement("return %1T(length, contents)", SolidityBase.DynamicType.Parts::class)
                                .build())
                        .build())
                .build()
