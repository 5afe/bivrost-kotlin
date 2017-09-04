import exceptions.InvalidBitLengthException
import model.Solidity
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
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
}
