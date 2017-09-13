package pm.gnosis.model

import java.lang.Exception
import java.math.BigInteger
import kotlin.Boolean
import kotlin.ByteArray
import kotlin.collections.List
import kotlin.collections.Map
import pm.gnosis.utils.padEndMultiple
import pm.gnosis.utils.toHex

/**
 * Generated code. Do not modify
 */
object Solidity {
    val map: Map<kotlin.String, kotlin.String> = mapOf(
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
            "uint" to "pm.gnosis.model.Solidity.UInt",
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
            "int" to "pm.gnosis.model.Solidity.Int",
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
            "int8[]" to "pm.gnosis.model.Solidity.ArrayOfInt8",
            "int16[]" to "pm.gnosis.model.Solidity.ArrayOfInt16",
            "int24[]" to "pm.gnosis.model.Solidity.ArrayOfInt24",
            "int32[]" to "pm.gnosis.model.Solidity.ArrayOfInt32",
            "int40[]" to "pm.gnosis.model.Solidity.ArrayOfInt40",
            "int48[]" to "pm.gnosis.model.Solidity.ArrayOfInt48",
            "int56[]" to "pm.gnosis.model.Solidity.ArrayOfInt56",
            "int64[]" to "pm.gnosis.model.Solidity.ArrayOfInt64",
            "int72[]" to "pm.gnosis.model.Solidity.ArrayOfInt72",
            "int80[]" to "pm.gnosis.model.Solidity.ArrayOfInt80",
            "int88[]" to "pm.gnosis.model.Solidity.ArrayOfInt88",
            "int96[]" to "pm.gnosis.model.Solidity.ArrayOfInt96",
            "int104[]" to "pm.gnosis.model.Solidity.ArrayOfInt104",
            "int112[]" to "pm.gnosis.model.Solidity.ArrayOfInt112",
            "int120[]" to "pm.gnosis.model.Solidity.ArrayOfInt120",
            "int128[]" to "pm.gnosis.model.Solidity.ArrayOfInt128",
            "int136[]" to "pm.gnosis.model.Solidity.ArrayOfInt136",
            "int144[]" to "pm.gnosis.model.Solidity.ArrayOfInt144",
            "int152[]" to "pm.gnosis.model.Solidity.ArrayOfInt152",
            "int160[]" to "pm.gnosis.model.Solidity.ArrayOfInt160",
            "int168[]" to "pm.gnosis.model.Solidity.ArrayOfInt168",
            "int176[]" to "pm.gnosis.model.Solidity.ArrayOfInt176",
            "int184[]" to "pm.gnosis.model.Solidity.ArrayOfInt184",
            "int192[]" to "pm.gnosis.model.Solidity.ArrayOfInt192",
            "int200[]" to "pm.gnosis.model.Solidity.ArrayOfInt200",
            "int208[]" to "pm.gnosis.model.Solidity.ArrayOfInt208",
            "int216[]" to "pm.gnosis.model.Solidity.ArrayOfInt216",
            "int224[]" to "pm.gnosis.model.Solidity.ArrayOfInt224",
            "int232[]" to "pm.gnosis.model.Solidity.ArrayOfInt232",
            "int240[]" to "pm.gnosis.model.Solidity.ArrayOfInt240",
            "int248[]" to "pm.gnosis.model.Solidity.ArrayOfInt248",
            "int256[]" to "pm.gnosis.model.Solidity.ArrayOfInt256",
            "int[]" to "pm.gnosis.model.Solidity.ArrayOfInt",
            "uint8[]" to "pm.gnosis.model.Solidity.ArrayOfUInt8",
            "uint16[]" to "pm.gnosis.model.Solidity.ArrayOfUInt16",
            "uint24[]" to "pm.gnosis.model.Solidity.ArrayOfUInt24",
            "uint32[]" to "pm.gnosis.model.Solidity.ArrayOfUInt32",
            "uint40[]" to "pm.gnosis.model.Solidity.ArrayOfUInt40",
            "uint48[]" to "pm.gnosis.model.Solidity.ArrayOfUInt48",
            "uint56[]" to "pm.gnosis.model.Solidity.ArrayOfUInt56",
            "uint64[]" to "pm.gnosis.model.Solidity.ArrayOfUInt64",
            "uint72[]" to "pm.gnosis.model.Solidity.ArrayOfUInt72",
            "uint80[]" to "pm.gnosis.model.Solidity.ArrayOfUInt80",
            "uint88[]" to "pm.gnosis.model.Solidity.ArrayOfUInt88",
            "uint96[]" to "pm.gnosis.model.Solidity.ArrayOfUInt96",
            "uint104[]" to "pm.gnosis.model.Solidity.ArrayOfUInt104",
            "uint112[]" to "pm.gnosis.model.Solidity.ArrayOfUInt112",
            "uint120[]" to "pm.gnosis.model.Solidity.ArrayOfUInt120",
            "uint128[]" to "pm.gnosis.model.Solidity.ArrayOfUInt128",
            "uint136[]" to "pm.gnosis.model.Solidity.ArrayOfUInt136",
            "uint144[]" to "pm.gnosis.model.Solidity.ArrayOfUInt144",
            "uint152[]" to "pm.gnosis.model.Solidity.ArrayOfUInt152",
            "uint160[]" to "pm.gnosis.model.Solidity.ArrayOfUInt160",
            "uint168[]" to "pm.gnosis.model.Solidity.ArrayOfUInt168",
            "uint176[]" to "pm.gnosis.model.Solidity.ArrayOfUInt176",
            "uint184[]" to "pm.gnosis.model.Solidity.ArrayOfUInt184",
            "uint192[]" to "pm.gnosis.model.Solidity.ArrayOfUInt192",
            "uint200[]" to "pm.gnosis.model.Solidity.ArrayOfUInt200",
            "uint208[]" to "pm.gnosis.model.Solidity.ArrayOfUInt208",
            "uint216[]" to "pm.gnosis.model.Solidity.ArrayOfUInt216",
            "uint224[]" to "pm.gnosis.model.Solidity.ArrayOfUInt224",
            "uint232[]" to "pm.gnosis.model.Solidity.ArrayOfUInt232",
            "uint240[]" to "pm.gnosis.model.Solidity.ArrayOfUInt240",
            "uint248[]" to "pm.gnosis.model.Solidity.ArrayOfUInt248",
            "uint256[]" to "pm.gnosis.model.Solidity.ArrayOfUInt256",
            "uint[]" to "pm.gnosis.model.Solidity.ArrayOfUInt",
            "bytes1[]" to "pm.gnosis.model.Solidity.ArrayOfBytes1",
            "bytes2[]" to "pm.gnosis.model.Solidity.ArrayOfBytes2",
            "bytes3[]" to "pm.gnosis.model.Solidity.ArrayOfBytes3",
            "bytes4[]" to "pm.gnosis.model.Solidity.ArrayOfBytes4",
            "bytes5[]" to "pm.gnosis.model.Solidity.ArrayOfBytes5",
            "bytes6[]" to "pm.gnosis.model.Solidity.ArrayOfBytes6",
            "bytes7[]" to "pm.gnosis.model.Solidity.ArrayOfBytes7",
            "bytes8[]" to "pm.gnosis.model.Solidity.ArrayOfBytes8",
            "bytes9[]" to "pm.gnosis.model.Solidity.ArrayOfBytes9",
            "bytes10[]" to "pm.gnosis.model.Solidity.ArrayOfBytes10",
            "bytes11[]" to "pm.gnosis.model.Solidity.ArrayOfBytes11",
            "bytes12[]" to "pm.gnosis.model.Solidity.ArrayOfBytes12",
            "bytes13[]" to "pm.gnosis.model.Solidity.ArrayOfBytes13",
            "bytes14[]" to "pm.gnosis.model.Solidity.ArrayOfBytes14",
            "bytes15[]" to "pm.gnosis.model.Solidity.ArrayOfBytes15",
            "bytes16[]" to "pm.gnosis.model.Solidity.ArrayOfBytes16",
            "bytes17[]" to "pm.gnosis.model.Solidity.ArrayOfBytes17",
            "bytes18[]" to "pm.gnosis.model.Solidity.ArrayOfBytes18",
            "bytes19[]" to "pm.gnosis.model.Solidity.ArrayOfBytes19",
            "bytes20[]" to "pm.gnosis.model.Solidity.ArrayOfBytes20",
            "bytes21[]" to "pm.gnosis.model.Solidity.ArrayOfBytes21",
            "bytes22[]" to "pm.gnosis.model.Solidity.ArrayOfBytes22",
            "bytes23[]" to "pm.gnosis.model.Solidity.ArrayOfBytes23",
            "bytes24[]" to "pm.gnosis.model.Solidity.ArrayOfBytes24",
            "bytes25[]" to "pm.gnosis.model.Solidity.ArrayOfBytes25",
            "bytes26[]" to "pm.gnosis.model.Solidity.ArrayOfBytes26",
            "bytes27[]" to "pm.gnosis.model.Solidity.ArrayOfBytes27",
            "bytes28[]" to "pm.gnosis.model.Solidity.ArrayOfBytes28",
            "bytes29[]" to "pm.gnosis.model.Solidity.ArrayOfBytes29",
            "bytes30[]" to "pm.gnosis.model.Solidity.ArrayOfBytes30",
            "bytes31[]" to "pm.gnosis.model.Solidity.ArrayOfBytes31",
            "bytes32[]" to "pm.gnosis.model.Solidity.ArrayOfBytes32",
            "address" to "pm.gnosis.model.Solidity.Address",
            "address[]" to "pm.gnosis.model.Solidity.ArrayOfAddress",
            "bool" to "pm.gnosis.model.Solidity.Bool",
            "bool[]" to "pm.gnosis.model.Solidity.ArrayOfBool",
            "bytes" to "pm.gnosis.model.Solidity.Bytes",
            "byte" to "pm.gnosis.model.Solidity.Byte",
            "byte[]" to "pm.gnosis.model.Solidity.ArrayOfByte",
            "string" to "pm.gnosis.model.Solidity.String")

    class UInt8(val value: BigInteger) : SolidityBase.UIntBase(value, 8) {
        companion object : SolidityBase.Type.Decoder<UInt8> {
            override fun decode(source: kotlin.String): UInt8 = UInt8(SolidityBase.decodeUInt(source))
        }
    }

    class UInt16(val value: BigInteger) : SolidityBase.UIntBase(value, 16) {
        companion object : SolidityBase.Type.Decoder<UInt16> {
            override fun decode(source: kotlin.String): UInt16 = UInt16(SolidityBase.decodeUInt(source))
        }
    }

    class UInt24(val value: BigInteger) : SolidityBase.UIntBase(value, 24) {
        companion object : SolidityBase.Type.Decoder<UInt24> {
            override fun decode(source: kotlin.String): UInt24 = UInt24(SolidityBase.decodeUInt(source))
        }
    }

    class UInt32(val value: BigInteger) : SolidityBase.UIntBase(value, 32) {
        companion object : SolidityBase.Type.Decoder<UInt32> {
            override fun decode(source: kotlin.String): UInt32 = UInt32(SolidityBase.decodeUInt(source))
        }
    }

    class UInt40(val value: BigInteger) : SolidityBase.UIntBase(value, 40) {
        companion object : SolidityBase.Type.Decoder<UInt40> {
            override fun decode(source: kotlin.String): UInt40 = UInt40(SolidityBase.decodeUInt(source))
        }
    }

    class UInt48(val value: BigInteger) : SolidityBase.UIntBase(value, 48) {
        companion object : SolidityBase.Type.Decoder<UInt48> {
            override fun decode(source: kotlin.String): UInt48 = UInt48(SolidityBase.decodeUInt(source))
        }
    }

    class UInt56(val value: BigInteger) : SolidityBase.UIntBase(value, 56) {
        companion object : SolidityBase.Type.Decoder<UInt56> {
            override fun decode(source: kotlin.String): UInt56 = UInt56(SolidityBase.decodeUInt(source))
        }
    }

    class UInt64(val value: BigInteger) : SolidityBase.UIntBase(value, 64) {
        companion object : SolidityBase.Type.Decoder<UInt64> {
            override fun decode(source: kotlin.String): UInt64 = UInt64(SolidityBase.decodeUInt(source))
        }
    }

    class UInt72(val value: BigInteger) : SolidityBase.UIntBase(value, 72) {
        companion object : SolidityBase.Type.Decoder<UInt72> {
            override fun decode(source: kotlin.String): UInt72 = UInt72(SolidityBase.decodeUInt(source))
        }
    }

    class UInt80(val value: BigInteger) : SolidityBase.UIntBase(value, 80) {
        companion object : SolidityBase.Type.Decoder<UInt80> {
            override fun decode(source: kotlin.String): UInt80 = UInt80(SolidityBase.decodeUInt(source))
        }
    }

    class UInt88(val value: BigInteger) : SolidityBase.UIntBase(value, 88) {
        companion object : SolidityBase.Type.Decoder<UInt88> {
            override fun decode(source: kotlin.String): UInt88 = UInt88(SolidityBase.decodeUInt(source))
        }
    }

    class UInt96(val value: BigInteger) : SolidityBase.UIntBase(value, 96) {
        companion object : SolidityBase.Type.Decoder<UInt96> {
            override fun decode(source: kotlin.String): UInt96 = UInt96(SolidityBase.decodeUInt(source))
        }
    }

    class UInt104(val value: BigInteger) : SolidityBase.UIntBase(value, 104) {
        companion object : SolidityBase.Type.Decoder<UInt104> {
            override fun decode(source: kotlin.String): UInt104 = UInt104(SolidityBase.decodeUInt(source))
        }
    }

    class UInt112(val value: BigInteger) : SolidityBase.UIntBase(value, 112) {
        companion object : SolidityBase.Type.Decoder<UInt112> {
            override fun decode(source: kotlin.String): UInt112 = UInt112(SolidityBase.decodeUInt(source))
        }
    }

    class UInt120(val value: BigInteger) : SolidityBase.UIntBase(value, 120) {
        companion object : SolidityBase.Type.Decoder<UInt120> {
            override fun decode(source: kotlin.String): UInt120 = UInt120(SolidityBase.decodeUInt(source))
        }
    }

    class UInt128(val value: BigInteger) : SolidityBase.UIntBase(value, 128) {
        companion object : SolidityBase.Type.Decoder<UInt128> {
            override fun decode(source: kotlin.String): UInt128 = UInt128(SolidityBase.decodeUInt(source))
        }
    }

    class UInt136(val value: BigInteger) : SolidityBase.UIntBase(value, 136) {
        companion object : SolidityBase.Type.Decoder<UInt136> {
            override fun decode(source: kotlin.String): UInt136 = UInt136(SolidityBase.decodeUInt(source))
        }
    }

    class UInt144(val value: BigInteger) : SolidityBase.UIntBase(value, 144) {
        companion object : SolidityBase.Type.Decoder<UInt144> {
            override fun decode(source: kotlin.String): UInt144 = UInt144(SolidityBase.decodeUInt(source))
        }
    }

    class UInt152(val value: BigInteger) : SolidityBase.UIntBase(value, 152) {
        companion object : SolidityBase.Type.Decoder<UInt152> {
            override fun decode(source: kotlin.String): UInt152 = UInt152(SolidityBase.decodeUInt(source))
        }
    }

    class UInt160(val value: BigInteger) : SolidityBase.UIntBase(value, 160) {
        companion object : SolidityBase.Type.Decoder<UInt160> {
            override fun decode(source: kotlin.String): UInt160 = UInt160(SolidityBase.decodeUInt(source))
        }
    }

    class UInt168(val value: BigInteger) : SolidityBase.UIntBase(value, 168) {
        companion object : SolidityBase.Type.Decoder<UInt168> {
            override fun decode(source: kotlin.String): UInt168 = UInt168(SolidityBase.decodeUInt(source))
        }
    }

    class UInt176(val value: BigInteger) : SolidityBase.UIntBase(value, 176) {
        companion object : SolidityBase.Type.Decoder<UInt176> {
            override fun decode(source: kotlin.String): UInt176 = UInt176(SolidityBase.decodeUInt(source))
        }
    }

    class UInt184(val value: BigInteger) : SolidityBase.UIntBase(value, 184) {
        companion object : SolidityBase.Type.Decoder<UInt184> {
            override fun decode(source: kotlin.String): UInt184 = UInt184(SolidityBase.decodeUInt(source))
        }
    }

    class UInt192(val value: BigInteger) : SolidityBase.UIntBase(value, 192) {
        companion object : SolidityBase.Type.Decoder<UInt192> {
            override fun decode(source: kotlin.String): UInt192 = UInt192(SolidityBase.decodeUInt(source))
        }
    }

    class UInt200(val value: BigInteger) : SolidityBase.UIntBase(value, 200) {
        companion object : SolidityBase.Type.Decoder<UInt200> {
            override fun decode(source: kotlin.String): UInt200 = UInt200(SolidityBase.decodeUInt(source))
        }
    }

    class UInt208(val value: BigInteger) : SolidityBase.UIntBase(value, 208) {
        companion object : SolidityBase.Type.Decoder<UInt208> {
            override fun decode(source: kotlin.String): UInt208 = UInt208(SolidityBase.decodeUInt(source))
        }
    }

    class UInt216(val value: BigInteger) : SolidityBase.UIntBase(value, 216) {
        companion object : SolidityBase.Type.Decoder<UInt216> {
            override fun decode(source: kotlin.String): UInt216 = UInt216(SolidityBase.decodeUInt(source))
        }
    }

    class UInt224(val value: BigInteger) : SolidityBase.UIntBase(value, 224) {
        companion object : SolidityBase.Type.Decoder<UInt224> {
            override fun decode(source: kotlin.String): UInt224 = UInt224(SolidityBase.decodeUInt(source))
        }
    }

    class UInt232(val value: BigInteger) : SolidityBase.UIntBase(value, 232) {
        companion object : SolidityBase.Type.Decoder<UInt232> {
            override fun decode(source: kotlin.String): UInt232 = UInt232(SolidityBase.decodeUInt(source))
        }
    }

    class UInt240(val value: BigInteger) : SolidityBase.UIntBase(value, 240) {
        companion object : SolidityBase.Type.Decoder<UInt240> {
            override fun decode(source: kotlin.String): UInt240 = UInt240(SolidityBase.decodeUInt(source))
        }
    }

    class UInt248(val value: BigInteger) : SolidityBase.UIntBase(value, 248) {
        companion object : SolidityBase.Type.Decoder<UInt248> {
            override fun decode(source: kotlin.String): UInt248 = UInt248(SolidityBase.decodeUInt(source))
        }
    }

    class UInt256(val value: BigInteger) : SolidityBase.UIntBase(value, 256) {
        companion object : SolidityBase.Type.Decoder<UInt256> {
            override fun decode(source: kotlin.String): UInt256 = UInt256(SolidityBase.decodeUInt(source))
        }
    }

    class UInt(val value: BigInteger) : SolidityBase.UIntBase(value, 256) {
        companion object : SolidityBase.Type.Decoder<UInt> {
            override fun decode(source: kotlin.String): UInt = UInt(SolidityBase.decodeUInt(source))
        }
    }

    class Int8(val value: BigInteger) : SolidityBase.IntBase(value, 8) {
        companion object : SolidityBase.Type.Decoder<Int8> {
            override fun decode(source: kotlin.String): Int8 = Int8(SolidityBase.decodeInt(source))
        }
    }

    class Int16(val value: BigInteger) : SolidityBase.IntBase(value, 16) {
        companion object : SolidityBase.Type.Decoder<Int16> {
            override fun decode(source: kotlin.String): Int16 = Int16(SolidityBase.decodeInt(source))
        }
    }

    class Int24(val value: BigInteger) : SolidityBase.IntBase(value, 24) {
        companion object : SolidityBase.Type.Decoder<Int24> {
            override fun decode(source: kotlin.String): Int24 = Int24(SolidityBase.decodeInt(source))
        }
    }

    class Int32(val value: BigInteger) : SolidityBase.IntBase(value, 32) {
        companion object : SolidityBase.Type.Decoder<Int32> {
            override fun decode(source: kotlin.String): Int32 = Int32(SolidityBase.decodeInt(source))
        }
    }

    class Int40(val value: BigInteger) : SolidityBase.IntBase(value, 40) {
        companion object : SolidityBase.Type.Decoder<Int40> {
            override fun decode(source: kotlin.String): Int40 = Int40(SolidityBase.decodeInt(source))
        }
    }

    class Int48(val value: BigInteger) : SolidityBase.IntBase(value, 48) {
        companion object : SolidityBase.Type.Decoder<Int48> {
            override fun decode(source: kotlin.String): Int48 = Int48(SolidityBase.decodeInt(source))
        }
    }

    class Int56(val value: BigInteger) : SolidityBase.IntBase(value, 56) {
        companion object : SolidityBase.Type.Decoder<Int56> {
            override fun decode(source: kotlin.String): Int56 = Int56(SolidityBase.decodeInt(source))
        }
    }

    class Int64(val value: BigInteger) : SolidityBase.IntBase(value, 64) {
        companion object : SolidityBase.Type.Decoder<Int64> {
            override fun decode(source: kotlin.String): Int64 = Int64(SolidityBase.decodeInt(source))
        }
    }

    class Int72(val value: BigInteger) : SolidityBase.IntBase(value, 72) {
        companion object : SolidityBase.Type.Decoder<Int72> {
            override fun decode(source: kotlin.String): Int72 = Int72(SolidityBase.decodeInt(source))
        }
    }

    class Int80(val value: BigInteger) : SolidityBase.IntBase(value, 80) {
        companion object : SolidityBase.Type.Decoder<Int80> {
            override fun decode(source: kotlin.String): Int80 = Int80(SolidityBase.decodeInt(source))
        }
    }

    class Int88(val value: BigInteger) : SolidityBase.IntBase(value, 88) {
        companion object : SolidityBase.Type.Decoder<Int88> {
            override fun decode(source: kotlin.String): Int88 = Int88(SolidityBase.decodeInt(source))
        }
    }

    class Int96(val value: BigInteger) : SolidityBase.IntBase(value, 96) {
        companion object : SolidityBase.Type.Decoder<Int96> {
            override fun decode(source: kotlin.String): Int96 = Int96(SolidityBase.decodeInt(source))
        }
    }

    class Int104(val value: BigInteger) : SolidityBase.IntBase(value, 104) {
        companion object : SolidityBase.Type.Decoder<Int104> {
            override fun decode(source: kotlin.String): Int104 = Int104(SolidityBase.decodeInt(source))
        }
    }

    class Int112(val value: BigInteger) : SolidityBase.IntBase(value, 112) {
        companion object : SolidityBase.Type.Decoder<Int112> {
            override fun decode(source: kotlin.String): Int112 = Int112(SolidityBase.decodeInt(source))
        }
    }

    class Int120(val value: BigInteger) : SolidityBase.IntBase(value, 120) {
        companion object : SolidityBase.Type.Decoder<Int120> {
            override fun decode(source: kotlin.String): Int120 = Int120(SolidityBase.decodeInt(source))
        }
    }

    class Int128(val value: BigInteger) : SolidityBase.IntBase(value, 128) {
        companion object : SolidityBase.Type.Decoder<Int128> {
            override fun decode(source: kotlin.String): Int128 = Int128(SolidityBase.decodeInt(source))
        }
    }

    class Int136(val value: BigInteger) : SolidityBase.IntBase(value, 136) {
        companion object : SolidityBase.Type.Decoder<Int136> {
            override fun decode(source: kotlin.String): Int136 = Int136(SolidityBase.decodeInt(source))
        }
    }

    class Int144(val value: BigInteger) : SolidityBase.IntBase(value, 144) {
        companion object : SolidityBase.Type.Decoder<Int144> {
            override fun decode(source: kotlin.String): Int144 = Int144(SolidityBase.decodeInt(source))
        }
    }

    class Int152(val value: BigInteger) : SolidityBase.IntBase(value, 152) {
        companion object : SolidityBase.Type.Decoder<Int152> {
            override fun decode(source: kotlin.String): Int152 = Int152(SolidityBase.decodeInt(source))
        }
    }

    class Int160(val value: BigInteger) : SolidityBase.IntBase(value, 160) {
        companion object : SolidityBase.Type.Decoder<Int160> {
            override fun decode(source: kotlin.String): Int160 = Int160(SolidityBase.decodeInt(source))
        }
    }

    class Int168(val value: BigInteger) : SolidityBase.IntBase(value, 168) {
        companion object : SolidityBase.Type.Decoder<Int168> {
            override fun decode(source: kotlin.String): Int168 = Int168(SolidityBase.decodeInt(source))
        }
    }

    class Int176(val value: BigInteger) : SolidityBase.IntBase(value, 176) {
        companion object : SolidityBase.Type.Decoder<Int176> {
            override fun decode(source: kotlin.String): Int176 = Int176(SolidityBase.decodeInt(source))
        }
    }

    class Int184(val value: BigInteger) : SolidityBase.IntBase(value, 184) {
        companion object : SolidityBase.Type.Decoder<Int184> {
            override fun decode(source: kotlin.String): Int184 = Int184(SolidityBase.decodeInt(source))
        }
    }

    class Int192(val value: BigInteger) : SolidityBase.IntBase(value, 192) {
        companion object : SolidityBase.Type.Decoder<Int192> {
            override fun decode(source: kotlin.String): Int192 = Int192(SolidityBase.decodeInt(source))
        }
    }

    class Int200(val value: BigInteger) : SolidityBase.IntBase(value, 200) {
        companion object : SolidityBase.Type.Decoder<Int200> {
            override fun decode(source: kotlin.String): Int200 = Int200(SolidityBase.decodeInt(source))
        }
    }

    class Int208(val value: BigInteger) : SolidityBase.IntBase(value, 208) {
        companion object : SolidityBase.Type.Decoder<Int208> {
            override fun decode(source: kotlin.String): Int208 = Int208(SolidityBase.decodeInt(source))
        }
    }

    class Int216(val value: BigInteger) : SolidityBase.IntBase(value, 216) {
        companion object : SolidityBase.Type.Decoder<Int216> {
            override fun decode(source: kotlin.String): Int216 = Int216(SolidityBase.decodeInt(source))
        }
    }

    class Int224(val value: BigInteger) : SolidityBase.IntBase(value, 224) {
        companion object : SolidityBase.Type.Decoder<Int224> {
            override fun decode(source: kotlin.String): Int224 = Int224(SolidityBase.decodeInt(source))
        }
    }

    class Int232(val value: BigInteger) : SolidityBase.IntBase(value, 232) {
        companion object : SolidityBase.Type.Decoder<Int232> {
            override fun decode(source: kotlin.String): Int232 = Int232(SolidityBase.decodeInt(source))
        }
    }

    class Int240(val value: BigInteger) : SolidityBase.IntBase(value, 240) {
        companion object : SolidityBase.Type.Decoder<Int240> {
            override fun decode(source: kotlin.String): Int240 = Int240(SolidityBase.decodeInt(source))
        }
    }

    class Int248(val value: BigInteger) : SolidityBase.IntBase(value, 248) {
        companion object : SolidityBase.Type.Decoder<Int248> {
            override fun decode(source: kotlin.String): Int248 = Int248(SolidityBase.decodeInt(source))
        }
    }

    class Int256(val value: BigInteger) : SolidityBase.IntBase(value, 256) {
        companion object : SolidityBase.Type.Decoder<Int256> {
            override fun decode(source: kotlin.String): Int256 = Int256(SolidityBase.decodeInt(source))
        }
    }

    class Int(val value: BigInteger) : SolidityBase.IntBase(value, 256) {
        companion object : SolidityBase.Type.Decoder<Int> {
            override fun decode(source: kotlin.String): Int = Int(SolidityBase.decodeInt(source))
        }
    }

    class Bytes1(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 1) {
        companion object : SolidityBase.Type.Decoder<Bytes1> {
            override fun decode(source: kotlin.String): Bytes1 = Bytes1(SolidityBase.decodeStaticBytes(source, 1))
        }
    }

    class Bytes2(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 2) {
        companion object : SolidityBase.Type.Decoder<Bytes2> {
            override fun decode(source: kotlin.String): Bytes2 = Bytes2(SolidityBase.decodeStaticBytes(source, 2))
        }
    }

    class Bytes3(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 3) {
        companion object : SolidityBase.Type.Decoder<Bytes3> {
            override fun decode(source: kotlin.String): Bytes3 = Bytes3(SolidityBase.decodeStaticBytes(source, 3))
        }
    }

    class Bytes4(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 4) {
        companion object : SolidityBase.Type.Decoder<Bytes4> {
            override fun decode(source: kotlin.String): Bytes4 = Bytes4(SolidityBase.decodeStaticBytes(source, 4))
        }
    }

    class Bytes5(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 5) {
        companion object : SolidityBase.Type.Decoder<Bytes5> {
            override fun decode(source: kotlin.String): Bytes5 = Bytes5(SolidityBase.decodeStaticBytes(source, 5))
        }
    }

    class Bytes6(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 6) {
        companion object : SolidityBase.Type.Decoder<Bytes6> {
            override fun decode(source: kotlin.String): Bytes6 = Bytes6(SolidityBase.decodeStaticBytes(source, 6))
        }
    }

    class Bytes7(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 7) {
        companion object : SolidityBase.Type.Decoder<Bytes7> {
            override fun decode(source: kotlin.String): Bytes7 = Bytes7(SolidityBase.decodeStaticBytes(source, 7))
        }
    }

    class Bytes8(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 8) {
        companion object : SolidityBase.Type.Decoder<Bytes8> {
            override fun decode(source: kotlin.String): Bytes8 = Bytes8(SolidityBase.decodeStaticBytes(source, 8))
        }
    }

    class Bytes9(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 9) {
        companion object : SolidityBase.Type.Decoder<Bytes9> {
            override fun decode(source: kotlin.String): Bytes9 = Bytes9(SolidityBase.decodeStaticBytes(source, 9))
        }
    }

    class Bytes10(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 10) {
        companion object : SolidityBase.Type.Decoder<Bytes10> {
            override fun decode(source: kotlin.String): Bytes10 = Bytes10(SolidityBase.decodeStaticBytes(source, 10))
        }
    }

    class Bytes11(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 11) {
        companion object : SolidityBase.Type.Decoder<Bytes11> {
            override fun decode(source: kotlin.String): Bytes11 = Bytes11(SolidityBase.decodeStaticBytes(source, 11))
        }
    }

    class Bytes12(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 12) {
        companion object : SolidityBase.Type.Decoder<Bytes12> {
            override fun decode(source: kotlin.String): Bytes12 = Bytes12(SolidityBase.decodeStaticBytes(source, 12))
        }
    }

    class Bytes13(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 13) {
        companion object : SolidityBase.Type.Decoder<Bytes13> {
            override fun decode(source: kotlin.String): Bytes13 = Bytes13(SolidityBase.decodeStaticBytes(source, 13))
        }
    }

    class Bytes14(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 14) {
        companion object : SolidityBase.Type.Decoder<Bytes14> {
            override fun decode(source: kotlin.String): Bytes14 = Bytes14(SolidityBase.decodeStaticBytes(source, 14))
        }
    }

    class Bytes15(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 15) {
        companion object : SolidityBase.Type.Decoder<Bytes15> {
            override fun decode(source: kotlin.String): Bytes15 = Bytes15(SolidityBase.decodeStaticBytes(source, 15))
        }
    }

    class Bytes16(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 16) {
        companion object : SolidityBase.Type.Decoder<Bytes16> {
            override fun decode(source: kotlin.String): Bytes16 = Bytes16(SolidityBase.decodeStaticBytes(source, 16))
        }
    }

    class Bytes17(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 17) {
        companion object : SolidityBase.Type.Decoder<Bytes17> {
            override fun decode(source: kotlin.String): Bytes17 = Bytes17(SolidityBase.decodeStaticBytes(source, 17))
        }
    }

    class Bytes18(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 18) {
        companion object : SolidityBase.Type.Decoder<Bytes18> {
            override fun decode(source: kotlin.String): Bytes18 = Bytes18(SolidityBase.decodeStaticBytes(source, 18))
        }
    }

    class Bytes19(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 19) {
        companion object : SolidityBase.Type.Decoder<Bytes19> {
            override fun decode(source: kotlin.String): Bytes19 = Bytes19(SolidityBase.decodeStaticBytes(source, 19))
        }
    }

    class Bytes20(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 20) {
        companion object : SolidityBase.Type.Decoder<Bytes20> {
            override fun decode(source: kotlin.String): Bytes20 = Bytes20(SolidityBase.decodeStaticBytes(source, 20))
        }
    }

    class Bytes21(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 21) {
        companion object : SolidityBase.Type.Decoder<Bytes21> {
            override fun decode(source: kotlin.String): Bytes21 = Bytes21(SolidityBase.decodeStaticBytes(source, 21))
        }
    }

    class Bytes22(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 22) {
        companion object : SolidityBase.Type.Decoder<Bytes22> {
            override fun decode(source: kotlin.String): Bytes22 = Bytes22(SolidityBase.decodeStaticBytes(source, 22))
        }
    }

    class Bytes23(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 23) {
        companion object : SolidityBase.Type.Decoder<Bytes23> {
            override fun decode(source: kotlin.String): Bytes23 = Bytes23(SolidityBase.decodeStaticBytes(source, 23))
        }
    }

    class Bytes24(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 24) {
        companion object : SolidityBase.Type.Decoder<Bytes24> {
            override fun decode(source: kotlin.String): Bytes24 = Bytes24(SolidityBase.decodeStaticBytes(source, 24))
        }
    }

    class Bytes25(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 25) {
        companion object : SolidityBase.Type.Decoder<Bytes25> {
            override fun decode(source: kotlin.String): Bytes25 = Bytes25(SolidityBase.decodeStaticBytes(source, 25))
        }
    }

    class Bytes26(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 26) {
        companion object : SolidityBase.Type.Decoder<Bytes26> {
            override fun decode(source: kotlin.String): Bytes26 = Bytes26(SolidityBase.decodeStaticBytes(source, 26))
        }
    }

    class Bytes27(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 27) {
        companion object : SolidityBase.Type.Decoder<Bytes27> {
            override fun decode(source: kotlin.String): Bytes27 = Bytes27(SolidityBase.decodeStaticBytes(source, 27))
        }
    }

    class Bytes28(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 28) {
        companion object : SolidityBase.Type.Decoder<Bytes28> {
            override fun decode(source: kotlin.String): Bytes28 = Bytes28(SolidityBase.decodeStaticBytes(source, 28))
        }
    }

    class Bytes29(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 29) {
        companion object : SolidityBase.Type.Decoder<Bytes29> {
            override fun decode(source: kotlin.String): Bytes29 = Bytes29(SolidityBase.decodeStaticBytes(source, 29))
        }
    }

    class Bytes30(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 30) {
        companion object : SolidityBase.Type.Decoder<Bytes30> {
            override fun decode(source: kotlin.String): Bytes30 = Bytes30(SolidityBase.decodeStaticBytes(source, 30))
        }
    }

    class Bytes31(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 31) {
        companion object : SolidityBase.Type.Decoder<Bytes31> {
            override fun decode(source: kotlin.String): Bytes31 = Bytes31(SolidityBase.decodeStaticBytes(source, 31))
        }
    }

    class Bytes32(val bytes: ByteArray) : SolidityBase.StaticBytes(bytes, 32) {
        companion object : SolidityBase.Type.Decoder<Bytes32> {
            override fun decode(source: kotlin.String): Bytes32 = Bytes32(SolidityBase.decodeStaticBytes(source, 32))
        }
    }

    class ArrayOfInt8(val items: List<Int8>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int8>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt8> {
            override fun decode(source: kotlin.String): ArrayOfInt8 = ArrayOfInt8(SolidityBase.decodeArray(source, Int8.Companion::decode))
        }
    }

    class ArrayOfInt16(val items: List<Int16>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int16>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt16> {
            override fun decode(source: kotlin.String): ArrayOfInt16 = ArrayOfInt16(SolidityBase.decodeArray(source, Int16.Companion::decode))
        }
    }

    class ArrayOfInt24(val items: List<Int24>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int24>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt24> {
            override fun decode(source: kotlin.String): ArrayOfInt24 = ArrayOfInt24(SolidityBase.decodeArray(source, Int24.Companion::decode))
        }
    }

    class ArrayOfInt32(val items: List<Int32>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int32>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt32> {
            override fun decode(source: kotlin.String): ArrayOfInt32 = ArrayOfInt32(SolidityBase.decodeArray(source, Int32.Companion::decode))
        }
    }

    class ArrayOfInt40(val items: List<Int40>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int40>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt40> {
            override fun decode(source: kotlin.String): ArrayOfInt40 = ArrayOfInt40(SolidityBase.decodeArray(source, Int40.Companion::decode))
        }
    }

    class ArrayOfInt48(val items: List<Int48>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int48>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt48> {
            override fun decode(source: kotlin.String): ArrayOfInt48 = ArrayOfInt48(SolidityBase.decodeArray(source, Int48.Companion::decode))
        }
    }

    class ArrayOfInt56(val items: List<Int56>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int56>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt56> {
            override fun decode(source: kotlin.String): ArrayOfInt56 = ArrayOfInt56(SolidityBase.decodeArray(source, Int56.Companion::decode))
        }
    }

    class ArrayOfInt64(val items: List<Int64>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int64>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt64> {
            override fun decode(source: kotlin.String): ArrayOfInt64 = ArrayOfInt64(SolidityBase.decodeArray(source, Int64.Companion::decode))
        }
    }

    class ArrayOfInt72(val items: List<Int72>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int72>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt72> {
            override fun decode(source: kotlin.String): ArrayOfInt72 = ArrayOfInt72(SolidityBase.decodeArray(source, Int72.Companion::decode))
        }
    }

    class ArrayOfInt80(val items: List<Int80>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int80>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt80> {
            override fun decode(source: kotlin.String): ArrayOfInt80 = ArrayOfInt80(SolidityBase.decodeArray(source, Int80.Companion::decode))
        }
    }

    class ArrayOfInt88(val items: List<Int88>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int88>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt88> {
            override fun decode(source: kotlin.String): ArrayOfInt88 = ArrayOfInt88(SolidityBase.decodeArray(source, Int88.Companion::decode))
        }
    }

    class ArrayOfInt96(val items: List<Int96>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int96>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt96> {
            override fun decode(source: kotlin.String): ArrayOfInt96 = ArrayOfInt96(SolidityBase.decodeArray(source, Int96.Companion::decode))
        }
    }

    class ArrayOfInt104(val items: List<Int104>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int104>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt104> {
            override fun decode(source: kotlin.String): ArrayOfInt104 = ArrayOfInt104(SolidityBase.decodeArray(source, Int104.Companion::decode))
        }
    }

    class ArrayOfInt112(val items: List<Int112>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int112>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt112> {
            override fun decode(source: kotlin.String): ArrayOfInt112 = ArrayOfInt112(SolidityBase.decodeArray(source, Int112.Companion::decode))
        }
    }

    class ArrayOfInt120(val items: List<Int120>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int120>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt120> {
            override fun decode(source: kotlin.String): ArrayOfInt120 = ArrayOfInt120(SolidityBase.decodeArray(source, Int120.Companion::decode))
        }
    }

    class ArrayOfInt128(val items: List<Int128>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int128>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt128> {
            override fun decode(source: kotlin.String): ArrayOfInt128 = ArrayOfInt128(SolidityBase.decodeArray(source, Int128.Companion::decode))
        }
    }

    class ArrayOfInt136(val items: List<Int136>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int136>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt136> {
            override fun decode(source: kotlin.String): ArrayOfInt136 = ArrayOfInt136(SolidityBase.decodeArray(source, Int136.Companion::decode))
        }
    }

    class ArrayOfInt144(val items: List<Int144>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int144>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt144> {
            override fun decode(source: kotlin.String): ArrayOfInt144 = ArrayOfInt144(SolidityBase.decodeArray(source, Int144.Companion::decode))
        }
    }

    class ArrayOfInt152(val items: List<Int152>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int152>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt152> {
            override fun decode(source: kotlin.String): ArrayOfInt152 = ArrayOfInt152(SolidityBase.decodeArray(source, Int152.Companion::decode))
        }
    }

    class ArrayOfInt160(val items: List<Int160>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int160>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt160> {
            override fun decode(source: kotlin.String): ArrayOfInt160 = ArrayOfInt160(SolidityBase.decodeArray(source, Int160.Companion::decode))
        }
    }

    class ArrayOfInt168(val items: List<Int168>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int168>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt168> {
            override fun decode(source: kotlin.String): ArrayOfInt168 = ArrayOfInt168(SolidityBase.decodeArray(source, Int168.Companion::decode))
        }
    }

    class ArrayOfInt176(val items: List<Int176>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int176>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt176> {
            override fun decode(source: kotlin.String): ArrayOfInt176 = ArrayOfInt176(SolidityBase.decodeArray(source, Int176.Companion::decode))
        }
    }

    class ArrayOfInt184(val items: List<Int184>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int184>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt184> {
            override fun decode(source: kotlin.String): ArrayOfInt184 = ArrayOfInt184(SolidityBase.decodeArray(source, Int184.Companion::decode))
        }
    }

    class ArrayOfInt192(val items: List<Int192>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int192>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt192> {
            override fun decode(source: kotlin.String): ArrayOfInt192 = ArrayOfInt192(SolidityBase.decodeArray(source, Int192.Companion::decode))
        }
    }

    class ArrayOfInt200(val items: List<Int200>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int200>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt200> {
            override fun decode(source: kotlin.String): ArrayOfInt200 = ArrayOfInt200(SolidityBase.decodeArray(source, Int200.Companion::decode))
        }
    }

    class ArrayOfInt208(val items: List<Int208>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int208>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt208> {
            override fun decode(source: kotlin.String): ArrayOfInt208 = ArrayOfInt208(SolidityBase.decodeArray(source, Int208.Companion::decode))
        }
    }

    class ArrayOfInt216(val items: List<Int216>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int216>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt216> {
            override fun decode(source: kotlin.String): ArrayOfInt216 = ArrayOfInt216(SolidityBase.decodeArray(source, Int216.Companion::decode))
        }
    }

    class ArrayOfInt224(val items: List<Int224>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int224>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt224> {
            override fun decode(source: kotlin.String): ArrayOfInt224 = ArrayOfInt224(SolidityBase.decodeArray(source, Int224.Companion::decode))
        }
    }

    class ArrayOfInt232(val items: List<Int232>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int232>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt232> {
            override fun decode(source: kotlin.String): ArrayOfInt232 = ArrayOfInt232(SolidityBase.decodeArray(source, Int232.Companion::decode))
        }
    }

    class ArrayOfInt240(val items: List<Int240>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int240>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt240> {
            override fun decode(source: kotlin.String): ArrayOfInt240 = ArrayOfInt240(SolidityBase.decodeArray(source, Int240.Companion::decode))
        }
    }

    class ArrayOfInt248(val items: List<Int248>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int248>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt248> {
            override fun decode(source: kotlin.String): ArrayOfInt248 = ArrayOfInt248(SolidityBase.decodeArray(source, Int248.Companion::decode))
        }
    }

    class ArrayOfInt256(val items: List<Int256>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int256>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt256> {
            override fun decode(source: kotlin.String): ArrayOfInt256 = ArrayOfInt256(SolidityBase.decodeArray(source, Int256.Companion::decode))
        }
    }

    class ArrayOfInt(val items: List<Int>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfInt> {
            override fun decode(source: kotlin.String): ArrayOfInt = ArrayOfInt(SolidityBase.decodeArray(source, Int.Companion::decode))
        }
    }

    class ArrayOfUInt(val items: List<UInt>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt> {
            override fun decode(source: kotlin.String): ArrayOfUInt = ArrayOfUInt(SolidityBase.decodeArray(source, UInt.Companion::decode))
        }
    }

    class ArrayOfUInt8(val items: List<UInt8>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt8>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt8> {
            override fun decode(source: kotlin.String): ArrayOfUInt8 = ArrayOfUInt8(SolidityBase.decodeArray(source, UInt8.Companion::decode))
        }
    }

    class ArrayOfUInt16(val items: List<UInt16>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt16>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt16> {
            override fun decode(source: kotlin.String): ArrayOfUInt16 = ArrayOfUInt16(SolidityBase.decodeArray(source, UInt16.Companion::decode))
        }
    }

    class ArrayOfUInt24(val items: List<UInt24>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt24>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt24> {
            override fun decode(source: kotlin.String): ArrayOfUInt24 = ArrayOfUInt24(SolidityBase.decodeArray(source, UInt24.Companion::decode))
        }
    }

    class ArrayOfUInt32(val items: List<UInt32>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt32>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt32> {
            override fun decode(source: kotlin.String): ArrayOfUInt32 = ArrayOfUInt32(SolidityBase.decodeArray(source, UInt32.Companion::decode))
        }
    }

    class ArrayOfUInt40(val items: List<UInt40>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt40>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt40> {
            override fun decode(source: kotlin.String): ArrayOfUInt40 = ArrayOfUInt40(SolidityBase.decodeArray(source, UInt40.Companion::decode))
        }
    }

    class ArrayOfUInt48(val items: List<UInt48>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt48>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt48> {
            override fun decode(source: kotlin.String): ArrayOfUInt48 = ArrayOfUInt48(SolidityBase.decodeArray(source, UInt48.Companion::decode))
        }
    }

    class ArrayOfUInt56(val items: List<UInt56>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt56>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt56> {
            override fun decode(source: kotlin.String): ArrayOfUInt56 = ArrayOfUInt56(SolidityBase.decodeArray(source, UInt56.Companion::decode))
        }
    }

    class ArrayOfUInt64(val items: List<UInt64>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt64>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt64> {
            override fun decode(source: kotlin.String): ArrayOfUInt64 = ArrayOfUInt64(SolidityBase.decodeArray(source, UInt64.Companion::decode))
        }
    }

    class ArrayOfUInt72(val items: List<UInt72>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt72>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt72> {
            override fun decode(source: kotlin.String): ArrayOfUInt72 = ArrayOfUInt72(SolidityBase.decodeArray(source, UInt72.Companion::decode))
        }
    }

    class ArrayOfUInt80(val items: List<UInt80>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt80>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt80> {
            override fun decode(source: kotlin.String): ArrayOfUInt80 = ArrayOfUInt80(SolidityBase.decodeArray(source, UInt80.Companion::decode))
        }
    }

    class ArrayOfUInt88(val items: List<UInt88>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt88>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt88> {
            override fun decode(source: kotlin.String): ArrayOfUInt88 = ArrayOfUInt88(SolidityBase.decodeArray(source, UInt88.Companion::decode))
        }
    }

    class ArrayOfUInt96(val items: List<UInt96>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt96>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt96> {
            override fun decode(source: kotlin.String): ArrayOfUInt96 = ArrayOfUInt96(SolidityBase.decodeArray(source, UInt96.Companion::decode))
        }
    }

    class ArrayOfUInt104(val items: List<UInt104>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt104>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt104> {
            override fun decode(source: kotlin.String): ArrayOfUInt104 = ArrayOfUInt104(SolidityBase.decodeArray(source, UInt104.Companion::decode))
        }
    }

    class ArrayOfUInt112(val items: List<UInt112>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt112>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt112> {
            override fun decode(source: kotlin.String): ArrayOfUInt112 = ArrayOfUInt112(SolidityBase.decodeArray(source, UInt112.Companion::decode))
        }
    }

    class ArrayOfUInt120(val items: List<UInt120>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt120>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt120> {
            override fun decode(source: kotlin.String): ArrayOfUInt120 = ArrayOfUInt120(SolidityBase.decodeArray(source, UInt120.Companion::decode))
        }
    }

    class ArrayOfUInt128(val items: List<UInt128>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt128>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt128> {
            override fun decode(source: kotlin.String): ArrayOfUInt128 = ArrayOfUInt128(SolidityBase.decodeArray(source, UInt128.Companion::decode))
        }
    }

    class ArrayOfUInt136(val items: List<UInt136>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt136>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt136> {
            override fun decode(source: kotlin.String): ArrayOfUInt136 = ArrayOfUInt136(SolidityBase.decodeArray(source, UInt136.Companion::decode))
        }
    }

    class ArrayOfUInt144(val items: List<UInt144>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt144>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt144> {
            override fun decode(source: kotlin.String): ArrayOfUInt144 = ArrayOfUInt144(SolidityBase.decodeArray(source, UInt144.Companion::decode))
        }
    }

    class ArrayOfUInt152(val items: List<UInt152>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt152>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt152> {
            override fun decode(source: kotlin.String): ArrayOfUInt152 = ArrayOfUInt152(SolidityBase.decodeArray(source, UInt152.Companion::decode))
        }
    }

    class ArrayOfUInt160(val items: List<UInt160>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt160>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt160> {
            override fun decode(source: kotlin.String): ArrayOfUInt160 = ArrayOfUInt160(SolidityBase.decodeArray(source, UInt160.Companion::decode))
        }
    }

    class ArrayOfUInt168(val items: List<UInt168>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt168>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt168> {
            override fun decode(source: kotlin.String): ArrayOfUInt168 = ArrayOfUInt168(SolidityBase.decodeArray(source, UInt168.Companion::decode))
        }
    }

    class ArrayOfUInt176(val items: List<UInt176>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt176>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt176> {
            override fun decode(source: kotlin.String): ArrayOfUInt176 = ArrayOfUInt176(SolidityBase.decodeArray(source, UInt176.Companion::decode))
        }
    }

    class ArrayOfUInt184(val items: List<UInt184>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt184>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt184> {
            override fun decode(source: kotlin.String): ArrayOfUInt184 = ArrayOfUInt184(SolidityBase.decodeArray(source, UInt184.Companion::decode))
        }
    }

    class ArrayOfUInt192(val items: List<UInt192>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt192>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt192> {
            override fun decode(source: kotlin.String): ArrayOfUInt192 = ArrayOfUInt192(SolidityBase.decodeArray(source, UInt192.Companion::decode))
        }
    }

    class ArrayOfUInt200(val items: List<UInt200>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt200>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt200> {
            override fun decode(source: kotlin.String): ArrayOfUInt200 = ArrayOfUInt200(SolidityBase.decodeArray(source, UInt200.Companion::decode))
        }
    }

    class ArrayOfUInt208(val items: List<UInt208>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt208>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt208> {
            override fun decode(source: kotlin.String): ArrayOfUInt208 = ArrayOfUInt208(SolidityBase.decodeArray(source, UInt208.Companion::decode))
        }
    }

    class ArrayOfUInt216(val items: List<UInt216>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt216>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt216> {
            override fun decode(source: kotlin.String): ArrayOfUInt216 = ArrayOfUInt216(SolidityBase.decodeArray(source, UInt216.Companion::decode))
        }
    }

    class ArrayOfUInt224(val items: List<UInt224>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt224>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt224> {
            override fun decode(source: kotlin.String): ArrayOfUInt224 = ArrayOfUInt224(SolidityBase.decodeArray(source, UInt224.Companion::decode))
        }
    }

    class ArrayOfUInt232(val items: List<UInt232>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt232>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt232> {
            override fun decode(source: kotlin.String): ArrayOfUInt232 = ArrayOfUInt232(SolidityBase.decodeArray(source, UInt232.Companion::decode))
        }
    }

    class ArrayOfUInt240(val items: List<UInt240>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt240>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt240> {
            override fun decode(source: kotlin.String): ArrayOfUInt240 = ArrayOfUInt240(SolidityBase.decodeArray(source, UInt240.Companion::decode))
        }
    }

    class ArrayOfUInt248(val items: List<UInt248>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt248>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt248> {
            override fun decode(source: kotlin.String): ArrayOfUInt248 = ArrayOfUInt248(SolidityBase.decodeArray(source, UInt248.Companion::decode))
        }
    }

    class ArrayOfUInt256(val items: List<UInt256>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt256>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfUInt256> {
            override fun decode(source: kotlin.String): ArrayOfUInt256 = ArrayOfUInt256(SolidityBase.decodeArray(source, UInt256.Companion::decode))
        }
    }

    class ArrayOfBytes1(val items: List<Bytes1>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes1>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes1> {
            override fun decode(source: kotlin.String): ArrayOfBytes1 = ArrayOfBytes1(SolidityBase.decodeArray(source, Bytes1.Companion::decode))
        }
    }

    class ArrayOfBytes2(val items: List<Bytes2>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes2>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes2> {
            override fun decode(source: kotlin.String): ArrayOfBytes2 = ArrayOfBytes2(SolidityBase.decodeArray(source, Bytes2.Companion::decode))
        }
    }

    class ArrayOfBytes3(val items: List<Bytes3>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes3>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes3> {
            override fun decode(source: kotlin.String): ArrayOfBytes3 = ArrayOfBytes3(SolidityBase.decodeArray(source, Bytes3.Companion::decode))
        }
    }

    class ArrayOfBytes4(val items: List<Bytes4>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes4>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes4> {
            override fun decode(source: kotlin.String): ArrayOfBytes4 = ArrayOfBytes4(SolidityBase.decodeArray(source, Bytes4.Companion::decode))
        }
    }

    class ArrayOfBytes5(val items: List<Bytes5>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes5>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes5> {
            override fun decode(source: kotlin.String): ArrayOfBytes5 = ArrayOfBytes5(SolidityBase.decodeArray(source, Bytes5.Companion::decode))
        }
    }

    class ArrayOfBytes6(val items: List<Bytes6>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes6>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes6> {
            override fun decode(source: kotlin.String): ArrayOfBytes6 = ArrayOfBytes6(SolidityBase.decodeArray(source, Bytes6.Companion::decode))
        }
    }

    class ArrayOfBytes7(val items: List<Bytes7>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes7>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes7> {
            override fun decode(source: kotlin.String): ArrayOfBytes7 = ArrayOfBytes7(SolidityBase.decodeArray(source, Bytes7.Companion::decode))
        }
    }

    class ArrayOfBytes8(val items: List<Bytes8>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes8>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes8> {
            override fun decode(source: kotlin.String): ArrayOfBytes8 = ArrayOfBytes8(SolidityBase.decodeArray(source, Bytes8.Companion::decode))
        }
    }

    class ArrayOfBytes9(val items: List<Bytes9>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes9>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes9> {
            override fun decode(source: kotlin.String): ArrayOfBytes9 = ArrayOfBytes9(SolidityBase.decodeArray(source, Bytes9.Companion::decode))
        }
    }

    class ArrayOfBytes10(val items: List<Bytes10>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes10>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes10> {
            override fun decode(source: kotlin.String): ArrayOfBytes10 = ArrayOfBytes10(SolidityBase.decodeArray(source, Bytes10.Companion::decode))
        }
    }

    class ArrayOfBytes11(val items: List<Bytes11>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes11>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes11> {
            override fun decode(source: kotlin.String): ArrayOfBytes11 = ArrayOfBytes11(SolidityBase.decodeArray(source, Bytes11.Companion::decode))
        }
    }

    class ArrayOfBytes12(val items: List<Bytes12>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes12>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes12> {
            override fun decode(source: kotlin.String): ArrayOfBytes12 = ArrayOfBytes12(SolidityBase.decodeArray(source, Bytes12.Companion::decode))
        }
    }

    class ArrayOfBytes13(val items: List<Bytes13>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes13>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes13> {
            override fun decode(source: kotlin.String): ArrayOfBytes13 = ArrayOfBytes13(SolidityBase.decodeArray(source, Bytes13.Companion::decode))
        }
    }

    class ArrayOfBytes14(val items: List<Bytes14>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes14>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes14> {
            override fun decode(source: kotlin.String): ArrayOfBytes14 = ArrayOfBytes14(SolidityBase.decodeArray(source, Bytes14.Companion::decode))
        }
    }

    class ArrayOfBytes15(val items: List<Bytes15>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes15>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes15> {
            override fun decode(source: kotlin.String): ArrayOfBytes15 = ArrayOfBytes15(SolidityBase.decodeArray(source, Bytes15.Companion::decode))
        }
    }

    class ArrayOfBytes16(val items: List<Bytes16>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes16>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes16> {
            override fun decode(source: kotlin.String): ArrayOfBytes16 = ArrayOfBytes16(SolidityBase.decodeArray(source, Bytes16.Companion::decode))
        }
    }

    class ArrayOfBytes17(val items: List<Bytes17>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes17>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes17> {
            override fun decode(source: kotlin.String): ArrayOfBytes17 = ArrayOfBytes17(SolidityBase.decodeArray(source, Bytes17.Companion::decode))
        }
    }

    class ArrayOfBytes18(val items: List<Bytes18>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes18>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes18> {
            override fun decode(source: kotlin.String): ArrayOfBytes18 = ArrayOfBytes18(SolidityBase.decodeArray(source, Bytes18.Companion::decode))
        }
    }

    class ArrayOfBytes19(val items: List<Bytes19>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes19>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes19> {
            override fun decode(source: kotlin.String): ArrayOfBytes19 = ArrayOfBytes19(SolidityBase.decodeArray(source, Bytes19.Companion::decode))
        }
    }

    class ArrayOfBytes20(val items: List<Bytes20>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes20>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes20> {
            override fun decode(source: kotlin.String): ArrayOfBytes20 = ArrayOfBytes20(SolidityBase.decodeArray(source, Bytes20.Companion::decode))
        }
    }

    class ArrayOfBytes21(val items: List<Bytes21>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes21>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes21> {
            override fun decode(source: kotlin.String): ArrayOfBytes21 = ArrayOfBytes21(SolidityBase.decodeArray(source, Bytes21.Companion::decode))
        }
    }

    class ArrayOfBytes22(val items: List<Bytes22>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes22>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes22> {
            override fun decode(source: kotlin.String): ArrayOfBytes22 = ArrayOfBytes22(SolidityBase.decodeArray(source, Bytes22.Companion::decode))
        }
    }

    class ArrayOfBytes23(val items: List<Bytes23>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes23>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes23> {
            override fun decode(source: kotlin.String): ArrayOfBytes23 = ArrayOfBytes23(SolidityBase.decodeArray(source, Bytes23.Companion::decode))
        }
    }

    class ArrayOfBytes24(val items: List<Bytes24>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes24>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes24> {
            override fun decode(source: kotlin.String): ArrayOfBytes24 = ArrayOfBytes24(SolidityBase.decodeArray(source, Bytes24.Companion::decode))
        }
    }

    class ArrayOfBytes25(val items: List<Bytes25>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes25>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes25> {
            override fun decode(source: kotlin.String): ArrayOfBytes25 = ArrayOfBytes25(SolidityBase.decodeArray(source, Bytes25.Companion::decode))
        }
    }

    class ArrayOfBytes26(val items: List<Bytes26>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes26>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes26> {
            override fun decode(source: kotlin.String): ArrayOfBytes26 = ArrayOfBytes26(SolidityBase.decodeArray(source, Bytes26.Companion::decode))
        }
    }

    class ArrayOfBytes27(val items: List<Bytes27>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes27>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes27> {
            override fun decode(source: kotlin.String): ArrayOfBytes27 = ArrayOfBytes27(SolidityBase.decodeArray(source, Bytes27.Companion::decode))
        }
    }

    class ArrayOfBytes28(val items: List<Bytes28>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes28>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes28> {
            override fun decode(source: kotlin.String): ArrayOfBytes28 = ArrayOfBytes28(SolidityBase.decodeArray(source, Bytes28.Companion::decode))
        }
    }

    class ArrayOfBytes29(val items: List<Bytes29>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes29>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes29> {
            override fun decode(source: kotlin.String): ArrayOfBytes29 = ArrayOfBytes29(SolidityBase.decodeArray(source, Bytes29.Companion::decode))
        }
    }

    class ArrayOfBytes30(val items: List<Bytes30>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes30>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes30> {
            override fun decode(source: kotlin.String): ArrayOfBytes30 = ArrayOfBytes30(SolidityBase.decodeArray(source, Bytes30.Companion::decode))
        }
    }

    class ArrayOfBytes31(val items: List<Bytes31>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes31>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes31> {
            override fun decode(source: kotlin.String): ArrayOfBytes31 = ArrayOfBytes31(SolidityBase.decodeArray(source, Bytes31.Companion::decode))
        }
    }

    class ArrayOfBytes32(val items: List<Bytes32>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes32>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBytes32> {
            override fun decode(source: kotlin.String): ArrayOfBytes32 = ArrayOfBytes32(SolidityBase.decodeArray(source, Bytes32.Companion::decode))
        }
    }

    class Address(val value: BigInteger) : SolidityBase.UIntBase(value, 160) {
        companion object : SolidityBase.Type.Decoder<Address> {
            override fun decode(source: kotlin.String): Address = Address(BigInteger(source, 16))
        }
    }

    class ArrayOfAddress(val items: List<Address>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Address>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfAddress> {
            override fun decode(source: kotlin.String): ArrayOfAddress = ArrayOfAddress(SolidityBase.decodeArray(source, Address.Companion::decode))
        }
    }

    class Bool(val value: Boolean) : SolidityBase.UIntBase(if (value) BigInteger.ONE else BigInteger.ZERO, 8) {
        companion object : SolidityBase.Type.Decoder<Bool> {
            override fun decode(source: kotlin.String): Bool = Bool(BigInteger(source, 16) != BigInteger.ZERO)
        }
    }

    class ArrayOfBool(val items: List<Bool>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bool>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfBool> {
            override fun decode(source: kotlin.String): ArrayOfBool = ArrayOfBool(SolidityBase.decodeArray(source, Bool.Companion::decode))
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

        override fun encodeParts(): SolidityBase.DynamicType.Parts {
            val length = items.size.toString(16).padStart(64, '0')
            val contents = items.toHex().padEndMultiple(64, '0')
            return SolidityBase.DynamicType.Parts(length, contents)
        }
        companion object : SolidityBase.Type.Decoder<Bytes> {
            override fun decode(source: kotlin.String): Bytes = Bytes(SolidityBase.decodeBytes(source))
        }
    }

    class Byte(val value: ByteArray) : SolidityBase.StaticBytes(value, 1) {
        companion object : SolidityBase.Type.Decoder<Byte> {
            override fun decode(source: kotlin.String): Byte = Byte(SolidityBase.decodeStaticBytes(source, 1))
        }
    }

    class ArrayOfByte(val items: List<Byte>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Byte>(items) {
        companion object : SolidityBase.Type.Decoder<ArrayOfByte> {
            override fun decode(source: kotlin.String): ArrayOfByte = ArrayOfByte(SolidityBase.decodeArray(source, Byte.Companion::decode))
        }
    }

    class String(val value: kotlin.String) : Bytes(value.toByteArray()) {
        companion object : SolidityBase.Type.Decoder<String> {
            override fun decode(source: kotlin.String): String = String(SolidityBase.decodeString(source))
        }
    }
}
