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

    abstract class UInt(private val value: BigInteger, bitLength: kotlin.Int) : StaticType {
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
    }

    abstract class Int(private val value: BigInteger, private val bitLength: kotlin.Int) : StaticType {
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
    }

    abstract class StaticBytes(val byteArray: ByteArray, nBytes: kotlin.Int) : StaticType {
        init {
            if (byteArray.size > nBytes) throw IllegalArgumentException("Byte array has ${byteArray.size} bytes. It should have no more than $nBytes bytes.")
        }

        override fun encode(): String {
            return byteArray.toHex().padEnd(PADDED_HEX_LENGTH, '0')
        }
    }

    // Solidity encodes length as a 256 bit value which can be greater than Integer.MAX_VALUE
    // A work around could be receiving the size of the array as a BigInteger and the items
    // as a List (no theoretical limit but the size of the list can be at max Integer.MAX_VALUE)
    // Another solution can be receiving Collections up to Integer.MAX_VALUE and then merge them here
    abstract class ArrayOfStatic<T : StaticType>(private val items: List<T>) : DynamicType {

        constructor(vararg items: T) : this(items.toList())

        override fun encode(): String {
            val parts = encodeParts()
            return parts.static + parts.dynamic
        }

        override fun encodeParts(): DynamicType.Parts {
            val length = items.size.toString(16).padStart(PADDED_HEX_LENGTH, '0')
            val stringBuilder = StringBuilder()
            items.forEach {
                stringBuilder.append(it.encode())
            }
            return DynamicType.Parts(length, stringBuilder.toString())
        }
    }

    @Suppress("unused")
    fun encodeFunctionArguments(vararg args: Type): String {
        val sizeOfStaticBlock = args.size * BYTES_PAD
        val staticArgsBuilder = StringBuilder()
        val dynamicArgsBuilder = StringBuilder()
        val locations = ArrayDeque<kotlin.Int>()
        args.filter { it is DynamicType }.forEach {
            locations.add(sizeOfStaticBlock + dynamicArgsBuilder.length / 2)
            dynamicArgsBuilder.append(it.encode())
        }
        args.forEach {
            if (it is DynamicType) {
                staticArgsBuilder.append(locations.removeFirst().toString(16).padStart(PADDED_HEX_LENGTH, '0'))
            } else {
                staticArgsBuilder.append(it.encode())
            }
        }

        return staticArgsBuilder.toString() + dynamicArgsBuilder.toString()
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

    fun decodeBytes(data: String): ByteArray {
        val params = partitionData(data)
        val contentSize = BigDecimal(BigInteger(params[0], 16)).intValueExact() * 2
        if (contentSize == 0) return kotlin.ByteArray(0)
        val contents = params.subList(1, params.size).joinToString("")
        return contents.substring(0, contentSize).hexToByteArray()
    }

    fun decodeString(data: String) =
            decodeBytes(data).toString(Charset.forName("UTF-8"))

    fun <T : Any> decodeArray(data: String, itemDecoder: (String) -> T): List<T> {
        val params = partitionData(data)
        val contentSize = BigDecimal(BigInteger(params[0])).intValueExact() * 2
        if (contentSize != params.size - 1) throw IllegalArgumentException("Number of items is different from the actual array size")
        if (contentSize == 0) return emptyList()
        return (1 until params.size).map { itemDecoder.invoke(params[it]) }.toList()
    }
}
