package pm.gnosis

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import pm.gnosis.model.*
import pm.gnosis.utils.BigIntegerToInt
import pm.gnosis.utils.generateSolidityMethodId
import java.io.File
import java.math.BigInteger


object AbiParser {
    internal const val DECODER_FUN_ARG_NAME = "data"
    internal const val DECODER_VAR_PARTITIONS_NAME = "source"
    internal const val DECODER_VAR_ARG_PREFIX = "arg" //arg0, arg1...
    internal const val DECODER_VAR_ARG_OFFSET_SUFFIX = "Offset"
    internal const val INDENTATION = "    "
    internal lateinit var context: GeneratorContext

    fun generateWrapper(packageName: String, abi: String, output: File, arraysMap: ArraysMap) {
        val jsonAdapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(AbiRoot::class.java)
        val abiRoot = jsonAdapter.fromJson(abi) ?: return
        context = GeneratorContext(abiRoot, arraysMap)

        val kotlinClass = TypeSpec.classBuilder(abiRoot.contractName)
        val kotlinFile = FileSpec.builder(packageName, abiRoot.contractName)

        kotlinClass.addTypes(generateFunctionObjects())
        EventParser.generateEventObjects()?.let { kotlinClass.addType(it) }
        kotlinClass.addTypes(generateTupleObjects())

        val build = kotlinFile.addType(kotlinClass.build()).indent(INDENTATION).build()
        output.mkdirs()
        build.writeTo(output)
    }

    class ArraysMap(basePackageName: String, private val map: MutableMap<Int, ClassName> = HashMap()) {

        private val arraysPackageName = "$basePackageName.arrays"

        fun get(capacity: Int) = map.getOrPut(capacity) { ClassName(arraysPackageName, "Array$capacity") }

        fun generate(output: File) {
            map.forEach {
                val capacity = it.key
                val arrayType = it.value

                val kotlinFile = FileSpec.builder(arraysPackageName, arrayType.simpleName)

                val typeVariable = TypeVariableName.invoke("T", SolidityBase.Type::class)
                val itemsType = List::class.asClassName().parameterizedBy(typeVariable)

                val parameterizedClassType = arrayType.parameterizedBy(typeVariable)
                val parameterizedItemDecoderType = SolidityBase.TypeDecoder::class.asClassName().parameterizedBy(typeVariable)
                val isDynamicBlock = CodeBlock.of("return " + if (capacity > 0) "$VARIABLE_NAME_ITEM_DECODER.isDynamic()" else "false")
                val decodeBlock =
                    CodeBlock.builder()
                        .addStatement(
                            "return·%1T(%2T.decodeList(%3L,·%4L,·%5L))",
                            arrayType,
                            SolidityBase::class,
                            "source",
                            capacity,
                            VARIABLE_NAME_ITEM_DECODER
                        )
                        .build()
                val decoderBuilder = GeneratorUtils.generateDecoderBuilder(parameterizedClassType, decodeBlock, isDynamicBlock)
                    .addTypeVariable(typeVariable)
                    .addProperty(
                        PropertySpec.builder(
                            VARIABLE_NAME_ITEM_DECODER,
                            parameterizedItemDecoderType
                        ).initializer(VARIABLE_NAME_ITEM_DECODER).build()
                    )
                    .primaryConstructor(FunSpec.constructorBuilder().addParameter(VARIABLE_NAME_ITEM_DECODER, parameterizedItemDecoderType).build())

                val kotlinClass = TypeSpec.classBuilder(arrayType)
                    .superclass(SolidityBase.Array::class.asClassName().parameterizedBy(typeVariable))
                    .addSuperclassConstructorParameter("items, %L", capacity)
                    .addTypeVariable(typeVariable)
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addParameter(ParameterSpec.builder("items", itemsType).build())
                            .build()
                    )
                    .addType(decoderBuilder.build())
                val build = kotlinFile.addType(kotlinClass.build()).indent(INDENTATION).build()
                output.mkdirs()
                build.writeTo(output)
            }
        }

        companion object {
            const val VARIABLE_NAME_ITEM_DECODER = "itemDecoder"
        }
    }

    internal class GeneratorContext(val root: AbiRoot, val arrays: ArraysMap, val tuples: MutableMap<String, TupleTypeHolder> = HashMap())

    private fun generateFunctionObjects() =
        context.root.abi
            .filter { it.type == "function" && it.name.isNotBlank() }
            .groupBy { it.name }
            .flatMap { (_, value) -> value.map { Pair(it, value.size > 1) } }
            .map { (functionJson, useMethodId) ->

                //Add method id
                val methodId = "${functionJson.name}${generateMethodSignature(functionJson.inputs)}".generateSolidityMethodId()
                val baseName = functionJson.name.capitalize()
                val name = if (useMethodId) "${baseName}_$methodId" else baseName
                val functionObject = TypeSpec.objectBuilder(name)

                functionObject.addProperty(PropertySpec.builder("METHOD_ID", String::class, KModifier.CONST).initializer("\"$methodId\"").build())
                functionObject.addFunction(generateFunctionEncoder(functionJson))
                if (functionJson.outputs.isNotEmpty()) {
                    val returnHolder = generateParameterHolder("Return", functionJson.outputs)
                    functionObject.addFunction(generateParameterDecoder("decode", functionJson.outputs, returnHolder.name!!))
                    functionObject.addType(returnHolder)
                }

                if (functionJson.inputs.isNotEmpty()) {
                    val argumentsHolder = generateParameterHolder("Arguments", functionJson.inputs)
                    functionObject.addFunction(generateParameterDecoder("decodeArguments", functionJson.inputs, argumentsHolder.name!!))
                    functionObject.addType(argumentsHolder)
                }

                functionObject.build()
            }.toList()

    private fun generateMethodSignature(parameters: List<ParameterJson>): String =
        "(${parameters.joinToString(",") {
            if (it.type.startsWith(TUPLE_TYPE_NAME)) {
                it.components ?: return@joinToString ""
                generateMethodSignature(it.components) + it.type.removePrefix(TUPLE_TYPE_NAME)
            } else {
                checkType(it.type)
            }
        }})"

    private fun generateTupleObjects() =
        context.tuples.map {
            val decoderTypeName = ClassName.bestGuess("Decoder")
            val typeHolder = it.value

            val builder = TypeSpec.classBuilder(typeHolder.name)
            val constructor = FunSpec.constructorBuilder()

            typeHolder.entries.forEach { (name, holder) ->
                val className = holder.toTypeName()
                constructor.addParameter(name, className)
                builder.addProperty(PropertySpec.builder(name, className).initializer(name).build())
            }

            //Generate decodings
            val decodeBlockBuilder = CodeBlock.builder()
            generateStaticArgDecoding(typeHolder, decodeBlockBuilder)
            decodeBlockBuilder.addStatement(
                "return·%1N(${(0 until typeHolder.entries.size).joinToString(",·") { "arg$it" }})",
                typeHolder.name
            )

            builder
                .addModifiers(KModifier.DATA)
                .addSuperinterface(if (typeHolder.isDynamic()) SolidityBase.DynamicType::class else SolidityBase.StaticType::class)
                .addFunction(generateTupleEncoder(typeHolder))
                .addType(
                    GeneratorUtils.generateDecoder(
                        typeHolder.name,
                        decodeBlockBuilder.build(),
                        typeHolder.isDynamic(),
                        DECODER_VAR_PARTITIONS_NAME
                    )
                )
                .addType(
                    GeneratorUtils.generateDecoderCompanion(
                        decoderTypeName,
                        CodeBlock.of("%1T()", decoderTypeName)
                    )
                )
            builder.primaryConstructor(constructor.build()).build()
        }.toList()


    private fun generateStaticArgDecoding(typeHolder: TupleTypeHolder, function: CodeBlock.Builder) {
        typeHolder.entries.forEachIndexed { index, info ->
            addDecoderStatementForType(function, index, info.second)
        }
    }

    private fun generateTupleEncoder(typeHolder: TupleTypeHolder) =
        FunSpec.builder("encode")
            .returns(String::class)
            .addModifiers(KModifier.OVERRIDE)
            .addStatement(
                "return·%T.encodeFunctionArguments(${typeHolder.entries.map { it.first }.joinToString { it }})",
                SolidityBase::class
            )
            .build()

    private fun generateFunctionEncoder(functionJson: AbiElementJson): FunSpec {
        val funSpec = FunSpec.builder("encode")
        functionJson.inputs.forEachIndexed { index, parameter ->
            val name = if (parameter.name.isEmpty()) "arg${index + 1}" else parameter.name
            val type = mapType(parameter, context)
            funSpec.addParameter(name, type.toTypeName())
        }

        val funWithParams = funSpec.build()
        val finalFun = funWithParams.toBuilder().returns(String::class)
        finalFun.addStatement(
            "return·\"0x\"·+·METHOD_ID${if (funWithParams.parameters.isNotEmpty()) {
                "·+·pm.gnosis.model.SolidityBase.encodeFunctionArguments(${funWithParams.parameters.joinToString { it.name }})"
            } else ""}"
        )

        return finalFun.build()
    }

    private fun generateParameterDecoder(functionName: String, parameters: List<ParameterJson>, dataClassName: String): FunSpec {
        val funSpecBuilder = FunSpec.builder(functionName).addParameter(DECODER_FUN_ARG_NAME, String::class)

        //Set function return
        val typeName = ClassName("", dataClassName)
        funSpecBuilder.returns(typeName)

        funSpecBuilder.addStatement("val $DECODER_VAR_PARTITIONS_NAME = %1T.of($DECODER_FUN_ARG_NAME)", SolidityBase.PartitionData::class)
        funSpecBuilder.addStatement("")
        funSpecBuilder.addComment("Add decoders")
        funSpecBuilder.addCode(generateParameterDecoderCode(parameters))
        funSpecBuilder.addStatement("")
        funSpecBuilder.addStatement("return·$dataClassName(${(0 until parameters.size).joinToString(",·") { "arg$it" }})")

        return funSpecBuilder.build()
    }

    internal fun generateParameterDecoderCode(parameters: List<ParameterJson>): CodeBlock {
        val codeBlock = CodeBlock.builder()

        generateStaticArgDecoding(parameters, codeBlock)

        return codeBlock.build()
    }

    private fun generateParameterHolder(holderName: String, parameters: List<ParameterJson>): TypeSpec {
        val returnContainerBuilder = TypeSpec.classBuilder(holderName).addModifiers(KModifier.DATA)
        val returnContainerConstructor = FunSpec.constructorBuilder()

        parameters.forEachIndexed { index, parameterJson ->
            val name = if (parameterJson.name.isEmpty()) "param$index" else parameterJson.name.toLowerCase()
            val className = mapType(parameterJson, context).toTypeName()
            returnContainerConstructor.addParameter(name, className)
            returnContainerBuilder.addProperty(PropertySpec.builder(name, className).initializer(name).build())
        }

        return returnContainerBuilder.primaryConstructor(returnContainerConstructor.build()).build()
    }

    private fun generateStaticArgDecoding(parameters: List<ParameterJson>, function: CodeBlock.Builder) {
        parameters.forEachIndexed { index, outputJson ->
            val className = mapType(outputJson, context)
            addDecoderStatementForType(function, index, className)
        }
    }

    private fun addDecoderStatementForType(function: CodeBlock.Builder, index: Int, className: TypeHolder) {
        val dynamicValName = "$DECODER_VAR_ARG_PREFIX$index"
        val source = if (isSolidityDynamicType(className)) {
            val dynamicValOffsetName = "$dynamicValName$DECODER_VAR_ARG_OFFSET_SUFFIX"
            function.addStatement(
                "val·$dynamicValOffsetName·=·%T.convert(%T(%L.consume(),·16))",
                BigIntegerToInt::class.asClassName(),
                BigInteger::class.asClassName(),
                DECODER_VAR_PARTITIONS_NAME
            )
            "%L.subData(%L)" to mutableListOf(DECODER_VAR_PARTITIONS_NAME, dynamicValOffsetName)
        } else {
            DECODER_VAR_PARTITIONS_NAME to mutableListOf()
        }

        if (isSolidityArray(className)) {
            val format = buildArrayDecoder(className)
            function.addStatement(
                "val·$dynamicValName·=·${format.first}.decode(${source.first})",
                *format.second.toTypedArray(),
                *source.second.toTypedArray()
            )
        } else {
            function.addStatement(
                "val·$dynamicValName·=·%T.DECODER.decode(${source.first})",
                className.toTypeName(),
                *source.second.toTypedArray()
            )
        }
    }

    private fun buildArrayDecoder(className: TypeHolder, forceStatic: Boolean = false): Pair<String, MutableList<TypeName>> {
        if (forceStatic && (
                    (className is VectorTypeHolder) ||
                            (className is ArrayTypeHolder && className.isDynamic()) ||
                            className.toTypeName() == Solidity.Bytes::class.asClassName() || className.toTypeName() == Solidity.String::class.asClassName())
        ) {
            throw IllegalArgumentException("No dynamic types allowed!")
        }
        if (className is CollectionTypeHolder) {
            val childClass = className.itemType
            val codeInfo = buildArrayDecoder(childClass, forceStatic)
            codeInfo.second.add(0, className.listType)
            return Pair("%T.Decoder(${codeInfo.first})", codeInfo.second)
        }
        val types = ArrayList<TypeName>()
        types.add(className.toTypeName())
        return Pair("%T.DECODER", types)
    }
}
