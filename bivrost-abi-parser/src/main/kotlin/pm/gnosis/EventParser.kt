package pm.gnosis

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import pm.gnosis.AbiParser.context
import pm.gnosis.model.AbiElementJson
import pm.gnosis.model.ParameterJson
import pm.gnosis.model.SolidityBase
import pm.gnosis.utils.keccak256

internal object EventParser {
    private const val ROOT_OBJECT_NAME = "Events"
    private const val ABI_SPEC_EVENT_TYPE = "event"
    private const val EVENT_ID_PROPERTY_NAME = "EVENT_ID"
    private const val EVENT_ARGUMENTS_CLASS_NAME = "Arguments"
    private const val DECODE_FUN_NAME = "decode"
    private const val TOPIC_ARG_NAME = "topics"

    internal fun generateEventObjects(): TypeSpec? {
        val eventsObject = TypeSpec.objectBuilder(ROOT_OBJECT_NAME)
        if (!context.root.abi.any { it.type == ABI_SPEC_EVENT_TYPE }) {
            return null
        }

        context.root.abi.filter { it.type == ABI_SPEC_EVENT_TYPE }
            .map { generateEventObject(it) }
            .forEach { eventsObject.addType(it) }

        return eventsObject.build()
    }

    private fun generateEventObject(abiElementJson: AbiElementJson): TypeSpec {
        val eventObject = TypeSpec.objectBuilder(abiElementJson.name.capitalize())

        val eventId = "${abiElementJson.name}(${abiElementJson.inputs.joinToString(",") { it.type }})".keccak256()
        eventObject.addProperty(PropertySpec.builder(EVENT_ID_PROPERTY_NAME, String::class, KModifier.CONST).initializer("\"$eventId\"").build())

        if (abiElementJson.inputs.isNotEmpty()) {
            val holder = generateEventParameterHolder(EVENT_ARGUMENTS_CLASS_NAME, abiElementJson.inputs)
            eventObject.addFunction(generateEventDecoder(abiElementJson, holder.name!!))
            eventObject.addType(holder)
        }

        return eventObject.build()
    }

    private fun generateEventDecoder(abiElementJson: AbiElementJson, holderName: String): FunSpec {
        val funSpec = FunSpec.builder(DECODE_FUN_NAME)
        val codeBlock = CodeBlock.builder()

        val topicElements = abiElementJson.inputs.filter { it.indexed }
        val dataElements = abiElementJson.inputs.filter { !it.indexed }

        funSpec.addParameter(TOPIC_ARG_NAME, List::class.asClassName().parameterizedBy(String::class.asClassName()))
        funSpec.addCode(generateTopicsDecoderCodeBlock(topicElements, abiElementJson.anonymous))

        if (dataElements.isNotEmpty()) {
            funSpec.addStatement("")
            funSpec.addParameter(AbiParser.DECODER_FUN_ARG_NAME, String::class)
            funSpec.addCode(generateDataDecoderCodeBlock(dataElements))
        }

        val holderClassName = ClassName("", holderName)
        funSpec.returns(holderClassName)

        var dataIndex = 0
        var topicIndex = 1
        val args = abiElementJson.inputs.joinToString(", ") { parameterJson ->
            val param: String
            if (parameterJson.indexed) {
                param = "t$topicIndex"
                topicIndex++
            } else {
                param = "${AbiParser.DECODER_VAR_ARG_PREFIX}$dataIndex"
                dataIndex++
            }
            param
        }

        codeBlock.addStatement("return %1T($args)", holderClassName)

        return funSpec.addCode(codeBlock.build()).build()
    }

    private fun generateTopicsDecoderCodeBlock(indexedParameters: List<ParameterJson>, isAnonymous: Boolean): CodeBlock {
        val codeBlock = CodeBlock.builder()
        codeBlock.addStatement("// Decode topics")
        if (!isAnonymous) {
            codeBlock.addStatement(
                "if·($TOPIC_ARG_NAME.first()·!=·$EVENT_ID_PROPERTY_NAME)·throw·%1T(\"topics[0]·does·not·match·event·id\")",
                IllegalArgumentException::class
            )
        }

        indexedParameters.forEachIndexed { index, topic ->
            val typeHolder = mapType(topic, context)
            if (typeHolder.isHashTopic(topic)) {
                codeBlock.addStatement("val·t${index + 1}·=·$TOPIC_ARG_NAME[${index + 1}]")
            } else {
                val sourceName = "source${index + 1}"
                codeBlock
                    .addStatement("val·$sourceName·=·%1T.of($TOPIC_ARG_NAME[${index + 1}])", SolidityBase.PartitionData::class)
                    .addStatement("val·t${index + 1}·=·%1T.DECODER.decode(%2L)", typeHolder.toTypeName(), sourceName)
            }
        }

        return codeBlock.build()
    }

    private fun generateDataDecoderCodeBlock(nonIndexedParameters: List<ParameterJson>): CodeBlock {
        val codeBlock = CodeBlock.builder()

        codeBlock.apply {
            addStatement("// Decode data")
            addStatement(
                "val·${AbiParser.DECODER_VAR_PARTITIONS_NAME}·=·%1T.of(${AbiParser.DECODER_FUN_ARG_NAME})",
                SolidityBase.PartitionData::class
            )
            add(AbiParser.generateParameterDecoderCode(nonIndexedParameters))
        }

        return codeBlock.build()
    }

    private fun generateEventParameterHolder(holderName: String, parameters: List<ParameterJson>): TypeSpec {
        val returnContainerBuilder = TypeSpec.classBuilder(holderName).addModifiers(KModifier.DATA)
        val returnContainerConstructor = FunSpec.constructorBuilder()

        parameters.forEachIndexed { index, parameterJson ->
            var name = if (parameterJson.name.isEmpty()) "param$index" else parameterJson.name.toLowerCase()

            val typeHolder = mapType(parameterJson, context)
            val className = if (typeHolder.isHashTopic(parameterJson)) {
                name = if (parameterJson.name.isEmpty()) "param${index}Hash" else "${parameterJson.name.toLowerCase()}Hash"
                ClassName.bestGuess("kotlin.String")
            } else {
                typeHolder.toTypeName()
            }
            returnContainerConstructor.addParameter(name, className)
            returnContainerBuilder.addProperty(PropertySpec.builder(name, className).initializer(name).build())
        }

        return returnContainerBuilder.primaryConstructor(returnContainerConstructor.build()).build()
    }

    // Indexed arrays, strings and bytes and tuples only have the keccak hash
    private fun TypeHolder.isHashTopic(parameterJson: ParameterJson) =
        parameterJson.indexed && (this is TupleTypeHolder || this is CollectionTypeHolder || isDynamic())
}
