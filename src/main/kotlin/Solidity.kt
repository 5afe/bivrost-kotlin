import java.math.BigInteger
import java.util.*

object Solidity {
    const val BYTES_PAD = 32
    const val BITS_PAD = BYTES_PAD * 8
    const val PADDED_HEX_LENGTH = BYTES_PAD * 2

    interface Type {
        fun encode(): String
    }

    interface DynamicType : Type {
        data class Parts(val static: String, val dynamic: String)

        fun encodeParts(): Parts
    }

    interface StaticType : Type

    abstract class UInt(private val value: BigInteger, bitLength: kotlin.Int) : StaticType {
        init {
            if (bitLength % 8 != 0 || value.bitLength() > bitLength || value.signum() == -1) throw Exception()
        }

        override fun encode(): String {
            val string = value.toString(16)
            return string.padStartMultiple(PADDED_HEX_LENGTH, '0')
        }

    }

    abstract class Int(private val value: BigInteger, private val bitLength: kotlin.Int) : StaticType {
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

    class FixedBytes(private val byteArray: ByteArray) : StaticType {
        init {
            if (byteArray.size > BYTES_PAD) throw Exception()
        }

        override fun encode(): String {
            return byteArray.toHex().padEnd(PADDED_HEX_LENGTH, '0')
        }
    }

    class Bytes(private val bytes: ByteArray) : DynamicType {
        init {
            if (BigInteger(bytes.size.toString(10)) > BigInteger.valueOf(2).pow(BITS_PAD)) throw Exception()
        }

        override fun encode(): String {
            val parts = encodeParts()
            return parts.static + parts.dynamic
        }

        override fun encodeParts(): DynamicType.Parts {
            val length = bytes.size.toString(16).padStart(PADDED_HEX_LENGTH, '0')
            val contents = bytes.toHex().padEndMultiple(PADDED_HEX_LENGTH, '0')
            return DynamicType.Parts(length, contents)
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


    class UInt256(value: BigInteger) : UInt(value, 256)
    open class UInt160(value: BigInteger) : UInt(value, 160)
    open class UInt8(value: BigInteger) : UInt(value, 8)
    class UInt32(value: BigInteger) : UInt(value, 32)

    class Address(value: BigInteger) : UInt160(value)
    class Boolean(value: kotlin.Boolean) : UInt8(if (value) BigInteger.ONE else BigInteger.ZERO)


    class Int8(value: BigInteger) : Int(value, 8)
    data class Int256(val value: BigInteger)

    fun partitionFunctionResult(data: String): List<String>? {
        var noPrefix = data.removePrefix("0x")
        if (noPrefix.isEmpty() || noPrefix.length.rem(64) != 0) return null
        val properties = arrayListOf<String>()

        while (noPrefix.length >= 64) {
            properties.add(noPrefix.subSequence(0, 64).toString())
            noPrefix = noPrefix.removeRange(0..63)
        }
        return properties
    }

    fun decodeUInt(data: String, bitLength: kotlin.Int): UInt {
        val value = BigInteger(data, 16)
        return when(bitLength) {
            8 -> UInt8(value)
            32 -> UInt32(value)
            160 -> UInt160(value)
            else -> throw Exception()
        }
    }
}
