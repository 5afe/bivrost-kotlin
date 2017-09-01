import model.Solidity
import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun main(args: Array<String>) {

    //SolidityTypeGenerator().generate()

    //println(SolidityBase.Int(BigInteger("127"), 8).encode())
    //println(SolidityBase.decodeInt("000000000000000000000000000000000000000000000000000000000000007f").toString(10))

    //AbiParser().generateEncoder(Files.readAllLines(Paths.get("/Users/frederico/repos/abi-decoder-kotlin/src/main/resources/EtherToken.json")).joinToString(""))
    AbiParser().generateWrapper(Files.readAllLines(Paths.get("/Users/frederico/repos/abi-decoder-kotlin/src/main/resources/test.json")).joinToString(""))

    //println(MultiSigWalletWithDailyLimit().transactions(Solidity.UInt256(BigInteger.ZERO)))

    //val t = decodeTransactions("0x000000000000000000000000a6b6f64e2e2ea9950e9c28d769aa21596257fb9e0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000044a9059cbb0000000000000000000000009bebe3b9e7a461e35775ec935336891edf856da20000000000000000000000000000000000000000000000008ac7230489e8000000000000000000000000000000000000000000000000000000000000")
    //println(t)

    //val bytes = SolidityBase.decodeBytes("0000000000000000000000000000000000000000000000000000000000000044a9059cbb000000000000000000000000ec2b21b2c22cc82effc93a1443a6df1cfb41081700000000000000000000000000000000000000000000021e19e0c9bab240000000000000000000000000000000000000000000000000000000000000")
    //println(bytes)

    /*println(
            SolidityBase.FixedBytes("dave".toByteArray()).encode()
    )

    println(
            SolidityBase.Bytes(byteArrayOf(0,1,2,3,4,5)).encode()
    )

    val bytes = SolidityBase.decodeBytes("00000000000000000000000000000000000000000000000000000000000000060001020304050000000000000000000000000000000000000000000000000000").bytes

    bytes.forEach { println(it.toString(10)) }*/

    //println(String(bytes, Charset.defaultCharset()))

    /*println(SolidityBase.encodeFunctionArguments(
            SolidityBase.Bytes("dave".toByteArray()),
            SolidityBase.Boolean(true),
            SolidityBase.ArrayOfStatic<SolidityBase.UInt256>(
                    SolidityBase.UInt256(BigInteger.valueOf(1)),
                    SolidityBase.UInt256(BigInteger.valueOf(2)),
                    SolidityBase.UInt256(BigInteger.valueOf(3)))
    ))

    println(SolidityBase.encodeFunctionArguments(
            SolidityBase.UInt256(BigInteger("123", 16)),
            SolidityBase.ArrayOfStatic(
                    SolidityBase.UInt32(BigInteger("456", 16)),
                    SolidityBase.UInt32(BigInteger("789", 16))),
            SolidityBase.FixedBytes("1234567890".toByteArray()),
            SolidityBase.Bytes("Hello, world!".toByteArray()))
    )*/

    /*println(SolidityBase.ArrayOfStatic(*array).encode())
    println(SolidityBase.Bytes(byteArrayOf(0, 1, 2, 3, 4)).encode())
    println(SolidityBase.Int8(BigInteger.valueOf(-126)).encode())
    println(SolidityBase.UInt8(BigInteger("255")).encode())
    println(SolidityBase.UInt256(BigInteger.TEN).encode())
    println(SolidityBase.Boolean(true).encode())
    println(SolidityBase.Boolean(false).encode())*/
}


class EtherToken {
    fun name(): String = "0x" + NAME_METHOD_ID

    fun approve(spender: Solidity.Address,
                value: Solidity.UInt256): String = "0x" + APPROVE_METHOD_ID + SolidityBase.encodeFunctionArguments(spender, value)

    fun totalSupply(): String = "0x" + TOTALSUPPLY_METHOD_ID

    fun transferFrom(from: Solidity.Address, to: Solidity.Address,
                     value: Solidity.UInt256): String = "0x" + TRANSFERFROM_METHOD_ID + SolidityBase.encodeFunctionArguments(from, to, value)

    fun withdraw(value: Solidity.UInt256): String = "0x" + WITHDRAW_METHOD_ID + SolidityBase.encodeFunctionArguments(value)

    fun decimals(): String = "0x" + DECIMALS_METHOD_ID

    fun balanceOf(owner: Solidity.Address): String = "0x" + BALANCEOF_METHOD_ID + SolidityBase.encodeFunctionArguments(owner)

    fun symbol(): String = "0x" + SYMBOL_METHOD_ID

    fun transfer(to: Solidity.Address,
                 value: Solidity.UInt256): String = "0x" + TRANSFER_METHOD_ID + SolidityBase.encodeFunctionArguments(to, value)

    fun deposit(): String = "0x" + DEPOSIT_METHOD_ID

    fun allowance(owner: Solidity.Address,
                  spender: Solidity.Address): String = "0x" + ALLOWANCE_METHOD_ID + SolidityBase.encodeFunctionArguments(owner, spender)

    companion object {
        const val NAME_METHOD_ID: String = "06fdde03"

        const val APPROVE_METHOD_ID: String = "095ea7b3"

        const val TOTALSUPPLY_METHOD_ID: String = "18160ddd"

        const val TRANSFERFROM_METHOD_ID: String = "23b872dd"

        const val WITHDRAW_METHOD_ID: String = "2e1a7d4d"

        const val DECIMALS_METHOD_ID: String = "313ce567"

        const val BALANCEOF_METHOD_ID: String = "70a08231"

        const val SYMBOL_METHOD_ID: String = "95d89b41"

        const val TRANSFER_METHOD_ID: String = "a9059cbb"

        const val DEPOSIT_METHOD_ID: String = "d0e30db0"

        const val ALLOWANCE_METHOD_ID: String = "dd62ed3e"
    }
}