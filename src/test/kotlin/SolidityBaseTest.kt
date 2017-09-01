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
}
