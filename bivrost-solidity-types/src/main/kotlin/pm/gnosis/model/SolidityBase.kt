package pm.gnosis.model

import pm.gnosis.exceptions.InvalidBitLengthException
import pm.gnosis.utils.hexToByteArray
import pm.gnosis.utils.padStartMultiple
import pm.gnosis.utils.toHex
import java.math.BigDecimal
import java.math.BigInteger
import java.nio.charset.Charset
import java.util.*
import kotlin.collections.ArrayList

object SolidityBase {
    const val BYTES_PAD = 32
    const val PADDED_HEX_LENGTH = BYTES_PAD * 2

    val dynamicTypes: List<kotlin.String> = listOf("bytes", "string")

    interface Type {
        fun encode(): String
    }

    interface StaticType : Type

    interface DynamicType : Type {
        data class Parts(val static: String, val dynamic: String)
    }

    abstract class Collection<out T : Type>(val items: List<T>) : Type {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Collection<*>

            if (items != other.items) return false

            return true
        }

        override fun hashCode(): Int {
            return items.hashCode()
        }

        abstract fun isDynamic(): Boolean
    }

    abstract class UIntBase(private val value: BigInteger, bitLength: kotlin.Int) : StaticType {
        init {
            when {
                bitLength % 8 != 0 -> throw InvalidBitLengthException.NOT_MULTIPLE_OF_EIGHT
                value.bitLength() > bitLength -> throw InvalidBitLengthException.BIG_VALUE
                value.signum() == -1 -> throw IllegalArgumentException("UInt doesn't allow negative numbers")
            }
        }

        override fun encode(): String {
            val string = value.toString(16)
            return string.padStartMultiple(PADDED_HEX_LENGTH, '0')
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as UIntBase

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        open class Decoder<out T : UIntBase>(private val factory: (BigInteger) -> T) : TypeDecoder<T> {
            override fun isDynamic(): Boolean {
                return false
            }

            override fun decode(source: PartitionData): T {
                return factory.invoke(decodeUInt(source.consume()))
            }
        }

    }

    abstract class IntBase(private val value: BigInteger, private val bitLength: kotlin.Int) : StaticType {
        init {
            if (bitLength % 8 != 0) throw InvalidBitLengthException.NOT_MULTIPLE_OF_EIGHT
            val min = BigInteger.valueOf(2).pow(bitLength - 1).negate()
            val max = BigInteger.valueOf(2).pow(bitLength - 1) - BigInteger.ONE

            if (value < min || value > max) throw IllegalArgumentException("Value is not within bit range [$min, $max]")
        }

        override fun encode(): String {
            return if (value.signum() == -1) {
                val bits = value.toString(2).removePrefix("-").padStart(bitLength, '0')
                val x = bits.map { if (it == '0') '1' else '0' }.joinToString("")
                BigInteger(x, 2).add(BigInteger.ONE).toString(16).padStartMultiple(PADDED_HEX_LENGTH, 'f')
            } else {
                value.toString(16).padStartMultiple(PADDED_HEX_LENGTH, '0')
            }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as IntBase

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        open class Decoder<out T : IntBase>(private val factory: (BigInteger) -> T) : TypeDecoder<T> {
            override fun isDynamic(): Boolean {
                return false
            }

            override fun decode(source: PartitionData): T {
                return factory.invoke(decodeInt(source.consume()))
            }
        }
    }

    abstract class StaticBytes(val byteArray: ByteArray, nBytes: kotlin.Int) : StaticType {
        init {
            if (byteArray.size > nBytes) throw IllegalArgumentException("Byte array has ${byteArray.size} bytes. It should have no more than $nBytes bytes.")
        }

        override fun encode(): String {
            return byteArray.toHex().padEnd(PADDED_HEX_LENGTH, '0')
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as StaticBytes

            if (byteArray != other.byteArray) return false

            return true
        }

        override fun hashCode(): Int {
            return byteArray.hashCode()
        }

        open class Decoder<out T : StaticBytes>(private val factory: (ByteArray) -> T, val nBytes: Int) : TypeDecoder<T> {
            override fun isDynamic(): Boolean {
                return false
            }

            override fun decode(source: PartitionData): T {
                return factory.invoke(decodeStaticBytes(source.consume(), nBytes))
            }
        }
    }

    class PartitionData(private val partitions: List<String>) {
        var index: Int = 0

        fun consume(): String {
            return partitions[index++].removePrefix("0x")
        }

        fun reset() {
            index = 0
        }

        companion object {
            fun of(data: String): PartitionData {
                return PartitionData(partitionData(data))
            }
        }
    }

    interface TypeDecoder<out T : Type> {

        fun isDynamic(): Boolean

        fun decode(source: PartitionData): T
    }

    fun <T : Type> decodeList(source: PartitionData, capacity: Int, itemDecoder: TypeDecoder<T>): List<T> {
        val decodeParams = ArrayList<T?>()
        val dynamicDecoders = LinkedList<TypeDecoder<T>>()

        for (i in 0 until capacity) {
            if (itemDecoder.isDynamic()) {
                // We cannot decode it right away, remember the decoder for later
                decodeParams.add(null)
                dynamicDecoders.push(itemDecoder)
                // consume location of dynamic value
                source.consume()
            } else {
                decodeParams += itemDecoder.decode(source)
            }
        }

        return decodeParams.map {
            it ?: dynamicDecoders.removeFirst().decode(source)
        }
    }

    abstract class Array<out T : Type>(items: List<T>, val capacity: Int) : Collection<T>(checkCapacity(items, capacity)) {
        override fun encode(): String {
            if (items.size != capacity) {
                throw IllegalStateException("Capacity mismatch!")
            }
            // Encode the fixed array as a tuple where all parts are of the same time
            return encodeTuple(items)
        }

        override fun isDynamic(): Boolean {
            if (capacity == 0) {
                return false
            }
            items.forEach {
                if (isDynamic(it)) {
                    return true
                }
            }
            return false
        }

        companion object {
            private fun <T : Type> checkCapacity(items: List<T>, capacity: Int): List<T> {
                if (items.size != capacity) {
                    throw IllegalStateException("Array is of wrong capacity!")
                }
                return ArrayList(items)
            }

        }
    }

    class Vector<out T : Type>(items: List<T>) : Collection<T>(items), DynamicType {

        override fun encode(): String {
            val parts = encodeParts()
            return parts.static + parts.dynamic
        }

        private fun encodeParts(): DynamicType.Parts {
            val length = items.size.toString(16).padStart(PADDED_HEX_LENGTH, '0')
            // Encode the dynamic array as the length and a tuple where all parts are of the same time
            return DynamicType.Parts(length, encodeTuple(items))
        }

        override fun isDynamic(): Boolean {
            return true
        }

        class Decoder<out T : Type>(private val itemDecoder: TypeDecoder<T>) : TypeDecoder<Vector<T>> {
            override fun isDynamic(): Boolean {
                return true
            }

            override fun decode(source: PartitionData): Vector<T> {
                val capacity = decodeUInt(source.consume()).toInt()
                return Vector(decodeList(source, capacity, itemDecoder))
            }
        }
    }

    @Suppress("unused")
    fun encodeFunctionArguments(vararg args: Type): String {
        return encodeTuple(args.toList())
    }

    @Suppress("unused")
    fun encodeTuple(parts: List<Type>): String {
        val encodedParts = ArrayList<Pair<String, Boolean>>()

        var sizeOfStaticBlock = 0
        parts.forEach {
            val encoded = it.encode()
            if (isDynamic(it)) {
                encodedParts += Pair(encoded, true)
                // Add length of an address to static block size
                sizeOfStaticBlock += BYTES_PAD
            } else {
                encodedParts += Pair(encoded, false)
                sizeOfStaticBlock += encoded.length / 2
            }
        }

        val staticArgsBuilder = StringBuilder()
        val dynamicArgsBuilder = StringBuilder()
        encodedParts.forEach { (encoded, dynamic) ->
            if (dynamic) {
                val location = sizeOfStaticBlock + dynamicArgsBuilder.length / 2
                staticArgsBuilder.append(location.toString(16).padStart(PADDED_HEX_LENGTH, '0'))
                dynamicArgsBuilder.append(encoded)
            } else {
                staticArgsBuilder.append(encoded)
            }
        }

        return staticArgsBuilder.toString() + dynamicArgsBuilder.toString()
    }

    private fun isDynamic(type: Type): Boolean {
        if (type is DynamicType) {
            return true
        }
        return (type as? Collection<*>)?.isDynamic() ?: false || (type is Vector<*>)
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun partitionData(data: String): List<String> {
        var noPrefix = data.removePrefix("0x")
        if (noPrefix.length.rem(PADDED_HEX_LENGTH) != 0) throw IllegalArgumentException("Data is not a multiple of $PADDED_HEX_LENGTH")
        val properties = arrayListOf<String>()

        while (noPrefix.length >= PADDED_HEX_LENGTH) {
            properties.add(noPrefix.subSequence(0, PADDED_HEX_LENGTH).toString())
            noPrefix = noPrefix.removeRange(0 until PADDED_HEX_LENGTH)
        }
        return properties
    }

    fun decodeUInt(data: String): BigInteger {
        return BigInteger(data, 16)
    }

    fun decodeBool(data: String): Boolean {
        val value = BigInteger(data)
        return when (value) {
            BigInteger.ZERO -> false
            BigInteger.ONE -> true
            else -> throw IllegalArgumentException("${value.toString(10)} is not a valid boolean representation. It should either be 0 (false) or 1 (true)")
        }
    }

    fun decodeInt(data: String): BigInteger {
        val value = BigInteger(data, 16)
        if (data.startsWith("8") ||
                data.startsWith("9") ||
                data.startsWith("A", true) ||
                data.startsWith("B", true) ||
                data.startsWith("C", true) ||
                data.startsWith("D", true) ||
                data.startsWith("E", true) ||
                data.startsWith("F", true)) {
            val x = value.toString(2).map { if (it == '0') '1' else '0' }.joinToString("")
            return BigInteger(x, 2).add(BigInteger.ONE).multiply(BigInteger("-1"))
        }
        return value
    }

    fun decodeStaticBytes(data: String, nBytes: kotlin.Int): ByteArray {
        return data.substring(0, nBytes * 2).hexToByteArray()
    }

    fun decodeBytes(source: PartitionData): ByteArray {
        val contentSize = BigDecimal(BigInteger(source.consume(), 16)).intValueExact() * 2
        if (contentSize == 0) return kotlin.ByteArray(0)
        val sb = StringBuilder()
        while (sb.length < contentSize) {
            sb.append(source.consume())
        }
        return sb.substring(0, contentSize).hexToByteArray()
    }

    fun decodeString(source: PartitionData) =
            decodeBytes(source).toString(Charset.forName("UTF-8"))

    fun <T : Any> decodeArray(data: String, itemDecoder: (String) -> T): List<T> {
        val params = partitionData(data)
        val contentSize = BigDecimal(BigInteger(params[0])).intValueExact()
        if (contentSize != params.size - 1) throw IllegalArgumentException("Number of items is different from the actual array size")
        if (contentSize == 0) return emptyList()
        return (1 until params.size).map { itemDecoder.invoke(params[it]) }.toList()
    }
}
