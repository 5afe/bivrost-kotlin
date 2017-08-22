import java.lang.Exception
import java.math.BigDecimal
import java.math.BigInteger

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

    abstract class UInt(private val value: BigInteger, private val bitLength: kotlin.Int) : Type {
        init {
            if (bitLength % 8 != 0 || value.bitLength() > bitLength || value.signum() == -1) throw Exception()
        }

        override fun encode(): String {
            val string = value.toString(16)
            return string.padStartMultiple(PADDED_HEX_LENGTH, '0')
        }

    }

    abstract class Int(private val value: BigInteger, private val bitLength: kotlin.Int) : Type {
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

    class FixedBytes(private val byteArray: ByteArray) : Type {
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

    class UInt256(value: BigInteger) : UInt(value, 256)
    open class UInt160(value: BigInteger) : UInt(value, 160)
    open class UInt8(value: BigInteger) : UInt(value, 8)

    class Address(value: BigInteger) : UInt160(value)
    class Boolean(value: kotlin.Boolean) : UInt8(if (value) BigInteger.ONE else BigInteger.ZERO)


    class Int8(value: BigInteger) : Int(value, 8)
    data class Int256(val value: BigInteger)
}
