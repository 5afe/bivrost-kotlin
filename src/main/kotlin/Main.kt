import java.math.BigInteger

fun main(args: Array<String>) {
    val array = arrayOf(
            Solidity.UInt32(BigInteger("456", 16)),
            Solidity.UInt32(BigInteger("789", 16)))


    println(Solidity.encodeFunctionArguments(
            Solidity.Bytes("dave".toByteArray()),
            Solidity.Boolean(true),
            Solidity.ArrayOfStatic<Solidity.UInt256>(
                    Solidity.UInt256(BigInteger.valueOf(1)),
                    Solidity.UInt256(BigInteger.valueOf(2)),
                    Solidity.UInt256(BigInteger.valueOf(3)))
    ))

    println(Solidity.encodeFunctionArguments(
            Solidity.UInt256(BigInteger("123", 16)),
            Solidity.ArrayOfStatic(
                    Solidity.UInt32(BigInteger("456", 16)),
                    Solidity.UInt32(BigInteger("789", 16))),
            Solidity.FixedBytes("1234567890".toByteArray()),
            Solidity.Bytes("Hello, world!".toByteArray()))
    )

    /*println(Solidity.ArrayOfStatic(*array).encode())
    println(Solidity.Bytes(byteArrayOf(0, 1, 2, 3, 4)).encode())
    println(Solidity.Int8(BigInteger.valueOf(-126)).encode())
    println(Solidity.UInt8(BigInteger("255")).encode())
    println(Solidity.UInt256(BigInteger.TEN).encode())
    println(Solidity.Boolean(true).encode())
    println(Solidity.Boolean(false).encode())*/
}
