import utils.hexToByteArray
import utils.padStartMultiple
import utils.toHex
import java.math.BigInteger
import java.util.*

object SolidityBase {
    const val BYTES_PAD = 32
    const val BITS_PAD = BYTES_PAD * 8
    const val PADDED_HEX_LENGTH = BYTES_PAD * 2

    interface Type {
        fun encode(): String
    }

    interface StaticType : Type

    interface DynamicType : Type {
        data class Parts(val static: String, val dynamic: String)

        fun encodeParts(): Parts
    }

    open class UInt(private val value: BigInteger, bitLength: kotlin.Int) : StaticType {
        init {
            if (bitLength % 8 != 0 || value.bitLength() > bitLength || value.signum() == -1) throw Exception()
        }

        override fun encode(): String {
            val string = value.toString(16)
            return string.padStartMultiple(PADDED_HEX_LENGTH, '0')
        }
    }

    open class Int(private val value: BigInteger, private val bitLength: kotlin.Int) : StaticType {
        init {
            if (bitLength % 8 != 0) throw Exception()
            val min = BigInteger.valueOf(2).pow(bitLength - 1).negate()
            val max = BigInteger.valueOf(2).pow(bitLength - 1) - BigInteger.ONE

            if (value < min || value > max) throw Exception()
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

    open class StaticBytes(val byteArray: ByteArray, nBytes: kotlin.Int) : StaticType {
        init {
            if (byteArray.size > nBytes) throw Exception()
        }

        override fun encode(): String {
            return byteArray.toHex().padEnd(PADDED_HEX_LENGTH, '0')
        }
    }

    open class ArrayOfStatic<T : StaticType>(private vararg val items: T) : DynamicType {
        init {
            if (BigInteger(items.size.toString(10)) > BigInteger.valueOf(2).pow(BITS_PAD)) throw Exception()
        }

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

    fun partitionData(data: String): List<String> {
        var noPrefix = data.removePrefix("0x")
        if (noPrefix.isEmpty() || noPrefix.length.rem(PADDED_HEX_LENGTH) != 0) throw Exception()
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
            else -> throw Exception()
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

    fun decodeStaticBytes(data: String): ByteArray {
        return BigInteger(data, 16).toByteArray()
    }

    fun decodeBytes(data: String): ByteArray {
        val params = partitionData(data)
        if (params === null || params.isEmpty()) throw Exception()
        val contentSize = BigInteger(params[0], 16).intValueExact() * 2
        if (contentSize == 0) return byteArrayOf(0)
        val contents = params.subList(1, params.size).joinToString("")
        return contents.substring(0, contentSize).hexToByteArray()
    }

    fun <T : Any> decodeArray(data: String, itemDecoder: (String) -> T): List<T> {
        val params = partitionData(data)
        if (params == null || params.isEmpty()) throw Exception()
        val contentSize = BigInteger(params[0]).intValueExact() * 2
        if (contentSize == 0) return emptyList()
        return (1 until params.size).map { itemDecoder.invoke(params[it]) }.toList()
    }
}
