package pm.gnosis

import com.squareup.kotlinpoet.*
import pm.gnosis.model.SolidityBase

object GeneratorUtils {
    fun generateDecoderCompanion(decoderTypeName: TypeName, decoderInit: CodeBlock) = TypeSpec.companionObjectBuilder()
            .addProperty(PropertySpec.builder("DECODER", decoderTypeName)
                    .initializer(decoderInit)
                    .build())
            .build()

    fun generateDecoder(name: String, decodeCode: CodeBlock, isDynamic: Boolean = true, paramName: String = "source")
            = generateDecoderBuilder(ClassName("", name), decodeCode, CodeBlock.of("return %L", isDynamic), paramName).build()

    fun generateDecoderBuilder(forClass: TypeName, decodeCode: CodeBlock, isDynamicBlock: CodeBlock, paramName: String = "source"): TypeSpec.Builder {
        return TypeSpec.classBuilder("Decoder")
                .addSuperinterface(ParameterizedTypeName.get(SolidityBase.TypeDecoder::class.asClassName(), forClass))
                .addFunction(generateIsDynamicFunction(isDynamicBlock))
                .addFunction(generateDecodeSourceFunction(decodeCode, forClass, paramName))
    }

    fun generateDecodeSourceFunction(decodeCode: CodeBlock, returnType: TypeName, paramName: String) =
            FunSpec.builder("decode")
                    .addParameter(paramName, SolidityBase.PartitionData::class)
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(returnType)
                    .addCode(decodeCode)
                    .build()

    fun generateIsDynamicFunction(isDynamicBlock: CodeBlock): FunSpec = FunSpec.builder("isDynamic")
            .addModifiers(KModifier.OVERRIDE)
            .returns(Boolean::class)
            .addCode(isDynamicBlock)
            .build()
}
