package pm.gnosis

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asClassName
import org.bouncycastle.crypto.digests.KeccakDigest
import pm.gnosis.model.ParameterJson
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase
import pm.gnosis.utils.toHex
import java.util.regex.Pattern

private val TUPLE_NAME_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
private val TYPE_PATTERN = Pattern.compile("^(\\w+)((?>\\[\\d*])*)$")
private val ARRAY_DEF_PATTERN = Pattern.compile("^\\[[0-9]*]")
internal const val TUPLE_TYPE_NAME = "tuple"

internal interface TypeHolder {
    fun toTypeName(): TypeName

    fun isDynamic(): Boolean

    fun hash(): String
}

internal class SimpleTypeHolder private constructor(private val className: ClassName, private val dynamic: Boolean) : TypeHolder {
    override fun toTypeName() = className

    override fun isDynamic() = dynamic

    override fun hash() = generateHash(listOf(className.toString()))

    companion object {
        /**
         * Create a SimpleTypeHolder for a given type if possible.
         * If the given type is not a simple type (e.g. Tuple) null will be returned.
         */
        fun forType(type: String): SimpleTypeHolder? {
            val baseType = Solidity.types[checkType(type)] ?: return null
            return SimpleTypeHolder(ClassName.bestGuess(baseType), SolidityBase.dynamicTypes.contains(type.toLowerCase()))
        }
    }
}

internal abstract class CollectionTypeHolder(val listType: ClassName, val itemType: TypeHolder) : TypeHolder {
    override fun hash() = generateHash(listOf(listType.toString(), itemType.hash()))
}

internal class ArrayTypeHolder(arraysMap: AbiParser.ArraysMap, itemType: TypeHolder, val capacity: Int) :
    CollectionTypeHolder(arraysMap.get(capacity), itemType) {

    override fun toTypeName() = listType.parameterizedBy(itemType.toTypeName())

    override fun isDynamic() = capacity > 0 && itemType.isDynamic()
}

internal class VectorTypeHolder(itemType: TypeHolder) : CollectionTypeHolder(SolidityBase.Vector::class.asClassName(), itemType) {

    override fun toTypeName() = listType.parameterizedBy(itemType.toTypeName())

    override fun isDynamic() = true
}

internal class TupleTypeHolder(index: Int, val entries: List<Pair<String, TypeHolder>>) : TypeHolder {

    val name = "Tuple" + numberToLetter(index)

    override fun toTypeName() = ClassName("", name)

    override fun hash() = generateHash(entries.map { "${it.first}:${it.second.hash()} " })

    override fun isDynamic() = entries.any { it.second.isDynamic() }
}

internal fun checkType(type: String): String {
    return Solidity.aliases.getOrElse(type) { type }
}

internal fun mapType(parameter: ParameterJson, context: AbiParser.GeneratorContext): TypeHolder {
    val matcher = TYPE_PATTERN.matcher(parameter.type)
    if (!matcher.find()) {
        throw IllegalArgumentException("Invalid parameter definition: ${parameter.type}!")
    }
    val arrayType = matcher.group(1)
    val baseType = SimpleTypeHolder.forType(arrayType) ?: generateTuple(arrayType, parameter, context)
    ?: throw IllegalArgumentException("Unknown parameter ${parameter.type}!")
    val arrayDef = matcher.group(2)
    if (arrayType.length < parameter.type.length && arrayDef.isNullOrBlank()) {
        throw IllegalArgumentException("Invalid parameter definition: ${parameter.type}!")
    }
    return parseArrayDefinition(arrayDef, baseType, context)
}

private fun generateTuple(type: String, parameters: ParameterJson, context: AbiParser.GeneratorContext): TypeHolder? {
    if (type != TUPLE_TYPE_NAME || parameters.components == null) {
        return null
    }
    val entries = parameters.components.mapIndexed { index, param ->
        Pair(if (param.name.isEmpty()) "param$index" else param.name.toLowerCase(), mapType(param, context))
    }
    val tupleTypeHolder = TupleTypeHolder(context.tuples.size, entries)
    return context.tuples.getOrPut(tupleTypeHolder.hash()) { tupleTypeHolder }
}

private fun parseArrayDefinition(arrayDef: String, innerType: TypeHolder, context: AbiParser.GeneratorContext): TypeHolder {
    if (arrayDef.isBlank()) {
        return innerType
    }
    val matcher = ARRAY_DEF_PATTERN.matcher(arrayDef)
    if (!matcher.find()) {
        throw IllegalArgumentException("Illegal array definition $arrayDef!")
    }
    val match = matcher.group() ?: throw IllegalArgumentException()
    val arraySizeDef = match.substring(1, match.length - 1)
    val arraySize = if (arraySizeDef.isBlank()) -1 else arraySizeDef.toInt()
    val collectionTypeHolder = if (arraySizeDef.isBlank()) {
        VectorTypeHolder(innerType)
    } else {
        ArrayTypeHolder(context.arrays, innerType, arraySize)
    }
    return parseArrayDefinition(arrayDef.removePrefix(match), collectionTypeHolder, context)
}

internal fun isSolidityDynamicType(type: TypeHolder) = type.isDynamic()

internal fun isSolidityArray(type: TypeHolder) = type is CollectionTypeHolder

private fun numberToLetter(index: Int): String {
    val builder = StringBuilder()
    var i = index
    do {
        builder.append(TUPLE_NAME_ALPHABET[i % TUPLE_NAME_ALPHABET.size])
        i /= TUPLE_NAME_ALPHABET.size
    } while (i > 0)
    return builder.toString()
}

private fun generateHash(parts: List<String>): String {
    val digest = KeccakDigest()
    parts.forEach {
        val bytes = it.toByteArray()
        digest.update(bytes, 0, bytes.size)
    }
    val hash = ByteArray(digest.digestSize)
    digest.doFinal(hash, 0)
    return hash.toHex()
}
