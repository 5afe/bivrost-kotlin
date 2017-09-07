package pm.gnosis.model

import org.junit.Assert.*
import org.junit.Test
import pm.gnosis.exceptions.InvalidBitLengthException
import java.math.BigInteger

class SolidityBaseTest {
    @Test
    fun testUIntEncoding() {
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000",
                Solidity.UInt256(BigInteger.ZERO).encode())

        assertEquals("0000000000000000000000000000000000000000000000000000000000000001",
                Solidity.UInt256(BigInteger.ONE).encode())

        //Max unsigned integer
        assertEquals("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
                Solidity.UInt256(BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935")).encode())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testNegativeUIntThrowsException() {
        Solidity.UInt256(BigInteger.valueOf(-1L))
    }

    @Test
    fun testUIntBitOverflow() {
        (8..256 step 8).forEach {
            SolidityBase.UInt(BigInteger.valueOf(2).pow(it).minus(BigInteger.ONE), it)
            try {
                SolidityBase.UInt(BigInteger.valueOf(2).pow(it), it)
                fail("Expected InvalidBitLengthException")
            } catch (e: InvalidBitLengthException) {
            }
        }
    }

    @Test
    fun testUIntDecoding() {
        assertEquals(BigInteger.ZERO,
                SolidityBase.decodeUInt("0000000000000000000000000000000000000000000000000000000000000000"))

        assertEquals(BigInteger.ONE,
                SolidityBase.decodeUInt("0000000000000000000000000000000000000000000000000000000000000001"))

        assertEquals(BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935"),
                SolidityBase.decodeUInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"))
    }

    @Test
    fun testBoolEncoding() {
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bool(false).encode())

        assertEquals("0000000000000000000000000000000000000000000000000000000000000001",
                Solidity.Bool(true).encode())
    }

    @Test
    fun testBoolDecoding() {
        assertEquals(false,
                SolidityBase.decodeBool("0000000000000000000000000000000000000000000000000000000000000000"))

        assertEquals(true,
                SolidityBase.decodeBool("0000000000000000000000000000000000000000000000000000000000000001"))
    }

    @Test
    fun testIntEncoding() {
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000",
                Solidity.Int8(BigInteger.ZERO).encode())

        assertEquals("0000000000000000000000000000000000000000000000000000000000000001",
                Solidity.Int8(BigInteger.ONE).encode())

        assertEquals("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
                Solidity.Int8(BigInteger.valueOf(-1)).encode())

        assertEquals("000000000000000000000000000000000000000000000000000000000000007f",
                Solidity.Int8(BigInteger.valueOf(127)).encode())

        assertEquals("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80",
                Solidity.Int8(BigInteger.valueOf(-128)).encode())
    }

    @Test
    fun testIntRange() {
        (8..256 step 8).forEach {
            val min = BigInteger.valueOf(2).pow(it - 1).negate()
            val max = BigInteger.valueOf(2).pow(it - 1) - BigInteger.ONE
            SolidityBase.Int(min, it)
            SolidityBase.Int(max, it)
            try {
                SolidityBase.UInt(BigInteger.valueOf(2).pow(it), it)
                fail("Expected InvalidBitLengthException")
            } catch (e: InvalidBitLengthException) {
            }

            try {
                SolidityBase.UInt(BigInteger.valueOf(2).pow(it), it)
                fail("Expected InvalidBitLengthException")
            } catch (e: InvalidBitLengthException) {
            }
        }
    }

    @Test
    fun testIntDecoding() {
        assertEquals(BigInteger.ZERO,
                SolidityBase.decodeInt("0000000000000000000000000000000000000000000000000000000000000000"))

        assertEquals(BigInteger.ONE,
                SolidityBase.decodeInt("0000000000000000000000000000000000000000000000000000000000000001"))

        assertEquals(BigInteger.valueOf(-1),
                SolidityBase.decodeInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"))

        assertEquals(BigInteger.valueOf(127),
                SolidityBase.decodeInt("000000000000000000000000000000000000000000000000000000000000007f"))

        assertEquals(BigInteger.valueOf(-128),
                SolidityBase.decodeInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80"))
    }

    @Test
    fun testStaticBytesEncoding() {
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes1(byteArrayOf(0)).encode())

        assertEquals("0001000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes2(byteArrayOf(0, 1)).encode())

        assertEquals("0001020000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes3(byteArrayOf(0, 1, 2)).encode())

        assertEquals("6461766500000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes4("dave".toByteArray()).encode())
    }

    @Test
    fun testStaticBytesDecoding() {
        assertArrayEquals(byteArrayOf(0), SolidityBase.decodeStaticBytes("0000000000000000000000000000000000000000000000000000000000000000", 1))

        assertArrayEquals(byteArrayOf(0, 1), SolidityBase.decodeStaticBytes("0001000000000000000000000000000000000000000000000000000000000000", 2))

        assertArrayEquals(byteArrayOf(0, 1, 2), SolidityBase.decodeStaticBytes("0001020000000000000000000000000000000000000000000000000000000000", 3))

        assertArrayEquals("dave".toByteArray(), SolidityBase.decodeStaticBytes("6461766500000000000000000000000000000000000000000000000000000000", 4))

        assertArrayEquals("dave".toByteArray(), Solidity.Bytes4.decode("6461766500000000000000000000000000000000000000000000000000000000").byteArray)
    }

    @Test
    fun testStaticBytesRange() {
        (1..32).forEach {
            val bytes = ByteArray(it, { it.toByte() })
            SolidityBase.StaticBytes(bytes, it)
            try {
                val oversizedBytes = ByteArray(it + 1, { it.toByte() })
                SolidityBase.StaticBytes(oversizedBytes, it)
                fail("Expected IllegalArgumentException")
            } catch (ignored: IllegalArgumentException) {
            }
        }
    }

    @Test
    fun testArrayOfStaticEncoding() {
        assertEquals("000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789",
                Solidity.ArrayOfUInt32(listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16)))).encode())

        assertEquals("000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000",
                Solidity.ArrayOfBool(listOf(Solidity.Bool(true), Solidity.Bool(false))).encode())

        assertEquals("0000000000000000000000000000000000000000000000000000000000000000",
                SolidityBase.ArrayOfStatic<Solidity.Bool>().encode())
    }

    @Test
    fun testArrayOfStaticDecoding() {
        assertEquals(listOf(BigInteger("456", 16), BigInteger("789", 16)),
                SolidityBase.decodeArray("000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789", SolidityBase::decodeUInt))

        assertEquals(listOf(true, false),
                SolidityBase.decodeArray("000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000", SolidityBase::decodeBool))

        assertEquals(emptyList<SolidityBase.StaticType>(),
                SolidityBase.decodeArray("0000000000000000000000000000000000000000000000000000000000000000", SolidityBase::decodeBool))
    }

    @Test
    fun testDynamicBytesEncoding() {
        assertEquals("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000",
                Solidity.Bytes("Hello, world!".toByteArray()).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes(byteArrayOf(0)).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000020001000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes(byteArrayOf(0, 1)).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000030001020000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes(byteArrayOf(0, 1, 2)).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000046461766500000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes("dave".toByteArray()).encode())
    }

    @Test
    fun testDynamicBytesDecoding() {
        assertArrayEquals("Hello, world!".toByteArray(),
                SolidityBase.decodeBytes("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000"))

        assertArrayEquals(byteArrayOf(0),
                SolidityBase.decodeBytes("00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000"))

        assertArrayEquals(byteArrayOf(0, 1),
                SolidityBase.decodeBytes("00000000000000000000000000000000000000000000000000000000000000020001000000000000000000000000000000000000000000000000000000000000"))

        assertArrayEquals(byteArrayOf(0, 1, 2),
                SolidityBase.decodeBytes("00000000000000000000000000000000000000000000000000000000000000030001020000000000000000000000000000000000000000000000000000000000"))

        assertArrayEquals("dave".toByteArray(),
                SolidityBase.decodeBytes("00000000000000000000000000000000000000000000000000000000000000046461766500000000000000000000000000000000000000000000000000000000"))
    }

    @Test
    fun testStringDecoding() {
        assertEquals("Hello, world!",
                SolidityBase.decodeString("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000"))
    }

    @Test
    fun testStringEncoding() {
        assertEquals("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000",
                Solidity.String("Hello, world!").encode())
    }
}
