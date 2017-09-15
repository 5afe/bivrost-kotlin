package pm.gnosis.model

import pm.gnosis.exceptions.InvalidBitLengthException
import pm.gnosis.utils.hexToByteArray
import pm.gnosis.utils.padStartMultiple
import pm.gnosis.utils.toHex
import java.math.BigDecimal
import java.math.BigInteger
import java.nio.charset.Charset
import java.util.*

object SolidityBase {
    const val BYTES_PAD = 32
    const val BITS_PAD = BYTES_PAD * 8
    const val PADDED_HEX_LENGTH = BYTES_PAD * 2

    val EMPTY_BYTE = "0".repeat(PADDED_HEX_LENGTH)

    interface Type {
        fun encode(): String

        interface Decoder<out T : Type> {
            fun decode(source: String): T
        }
    }

    interface StaticType : Type

    interface DynamicType : Type {
        data class Parts(val static: String, val dynamic: String)

        fun encodeParts(): Parts
    }

    abstract class BaseArray<T : Type>(val hasDynamicItems: Boolean = false) : Type

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
            return partitions[index++]
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

    private fun <T : Type> decodeBaseArray(source: PartitionData, capacity: Int, itemDecoder: TypeDecoder<T>): List<T> {
        if (itemDecoder.isDynamic()) {
            // TODO: implement decoding of dynamic elements in an array
            throw UnsupportedOperationException("Dynamic types are not supported in arrays yet!")
        }
        if (capacity == 0) {
            return emptyList()
        }
        return (0 until capacity).map { itemDecoder.decode(source) }.toList()
    }

    class FixedArray<T : Type>(val items: List<T>, val capacity: Int, hasDynamicItems: Boolean = false) : BaseArray<T>(hasDynamicItems) {
        override fun encode(): String {
            if (hasDynamicItems) {
                // TODO: implement encoding of dynamic elements in an array
                throw UnsupportedOperationException("Dynamic types are not supported in arrays yet!")
            }
            if (items.size > capacity) {
                throw IllegalStateException("Too many items in array!")
            }
            val stringBuilder = StringBuilder()
            items.forEach {
                stringBuilder.append(it.encode())
            }
            // Fill missing items with 0 bytes
            for (i in items.size + 1 .. capacity) {
                stringBuilder.append(EMPTY_BYTE)
            }
            return stringBuilder.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as FixedArray<*>

            if (items != other.items) return false
            if (capacity != other.capacity) return false
            if (hasDynamicItems != other.hasDynamicItems) return false

            return true
        }

        override fun hashCode(): Int {
            var result = items.hashCode()
            result = 31 * result + capacity
            result = 31 * result + hasDynamicItems.hashCode()
            return result
        }

        open class Decoder<T : Type>(val itemDecoder: TypeDecoder<T>, val capacity: Int) : TypeDecoder<FixedArray<T>> {
            override fun isDynamic(): Boolean {
                return itemDecoder.isDynamic()
            }

            override fun decode(source: PartitionData): FixedArray<T> {
                return FixedArray(decodeBaseArray(source, capacity, itemDecoder), capacity, isDynamic())
            }

        }
    }

    class DynamicArray<T : Type>(val items: List<T>, hasDynamicItems: Boolean = false) : BaseArray<T>(hasDynamicItems) {

        override fun encode(): String {
            val parts = encodeParts()
            return parts.static + parts.dynamic
        }

        private fun encodeParts(): DynamicType.Parts {
            val length = items.size.toString(16).padStart(PADDED_HEX_LENGTH, '0')
            return DynamicType.Parts(length, FixedArray(items, items.size, hasDynamicItems).encode())
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as DynamicArray<*>

            if (items != other.items) return false
            if (hasDynamicItems != other.hasDynamicItems) return false

            return true
        }

        override fun hashCode(): Int {
            var result = items.hashCode()
            result = 31 * result + hasDynamicItems.hashCode()
            return result
        }


        open class Decoder<T : Type>(val itemDecoder: TypeDecoder<T>) : TypeDecoder<DynamicArray<T>> {
            override fun isDynamic(): Boolean {
                return true
            }

            override fun decode(source: PartitionData): DynamicArray<T> {
                val capacity = decodeUInt(source.consume()).toInt()
                return DynamicArray(decodeBaseArray(source, capacity, itemDecoder), itemDecoder.isDynamic())
            }
        }
    }

    @Suppress("unused")
    fun encodeFunctionArguments(vararg args: Type): String {
        val sizeOfStaticBlock = args.size * BYTES_PAD
        val staticArgsBuilder = StringBuilder()
        val dynamicArgsBuilder = StringBuilder()
        val locations = ArrayDeque<kotlin.Int>()
        args.filter { isDynamic(it) }.forEach {
            locations.add(sizeOfStaticBlock + dynamicArgsBuilder.length / 2)
            dynamicArgsBuilder.append(it.encode())
        }
        args.forEach {
            if (isDynamic(it)) {
                staticArgsBuilder.append(locations.removeFirst().toString(16).padStart(PADDED_HEX_LENGTH, '0'))
            } else {
                staticArgsBuilder.append(it.encode())
            }
        }

        return staticArgsBuilder.toString() + dynamicArgsBuilder.toString()
    }

    @Suppress("unused")
    fun encodeFixedArray(vararg args: Type): String {
        val sizeOfStaticBlock = args.size * BYTES_PAD
        val staticArgsBuilder = StringBuilder()
        val dynamicArgsBuilder = StringBuilder()
        val locations = ArrayDeque<kotlin.Int>()
        args.filter { isDynamic(it) }.forEach {
            locations.add(sizeOfStaticBlock + dynamicArgsBuilder.length / 2)
            dynamicArgsBuilder.append(it.encode())
        }
        args.forEach {
            if (isDynamic(it)) {
                staticArgsBuilder.append(locations.removeFirst().toString(16).padStart(PADDED_HEX_LENGTH, '0'))
            } else {
                staticArgsBuilder.append(it.encode())
            }
        }

        return staticArgsBuilder.toString() + dynamicArgsBuilder.toString()
    }

    private fun isDynamic(type: Type): Boolean {
        if (type is DynamicType) {
            return true
        }
        return (type as? BaseArray<*>)?.hasDynamicItems ?: false
    }

    @Suppress("MemberVisibilityCanPrivate")
    fun partitionData(data: String): List<String> {
        var noPrefix = data.removePrefix("0x")
        if (noPrefix.isEmpty() || noPrefix.length.rem(PADDED_HEX_LENGTH) != 0) throw IllegalArgumentException("Data is not a multiple of ${PADDED_HEX_LENGTH}")
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
