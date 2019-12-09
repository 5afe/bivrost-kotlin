package pm.gnosis.model

import pm.gnosis.exceptions.InvalidBitLengthException
import pm.gnosis.utils.hexToByteArray
import pm.gnosis.utils.padStartMultiple
import pm.gnosis.utils.toHex
import java.math.BigDecimal
import java.math.BigInteger
import java.nio.charset.Charset
import kotlin.collections.ArrayList

object SolidityBase {
    const val BYTES_PAD = 32
    const val PADDED_HEX_LENGTH = BYTES_PAD * 2

    val dynamicTypes: List<String> = listOf("bytes", "string")

    interface Type {
        fun encode(): String
        fun encodePacked(): String
    }

    interface StaticType : Type

    interface DynamicType : Type {
        data class Parts(val static: String, val dynamic: String)
    }

    abstract class Collection<out T : Type>(val items: List<T>) : Type {

        override fun encodePacked(): String {
            if (items.any { isDynamic(it) }) throw IllegalArgumentException("Cannot encode dynamic items packed!")
            return encodeTuple(items)
        }

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

    abstract class UIntBase(private val value: BigInteger, private val bitLength: Int) : StaticType {
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

        override fun encodePacked(): String {
            val string = value.toString(16)
            return string.padStart(bitLength / 8 * 2, '0')
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

    abstract class IntBase(private val value: BigInteger, private val bitLength: Int) : StaticType {
        init {
            if (bitLength % 8 != 0) throw InvalidBitLengthException.NOT_MULTIPLE_OF_EIGHT
            val min = BigInteger.valueOf(2).pow(bitLength - 1).negate()
            val max = BigInteger.valueOf(2).pow(bitLength - 1) - BigInteger.ONE

            if (value < min || value > max) throw IllegalArgumentException("Value is not within bit range [$min, $max]")
        }

        private fun encodeWithPadding(paddingLength: Int): String {
            return if (value.signum() == -1) {
                val bits = value.toString(2).removePrefix("-").padStart(bitLength, '0')
                val x = bits.map { if (it == '0') '1' else '0' }.joinToString("")
                BigInteger(x, 2).add(BigInteger.ONE).toString(16).padStartMultiple(paddingLength, 'f')
            } else {
                value.toString(16).padStartMultiple(paddingLength, '0')
            }

        }

        override fun encode(): String = encodeWithPadding(PADDED_HEX_LENGTH)

        override fun encodePacked(): String = encodeWithPadding(bitLength / 8 * 2)

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

    abstract class StaticBytes(val byteArray: ByteArray, nBytes: Int) : StaticType {
        init {
            if (byteArray.size > nBytes) throw IllegalArgumentException("Byte array has ${byteArray.size} bytes. It should have no more than $nBytes bytes.")
        }

        override fun encode(): String {
            return byteArray.toHex().padEnd(PADDED_HEX_LENGTH, '0')
        }

        override fun encodePacked(): String {
            return byteArray.toHex()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as StaticBytes

            return byteArray.contentEquals(other.byteArray)
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

    class PartitionData(data: String, private val offset: Int = 0) {
        private val cleanData = data.removePrefix("0x")
        private var index: Int = 0

        fun consume(): String {
            return cleanData.substring(offset + index * 64, offset + (index + 1) * 64).apply {
                index++
            }
        }

        fun reset() {
            index = 0
        }

        fun subData() = PartitionData(cleanData, offset + index * 64)

        fun subData(bytesOffset: Int) = PartitionData(cleanData, offset + bytesOffset * 2)

        companion object {
            fun of(data: String): PartitionData {
                return PartitionData(data)
            }
        }
    }

    interface TypeDecoder<out T : Type> {

        fun isDynamic(): Boolean

        fun decode(source: PartitionData): T
    }

    fun <T : Type> decodeList(source: PartitionData, capacity: Int, itemDecoder: TypeDecoder<T>): List<T> {
        return (0 until capacity).map {
            if (itemDecoder.isDynamic()) {
                // Get offset
                val offset = BigInteger(source.consume(), 16).intValueExact()
                // Decode dynamic data at offset
                itemDecoder.decode(source.subData(offset))
            } else {
                itemDecoder.decode(source)
            }
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
            return items.any { isDynamic(it) }
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
                return Vector(decodeList(source.subData(), capacity, itemDecoder))
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

    @Deprecated("Deprecated for decodeList")
    fun <T : Any> decodeArray(data: String, itemDecoder: (String) -> T): List<T> {
        val params = PartitionData.of(data)
        val contentSize = BigInteger(params.consume()).intValueExact()
        if (contentSize == 0) return emptyList()
        return (0 until contentSize).map { itemDecoder(params.consume()) }
    }
}
