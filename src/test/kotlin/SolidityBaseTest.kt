import exceptions.InvalidBitLengthException
import model.Solidity
import org.junit.Assert.assertEquals
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

    @Test(expected = InvalidBitLengthException::class)
    fun testUInt8BitOverflow() {
        assertEquals("00000000000000000000000000000000000000000000000000000000000000ff", Solidity.UInt8(BigInteger("255")).encode())
        Solidity.UInt8(BigInteger("256"))
    }
}