package pm.gnosis

import com.squareup.kotlinpoet.*
import pm.gnosis.model.SolidityBase


object GeneratorUtils {
    fun generateDecoderCompanion(decoderTypeName: TypeName, decoderInit: CodeBlock) = TypeSpec.companionObjectBuilder()
            .addProperty(PropertySpec.builder("DECODER", decoderTypeName)
                    .initializer(decoderInit)
                    .build())
            .build()

    fun generateDecoder(name: String, decodeCode: CodeBlock, isDynamic: Boolean = true, paramName: String = "source"): TypeSpec {
        val className = ClassName("", name)
        return TypeSpec.classBuilder("Decoder")
                .addSuperinterface(ParameterizedTypeName.get(SolidityBase.TypeDecoder::class.asClassName(), className))
                .addFun(generateIsDynamicFunction(isDynamic))
                .addFun(generateDecodeSourceFunction(decodeCode, className, paramName))
                .build()
    }

    fun generateDecodeSourceFunction(decodeCode: CodeBlock, className: ClassName, paramName: String) =
            FunSpec.builder("decode")
                    .addParameter(paramName, SolidityBase.PartitionData::class)
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(className)
                    .addCode(decodeCode)
                    .build()

    fun generateIsDynamicFunction(isDynamic: Boolean): FunSpec = FunSpec.builder("isDynamic")
            .addModifiers(KModifier.OVERRIDE)
            .returns(Boolean::class)
            .addCode(CodeBlock.of("return %L", isDynamic))
            .build()
}