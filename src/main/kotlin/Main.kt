import java.math.BigInteger

fun main(args: Array<String>) {
    println(Solidity.Bytes(byteArrayOf(0, 1, 2, 3, 4)).encode())
    println(Solidity.Int8(BigInteger.valueOf(-126)).encode())
    println(Solidity.UInt8(BigInteger("255")).encode())
    println(Solidity.UInt256(BigInteger.TEN).encode())
    println(Solidity.Boolean(true).encode())
    println(Solidity.Boolean(false).encode())
}
