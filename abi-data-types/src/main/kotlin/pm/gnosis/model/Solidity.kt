package pm.gnosis.model

import java.lang.Exception
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.Boolean
import kotlin.ByteArray
import kotlin.collections.List
import kotlin.collections.Map
import pm.gnosis.utils.hexToByteArray
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

  class UInt8(value: BigInteger) : SolidityBase.UInt(value, 8) {
    companion object : SolidityBase.Type.Decoder<UInt8> {
      override fun decode(source: kotlin.String): UInt8 = UInt8(BigInteger(source, 16))
    }
  }

  class UInt16(value: BigInteger) : SolidityBase.UInt(value, 16) {
    companion object : SolidityBase.Type.Decoder<UInt16> {
      override fun decode(source: kotlin.String): UInt16 = UInt16(BigInteger(source, 16))
    }
  }

  class UInt24(value: BigInteger) : SolidityBase.UInt(value, 24) {
    companion object : SolidityBase.Type.Decoder<UInt24> {
      override fun decode(source: kotlin.String): UInt24 = UInt24(BigInteger(source, 16))
    }
  }

  class UInt32(value: BigInteger) : SolidityBase.UInt(value, 32) {
    companion object : SolidityBase.Type.Decoder<UInt32> {
      override fun decode(source: kotlin.String): UInt32 = UInt32(BigInteger(source, 16))
    }
  }

  class UInt40(value: BigInteger) : SolidityBase.UInt(value, 40) {
    companion object : SolidityBase.Type.Decoder<UInt40> {
      override fun decode(source: kotlin.String): UInt40 = UInt40(BigInteger(source, 16))
    }
  }

  class UInt48(value: BigInteger) : SolidityBase.UInt(value, 48) {
    companion object : SolidityBase.Type.Decoder<UInt48> {
      override fun decode(source: kotlin.String): UInt48 = UInt48(BigInteger(source, 16))
    }
  }

  class UInt56(value: BigInteger) : SolidityBase.UInt(value, 56) {
    companion object : SolidityBase.Type.Decoder<UInt56> {
      override fun decode(source: kotlin.String): UInt56 = UInt56(BigInteger(source, 16))
    }
  }

  class UInt64(value: BigInteger) : SolidityBase.UInt(value, 64) {
    companion object : SolidityBase.Type.Decoder<UInt64> {
      override fun decode(source: kotlin.String): UInt64 = UInt64(BigInteger(source, 16))
    }
  }

  class UInt72(value: BigInteger) : SolidityBase.UInt(value, 72) {
    companion object : SolidityBase.Type.Decoder<UInt72> {
      override fun decode(source: kotlin.String): UInt72 = UInt72(BigInteger(source, 16))
    }
  }

  class UInt80(value: BigInteger) : SolidityBase.UInt(value, 80) {
    companion object : SolidityBase.Type.Decoder<UInt80> {
      override fun decode(source: kotlin.String): UInt80 = UInt80(BigInteger(source, 16))
    }
  }

  class UInt88(value: BigInteger) : SolidityBase.UInt(value, 88) {
    companion object : SolidityBase.Type.Decoder<UInt88> {
      override fun decode(source: kotlin.String): UInt88 = UInt88(BigInteger(source, 16))
    }
  }

  class UInt96(value: BigInteger) : SolidityBase.UInt(value, 96) {
    companion object : SolidityBase.Type.Decoder<UInt96> {
      override fun decode(source: kotlin.String): UInt96 = UInt96(BigInteger(source, 16))
    }
  }

  class UInt104(value: BigInteger) : SolidityBase.UInt(value, 104) {
    companion object : SolidityBase.Type.Decoder<UInt104> {
      override fun decode(source: kotlin.String): UInt104 = UInt104(BigInteger(source, 16))
    }
  }

  class UInt112(value: BigInteger) : SolidityBase.UInt(value, 112) {
    companion object : SolidityBase.Type.Decoder<UInt112> {
      override fun decode(source: kotlin.String): UInt112 = UInt112(BigInteger(source, 16))
    }
  }

  class UInt120(value: BigInteger) : SolidityBase.UInt(value, 120) {
    companion object : SolidityBase.Type.Decoder<UInt120> {
      override fun decode(source: kotlin.String): UInt120 = UInt120(BigInteger(source, 16))
    }
  }

  class UInt128(value: BigInteger) : SolidityBase.UInt(value, 128) {
    companion object : SolidityBase.Type.Decoder<UInt128> {
      override fun decode(source: kotlin.String): UInt128 = UInt128(BigInteger(source, 16))
    }
  }

  class UInt136(value: BigInteger) : SolidityBase.UInt(value, 136) {
    companion object : SolidityBase.Type.Decoder<UInt136> {
      override fun decode(source: kotlin.String): UInt136 = UInt136(BigInteger(source, 16))
    }
  }

  class UInt144(value: BigInteger) : SolidityBase.UInt(value, 144) {
    companion object : SolidityBase.Type.Decoder<UInt144> {
      override fun decode(source: kotlin.String): UInt144 = UInt144(BigInteger(source, 16))
    }
  }

  class UInt152(value: BigInteger) : SolidityBase.UInt(value, 152) {
    companion object : SolidityBase.Type.Decoder<UInt152> {
      override fun decode(source: kotlin.String): UInt152 = UInt152(BigInteger(source, 16))
    }
  }

  class UInt160(value: BigInteger) : SolidityBase.UInt(value, 160) {
    companion object : SolidityBase.Type.Decoder<UInt160> {
      override fun decode(source: kotlin.String): UInt160 = UInt160(BigInteger(source, 16))
    }
  }

  class UInt168(value: BigInteger) : SolidityBase.UInt(value, 168) {
    companion object : SolidityBase.Type.Decoder<UInt168> {
      override fun decode(source: kotlin.String): UInt168 = UInt168(BigInteger(source, 16))
    }
  }

  class UInt176(value: BigInteger) : SolidityBase.UInt(value, 176) {
    companion object : SolidityBase.Type.Decoder<UInt176> {
      override fun decode(source: kotlin.String): UInt176 = UInt176(BigInteger(source, 16))
    }
  }

  class UInt184(value: BigInteger) : SolidityBase.UInt(value, 184) {
    companion object : SolidityBase.Type.Decoder<UInt184> {
      override fun decode(source: kotlin.String): UInt184 = UInt184(BigInteger(source, 16))
    }
  }

  class UInt192(value: BigInteger) : SolidityBase.UInt(value, 192) {
    companion object : SolidityBase.Type.Decoder<UInt192> {
      override fun decode(source: kotlin.String): UInt192 = UInt192(BigInteger(source, 16))
    }
  }

  class UInt200(value: BigInteger) : SolidityBase.UInt(value, 200) {
    companion object : SolidityBase.Type.Decoder<UInt200> {
      override fun decode(source: kotlin.String): UInt200 = UInt200(BigInteger(source, 16))
    }
  }

  class UInt208(value: BigInteger) : SolidityBase.UInt(value, 208) {
    companion object : SolidityBase.Type.Decoder<UInt208> {
      override fun decode(source: kotlin.String): UInt208 = UInt208(BigInteger(source, 16))
    }
  }

  class UInt216(value: BigInteger) : SolidityBase.UInt(value, 216) {
    companion object : SolidityBase.Type.Decoder<UInt216> {
      override fun decode(source: kotlin.String): UInt216 = UInt216(BigInteger(source, 16))
    }
  }

  class UInt224(value: BigInteger) : SolidityBase.UInt(value, 224) {
    companion object : SolidityBase.Type.Decoder<UInt224> {
      override fun decode(source: kotlin.String): UInt224 = UInt224(BigInteger(source, 16))
    }
  }

  class UInt232(value: BigInteger) : SolidityBase.UInt(value, 232) {
    companion object : SolidityBase.Type.Decoder<UInt232> {
      override fun decode(source: kotlin.String): UInt232 = UInt232(BigInteger(source, 16))
    }
  }

  class UInt240(value: BigInteger) : SolidityBase.UInt(value, 240) {
    companion object : SolidityBase.Type.Decoder<UInt240> {
      override fun decode(source: kotlin.String): UInt240 = UInt240(BigInteger(source, 16))
    }
  }

  class UInt248(value: BigInteger) : SolidityBase.UInt(value, 248) {
    companion object : SolidityBase.Type.Decoder<UInt248> {
      override fun decode(source: kotlin.String): UInt248 = UInt248(BigInteger(source, 16))
    }
  }

  class UInt256(value: BigInteger) : SolidityBase.UInt(value, 256) {
    companion object : SolidityBase.Type.Decoder<UInt256> {
      override fun decode(source: kotlin.String): UInt256 = UInt256(BigInteger(source, 16))
    }
  }

  class Int8(value: BigInteger) : SolidityBase.Int(value, 8) {
    companion object : SolidityBase.Type.Decoder<Int8> {
      override fun decode(source: kotlin.String): Int8 = Int8(BigInteger(source, 16))
    }
  }

  class Int16(value: BigInteger) : SolidityBase.Int(value, 16) {
    companion object : SolidityBase.Type.Decoder<Int16> {
      override fun decode(source: kotlin.String): Int16 = Int16(BigInteger(source, 16))
    }
  }

  class Int24(value: BigInteger) : SolidityBase.Int(value, 24) {
    companion object : SolidityBase.Type.Decoder<Int24> {
      override fun decode(source: kotlin.String): Int24 = Int24(BigInteger(source, 16))
    }
  }

  class Int32(value: BigInteger) : SolidityBase.Int(value, 32) {
    companion object : SolidityBase.Type.Decoder<Int32> {
      override fun decode(source: kotlin.String): Int32 = Int32(BigInteger(source, 16))
    }
  }

  class Int40(value: BigInteger) : SolidityBase.Int(value, 40) {
    companion object : SolidityBase.Type.Decoder<Int40> {
      override fun decode(source: kotlin.String): Int40 = Int40(BigInteger(source, 16))
    }
  }

  class Int48(value: BigInteger) : SolidityBase.Int(value, 48) {
    companion object : SolidityBase.Type.Decoder<Int48> {
      override fun decode(source: kotlin.String): Int48 = Int48(BigInteger(source, 16))
    }
  }

  class Int56(value: BigInteger) : SolidityBase.Int(value, 56) {
    companion object : SolidityBase.Type.Decoder<Int56> {
      override fun decode(source: kotlin.String): Int56 = Int56(BigInteger(source, 16))
    }
  }

  class Int64(value: BigInteger) : SolidityBase.Int(value, 64) {
    companion object : SolidityBase.Type.Decoder<Int64> {
      override fun decode(source: kotlin.String): Int64 = Int64(BigInteger(source, 16))
    }
  }

  class Int72(value: BigInteger) : SolidityBase.Int(value, 72) {
    companion object : SolidityBase.Type.Decoder<Int72> {
      override fun decode(source: kotlin.String): Int72 = Int72(BigInteger(source, 16))
    }
  }

  class Int80(value: BigInteger) : SolidityBase.Int(value, 80) {
    companion object : SolidityBase.Type.Decoder<Int80> {
      override fun decode(source: kotlin.String): Int80 = Int80(BigInteger(source, 16))
    }
  }

  class Int88(value: BigInteger) : SolidityBase.Int(value, 88) {
    companion object : SolidityBase.Type.Decoder<Int88> {
      override fun decode(source: kotlin.String): Int88 = Int88(BigInteger(source, 16))
    }
  }

  class Int96(value: BigInteger) : SolidityBase.Int(value, 96) {
    companion object : SolidityBase.Type.Decoder<Int96> {
      override fun decode(source: kotlin.String): Int96 = Int96(BigInteger(source, 16))
    }
  }

  class Int104(value: BigInteger) : SolidityBase.Int(value, 104) {
    companion object : SolidityBase.Type.Decoder<Int104> {
      override fun decode(source: kotlin.String): Int104 = Int104(BigInteger(source, 16))
    }
  }

  class Int112(value: BigInteger) : SolidityBase.Int(value, 112) {
    companion object : SolidityBase.Type.Decoder<Int112> {
      override fun decode(source: kotlin.String): Int112 = Int112(BigInteger(source, 16))
    }
  }

  class Int120(value: BigInteger) : SolidityBase.Int(value, 120) {
    companion object : SolidityBase.Type.Decoder<Int120> {
      override fun decode(source: kotlin.String): Int120 = Int120(BigInteger(source, 16))
    }
  }

  class Int128(value: BigInteger) : SolidityBase.Int(value, 128) {
    companion object : SolidityBase.Type.Decoder<Int128> {
      override fun decode(source: kotlin.String): Int128 = Int128(BigInteger(source, 16))
    }
  }

  class Int136(value: BigInteger) : SolidityBase.Int(value, 136) {
    companion object : SolidityBase.Type.Decoder<Int136> {
      override fun decode(source: kotlin.String): Int136 = Int136(BigInteger(source, 16))
    }
  }

  class Int144(value: BigInteger) : SolidityBase.Int(value, 144) {
    companion object : SolidityBase.Type.Decoder<Int144> {
      override fun decode(source: kotlin.String): Int144 = Int144(BigInteger(source, 16))
    }
  }

  class Int152(value: BigInteger) : SolidityBase.Int(value, 152) {
    companion object : SolidityBase.Type.Decoder<Int152> {
      override fun decode(source: kotlin.String): Int152 = Int152(BigInteger(source, 16))
    }
  }

  class Int160(value: BigInteger) : SolidityBase.Int(value, 160) {
    companion object : SolidityBase.Type.Decoder<Int160> {
      override fun decode(source: kotlin.String): Int160 = Int160(BigInteger(source, 16))
    }
  }

  class Int168(value: BigInteger) : SolidityBase.Int(value, 168) {
    companion object : SolidityBase.Type.Decoder<Int168> {
      override fun decode(source: kotlin.String): Int168 = Int168(BigInteger(source, 16))
    }
  }

  class Int176(value: BigInteger) : SolidityBase.Int(value, 176) {
    companion object : SolidityBase.Type.Decoder<Int176> {
      override fun decode(source: kotlin.String): Int176 = Int176(BigInteger(source, 16))
    }
  }

  class Int184(value: BigInteger) : SolidityBase.Int(value, 184) {
    companion object : SolidityBase.Type.Decoder<Int184> {
      override fun decode(source: kotlin.String): Int184 = Int184(BigInteger(source, 16))
    }
  }

  class Int192(value: BigInteger) : SolidityBase.Int(value, 192) {
    companion object : SolidityBase.Type.Decoder<Int192> {
      override fun decode(source: kotlin.String): Int192 = Int192(BigInteger(source, 16))
    }
  }

  class Int200(value: BigInteger) : SolidityBase.Int(value, 200) {
    companion object : SolidityBase.Type.Decoder<Int200> {
      override fun decode(source: kotlin.String): Int200 = Int200(BigInteger(source, 16))
    }
  }

  class Int208(value: BigInteger) : SolidityBase.Int(value, 208) {
    companion object : SolidityBase.Type.Decoder<Int208> {
      override fun decode(source: kotlin.String): Int208 = Int208(BigInteger(source, 16))
    }
  }

  class Int216(value: BigInteger) : SolidityBase.Int(value, 216) {
    companion object : SolidityBase.Type.Decoder<Int216> {
      override fun decode(source: kotlin.String): Int216 = Int216(BigInteger(source, 16))
    }
  }

  class Int224(value: BigInteger) : SolidityBase.Int(value, 224) {
    companion object : SolidityBase.Type.Decoder<Int224> {
      override fun decode(source: kotlin.String): Int224 = Int224(BigInteger(source, 16))
    }
  }

  class Int232(value: BigInteger) : SolidityBase.Int(value, 232) {
    companion object : SolidityBase.Type.Decoder<Int232> {
      override fun decode(source: kotlin.String): Int232 = Int232(BigInteger(source, 16))
    }
  }

  class Int240(value: BigInteger) : SolidityBase.Int(value, 240) {
    companion object : SolidityBase.Type.Decoder<Int240> {
      override fun decode(source: kotlin.String): Int240 = Int240(BigInteger(source, 16))
    }
  }

  class Int248(value: BigInteger) : SolidityBase.Int(value, 248) {
    companion object : SolidityBase.Type.Decoder<Int248> {
      override fun decode(source: kotlin.String): Int248 = Int248(BigInteger(source, 16))
    }
  }

  class Int256(value: BigInteger) : SolidityBase.Int(value, 256) {
    companion object : SolidityBase.Type.Decoder<Int256> {
      override fun decode(source: kotlin.String): Int256 = Int256(BigInteger(source, 16))
    }
  }

  class Bytes1(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 1) {
    companion object : SolidityBase.Type.Decoder<Bytes1> {
      override fun decode(source: kotlin.String): Bytes1 = Bytes1(source.substring(0, 1 * 2).hexToByteArray())
    }
  }

  class Bytes2(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 2) {
    companion object : SolidityBase.Type.Decoder<Bytes2> {
      override fun decode(source: kotlin.String): Bytes2 = Bytes2(source.substring(0, 2 * 2).hexToByteArray())
    }
  }

  class Bytes3(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 3) {
    companion object : SolidityBase.Type.Decoder<Bytes3> {
      override fun decode(source: kotlin.String): Bytes3 = Bytes3(source.substring(0, 3 * 2).hexToByteArray())
    }
  }

  class Bytes4(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 4) {
    companion object : SolidityBase.Type.Decoder<Bytes4> {
      override fun decode(source: kotlin.String): Bytes4 = Bytes4(source.substring(0, 4 * 2).hexToByteArray())
    }
  }

  class Bytes5(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 5) {
    companion object : SolidityBase.Type.Decoder<Bytes5> {
      override fun decode(source: kotlin.String): Bytes5 = Bytes5(source.substring(0, 5 * 2).hexToByteArray())
    }
  }

  class Bytes6(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 6) {
    companion object : SolidityBase.Type.Decoder<Bytes6> {
      override fun decode(source: kotlin.String): Bytes6 = Bytes6(source.substring(0, 6 * 2).hexToByteArray())
    }
  }

  class Bytes7(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 7) {
    companion object : SolidityBase.Type.Decoder<Bytes7> {
      override fun decode(source: kotlin.String): Bytes7 = Bytes7(source.substring(0, 7 * 2).hexToByteArray())
    }
  }

  class Bytes8(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 8) {
    companion object : SolidityBase.Type.Decoder<Bytes8> {
      override fun decode(source: kotlin.String): Bytes8 = Bytes8(source.substring(0, 8 * 2).hexToByteArray())
    }
  }

  class Bytes9(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 9) {
    companion object : SolidityBase.Type.Decoder<Bytes9> {
      override fun decode(source: kotlin.String): Bytes9 = Bytes9(source.substring(0, 9 * 2).hexToByteArray())
    }
  }

  class Bytes10(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 10) {
    companion object : SolidityBase.Type.Decoder<Bytes10> {
      override fun decode(source: kotlin.String): Bytes10 = Bytes10(source.substring(0, 10 * 2).hexToByteArray())
    }
  }

  class Bytes11(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 11) {
    companion object : SolidityBase.Type.Decoder<Bytes11> {
      override fun decode(source: kotlin.String): Bytes11 = Bytes11(source.substring(0, 11 * 2).hexToByteArray())
    }
  }

  class Bytes12(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 12) {
    companion object : SolidityBase.Type.Decoder<Bytes12> {
      override fun decode(source: kotlin.String): Bytes12 = Bytes12(source.substring(0, 12 * 2).hexToByteArray())
    }
  }

  class Bytes13(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 13) {
    companion object : SolidityBase.Type.Decoder<Bytes13> {
      override fun decode(source: kotlin.String): Bytes13 = Bytes13(source.substring(0, 13 * 2).hexToByteArray())
    }
  }

  class Bytes14(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 14) {
    companion object : SolidityBase.Type.Decoder<Bytes14> {
      override fun decode(source: kotlin.String): Bytes14 = Bytes14(source.substring(0, 14 * 2).hexToByteArray())
    }
  }

  class Bytes15(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 15) {
    companion object : SolidityBase.Type.Decoder<Bytes15> {
      override fun decode(source: kotlin.String): Bytes15 = Bytes15(source.substring(0, 15 * 2).hexToByteArray())
    }
  }

  class Bytes16(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 16) {
    companion object : SolidityBase.Type.Decoder<Bytes16> {
      override fun decode(source: kotlin.String): Bytes16 = Bytes16(source.substring(0, 16 * 2).hexToByteArray())
    }
  }

  class Bytes17(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 17) {
    companion object : SolidityBase.Type.Decoder<Bytes17> {
      override fun decode(source: kotlin.String): Bytes17 = Bytes17(source.substring(0, 17 * 2).hexToByteArray())
    }
  }

  class Bytes18(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 18) {
    companion object : SolidityBase.Type.Decoder<Bytes18> {
      override fun decode(source: kotlin.String): Bytes18 = Bytes18(source.substring(0, 18 * 2).hexToByteArray())
    }
  }

  class Bytes19(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 19) {
    companion object : SolidityBase.Type.Decoder<Bytes19> {
      override fun decode(source: kotlin.String): Bytes19 = Bytes19(source.substring(0, 19 * 2).hexToByteArray())
    }
  }

  class Bytes20(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 20) {
    companion object : SolidityBase.Type.Decoder<Bytes20> {
      override fun decode(source: kotlin.String): Bytes20 = Bytes20(source.substring(0, 20 * 2).hexToByteArray())
    }
  }

  class Bytes21(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 21) {
    companion object : SolidityBase.Type.Decoder<Bytes21> {
      override fun decode(source: kotlin.String): Bytes21 = Bytes21(source.substring(0, 21 * 2).hexToByteArray())
    }
  }

  class Bytes22(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 22) {
    companion object : SolidityBase.Type.Decoder<Bytes22> {
      override fun decode(source: kotlin.String): Bytes22 = Bytes22(source.substring(0, 22 * 2).hexToByteArray())
    }
  }

  class Bytes23(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 23) {
    companion object : SolidityBase.Type.Decoder<Bytes23> {
      override fun decode(source: kotlin.String): Bytes23 = Bytes23(source.substring(0, 23 * 2).hexToByteArray())
    }
  }

  class Bytes24(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 24) {
    companion object : SolidityBase.Type.Decoder<Bytes24> {
      override fun decode(source: kotlin.String): Bytes24 = Bytes24(source.substring(0, 24 * 2).hexToByteArray())
    }
  }

  class Bytes25(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 25) {
    companion object : SolidityBase.Type.Decoder<Bytes25> {
      override fun decode(source: kotlin.String): Bytes25 = Bytes25(source.substring(0, 25 * 2).hexToByteArray())
    }
  }

  class Bytes26(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 26) {
    companion object : SolidityBase.Type.Decoder<Bytes26> {
      override fun decode(source: kotlin.String): Bytes26 = Bytes26(source.substring(0, 26 * 2).hexToByteArray())
    }
  }

  class Bytes27(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 27) {
    companion object : SolidityBase.Type.Decoder<Bytes27> {
      override fun decode(source: kotlin.String): Bytes27 = Bytes27(source.substring(0, 27 * 2).hexToByteArray())
    }
  }

  class Bytes28(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 28) {
    companion object : SolidityBase.Type.Decoder<Bytes28> {
      override fun decode(source: kotlin.String): Bytes28 = Bytes28(source.substring(0, 28 * 2).hexToByteArray())
    }
  }

  class Bytes29(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 29) {
    companion object : SolidityBase.Type.Decoder<Bytes29> {
      override fun decode(source: kotlin.String): Bytes29 = Bytes29(source.substring(0, 29 * 2).hexToByteArray())
    }
  }

  class Bytes30(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 30) {
    companion object : SolidityBase.Type.Decoder<Bytes30> {
      override fun decode(source: kotlin.String): Bytes30 = Bytes30(source.substring(0, 30 * 2).hexToByteArray())
    }
  }

  class Bytes31(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 31) {
    companion object : SolidityBase.Type.Decoder<Bytes31> {
      override fun decode(source: kotlin.String): Bytes31 = Bytes31(source.substring(0, 31 * 2).hexToByteArray())
    }
  }

  class Bytes32(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 32) {
    companion object : SolidityBase.Type.Decoder<Bytes32> {
      override fun decode(source: kotlin.String): Bytes32 = Bytes32(source.substring(0, 32 * 2).hexToByteArray())
    }
  }

  class ArrayOfInt8(items: List<Int8>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int8>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt8> {
      override fun decode(source: kotlin.String): ArrayOfInt8 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt8(ArrayList())
        return ArrayOfInt8((1 until partitions.size).map { Int8.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt16(items: List<Int16>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int16>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt16> {
      override fun decode(source: kotlin.String): ArrayOfInt16 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt16(ArrayList())
        return ArrayOfInt16((1 until partitions.size).map { Int16.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt24(items: List<Int24>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int24>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt24> {
      override fun decode(source: kotlin.String): ArrayOfInt24 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt24(ArrayList())
        return ArrayOfInt24((1 until partitions.size).map { Int24.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt32(items: List<Int32>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int32>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt32> {
      override fun decode(source: kotlin.String): ArrayOfInt32 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt32(ArrayList())
        return ArrayOfInt32((1 until partitions.size).map { Int32.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt40(items: List<Int40>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int40>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt40> {
      override fun decode(source: kotlin.String): ArrayOfInt40 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt40(ArrayList())
        return ArrayOfInt40((1 until partitions.size).map { Int40.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt48(items: List<Int48>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int48>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt48> {
      override fun decode(source: kotlin.String): ArrayOfInt48 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt48(ArrayList())
        return ArrayOfInt48((1 until partitions.size).map { Int48.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt56(items: List<Int56>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int56>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt56> {
      override fun decode(source: kotlin.String): ArrayOfInt56 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt56(ArrayList())
        return ArrayOfInt56((1 until partitions.size).map { Int56.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt64(items: List<Int64>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int64>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt64> {
      override fun decode(source: kotlin.String): ArrayOfInt64 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt64(ArrayList())
        return ArrayOfInt64((1 until partitions.size).map { Int64.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt72(items: List<Int72>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int72>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt72> {
      override fun decode(source: kotlin.String): ArrayOfInt72 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt72(ArrayList())
        return ArrayOfInt72((1 until partitions.size).map { Int72.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt80(items: List<Int80>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int80>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt80> {
      override fun decode(source: kotlin.String): ArrayOfInt80 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt80(ArrayList())
        return ArrayOfInt80((1 until partitions.size).map { Int80.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt88(items: List<Int88>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int88>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt88> {
      override fun decode(source: kotlin.String): ArrayOfInt88 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt88(ArrayList())
        return ArrayOfInt88((1 until partitions.size).map { Int88.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt96(items: List<Int96>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int96>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt96> {
      override fun decode(source: kotlin.String): ArrayOfInt96 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt96(ArrayList())
        return ArrayOfInt96((1 until partitions.size).map { Int96.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt104(items: List<Int104>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int104>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt104> {
      override fun decode(source: kotlin.String): ArrayOfInt104 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt104(ArrayList())
        return ArrayOfInt104((1 until partitions.size).map { Int104.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt112(items: List<Int112>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int112>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt112> {
      override fun decode(source: kotlin.String): ArrayOfInt112 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt112(ArrayList())
        return ArrayOfInt112((1 until partitions.size).map { Int112.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt120(items: List<Int120>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int120>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt120> {
      override fun decode(source: kotlin.String): ArrayOfInt120 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt120(ArrayList())
        return ArrayOfInt120((1 until partitions.size).map { Int120.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt128(items: List<Int128>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int128>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt128> {
      override fun decode(source: kotlin.String): ArrayOfInt128 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt128(ArrayList())
        return ArrayOfInt128((1 until partitions.size).map { Int128.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt136(items: List<Int136>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int136>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt136> {
      override fun decode(source: kotlin.String): ArrayOfInt136 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt136(ArrayList())
        return ArrayOfInt136((1 until partitions.size).map { Int136.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt144(items: List<Int144>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int144>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt144> {
      override fun decode(source: kotlin.String): ArrayOfInt144 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt144(ArrayList())
        return ArrayOfInt144((1 until partitions.size).map { Int144.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt152(items: List<Int152>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int152>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt152> {
      override fun decode(source: kotlin.String): ArrayOfInt152 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt152(ArrayList())
        return ArrayOfInt152((1 until partitions.size).map { Int152.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt160(items: List<Int160>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int160>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt160> {
      override fun decode(source: kotlin.String): ArrayOfInt160 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt160(ArrayList())
        return ArrayOfInt160((1 until partitions.size).map { Int160.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt168(items: List<Int168>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int168>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt168> {
      override fun decode(source: kotlin.String): ArrayOfInt168 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt168(ArrayList())
        return ArrayOfInt168((1 until partitions.size).map { Int168.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt176(items: List<Int176>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int176>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt176> {
      override fun decode(source: kotlin.String): ArrayOfInt176 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt176(ArrayList())
        return ArrayOfInt176((1 until partitions.size).map { Int176.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt184(items: List<Int184>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int184>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt184> {
      override fun decode(source: kotlin.String): ArrayOfInt184 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt184(ArrayList())
        return ArrayOfInt184((1 until partitions.size).map { Int184.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt192(items: List<Int192>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int192>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt192> {
      override fun decode(source: kotlin.String): ArrayOfInt192 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt192(ArrayList())
        return ArrayOfInt192((1 until partitions.size).map { Int192.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt200(items: List<Int200>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int200>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt200> {
      override fun decode(source: kotlin.String): ArrayOfInt200 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt200(ArrayList())
        return ArrayOfInt200((1 until partitions.size).map { Int200.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt208(items: List<Int208>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int208>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt208> {
      override fun decode(source: kotlin.String): ArrayOfInt208 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt208(ArrayList())
        return ArrayOfInt208((1 until partitions.size).map { Int208.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt216(items: List<Int216>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int216>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt216> {
      override fun decode(source: kotlin.String): ArrayOfInt216 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt216(ArrayList())
        return ArrayOfInt216((1 until partitions.size).map { Int216.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt224(items: List<Int224>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int224>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt224> {
      override fun decode(source: kotlin.String): ArrayOfInt224 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt224(ArrayList())
        return ArrayOfInt224((1 until partitions.size).map { Int224.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt232(items: List<Int232>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int232>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt232> {
      override fun decode(source: kotlin.String): ArrayOfInt232 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt232(ArrayList())
        return ArrayOfInt232((1 until partitions.size).map { Int232.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt240(items: List<Int240>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int240>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt240> {
      override fun decode(source: kotlin.String): ArrayOfInt240 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt240(ArrayList())
        return ArrayOfInt240((1 until partitions.size).map { Int240.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt248(items: List<Int248>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int248>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt248> {
      override fun decode(source: kotlin.String): ArrayOfInt248 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt248(ArrayList())
        return ArrayOfInt248((1 until partitions.size).map { Int248.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt256(items: List<Int256>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int256>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt256> {
      override fun decode(source: kotlin.String): ArrayOfInt256 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt256(ArrayList())
        return ArrayOfInt256((1 until partitions.size).map { Int256.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt8(items: List<UInt8>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt8>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt8> {
      override fun decode(source: kotlin.String): ArrayOfUInt8 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt8(ArrayList())
        return ArrayOfUInt8((1 until partitions.size).map { UInt8.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt16(items: List<UInt16>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt16>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt16> {
      override fun decode(source: kotlin.String): ArrayOfUInt16 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt16(ArrayList())
        return ArrayOfUInt16((1 until partitions.size).map { UInt16.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt24(items: List<UInt24>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt24>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt24> {
      override fun decode(source: kotlin.String): ArrayOfUInt24 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt24(ArrayList())
        return ArrayOfUInt24((1 until partitions.size).map { UInt24.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt32(items: List<UInt32>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt32>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt32> {
      override fun decode(source: kotlin.String): ArrayOfUInt32 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt32(ArrayList())
        return ArrayOfUInt32((1 until partitions.size).map { UInt32.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt40(items: List<UInt40>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt40>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt40> {
      override fun decode(source: kotlin.String): ArrayOfUInt40 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt40(ArrayList())
        return ArrayOfUInt40((1 until partitions.size).map { UInt40.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt48(items: List<UInt48>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt48>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt48> {
      override fun decode(source: kotlin.String): ArrayOfUInt48 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt48(ArrayList())
        return ArrayOfUInt48((1 until partitions.size).map { UInt48.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt56(items: List<UInt56>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt56>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt56> {
      override fun decode(source: kotlin.String): ArrayOfUInt56 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt56(ArrayList())
        return ArrayOfUInt56((1 until partitions.size).map { UInt56.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt64(items: List<UInt64>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt64>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt64> {
      override fun decode(source: kotlin.String): ArrayOfUInt64 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt64(ArrayList())
        return ArrayOfUInt64((1 until partitions.size).map { UInt64.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt72(items: List<UInt72>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt72>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt72> {
      override fun decode(source: kotlin.String): ArrayOfUInt72 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt72(ArrayList())
        return ArrayOfUInt72((1 until partitions.size).map { UInt72.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt80(items: List<UInt80>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt80>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt80> {
      override fun decode(source: kotlin.String): ArrayOfUInt80 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt80(ArrayList())
        return ArrayOfUInt80((1 until partitions.size).map { UInt80.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt88(items: List<UInt88>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt88>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt88> {
      override fun decode(source: kotlin.String): ArrayOfUInt88 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt88(ArrayList())
        return ArrayOfUInt88((1 until partitions.size).map { UInt88.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt96(items: List<UInt96>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt96>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt96> {
      override fun decode(source: kotlin.String): ArrayOfUInt96 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt96(ArrayList())
        return ArrayOfUInt96((1 until partitions.size).map { UInt96.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt104(items: List<UInt104>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt104>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt104> {
      override fun decode(source: kotlin.String): ArrayOfUInt104 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt104(ArrayList())
        return ArrayOfUInt104((1 until partitions.size).map { UInt104.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt112(items: List<UInt112>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt112>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt112> {
      override fun decode(source: kotlin.String): ArrayOfUInt112 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt112(ArrayList())
        return ArrayOfUInt112((1 until partitions.size).map { UInt112.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt120(items: List<UInt120>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt120>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt120> {
      override fun decode(source: kotlin.String): ArrayOfUInt120 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt120(ArrayList())
        return ArrayOfUInt120((1 until partitions.size).map { UInt120.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt128(items: List<UInt128>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt128>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt128> {
      override fun decode(source: kotlin.String): ArrayOfUInt128 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt128(ArrayList())
        return ArrayOfUInt128((1 until partitions.size).map { UInt128.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt136(items: List<UInt136>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt136>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt136> {
      override fun decode(source: kotlin.String): ArrayOfUInt136 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt136(ArrayList())
        return ArrayOfUInt136((1 until partitions.size).map { UInt136.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt144(items: List<UInt144>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt144>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt144> {
      override fun decode(source: kotlin.String): ArrayOfUInt144 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt144(ArrayList())
        return ArrayOfUInt144((1 until partitions.size).map { UInt144.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt152(items: List<UInt152>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt152>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt152> {
      override fun decode(source: kotlin.String): ArrayOfUInt152 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt152(ArrayList())
        return ArrayOfUInt152((1 until partitions.size).map { UInt152.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt160(items: List<UInt160>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt160>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt160> {
      override fun decode(source: kotlin.String): ArrayOfUInt160 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt160(ArrayList())
        return ArrayOfUInt160((1 until partitions.size).map { UInt160.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt168(items: List<UInt168>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt168>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt168> {
      override fun decode(source: kotlin.String): ArrayOfUInt168 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt168(ArrayList())
        return ArrayOfUInt168((1 until partitions.size).map { UInt168.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt176(items: List<UInt176>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt176>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt176> {
      override fun decode(source: kotlin.String): ArrayOfUInt176 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt176(ArrayList())
        return ArrayOfUInt176((1 until partitions.size).map { UInt176.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt184(items: List<UInt184>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt184>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt184> {
      override fun decode(source: kotlin.String): ArrayOfUInt184 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt184(ArrayList())
        return ArrayOfUInt184((1 until partitions.size).map { UInt184.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt192(items: List<UInt192>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt192>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt192> {
      override fun decode(source: kotlin.String): ArrayOfUInt192 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt192(ArrayList())
        return ArrayOfUInt192((1 until partitions.size).map { UInt192.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt200(items: List<UInt200>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt200>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt200> {
      override fun decode(source: kotlin.String): ArrayOfUInt200 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt200(ArrayList())
        return ArrayOfUInt200((1 until partitions.size).map { UInt200.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt208(items: List<UInt208>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt208>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt208> {
      override fun decode(source: kotlin.String): ArrayOfUInt208 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt208(ArrayList())
        return ArrayOfUInt208((1 until partitions.size).map { UInt208.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt216(items: List<UInt216>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt216>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt216> {
      override fun decode(source: kotlin.String): ArrayOfUInt216 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt216(ArrayList())
        return ArrayOfUInt216((1 until partitions.size).map { UInt216.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt224(items: List<UInt224>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt224>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt224> {
      override fun decode(source: kotlin.String): ArrayOfUInt224 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt224(ArrayList())
        return ArrayOfUInt224((1 until partitions.size).map { UInt224.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt232(items: List<UInt232>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt232>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt232> {
      override fun decode(source: kotlin.String): ArrayOfUInt232 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt232(ArrayList())
        return ArrayOfUInt232((1 until partitions.size).map { UInt232.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt240(items: List<UInt240>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt240>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt240> {
      override fun decode(source: kotlin.String): ArrayOfUInt240 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt240(ArrayList())
        return ArrayOfUInt240((1 until partitions.size).map { UInt240.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt248(items: List<UInt248>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt248>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt248> {
      override fun decode(source: kotlin.String): ArrayOfUInt248 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt248(ArrayList())
        return ArrayOfUInt248((1 until partitions.size).map { UInt248.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt256(items: List<UInt256>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt256>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt256> {
      override fun decode(source: kotlin.String): ArrayOfUInt256 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt256(ArrayList())
        return ArrayOfUInt256((1 until partitions.size).map { UInt256.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes1(items: List<Bytes1>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes1>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes1> {
      override fun decode(source: kotlin.String): ArrayOfBytes1 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes1(ArrayList())
        return ArrayOfBytes1((1 until partitions.size).map { Bytes1.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes2(items: List<Bytes2>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes2>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes2> {
      override fun decode(source: kotlin.String): ArrayOfBytes2 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes2(ArrayList())
        return ArrayOfBytes2((1 until partitions.size).map { Bytes2.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes3(items: List<Bytes3>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes3>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes3> {
      override fun decode(source: kotlin.String): ArrayOfBytes3 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes3(ArrayList())
        return ArrayOfBytes3((1 until partitions.size).map { Bytes3.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes4(items: List<Bytes4>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes4>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes4> {
      override fun decode(source: kotlin.String): ArrayOfBytes4 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes4(ArrayList())
        return ArrayOfBytes4((1 until partitions.size).map { Bytes4.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes5(items: List<Bytes5>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes5>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes5> {
      override fun decode(source: kotlin.String): ArrayOfBytes5 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes5(ArrayList())
        return ArrayOfBytes5((1 until partitions.size).map { Bytes5.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes6(items: List<Bytes6>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes6>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes6> {
      override fun decode(source: kotlin.String): ArrayOfBytes6 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes6(ArrayList())
        return ArrayOfBytes6((1 until partitions.size).map { Bytes6.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes7(items: List<Bytes7>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes7>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes7> {
      override fun decode(source: kotlin.String): ArrayOfBytes7 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes7(ArrayList())
        return ArrayOfBytes7((1 until partitions.size).map { Bytes7.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes8(items: List<Bytes8>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes8>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes8> {
      override fun decode(source: kotlin.String): ArrayOfBytes8 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes8(ArrayList())
        return ArrayOfBytes8((1 until partitions.size).map { Bytes8.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes9(items: List<Bytes9>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes9>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes9> {
      override fun decode(source: kotlin.String): ArrayOfBytes9 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes9(ArrayList())
        return ArrayOfBytes9((1 until partitions.size).map { Bytes9.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes10(items: List<Bytes10>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes10>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes10> {
      override fun decode(source: kotlin.String): ArrayOfBytes10 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes10(ArrayList())
        return ArrayOfBytes10((1 until partitions.size).map { Bytes10.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes11(items: List<Bytes11>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes11>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes11> {
      override fun decode(source: kotlin.String): ArrayOfBytes11 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes11(ArrayList())
        return ArrayOfBytes11((1 until partitions.size).map { Bytes11.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes12(items: List<Bytes12>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes12>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes12> {
      override fun decode(source: kotlin.String): ArrayOfBytes12 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes12(ArrayList())
        return ArrayOfBytes12((1 until partitions.size).map { Bytes12.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes13(items: List<Bytes13>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes13>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes13> {
      override fun decode(source: kotlin.String): ArrayOfBytes13 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes13(ArrayList())
        return ArrayOfBytes13((1 until partitions.size).map { Bytes13.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes14(items: List<Bytes14>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes14>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes14> {
      override fun decode(source: kotlin.String): ArrayOfBytes14 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes14(ArrayList())
        return ArrayOfBytes14((1 until partitions.size).map { Bytes14.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes15(items: List<Bytes15>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes15>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes15> {
      override fun decode(source: kotlin.String): ArrayOfBytes15 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes15(ArrayList())
        return ArrayOfBytes15((1 until partitions.size).map { Bytes15.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes16(items: List<Bytes16>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes16>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes16> {
      override fun decode(source: kotlin.String): ArrayOfBytes16 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes16(ArrayList())
        return ArrayOfBytes16((1 until partitions.size).map { Bytes16.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes17(items: List<Bytes17>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes17>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes17> {
      override fun decode(source: kotlin.String): ArrayOfBytes17 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes17(ArrayList())
        return ArrayOfBytes17((1 until partitions.size).map { Bytes17.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes18(items: List<Bytes18>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes18>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes18> {
      override fun decode(source: kotlin.String): ArrayOfBytes18 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes18(ArrayList())
        return ArrayOfBytes18((1 until partitions.size).map { Bytes18.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes19(items: List<Bytes19>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes19>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes19> {
      override fun decode(source: kotlin.String): ArrayOfBytes19 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes19(ArrayList())
        return ArrayOfBytes19((1 until partitions.size).map { Bytes19.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes20(items: List<Bytes20>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes20>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes20> {
      override fun decode(source: kotlin.String): ArrayOfBytes20 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes20(ArrayList())
        return ArrayOfBytes20((1 until partitions.size).map { Bytes20.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes21(items: List<Bytes21>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes21>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes21> {
      override fun decode(source: kotlin.String): ArrayOfBytes21 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes21(ArrayList())
        return ArrayOfBytes21((1 until partitions.size).map { Bytes21.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes22(items: List<Bytes22>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes22>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes22> {
      override fun decode(source: kotlin.String): ArrayOfBytes22 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes22(ArrayList())
        return ArrayOfBytes22((1 until partitions.size).map { Bytes22.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes23(items: List<Bytes23>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes23>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes23> {
      override fun decode(source: kotlin.String): ArrayOfBytes23 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes23(ArrayList())
        return ArrayOfBytes23((1 until partitions.size).map { Bytes23.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes24(items: List<Bytes24>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes24>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes24> {
      override fun decode(source: kotlin.String): ArrayOfBytes24 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes24(ArrayList())
        return ArrayOfBytes24((1 until partitions.size).map { Bytes24.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes25(items: List<Bytes25>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes25>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes25> {
      override fun decode(source: kotlin.String): ArrayOfBytes25 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes25(ArrayList())
        return ArrayOfBytes25((1 until partitions.size).map { Bytes25.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes26(items: List<Bytes26>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes26>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes26> {
      override fun decode(source: kotlin.String): ArrayOfBytes26 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes26(ArrayList())
        return ArrayOfBytes26((1 until partitions.size).map { Bytes26.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes27(items: List<Bytes27>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes27>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes27> {
      override fun decode(source: kotlin.String): ArrayOfBytes27 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes27(ArrayList())
        return ArrayOfBytes27((1 until partitions.size).map { Bytes27.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes28(items: List<Bytes28>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes28>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes28> {
      override fun decode(source: kotlin.String): ArrayOfBytes28 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes28(ArrayList())
        return ArrayOfBytes28((1 until partitions.size).map { Bytes28.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes29(items: List<Bytes29>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes29>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes29> {
      override fun decode(source: kotlin.String): ArrayOfBytes29 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes29(ArrayList())
        return ArrayOfBytes29((1 until partitions.size).map { Bytes29.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes30(items: List<Bytes30>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes30>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes30> {
      override fun decode(source: kotlin.String): ArrayOfBytes30 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes30(ArrayList())
        return ArrayOfBytes30((1 until partitions.size).map { Bytes30.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes31(items: List<Bytes31>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes31>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes31> {
      override fun decode(source: kotlin.String): ArrayOfBytes31 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes31(ArrayList())
        return ArrayOfBytes31((1 until partitions.size).map { Bytes31.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes32(items: List<Bytes32>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes32>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes32> {
      override fun decode(source: kotlin.String): ArrayOfBytes32 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes32(ArrayList())
        return ArrayOfBytes32((1 until partitions.size).map { Bytes32.decode(partitions[it]) }.toList())
      }
    }
  }

  class Address(value: BigInteger) : SolidityBase.UInt(value, 160) {
    companion object : SolidityBase.Type.Decoder<Address> {
      override fun decode(source: kotlin.String): Address = Address(BigInteger(source, 16))
    }
  }

  class ArrayOfAddress(items: List<Address>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Address>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfAddress> {
      override fun decode(source: kotlin.String): ArrayOfAddress {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfAddress(ArrayList())
        return ArrayOfAddress((1 until partitions.size).map { Address.decode(partitions[it]) }.toList())
      }
    }
  }

  class Bool(value: Boolean) : SolidityBase.UInt(if (value) BigInteger.ONE else BigInteger.ZERO, 8) {
    companion object : SolidityBase.Type.Decoder<Bool> {
      override fun decode(source: kotlin.String): Bool = Bool(BigInteger(source, 16) != BigInteger.ZERO)
    }
  }

  class ArrayOfBool(items: List<Bool>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bool>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBool> {
      override fun decode(source: kotlin.String): ArrayOfBool {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBool(ArrayList())
        return ArrayOfBool((1 until partitions.size).map { Bool.decode(partitions[it]) }.toList())
      }
    }
  }

  open class Bytes(val bytes: ByteArray) : SolidityBase.DynamicType {
    init {
      if (BigInteger(bytes.size.toString(10)) > BigInteger.valueOf(2).pow(256)) throw Exception()
    }

    override fun encode(): kotlin.String {
      val parts = encodeParts()
      return parts.static + parts.dynamic
    }

    override fun encodeParts(): SolidityBase.DynamicType.Parts {
      val length = bytes.size.toString(16).padStart(64, '0')
      val contents = bytes.toHex().padEndMultiple(64, '0')
      return SolidityBase.DynamicType.Parts(length, contents)
    }
  }

  class Byte(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 1) {
    companion object : SolidityBase.Type.Decoder<Byte> {
      override fun decode(source: kotlin.String): Byte = Byte(SolidityBase.decodeStaticBytes(source, 1))
    }
  }

  class ArrayOfByte(items: List<Byte>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Byte>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfByte> {
      override fun decode(source: kotlin.String): ArrayOfByte {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfByte(ArrayList())
        return ArrayOfByte((1 until partitions.size).map { Byte.decode(partitions[it]) }.toList())
      }
    }
  }

  class String(string: kotlin.String) : Bytes(string.toByteArray())
}
