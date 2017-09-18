package pm.gnosis.model

import pm.gnosis.exceptions.InvalidBitLengthException
import pm.gnosis.utils.hexToByteArray
import pm.gnosis.utils.padStartMultiple
import pm.gnosis.utils.toHex
import java.math.BigDecimal
import java.math.BigInteger
import java.nio.charset.Charset

object SolidityBase {
    const val BYTES_PAD = 32
    const val PADDED_HEX_LENGTH = BYTES_PAD * 2

    interface Type {
        fun encode(): String

        interface Decoder<out T : Type> {
            fun decode(source: String): T
        }
    }

    interface StaticType : Type

    interface DynamicType : Type {
        data class Parts(val static: String, val dynamic: String)
    }

    abstract class Collection<out T : Type>(val items: List<T>, val hasDynamicItems: Boolean = false) : Type {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Collection<*>

            if (items != other.items) return false
            if (hasDynamicItems != other.hasDynamicItems) return false

            return true
        }

        override fun hashCode(): Int {
            var result = items.hashCode()
            result = 31 * result + hasDynamicItems.hashCode()
            return result
        }
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

    private fun <T : DynamicType> decodeDynamicArray(source: PartitionData, capacity: Int, itemDecoder: TypeDecoder<T>): List<T> {
        if (!itemDecoder.isDynamic()) {
            throw IllegalStateException("Item decoder ${itemDecoder::class} should be for dynamic type")
        }
        for (i in 0 until capacity) {
            source.consume()
        }
        val items = ArrayList<T>()
        for (i in 0 until capacity) {
            System.out.println(source.index)
            items.add(itemDecoder.decode(source))
        }
        return items
    }

    private fun <T : StaticType> decodeStaticArray(source: PartitionData, capacity: Int, itemDecoder: TypeDecoder<T>): List<T> {
        if (itemDecoder.isDynamic()) {
            throw IllegalStateException("Item decoder ${itemDecoder::class} should be for static type")
        }
        if (capacity == 0) {
            return emptyList()
        }
        return (0 until capacity).map { itemDecoder.decode(source) }.toList()
    }

    abstract class Array<T : Type> internal constructor(items: List<T>, val capacity: Int, hasDynamicItems: Boolean = false) : Collection<T>(items, hasDynamicItems) {

        fun checkCapacity(targetCapacity: Int) {
            if (targetCapacity != capacity) {
                throw IllegalStateException("Array is of wrong capacity!")
            }
        }

        override fun encode(): String {
            if (items.size != capacity) {
                throw IllegalStateException("Capacity mismatch!")
            }
            return encodeFixedArray(items)
        }
    }

    class ArrayST<T : StaticType>(items: List<T>, capacity: Int) : Array<T>(items, capacity, false), StaticType {
        open class Decoder<T : StaticType>(val itemDecoder: TypeDecoder<T>, val capacity: Int) : TypeDecoder<ArrayST<T>> {
            override fun isDynamic(): Boolean {
                return itemDecoder.isDynamic()
            }

            override fun decode(source: PartitionData): ArrayST<T> {
                return ArrayST(decodeStaticArray(source, capacity, itemDecoder), capacity)
            }
        }
    }

    class ArrayDT<T : DynamicType>(items: List<T>, capacity: Int) : Array<T>(items, capacity, true), DynamicType {
        open class Decoder<T : DynamicType>(val itemDecoder: TypeDecoder<T>, val capacity: Int) : TypeDecoder<ArrayDT<T>> {
            override fun isDynamic(): Boolean {
                return itemDecoder.isDynamic()
            }

            override fun decode(source: PartitionData): ArrayDT<T> {
                return ArrayDT(decodeDynamicArray(source, capacity, itemDecoder), capacity)
            }

        }
    }

    abstract class Vector<T : Type>(items: List<T>, hasDynamicItems: Boolean) : Collection<T>(items, hasDynamicItems), DynamicType {

        override fun encode(): String {
            val parts = encodeParts()
            return parts.static + parts.dynamic
        }

        private fun encodeParts(): DynamicType.Parts {
            val length = items.size.toString(16).padStart(PADDED_HEX_LENGTH, '0')
            return DynamicType.Parts(length, encodeFixedArray(items))
        }
    }

    class VectorST<T : StaticType>(items: List<T>) : Vector<T>(items, false) {
        open class Decoder<T : StaticType>(val itemDecoder: TypeDecoder<T>) : TypeDecoder<VectorST<T>> {
            override fun isDynamic(): Boolean {
                return true
            }

            override fun decode(source: PartitionData): VectorST<T> {
                val capacity = decodeUInt(source.consume()).toInt()
                return VectorST(decodeStaticArray(source, capacity, itemDecoder))
            }
        }
    }

    class VectorDT<T : DynamicType>(items: List<T>) : Vector<T>(items, true) {
        open class Decoder<T : DynamicType>(val itemDecoder: TypeDecoder<T>) : TypeDecoder<VectorDT<T>> {
            override fun isDynamic(): Boolean {
                return true
            }

            override fun decode(source: PartitionData): VectorDT<T> {
                val capacity = decodeUInt(source.consume()).toInt()
                return VectorDT(decodeDynamicArray(source, capacity, itemDecoder))
            }
        }
    }

    @Suppress("unused")
    fun encodeFunctionArguments(vararg args: Type): String {
        return encodeFixedArray(args.toList())
    }

    @Suppress("unused")
    fun encodeFixedArray(args: List<Type>): String {
        val parts = ArrayList<Pair<String, Boolean>>()

        var sizeOfStaticBlock = 0
        args.forEach {
            val encoded = it.encode()
            if (isDynamic(it)) {
                parts += Pair(encoded, true)
                // Add length of an address to static block size
                sizeOfStaticBlock += BYTES_PAD
            } else {
                parts += Pair(encoded, false)
                sizeOfStaticBlock += encoded.length / 2
            }
        }

        val staticArgsBuilder = StringBuilder()
        val dynamicArgsBuilder = StringBuilder()
        parts.forEach { (encoded, dynamic) ->
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
        return (type as? Collection<*>)?.hasDynamicItems ?: false || (type is Vector<*>)
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
