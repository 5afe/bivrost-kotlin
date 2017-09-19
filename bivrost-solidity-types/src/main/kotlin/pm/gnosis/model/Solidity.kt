package pm.gnosis.model

import pm.gnosis.utils.padEndMultiple
import pm.gnosis.utils.toHex
import java.lang.Exception
import java.math.BigInteger

/**
 * Generated code. Do not modify
 */
object Solidity {
    val aliases: Map<kotlin.String, kotlin.String> = mapOf(
            "int" to "int256",
            "uint" to "uint256",
            "byte" to "bytes1")

    val types: Map<kotlin.String, kotlin.String> = mapOf(
            "uint8" to "pm.gnosis.model.Solidity.UInt8",
            "uint16" to "pm.gnosis.model.Solidity.UInt16",
            "uint24" to "pm.gnosis.model.Solidity.UInt24",
            "uint32" to "pm.gnosis.model.Solidity.UInt32",
            "uint40" to "pm.gnosis.model.Solidity.UInt40",
            "uint48" to "pm.gnosis.model.Solidity.UInt48",
            "uint56" to "pm.gnosis.model.Solidity.UInt56",
            "uint64" to "pm.gnosis.model.Solidity.UInt64",
            "uint72" to "pm.gnosis.model.Solidity.UInt72",
            "uint80" to "pm.gnosis.model.Solidity.UInt80",
            "uint88" to "pm.gnosis.model.Solidity.UInt88",
            "uint96" to "pm.gnosis.model.Solidity.UInt96",
            "uint104" to "pm.gnosis.model.Solidity.UInt104",
            "uint112" to "pm.gnosis.model.Solidity.UInt112",
            "uint120" to "pm.gnosis.model.Solidity.UInt120",
            "uint128" to "pm.gnosis.model.Solidity.UInt128",
            "uint136" to "pm.gnosis.model.Solidity.UInt136",
            "uint144" to "pm.gnosis.model.Solidity.UInt144",
            "uint152" to "pm.gnosis.model.Solidity.UInt152",
            "uint160" to "pm.gnosis.model.Solidity.UInt160",
            "uint168" to "pm.gnosis.model.Solidity.UInt168",
            "uint176" to "pm.gnosis.model.Solidity.UInt176",
            "uint184" to "pm.gnosis.model.Solidity.UInt184",
            "uint192" to "pm.gnosis.model.Solidity.UInt192",
            "uint200" to "pm.gnosis.model.Solidity.UInt200",
            "uint208" to "pm.gnosis.model.Solidity.UInt208",
            "uint216" to "pm.gnosis.model.Solidity.UInt216",
            "uint224" to "pm.gnosis.model.Solidity.UInt224",
            "uint232" to "pm.gnosis.model.Solidity.UInt232",
            "uint240" to "pm.gnosis.model.Solidity.UInt240",
            "uint248" to "pm.gnosis.model.Solidity.UInt248",
            "uint256" to "pm.gnosis.model.Solidity.UInt256",
            "int8" to "pm.gnosis.model.Solidity.Int8",
            "int16" to "pm.gnosis.model.Solidity.Int16",
            "int24" to "pm.gnosis.model.Solidity.Int24",
            "int32" to "pm.gnosis.model.Solidity.Int32",
            "int40" to "pm.gnosis.model.Solidity.Int40",
            "int48" to "pm.gnosis.model.Solidity.Int48",
            "int56" to "pm.gnosis.model.Solidity.Int56",
            "int64" to "pm.gnosis.model.Solidity.Int64",
            "int72" to "pm.gnosis.model.Solidity.Int72",
            "int80" to "pm.gnosis.model.Solidity.Int80",
            "int88" to "pm.gnosis.model.Solidity.Int88",
            "int96" to "pm.gnosis.model.Solidity.Int96",
            "int104" to "pm.gnosis.model.Solidity.Int104",
            "int112" to "pm.gnosis.model.Solidity.Int112",
            "int120" to "pm.gnosis.model.Solidity.Int120",
            "int128" to "pm.gnosis.model.Solidity.Int128",
            "int136" to "pm.gnosis.model.Solidity.Int136",
            "int144" to "pm.gnosis.model.Solidity.Int144",
            "int152" to "pm.gnosis.model.Solidity.Int152",
            "int160" to "pm.gnosis.model.Solidity.Int160",
            "int168" to "pm.gnosis.model.Solidity.Int168",
            "int176" to "pm.gnosis.model.Solidity.Int176",
            "int184" to "pm.gnosis.model.Solidity.Int184",
            "int192" to "pm.gnosis.model.Solidity.Int192",
            "int200" to "pm.gnosis.model.Solidity.Int200",
            "int208" to "pm.gnosis.model.Solidity.Int208",
            "int216" to "pm.gnosis.model.Solidity.Int216",
            "int224" to "pm.gnosis.model.Solidity.Int224",
            "int232" to "pm.gnosis.model.Solidity.Int232",
            "int240" to "pm.gnosis.model.Solidity.Int240",
            "int248" to "pm.gnosis.model.Solidity.Int248",
            "int256" to "pm.gnosis.model.Solidity.Int256",
            "bytes1" to "pm.gnosis.model.Solidity.Bytes1",
            "bytes2" to "pm.gnosis.model.Solidity.Bytes2",
            "bytes3" to "pm.gnosis.model.Solidity.Bytes3",
            "bytes4" to "pm.gnosis.model.Solidity.Bytes4",
            "bytes5" to "pm.gnosis.model.Solidity.Bytes5",
            "bytes6" to "pm.gnosis.model.Solidity.Bytes6",
            "bytes7" to "pm.gnosis.model.Solidity.Bytes7",
            "bytes8" to "pm.gnosis.model.Solidity.Bytes8",
            "bytes9" to "pm.gnosis.model.Solidity.Bytes9",
            "bytes10" to "pm.gnosis.model.Solidity.Bytes10",
            "bytes11" to "pm.gnosis.model.Solidity.Bytes11",
            "bytes12" to "pm.gnosis.model.Solidity.Bytes12",
            "bytes13" to "pm.gnosis.model.Solidity.Bytes13",
            "bytes14" to "pm.gnosis.model.Solidity.Bytes14",
            "bytes15" to "pm.gnosis.model.Solidity.Bytes15",
            "bytes16" to "pm.gnosis.model.Solidity.Bytes16",
            "bytes17" to "pm.gnosis.model.Solidity.Bytes17",
            "bytes18" to "pm.gnosis.model.Solidity.Bytes18",
            "bytes19" to "pm.gnosis.model.Solidity.Bytes19",
            "bytes20" to "pm.gnosis.model.Solidity.Bytes20",
            "bytes21" to "pm.gnosis.model.Solidity.Bytes21",
            "bytes22" to "pm.gnosis.model.Solidity.Bytes22",
            "bytes23" to "pm.gnosis.model.Solidity.Bytes23",
            "bytes24" to "pm.gnosis.model.Solidity.Bytes24",
            "bytes25" to "pm.gnosis.model.Solidity.Bytes25",
            "bytes26" to "pm.gnosis.model.Solidity.Bytes26",
            "bytes27" to "pm.gnosis.model.Solidity.Bytes27",
            "bytes28" to "pm.gnosis.model.Solidity.Bytes28",
            "bytes29" to "pm.gnosis.model.Solidity.Bytes29",
            "bytes30" to "pm.gnosis.model.Solidity.Bytes30",
            "bytes31" to "pm.gnosis.model.Solidity.Bytes31",
            "bytes32" to "pm.gnosis.model.Solidity.Bytes32",
            "address" to "pm.gnosis.model.Solidity.Address",
            "bool" to "pm.gnosis.model.Solidity.Bool",
            "bytes" to "pm.gnosis.model.Solidity.Bytes",
            "string" to "pm.gnosis.model.Solidity.String")

    class UInt8(val value: BigInteger) : SolidityBase.UIntBase(value, 8) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt8> = SolidityBase.UIntBase.Decoder<UInt8>({ UInt8(it) })
        }
    }

    class UInt16(val value: BigInteger) : SolidityBase.UIntBase(value, 16) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt16> = SolidityBase.UIntBase.Decoder<UInt16>({ UInt16(it) })
        }
    }

    class UInt24(val value: BigInteger) : SolidityBase.UIntBase(value, 24) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt24> = SolidityBase.UIntBase.Decoder<UInt24>({ UInt24(it) })
        }
    }

    class UInt32(val value: BigInteger) : SolidityBase.UIntBase(value, 32) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt32> = SolidityBase.UIntBase.Decoder<UInt32>({ UInt32(it) })
        }
    }

    class UInt40(val value: BigInteger) : SolidityBase.UIntBase(value, 40) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt40> = SolidityBase.UIntBase.Decoder<UInt40>({ UInt40(it) })
        }
    }

    class UInt48(val value: BigInteger) : SolidityBase.UIntBase(value, 48) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt48> = SolidityBase.UIntBase.Decoder<UInt48>({ UInt48(it) })
        }
    }

    class UInt56(val value: BigInteger) : SolidityBase.UIntBase(value, 56) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt56> = SolidityBase.UIntBase.Decoder<UInt56>({ UInt56(it) })
        }
    }

    class UInt64(val value: BigInteger) : SolidityBase.UIntBase(value, 64) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt64> = SolidityBase.UIntBase.Decoder<UInt64>({ UInt64(it) })
        }
    }

    class UInt72(val value: BigInteger) : SolidityBase.UIntBase(value, 72) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt72> = SolidityBase.UIntBase.Decoder<UInt72>({ UInt72(it) })
        }
    }

    class UInt80(val value: BigInteger) : SolidityBase.UIntBase(value, 80) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt80> = SolidityBase.UIntBase.Decoder<UInt80>({ UInt80(it) })
        }
    }

    class UInt88(val value: BigInteger) : SolidityBase.UIntBase(value, 88) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt88> = SolidityBase.UIntBase.Decoder<UInt88>({ UInt88(it) })
        }
    }

    class UInt96(val value: BigInteger) : SolidityBase.UIntBase(value, 96) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt96> = SolidityBase.UIntBase.Decoder<UInt96>({ UInt96(it) })
        }
    }

    class UInt104(val value: BigInteger) : SolidityBase.UIntBase(value, 104) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt104> = SolidityBase.UIntBase.Decoder<UInt104>({ UInt104(it) })
        }
    }

    class UInt112(val value: BigInteger) : SolidityBase.UIntBase(value, 112) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt112> = SolidityBase.UIntBase.Decoder<UInt112>({ UInt112(it) })
        }
    }

    class UInt120(val value: BigInteger) : SolidityBase.UIntBase(value, 120) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt120> = SolidityBase.UIntBase.Decoder<UInt120>({ UInt120(it) })
        }
    }

    class UInt128(val value: BigInteger) : SolidityBase.UIntBase(value, 128) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt128> = SolidityBase.UIntBase.Decoder<UInt128>({ UInt128(it) })
        }
    }

    class UInt136(val value: BigInteger) : SolidityBase.UIntBase(value, 136) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt136> = SolidityBase.UIntBase.Decoder<UInt136>({ UInt136(it) })
        }
    }

    class UInt144(val value: BigInteger) : SolidityBase.UIntBase(value, 144) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt144> = SolidityBase.UIntBase.Decoder<UInt144>({ UInt144(it) })
        }
    }

    class UInt152(val value: BigInteger) : SolidityBase.UIntBase(value, 152) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt152> = SolidityBase.UIntBase.Decoder<UInt152>({ UInt152(it) })
        }
    }

    class UInt160(val value: BigInteger) : SolidityBase.UIntBase(value, 160) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt160> = SolidityBase.UIntBase.Decoder<UInt160>({ UInt160(it) })
        }
    }

    class UInt168(val value: BigInteger) : SolidityBase.UIntBase(value, 168) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt168> = SolidityBase.UIntBase.Decoder<UInt168>({ UInt168(it) })
        }
    }

    class UInt176(val value: BigInteger) : SolidityBase.UIntBase(value, 176) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt176> = SolidityBase.UIntBase.Decoder<UInt176>({ UInt176(it) })
        }
    }

    class UInt184(val value: BigInteger) : SolidityBase.UIntBase(value, 184) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt184> = SolidityBase.UIntBase.Decoder<UInt184>({ UInt184(it) })
        }
    }

    class UInt192(val value: BigInteger) : SolidityBase.UIntBase(value, 192) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt192> = SolidityBase.UIntBase.Decoder<UInt192>({ UInt192(it) })
        }
    }

    class UInt200(val value: BigInteger) : SolidityBase.UIntBase(value, 200) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt200> = SolidityBase.UIntBase.Decoder<UInt200>({ UInt200(it) })
        }
    }

    class UInt208(val value: BigInteger) : SolidityBase.UIntBase(value, 208) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt208> = SolidityBase.UIntBase.Decoder<UInt208>({ UInt208(it) })
        }
    }

    class UInt216(val value: BigInteger) : SolidityBase.UIntBase(value, 216) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt216> = SolidityBase.UIntBase.Decoder<UInt216>({ UInt216(it) })
        }
    }

    class UInt224(val value: BigInteger) : SolidityBase.UIntBase(value, 224) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt224> = SolidityBase.UIntBase.Decoder<UInt224>({ UInt224(it) })
        }
    }

    class UInt232(val value: BigInteger) : SolidityBase.UIntBase(value, 232) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt232> = SolidityBase.UIntBase.Decoder<UInt232>({ UInt232(it) })
        }
    }

    class UInt240(val value: BigInteger) : SolidityBase.UIntBase(value, 240) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt240> = SolidityBase.UIntBase.Decoder<UInt240>({ UInt240(it) })
        }
    }

    class UInt248(val value: BigInteger) : SolidityBase.UIntBase(value, 248) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt248> = SolidityBase.UIntBase.Decoder<UInt248>({ UInt248(it) })
        }
    }

    class UInt256(val value: BigInteger) : SolidityBase.UIntBase(value, 256) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<UInt256> = SolidityBase.UIntBase.Decoder<UInt256>({ UInt256(it) })
        }
    }

    class Int8(val value: BigInteger) : SolidityBase.IntBase(value, 8) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int8> = SolidityBase.IntBase.Decoder<Int8>({ Int8(it) })
        }
    }

    class Int16(val value: BigInteger) : SolidityBase.IntBase(value, 16) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int16> = SolidityBase.IntBase.Decoder<Int16>({ Int16(it) })
        }
    }

    class Int24(val value: BigInteger) : SolidityBase.IntBase(value, 24) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int24> = SolidityBase.IntBase.Decoder<Int24>({ Int24(it) })
        }
    }

    class Int32(val value: BigInteger) : SolidityBase.IntBase(value, 32) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int32> = SolidityBase.IntBase.Decoder<Int32>({ Int32(it) })
        }
    }

    class Int40(val value: BigInteger) : SolidityBase.IntBase(value, 40) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int40> = SolidityBase.IntBase.Decoder<Int40>({ Int40(it) })
        }
    }

    class Int48(val value: BigInteger) : SolidityBase.IntBase(value, 48) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int48> = SolidityBase.IntBase.Decoder<Int48>({ Int48(it) })
        }
    }

    class Int56(val value: BigInteger) : SolidityBase.IntBase(value, 56) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int56> = SolidityBase.IntBase.Decoder<Int56>({ Int56(it) })
        }
    }

    class Int64(val value: BigInteger) : SolidityBase.IntBase(value, 64) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int64> = SolidityBase.IntBase.Decoder<Int64>({ Int64(it) })
        }
    }

    class Int72(val value: BigInteger) : SolidityBase.IntBase(value, 72) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int72> = SolidityBase.IntBase.Decoder<Int72>({ Int72(it) })
        }
    }

    class Int80(val value: BigInteger) : SolidityBase.IntBase(value, 80) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int80> = SolidityBase.IntBase.Decoder<Int80>({ Int80(it) })
        }
    }

    class Int88(val value: BigInteger) : SolidityBase.IntBase(value, 88) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int88> = SolidityBase.IntBase.Decoder<Int88>({ Int88(it) })
        }
    }

    class Int96(val value: BigInteger) : SolidityBase.IntBase(value, 96) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int96> = SolidityBase.IntBase.Decoder<Int96>({ Int96(it) })
        }
    }

    class Int104(val value: BigInteger) : SolidityBase.IntBase(value, 104) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int104> = SolidityBase.IntBase.Decoder<Int104>({ Int104(it) })
        }
    }

    class Int112(val value: BigInteger) : SolidityBase.IntBase(value, 112) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int112> = SolidityBase.IntBase.Decoder<Int112>({ Int112(it) })
        }
    }

    class Int120(val value: BigInteger) : SolidityBase.IntBase(value, 120) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int120> = SolidityBase.IntBase.Decoder<Int120>({ Int120(it) })
        }
    }

    class Int128(val value: BigInteger) : SolidityBase.IntBase(value, 128) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int128> = SolidityBase.IntBase.Decoder<Int128>({ Int128(it) })
        }
    }

    class Int136(val value: BigInteger) : SolidityBase.IntBase(value, 136) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int136> = SolidityBase.IntBase.Decoder<Int136>({ Int136(it) })
        }
    }

    class Int144(val value: BigInteger) : SolidityBase.IntBase(value, 144) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int144> = SolidityBase.IntBase.Decoder<Int144>({ Int144(it) })
        }
    }

    class Int152(val value: BigInteger) : SolidityBase.IntBase(value, 152) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int152> = SolidityBase.IntBase.Decoder<Int152>({ Int152(it) })
        }
    }

    class Int160(val value: BigInteger) : SolidityBase.IntBase(value, 160) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int160> = SolidityBase.IntBase.Decoder<Int160>({ Int160(it) })
        }
    }

    class Int168(val value: BigInteger) : SolidityBase.IntBase(value, 168) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int168> = SolidityBase.IntBase.Decoder<Int168>({ Int168(it) })
        }
    }

    class Int176(val value: BigInteger) : SolidityBase.IntBase(value, 176) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int176> = SolidityBase.IntBase.Decoder<Int176>({ Int176(it) })
        }
    }

    class Int184(val value: BigInteger) : SolidityBase.IntBase(value, 184) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int184> = SolidityBase.IntBase.Decoder<Int184>({ Int184(it) })
        }
    }

    class Int192(val value: BigInteger) : SolidityBase.IntBase(value, 192) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int192> = SolidityBase.IntBase.Decoder<Int192>({ Int192(it) })
        }
    }

    class Int200(val value: BigInteger) : SolidityBase.IntBase(value, 200) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int200> = SolidityBase.IntBase.Decoder<Int200>({ Int200(it) })
        }
    }

    class Int208(val value: BigInteger) : SolidityBase.IntBase(value, 208) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int208> = SolidityBase.IntBase.Decoder<Int208>({ Int208(it) })
        }
    }

    class Int216(val value: BigInteger) : SolidityBase.IntBase(value, 216) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int216> = SolidityBase.IntBase.Decoder<Int216>({ Int216(it) })
        }
    }

    class Int224(val value: BigInteger) : SolidityBase.IntBase(value, 224) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int224> = SolidityBase.IntBase.Decoder<Int224>({ Int224(it) })
        }
    }

    class Int232(val value: BigInteger) : SolidityBase.IntBase(value, 232) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int232> = SolidityBase.IntBase.Decoder<Int232>({ Int232(it) })
        }
    }

    class Int240(val value: BigInteger) : SolidityBase.IntBase(value, 240) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int240> = SolidityBase.IntBase.Decoder<Int240>({ Int240(it) })
        }
    }

    class Int248(val value: BigInteger) : SolidityBase.IntBase(value, 248) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int248> = SolidityBase.IntBase.Decoder<Int248>({ Int248(it) })
        }
    }

    class Int256(val value: BigInteger) : SolidityBase.IntBase(value, 256) {
        companion object {
            val DECODER: SolidityBase.IntBase.Decoder<Int256> = SolidityBase.IntBase.Decoder<Int256>({ Int256(it) })
        }
    }

    class Bytes1(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 1) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes1> = SolidityBase.StaticBytes.Decoder<Bytes1>({ Bytes1(it) }, 1)
        }
    }

    class Bytes2(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 2) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes2> = SolidityBase.StaticBytes.Decoder<Bytes2>({ Bytes2(it) }, 2)
        }
    }

    class Bytes3(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 3) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes3> = SolidityBase.StaticBytes.Decoder<Bytes3>({ Bytes3(it) }, 3)
        }
    }

    class Bytes4(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 4) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes4> = SolidityBase.StaticBytes.Decoder<Bytes4>({ Bytes4(it) }, 4)
        }
    }

    class Bytes5(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 5) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes5> = SolidityBase.StaticBytes.Decoder<Bytes5>({ Bytes5(it) }, 5)
        }
    }

    class Bytes6(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 6) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes6> = SolidityBase.StaticBytes.Decoder<Bytes6>({ Bytes6(it) }, 6)
        }
    }

    class Bytes7(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 7) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes7> = SolidityBase.StaticBytes.Decoder<Bytes7>({ Bytes7(it) }, 7)
        }
    }

    class Bytes8(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 8) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes8> = SolidityBase.StaticBytes.Decoder<Bytes8>({ Bytes8(it) }, 8)
        }
    }

    class Bytes9(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 9) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes9> = SolidityBase.StaticBytes.Decoder<Bytes9>({ Bytes9(it) }, 9)
        }
    }

    class Bytes10(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 10) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes10> = SolidityBase.StaticBytes.Decoder<Bytes10>({ Bytes10(it) }, 10)
        }
    }

    class Bytes11(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 11) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes11> = SolidityBase.StaticBytes.Decoder<Bytes11>({ Bytes11(it) }, 11)
        }
    }

    class Bytes12(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 12) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes12> = SolidityBase.StaticBytes.Decoder<Bytes12>({ Bytes12(it) }, 12)
        }
    }

    class Bytes13(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 13) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes13> = SolidityBase.StaticBytes.Decoder<Bytes13>({ Bytes13(it) }, 13)
        }
    }

    class Bytes14(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 14) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes14> = SolidityBase.StaticBytes.Decoder<Bytes14>({ Bytes14(it) }, 14)
        }
    }

    class Bytes15(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 15) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes15> = SolidityBase.StaticBytes.Decoder<Bytes15>({ Bytes15(it) }, 15)
        }
    }

    class Bytes16(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 16) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes16> = SolidityBase.StaticBytes.Decoder<Bytes16>({ Bytes16(it) }, 16)
        }
    }

    class Bytes17(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 17) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes17> = SolidityBase.StaticBytes.Decoder<Bytes17>({ Bytes17(it) }, 17)
        }
    }

    class Bytes18(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 18) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes18> = SolidityBase.StaticBytes.Decoder<Bytes18>({ Bytes18(it) }, 18)
        }
    }

    class Bytes19(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 19) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes19> = SolidityBase.StaticBytes.Decoder<Bytes19>({ Bytes19(it) }, 19)
        }
    }

    class Bytes20(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 20) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes20> = SolidityBase.StaticBytes.Decoder<Bytes20>({ Bytes20(it) }, 20)
        }
    }

    class Bytes21(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 21) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes21> = SolidityBase.StaticBytes.Decoder<Bytes21>({ Bytes21(it) }, 21)
        }
    }

    class Bytes22(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 22) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes22> = SolidityBase.StaticBytes.Decoder<Bytes22>({ Bytes22(it) }, 22)
        }
    }

    class Bytes23(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 23) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes23> = SolidityBase.StaticBytes.Decoder<Bytes23>({ Bytes23(it) }, 23)
        }
    }

    class Bytes24(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 24) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes24> = SolidityBase.StaticBytes.Decoder<Bytes24>({ Bytes24(it) }, 24)
        }
    }

    class Bytes25(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 25) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes25> = SolidityBase.StaticBytes.Decoder<Bytes25>({ Bytes25(it) }, 25)
        }
    }

    class Bytes26(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 26) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes26> = SolidityBase.StaticBytes.Decoder<Bytes26>({ Bytes26(it) }, 26)
        }
    }

    class Bytes27(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 27) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes27> = SolidityBase.StaticBytes.Decoder<Bytes27>({ Bytes27(it) }, 27)
        }
    }

    class Bytes28(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 28) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes28> = SolidityBase.StaticBytes.Decoder<Bytes28>({ Bytes28(it) }, 28)
        }
    }

    class Bytes29(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 29) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes29> = SolidityBase.StaticBytes.Decoder<Bytes29>({ Bytes29(it) }, 29)
        }
    }

    class Bytes30(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 30) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes30> = SolidityBase.StaticBytes.Decoder<Bytes30>({ Bytes30(it) }, 30)
        }
    }

    class Bytes31(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 31) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes31> = SolidityBase.StaticBytes.Decoder<Bytes31>({ Bytes31(it) }, 31)
        }
    }

    class Bytes32(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 32) {
        companion object {
            val DECODER: SolidityBase.StaticBytes.Decoder<Bytes32> = SolidityBase.StaticBytes.Decoder<Bytes32>({ Bytes32(it) }, 32)
        }
    }

    class Address(val value: BigInteger) : SolidityBase.UIntBase(value, 160) {
        companion object {
            val DECODER: SolidityBase.UIntBase.Decoder<Address> = SolidityBase.UIntBase.Decoder<Address>({ Address(it) })
        }
    }

    class Bool(val value: Boolean) : SolidityBase.UIntBase(if (value) BigInteger.ONE else BigInteger.ZERO, 8) {
        class Decoder : SolidityBase.TypeDecoder<Bool> {
            override fun isDynamic(): Boolean = false
            override fun decode(source: SolidityBase.PartitionData): Bool = Bool(SolidityBase.decodeBool(source.consume()))
        }
        companion object {
            val DECODER: Decoder = Decoder()
        }
    }

    open class Bytes(val items: ByteArray) : SolidityBase.DynamicType {
        init {
            if (BigInteger(items.size.toString(10)) > BigInteger.valueOf(2).pow(256)) throw Exception()
        }

        override fun encode(): kotlin.String {
            val parts = encodeParts()
            return parts.static + parts.dynamic
        }

        private fun encodeParts(): SolidityBase.DynamicType.Parts {
            val length = items.size.toString(16).padStart(64, '0')
            val contents = items.toHex().padEndMultiple(64, '0')
            return SolidityBase.DynamicType.Parts(length, contents)
        }

        class Decoder : SolidityBase.TypeDecoder<Bytes> {
            override fun isDynamic(): Boolean = true
            override fun decode(source: SolidityBase.PartitionData): Bytes = Bytes(SolidityBase.decodeBytes(source))
        }
        companion object {
            val DECODER: Decoder = Decoder()
        }
    }

    class String(val value: kotlin.String) : Bytes(value.toByteArray()) {
        class Decoder : SolidityBase.TypeDecoder<String> {
            override fun isDynamic(): Boolean = true
            override fun decode(source: SolidityBase.PartitionData): String = String(SolidityBase.decodeString(source))
        }
        companion object {
            val DECODER: Decoder = Decoder()
        }
    }
}
