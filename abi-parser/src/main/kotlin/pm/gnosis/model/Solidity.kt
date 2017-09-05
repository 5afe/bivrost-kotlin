package pm.gnosis.model

import java.lang.Exception
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.Boolean
import kotlin.ByteArray
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.reflect.KClass
import pm.gnosis.utils.hexToByteArray
import pm.gnosis.utils.padEndMultiple
import pm.gnosis.utils.toHex

/**
 * Generated code. Do not modify
 */
object Solidity {
  val map: Map<String, KClass<*>> = mapOf(
      "uint8" to UInt8::class,
      "uint16" to UInt16::class,
      "uint24" to UInt24::class,
      "uint32" to UInt32::class,
      "uint40" to UInt40::class,
      "uint48" to UInt48::class,
      "uint56" to UInt56::class,
      "uint64" to UInt64::class,
      "uint72" to UInt72::class,
      "uint80" to UInt80::class,
      "uint88" to UInt88::class,
      "uint96" to UInt96::class,
      "uint104" to UInt104::class,
      "uint112" to UInt112::class,
      "uint120" to UInt120::class,
      "uint128" to UInt128::class,
      "uint136" to UInt136::class,
      "uint144" to UInt144::class,
      "uint152" to UInt152::class,
      "uint160" to UInt160::class,
      "uint168" to UInt168::class,
      "uint176" to UInt176::class,
      "uint184" to UInt184::class,
      "uint192" to UInt192::class,
      "uint200" to UInt200::class,
      "uint208" to UInt208::class,
      "uint216" to UInt216::class,
      "uint224" to UInt224::class,
      "uint232" to UInt232::class,
      "uint240" to UInt240::class,
      "uint248" to UInt248::class,
      "uint256" to UInt256::class,
      "int8" to Int8::class,
      "int16" to Int16::class,
      "int24" to Int24::class,
      "int32" to Int32::class,
      "int40" to Int40::class,
      "int48" to Int48::class,
      "int56" to Int56::class,
      "int64" to Int64::class,
      "int72" to Int72::class,
      "int80" to Int80::class,
      "int88" to Int88::class,
      "int96" to Int96::class,
      "int104" to Int104::class,
      "int112" to Int112::class,
      "int120" to Int120::class,
      "int128" to Int128::class,
      "int136" to Int136::class,
      "int144" to Int144::class,
      "int152" to Int152::class,
      "int160" to Int160::class,
      "int168" to Int168::class,
      "int176" to Int176::class,
      "int184" to Int184::class,
      "int192" to Int192::class,
      "int200" to Int200::class,
      "int208" to Int208::class,
      "int216" to Int216::class,
      "int224" to Int224::class,
      "int232" to Int232::class,
      "int240" to Int240::class,
      "int248" to Int248::class,
      "int256" to Int256::class,
      "bytes1" to Bytes1::class,
      "bytes2" to Bytes2::class,
      "bytes3" to Bytes3::class,
      "bytes4" to Bytes4::class,
      "bytes5" to Bytes5::class,
      "bytes6" to Bytes6::class,
      "bytes7" to Bytes7::class,
      "bytes8" to Bytes8::class,
      "bytes9" to Bytes9::class,
      "bytes10" to Bytes10::class,
      "bytes11" to Bytes11::class,
      "bytes12" to Bytes12::class,
      "bytes13" to Bytes13::class,
      "bytes14" to Bytes14::class,
      "bytes15" to Bytes15::class,
      "bytes16" to Bytes16::class,
      "bytes17" to Bytes17::class,
      "bytes18" to Bytes18::class,
      "bytes19" to Bytes19::class,
      "bytes20" to Bytes20::class,
      "bytes21" to Bytes21::class,
      "bytes22" to Bytes22::class,
      "bytes23" to Bytes23::class,
      "bytes24" to Bytes24::class,
      "bytes25" to Bytes25::class,
      "bytes26" to Bytes26::class,
      "bytes27" to Bytes27::class,
      "bytes28" to Bytes28::class,
      "bytes29" to Bytes29::class,
      "bytes30" to Bytes30::class,
      "bytes31" to Bytes31::class,
      "bytes32" to Bytes32::class,
      "int8[]" to ArrayOfInt8::class,
      "int16[]" to ArrayOfInt16::class,
      "int24[]" to ArrayOfInt24::class,
      "int32[]" to ArrayOfInt32::class,
      "int40[]" to ArrayOfInt40::class,
      "int48[]" to ArrayOfInt48::class,
      "int56[]" to ArrayOfInt56::class,
      "int64[]" to ArrayOfInt64::class,
      "int72[]" to ArrayOfInt72::class,
      "int80[]" to ArrayOfInt80::class,
      "int88[]" to ArrayOfInt88::class,
      "int96[]" to ArrayOfInt96::class,
      "int104[]" to ArrayOfInt104::class,
      "int112[]" to ArrayOfInt112::class,
      "int120[]" to ArrayOfInt120::class,
      "int128[]" to ArrayOfInt128::class,
      "int136[]" to ArrayOfInt136::class,
      "int144[]" to ArrayOfInt144::class,
      "int152[]" to ArrayOfInt152::class,
      "int160[]" to ArrayOfInt160::class,
      "int168[]" to ArrayOfInt168::class,
      "int176[]" to ArrayOfInt176::class,
      "int184[]" to ArrayOfInt184::class,
      "int192[]" to ArrayOfInt192::class,
      "int200[]" to ArrayOfInt200::class,
      "int208[]" to ArrayOfInt208::class,
      "int216[]" to ArrayOfInt216::class,
      "int224[]" to ArrayOfInt224::class,
      "int232[]" to ArrayOfInt232::class,
      "int240[]" to ArrayOfInt240::class,
      "int248[]" to ArrayOfInt248::class,
      "int256[]" to ArrayOfInt256::class,
      "uint8[]" to ArrayOfUInt8::class,
      "uint16[]" to ArrayOfUInt16::class,
      "uint24[]" to ArrayOfUInt24::class,
      "uint32[]" to ArrayOfUInt32::class,
      "uint40[]" to ArrayOfUInt40::class,
      "uint48[]" to ArrayOfUInt48::class,
      "uint56[]" to ArrayOfUInt56::class,
      "uint64[]" to ArrayOfUInt64::class,
      "uint72[]" to ArrayOfUInt72::class,
      "uint80[]" to ArrayOfUInt80::class,
      "uint88[]" to ArrayOfUInt88::class,
      "uint96[]" to ArrayOfUInt96::class,
      "uint104[]" to ArrayOfUInt104::class,
      "uint112[]" to ArrayOfUInt112::class,
      "uint120[]" to ArrayOfUInt120::class,
      "uint128[]" to ArrayOfUInt128::class,
      "uint136[]" to ArrayOfUInt136::class,
      "uint144[]" to ArrayOfUInt144::class,
      "uint152[]" to ArrayOfUInt152::class,
      "uint160[]" to ArrayOfUInt160::class,
      "uint168[]" to ArrayOfUInt168::class,
      "uint176[]" to ArrayOfUInt176::class,
      "uint184[]" to ArrayOfUInt184::class,
      "uint192[]" to ArrayOfUInt192::class,
      "uint200[]" to ArrayOfUInt200::class,
      "uint208[]" to ArrayOfUInt208::class,
      "uint216[]" to ArrayOfUInt216::class,
      "uint224[]" to ArrayOfUInt224::class,
      "uint232[]" to ArrayOfUInt232::class,
      "uint240[]" to ArrayOfUInt240::class,
      "uint248[]" to ArrayOfUInt248::class,
      "uint256[]" to ArrayOfUInt256::class,
      "bytes1[]" to ArrayOfBytes1::class,
      "bytes2[]" to ArrayOfBytes2::class,
      "bytes3[]" to ArrayOfBytes3::class,
      "bytes4[]" to ArrayOfBytes4::class,
      "bytes5[]" to ArrayOfBytes5::class,
      "bytes6[]" to ArrayOfBytes6::class,
      "bytes7[]" to ArrayOfBytes7::class,
      "bytes8[]" to ArrayOfBytes8::class,
      "bytes9[]" to ArrayOfBytes9::class,
      "bytes10[]" to ArrayOfBytes10::class,
      "bytes11[]" to ArrayOfBytes11::class,
      "bytes12[]" to ArrayOfBytes12::class,
      "bytes13[]" to ArrayOfBytes13::class,
      "bytes14[]" to ArrayOfBytes14::class,
      "bytes15[]" to ArrayOfBytes15::class,
      "bytes16[]" to ArrayOfBytes16::class,
      "bytes17[]" to ArrayOfBytes17::class,
      "bytes18[]" to ArrayOfBytes18::class,
      "bytes19[]" to ArrayOfBytes19::class,
      "bytes20[]" to ArrayOfBytes20::class,
      "bytes21[]" to ArrayOfBytes21::class,
      "bytes22[]" to ArrayOfBytes22::class,
      "bytes23[]" to ArrayOfBytes23::class,
      "bytes24[]" to ArrayOfBytes24::class,
      "bytes25[]" to ArrayOfBytes25::class,
      "bytes26[]" to ArrayOfBytes26::class,
      "bytes27[]" to ArrayOfBytes27::class,
      "bytes28[]" to ArrayOfBytes28::class,
      "bytes29[]" to ArrayOfBytes29::class,
      "bytes30[]" to ArrayOfBytes30::class,
      "bytes31[]" to ArrayOfBytes31::class,
      "bytes32[]" to ArrayOfBytes32::class,
      "address" to Address::class,
      "address[]" to ArrayOfAddress::class,
      "bool" to Bool::class,
      "bool[]" to ArrayOfBool::class,
      "bytes" to Bytes::class)

  class UInt8(value: BigInteger) : SolidityBase.UInt(value, 8) {
    companion object : SolidityBase.Type.Decoder<UInt8> {
      override fun decode(source: String): UInt8 = UInt8(BigInteger(source, 16))
    }
  }

  class UInt16(value: BigInteger) : SolidityBase.UInt(value, 16) {
    companion object : SolidityBase.Type.Decoder<UInt16> {
      override fun decode(source: String): UInt16 = UInt16(BigInteger(source, 16))
    }
  }

  class UInt24(value: BigInteger) : SolidityBase.UInt(value, 24) {
    companion object : SolidityBase.Type.Decoder<UInt24> {
      override fun decode(source: String): UInt24 = UInt24(BigInteger(source, 16))
    }
  }

  class UInt32(value: BigInteger) : SolidityBase.UInt(value, 32) {
    companion object : SolidityBase.Type.Decoder<UInt32> {
      override fun decode(source: String): UInt32 = UInt32(BigInteger(source, 16))
    }
  }

  class UInt40(value: BigInteger) : SolidityBase.UInt(value, 40) {
    companion object : SolidityBase.Type.Decoder<UInt40> {
      override fun decode(source: String): UInt40 = UInt40(BigInteger(source, 16))
    }
  }

  class UInt48(value: BigInteger) : SolidityBase.UInt(value, 48) {
    companion object : SolidityBase.Type.Decoder<UInt48> {
      override fun decode(source: String): UInt48 = UInt48(BigInteger(source, 16))
    }
  }

  class UInt56(value: BigInteger) : SolidityBase.UInt(value, 56) {
    companion object : SolidityBase.Type.Decoder<UInt56> {
      override fun decode(source: String): UInt56 = UInt56(BigInteger(source, 16))
    }
  }

  class UInt64(value: BigInteger) : SolidityBase.UInt(value, 64) {
    companion object : SolidityBase.Type.Decoder<UInt64> {
      override fun decode(source: String): UInt64 = UInt64(BigInteger(source, 16))
    }
  }

  class UInt72(value: BigInteger) : SolidityBase.UInt(value, 72) {
    companion object : SolidityBase.Type.Decoder<UInt72> {
      override fun decode(source: String): UInt72 = UInt72(BigInteger(source, 16))
    }
  }

  class UInt80(value: BigInteger) : SolidityBase.UInt(value, 80) {
    companion object : SolidityBase.Type.Decoder<UInt80> {
      override fun decode(source: String): UInt80 = UInt80(BigInteger(source, 16))
    }
  }

  class UInt88(value: BigInteger) : SolidityBase.UInt(value, 88) {
    companion object : SolidityBase.Type.Decoder<UInt88> {
      override fun decode(source: String): UInt88 = UInt88(BigInteger(source, 16))
    }
  }

  class UInt96(value: BigInteger) : SolidityBase.UInt(value, 96) {
    companion object : SolidityBase.Type.Decoder<UInt96> {
      override fun decode(source: String): UInt96 = UInt96(BigInteger(source, 16))
    }
  }

  class UInt104(value: BigInteger) : SolidityBase.UInt(value, 104) {
    companion object : SolidityBase.Type.Decoder<UInt104> {
      override fun decode(source: String): UInt104 = UInt104(BigInteger(source, 16))
    }
  }

  class UInt112(value: BigInteger) : SolidityBase.UInt(value, 112) {
    companion object : SolidityBase.Type.Decoder<UInt112> {
      override fun decode(source: String): UInt112 = UInt112(BigInteger(source, 16))
    }
  }

  class UInt120(value: BigInteger) : SolidityBase.UInt(value, 120) {
    companion object : SolidityBase.Type.Decoder<UInt120> {
      override fun decode(source: String): UInt120 = UInt120(BigInteger(source, 16))
    }
  }

  class UInt128(value: BigInteger) : SolidityBase.UInt(value, 128) {
    companion object : SolidityBase.Type.Decoder<UInt128> {
      override fun decode(source: String): UInt128 = UInt128(BigInteger(source, 16))
    }
  }

  class UInt136(value: BigInteger) : SolidityBase.UInt(value, 136) {
    companion object : SolidityBase.Type.Decoder<UInt136> {
      override fun decode(source: String): UInt136 = UInt136(BigInteger(source, 16))
    }
  }

  class UInt144(value: BigInteger) : SolidityBase.UInt(value, 144) {
    companion object : SolidityBase.Type.Decoder<UInt144> {
      override fun decode(source: String): UInt144 = UInt144(BigInteger(source, 16))
    }
  }

  class UInt152(value: BigInteger) : SolidityBase.UInt(value, 152) {
    companion object : SolidityBase.Type.Decoder<UInt152> {
      override fun decode(source: String): UInt152 = UInt152(BigInteger(source, 16))
    }
  }

  class UInt160(value: BigInteger) : SolidityBase.UInt(value, 160) {
    companion object : SolidityBase.Type.Decoder<UInt160> {
      override fun decode(source: String): UInt160 = UInt160(BigInteger(source, 16))
    }
  }

  class UInt168(value: BigInteger) : SolidityBase.UInt(value, 168) {
    companion object : SolidityBase.Type.Decoder<UInt168> {
      override fun decode(source: String): UInt168 = UInt168(BigInteger(source, 16))
    }
  }

  class UInt176(value: BigInteger) : SolidityBase.UInt(value, 176) {
    companion object : SolidityBase.Type.Decoder<UInt176> {
      override fun decode(source: String): UInt176 = UInt176(BigInteger(source, 16))
    }
  }

  class UInt184(value: BigInteger) : SolidityBase.UInt(value, 184) {
    companion object : SolidityBase.Type.Decoder<UInt184> {
      override fun decode(source: String): UInt184 = UInt184(BigInteger(source, 16))
    }
  }

  class UInt192(value: BigInteger) : SolidityBase.UInt(value, 192) {
    companion object : SolidityBase.Type.Decoder<UInt192> {
      override fun decode(source: String): UInt192 = UInt192(BigInteger(source, 16))
    }
  }

  class UInt200(value: BigInteger) : SolidityBase.UInt(value, 200) {
    companion object : SolidityBase.Type.Decoder<UInt200> {
      override fun decode(source: String): UInt200 = UInt200(BigInteger(source, 16))
    }
  }

  class UInt208(value: BigInteger) : SolidityBase.UInt(value, 208) {
    companion object : SolidityBase.Type.Decoder<UInt208> {
      override fun decode(source: String): UInt208 = UInt208(BigInteger(source, 16))
    }
  }

  class UInt216(value: BigInteger) : SolidityBase.UInt(value, 216) {
    companion object : SolidityBase.Type.Decoder<UInt216> {
      override fun decode(source: String): UInt216 = UInt216(BigInteger(source, 16))
    }
  }

  class UInt224(value: BigInteger) : SolidityBase.UInt(value, 224) {
    companion object : SolidityBase.Type.Decoder<UInt224> {
      override fun decode(source: String): UInt224 = UInt224(BigInteger(source, 16))
    }
  }

  class UInt232(value: BigInteger) : SolidityBase.UInt(value, 232) {
    companion object : SolidityBase.Type.Decoder<UInt232> {
      override fun decode(source: String): UInt232 = UInt232(BigInteger(source, 16))
    }
  }

  class UInt240(value: BigInteger) : SolidityBase.UInt(value, 240) {
    companion object : SolidityBase.Type.Decoder<UInt240> {
      override fun decode(source: String): UInt240 = UInt240(BigInteger(source, 16))
    }
  }

  class UInt248(value: BigInteger) : SolidityBase.UInt(value, 248) {
    companion object : SolidityBase.Type.Decoder<UInt248> {
      override fun decode(source: String): UInt248 = UInt248(BigInteger(source, 16))
    }
  }

  class UInt256(value: BigInteger) : SolidityBase.UInt(value, 256) {
    companion object : SolidityBase.Type.Decoder<UInt256> {
      override fun decode(source: String): UInt256 = UInt256(BigInteger(source, 16))
    }
  }

  class Int8(value: BigInteger) : SolidityBase.Int(value, 8) {
    companion object : SolidityBase.Type.Decoder<Int8> {
      override fun decode(source: String): Int8 = Int8(BigInteger(source, 16))
    }
  }

  class Int16(value: BigInteger) : SolidityBase.Int(value, 16) {
    companion object : SolidityBase.Type.Decoder<Int16> {
      override fun decode(source: String): Int16 = Int16(BigInteger(source, 16))
    }
  }

  class Int24(value: BigInteger) : SolidityBase.Int(value, 24) {
    companion object : SolidityBase.Type.Decoder<Int24> {
      override fun decode(source: String): Int24 = Int24(BigInteger(source, 16))
    }
  }

  class Int32(value: BigInteger) : SolidityBase.Int(value, 32) {
    companion object : SolidityBase.Type.Decoder<Int32> {
      override fun decode(source: String): Int32 = Int32(BigInteger(source, 16))
    }
  }

  class Int40(value: BigInteger) : SolidityBase.Int(value, 40) {
    companion object : SolidityBase.Type.Decoder<Int40> {
      override fun decode(source: String): Int40 = Int40(BigInteger(source, 16))
    }
  }

  class Int48(value: BigInteger) : SolidityBase.Int(value, 48) {
    companion object : SolidityBase.Type.Decoder<Int48> {
      override fun decode(source: String): Int48 = Int48(BigInteger(source, 16))
    }
  }

  class Int56(value: BigInteger) : SolidityBase.Int(value, 56) {
    companion object : SolidityBase.Type.Decoder<Int56> {
      override fun decode(source: String): Int56 = Int56(BigInteger(source, 16))
    }
  }

  class Int64(value: BigInteger) : SolidityBase.Int(value, 64) {
    companion object : SolidityBase.Type.Decoder<Int64> {
      override fun decode(source: String): Int64 = Int64(BigInteger(source, 16))
    }
  }

  class Int72(value: BigInteger) : SolidityBase.Int(value, 72) {
    companion object : SolidityBase.Type.Decoder<Int72> {
      override fun decode(source: String): Int72 = Int72(BigInteger(source, 16))
    }
  }

  class Int80(value: BigInteger) : SolidityBase.Int(value, 80) {
    companion object : SolidityBase.Type.Decoder<Int80> {
      override fun decode(source: String): Int80 = Int80(BigInteger(source, 16))
    }
  }

  class Int88(value: BigInteger) : SolidityBase.Int(value, 88) {
    companion object : SolidityBase.Type.Decoder<Int88> {
      override fun decode(source: String): Int88 = Int88(BigInteger(source, 16))
    }
  }

  class Int96(value: BigInteger) : SolidityBase.Int(value, 96) {
    companion object : SolidityBase.Type.Decoder<Int96> {
      override fun decode(source: String): Int96 = Int96(BigInteger(source, 16))
    }
  }

  class Int104(value: BigInteger) : SolidityBase.Int(value, 104) {
    companion object : SolidityBase.Type.Decoder<Int104> {
      override fun decode(source: String): Int104 = Int104(BigInteger(source, 16))
    }
  }

  class Int112(value: BigInteger) : SolidityBase.Int(value, 112) {
    companion object : SolidityBase.Type.Decoder<Int112> {
      override fun decode(source: String): Int112 = Int112(BigInteger(source, 16))
    }
  }

  class Int120(value: BigInteger) : SolidityBase.Int(value, 120) {
    companion object : SolidityBase.Type.Decoder<Int120> {
      override fun decode(source: String): Int120 = Int120(BigInteger(source, 16))
    }
  }

  class Int128(value: BigInteger) : SolidityBase.Int(value, 128) {
    companion object : SolidityBase.Type.Decoder<Int128> {
      override fun decode(source: String): Int128 = Int128(BigInteger(source, 16))
    }
  }

  class Int136(value: BigInteger) : SolidityBase.Int(value, 136) {
    companion object : SolidityBase.Type.Decoder<Int136> {
      override fun decode(source: String): Int136 = Int136(BigInteger(source, 16))
    }
  }

  class Int144(value: BigInteger) : SolidityBase.Int(value, 144) {
    companion object : SolidityBase.Type.Decoder<Int144> {
      override fun decode(source: String): Int144 = Int144(BigInteger(source, 16))
    }
  }

  class Int152(value: BigInteger) : SolidityBase.Int(value, 152) {
    companion object : SolidityBase.Type.Decoder<Int152> {
      override fun decode(source: String): Int152 = Int152(BigInteger(source, 16))
    }
  }

  class Int160(value: BigInteger) : SolidityBase.Int(value, 160) {
    companion object : SolidityBase.Type.Decoder<Int160> {
      override fun decode(source: String): Int160 = Int160(BigInteger(source, 16))
    }
  }

  class Int168(value: BigInteger) : SolidityBase.Int(value, 168) {
    companion object : SolidityBase.Type.Decoder<Int168> {
      override fun decode(source: String): Int168 = Int168(BigInteger(source, 16))
    }
  }

  class Int176(value: BigInteger) : SolidityBase.Int(value, 176) {
    companion object : SolidityBase.Type.Decoder<Int176> {
      override fun decode(source: String): Int176 = Int176(BigInteger(source, 16))
    }
  }

  class Int184(value: BigInteger) : SolidityBase.Int(value, 184) {
    companion object : SolidityBase.Type.Decoder<Int184> {
      override fun decode(source: String): Int184 = Int184(BigInteger(source, 16))
    }
  }

  class Int192(value: BigInteger) : SolidityBase.Int(value, 192) {
    companion object : SolidityBase.Type.Decoder<Int192> {
      override fun decode(source: String): Int192 = Int192(BigInteger(source, 16))
    }
  }

  class Int200(value: BigInteger) : SolidityBase.Int(value, 200) {
    companion object : SolidityBase.Type.Decoder<Int200> {
      override fun decode(source: String): Int200 = Int200(BigInteger(source, 16))
    }
  }

  class Int208(value: BigInteger) : SolidityBase.Int(value, 208) {
    companion object : SolidityBase.Type.Decoder<Int208> {
      override fun decode(source: String): Int208 = Int208(BigInteger(source, 16))
    }
  }

  class Int216(value: BigInteger) : SolidityBase.Int(value, 216) {
    companion object : SolidityBase.Type.Decoder<Int216> {
      override fun decode(source: String): Int216 = Int216(BigInteger(source, 16))
    }
  }

  class Int224(value: BigInteger) : SolidityBase.Int(value, 224) {
    companion object : SolidityBase.Type.Decoder<Int224> {
      override fun decode(source: String): Int224 = Int224(BigInteger(source, 16))
    }
  }

  class Int232(value: BigInteger) : SolidityBase.Int(value, 232) {
    companion object : SolidityBase.Type.Decoder<Int232> {
      override fun decode(source: String): Int232 = Int232(BigInteger(source, 16))
    }
  }

  class Int240(value: BigInteger) : SolidityBase.Int(value, 240) {
    companion object : SolidityBase.Type.Decoder<Int240> {
      override fun decode(source: String): Int240 = Int240(BigInteger(source, 16))
    }
  }

  class Int248(value: BigInteger) : SolidityBase.Int(value, 248) {
    companion object : SolidityBase.Type.Decoder<Int248> {
      override fun decode(source: String): Int248 = Int248(BigInteger(source, 16))
    }
  }

  class Int256(value: BigInteger) : SolidityBase.Int(value, 256) {
    companion object : SolidityBase.Type.Decoder<Int256> {
      override fun decode(source: String): Int256 = Int256(BigInteger(source, 16))
    }
  }

  class Bytes1(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 1) {
    companion object : SolidityBase.Type.Decoder<Bytes1> {
      override fun decode(source: String): Bytes1 = Bytes1(source.hexToByteArray())
    }
  }

  class Bytes2(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 2) {
    companion object : SolidityBase.Type.Decoder<Bytes2> {
      override fun decode(source: String): Bytes2 = Bytes2(source.hexToByteArray())
    }
  }

  class Bytes3(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 3) {
    companion object : SolidityBase.Type.Decoder<Bytes3> {
      override fun decode(source: String): Bytes3 = Bytes3(source.hexToByteArray())
    }
  }

  class Bytes4(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 4) {
    companion object : SolidityBase.Type.Decoder<Bytes4> {
      override fun decode(source: String): Bytes4 = Bytes4(source.hexToByteArray())
    }
  }

  class Bytes5(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 5) {
    companion object : SolidityBase.Type.Decoder<Bytes5> {
      override fun decode(source: String): Bytes5 = Bytes5(source.hexToByteArray())
    }
  }

  class Bytes6(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 6) {
    companion object : SolidityBase.Type.Decoder<Bytes6> {
      override fun decode(source: String): Bytes6 = Bytes6(source.hexToByteArray())
    }
  }

  class Bytes7(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 7) {
    companion object : SolidityBase.Type.Decoder<Bytes7> {
      override fun decode(source: String): Bytes7 = Bytes7(source.hexToByteArray())
    }
  }

  class Bytes8(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 8) {
    companion object : SolidityBase.Type.Decoder<Bytes8> {
      override fun decode(source: String): Bytes8 = Bytes8(source.hexToByteArray())
    }
  }

  class Bytes9(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 9) {
    companion object : SolidityBase.Type.Decoder<Bytes9> {
      override fun decode(source: String): Bytes9 = Bytes9(source.hexToByteArray())
    }
  }

  class Bytes10(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 10) {
    companion object : SolidityBase.Type.Decoder<Bytes10> {
      override fun decode(source: String): Bytes10 = Bytes10(source.hexToByteArray())
    }
  }

  class Bytes11(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 11) {
    companion object : SolidityBase.Type.Decoder<Bytes11> {
      override fun decode(source: String): Bytes11 = Bytes11(source.hexToByteArray())
    }
  }

  class Bytes12(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 12) {
    companion object : SolidityBase.Type.Decoder<Bytes12> {
      override fun decode(source: String): Bytes12 = Bytes12(source.hexToByteArray())
    }
  }

  class Bytes13(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 13) {
    companion object : SolidityBase.Type.Decoder<Bytes13> {
      override fun decode(source: String): Bytes13 = Bytes13(source.hexToByteArray())
    }
  }

  class Bytes14(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 14) {
    companion object : SolidityBase.Type.Decoder<Bytes14> {
      override fun decode(source: String): Bytes14 = Bytes14(source.hexToByteArray())
    }
  }

  class Bytes15(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 15) {
    companion object : SolidityBase.Type.Decoder<Bytes15> {
      override fun decode(source: String): Bytes15 = Bytes15(source.hexToByteArray())
    }
  }

  class Bytes16(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 16) {
    companion object : SolidityBase.Type.Decoder<Bytes16> {
      override fun decode(source: String): Bytes16 = Bytes16(source.hexToByteArray())
    }
  }

  class Bytes17(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 17) {
    companion object : SolidityBase.Type.Decoder<Bytes17> {
      override fun decode(source: String): Bytes17 = Bytes17(source.hexToByteArray())
    }
  }

  class Bytes18(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 18) {
    companion object : SolidityBase.Type.Decoder<Bytes18> {
      override fun decode(source: String): Bytes18 = Bytes18(source.hexToByteArray())
    }
  }

  class Bytes19(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 19) {
    companion object : SolidityBase.Type.Decoder<Bytes19> {
      override fun decode(source: String): Bytes19 = Bytes19(source.hexToByteArray())
    }
  }

  class Bytes20(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 20) {
    companion object : SolidityBase.Type.Decoder<Bytes20> {
      override fun decode(source: String): Bytes20 = Bytes20(source.hexToByteArray())
    }
  }

  class Bytes21(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 21) {
    companion object : SolidityBase.Type.Decoder<Bytes21> {
      override fun decode(source: String): Bytes21 = Bytes21(source.hexToByteArray())
    }
  }

  class Bytes22(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 22) {
    companion object : SolidityBase.Type.Decoder<Bytes22> {
      override fun decode(source: String): Bytes22 = Bytes22(source.hexToByteArray())
    }
  }

  class Bytes23(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 23) {
    companion object : SolidityBase.Type.Decoder<Bytes23> {
      override fun decode(source: String): Bytes23 = Bytes23(source.hexToByteArray())
    }
  }

  class Bytes24(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 24) {
    companion object : SolidityBase.Type.Decoder<Bytes24> {
      override fun decode(source: String): Bytes24 = Bytes24(source.hexToByteArray())
    }
  }

  class Bytes25(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 25) {
    companion object : SolidityBase.Type.Decoder<Bytes25> {
      override fun decode(source: String): Bytes25 = Bytes25(source.hexToByteArray())
    }
  }

  class Bytes26(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 26) {
    companion object : SolidityBase.Type.Decoder<Bytes26> {
      override fun decode(source: String): Bytes26 = Bytes26(source.hexToByteArray())
    }
  }

  class Bytes27(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 27) {
    companion object : SolidityBase.Type.Decoder<Bytes27> {
      override fun decode(source: String): Bytes27 = Bytes27(source.hexToByteArray())
    }
  }

  class Bytes28(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 28) {
    companion object : SolidityBase.Type.Decoder<Bytes28> {
      override fun decode(source: String): Bytes28 = Bytes28(source.hexToByteArray())
    }
  }

  class Bytes29(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 29) {
    companion object : SolidityBase.Type.Decoder<Bytes29> {
      override fun decode(source: String): Bytes29 = Bytes29(source.hexToByteArray())
    }
  }

  class Bytes30(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 30) {
    companion object : SolidityBase.Type.Decoder<Bytes30> {
      override fun decode(source: String): Bytes30 = Bytes30(source.hexToByteArray())
    }
  }

  class Bytes31(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 31) {
    companion object : SolidityBase.Type.Decoder<Bytes31> {
      override fun decode(source: String): Bytes31 = Bytes31(source.hexToByteArray())
    }
  }

  class Bytes32(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 32) {
    companion object : SolidityBase.Type.Decoder<Bytes32> {
      override fun decode(source: String): Bytes32 = Bytes32(source.hexToByteArray())
    }
  }

  class ArrayOfInt8(items: List<Int8>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int8>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt8> {
      override fun decode(source: String): ArrayOfInt8 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt8(ArrayList())
        return ArrayOfInt8((1 until partitions.size).map { Int8.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt16(items: List<Int16>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int16>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt16> {
      override fun decode(source: String): ArrayOfInt16 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt16(ArrayList())
        return ArrayOfInt16((1 until partitions.size).map { Int16.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt24(items: List<Int24>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int24>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt24> {
      override fun decode(source: String): ArrayOfInt24 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt24(ArrayList())
        return ArrayOfInt24((1 until partitions.size).map { Int24.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt32(items: List<Int32>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int32>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt32> {
      override fun decode(source: String): ArrayOfInt32 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt32(ArrayList())
        return ArrayOfInt32((1 until partitions.size).map { Int32.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt40(items: List<Int40>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int40>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt40> {
      override fun decode(source: String): ArrayOfInt40 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt40(ArrayList())
        return ArrayOfInt40((1 until partitions.size).map { Int40.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt48(items: List<Int48>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int48>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt48> {
      override fun decode(source: String): ArrayOfInt48 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt48(ArrayList())
        return ArrayOfInt48((1 until partitions.size).map { Int48.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt56(items: List<Int56>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int56>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt56> {
      override fun decode(source: String): ArrayOfInt56 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt56(ArrayList())
        return ArrayOfInt56((1 until partitions.size).map { Int56.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt64(items: List<Int64>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int64>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt64> {
      override fun decode(source: String): ArrayOfInt64 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt64(ArrayList())
        return ArrayOfInt64((1 until partitions.size).map { Int64.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt72(items: List<Int72>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int72>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt72> {
      override fun decode(source: String): ArrayOfInt72 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt72(ArrayList())
        return ArrayOfInt72((1 until partitions.size).map { Int72.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt80(items: List<Int80>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int80>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt80> {
      override fun decode(source: String): ArrayOfInt80 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt80(ArrayList())
        return ArrayOfInt80((1 until partitions.size).map { Int80.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt88(items: List<Int88>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int88>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt88> {
      override fun decode(source: String): ArrayOfInt88 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt88(ArrayList())
        return ArrayOfInt88((1 until partitions.size).map { Int88.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt96(items: List<Int96>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int96>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt96> {
      override fun decode(source: String): ArrayOfInt96 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt96(ArrayList())
        return ArrayOfInt96((1 until partitions.size).map { Int96.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt104(items: List<Int104>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int104>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt104> {
      override fun decode(source: String): ArrayOfInt104 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt104(ArrayList())
        return ArrayOfInt104((1 until partitions.size).map { Int104.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt112(items: List<Int112>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int112>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt112> {
      override fun decode(source: String): ArrayOfInt112 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt112(ArrayList())
        return ArrayOfInt112((1 until partitions.size).map { Int112.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt120(items: List<Int120>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int120>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt120> {
      override fun decode(source: String): ArrayOfInt120 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt120(ArrayList())
        return ArrayOfInt120((1 until partitions.size).map { Int120.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt128(items: List<Int128>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int128>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt128> {
      override fun decode(source: String): ArrayOfInt128 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt128(ArrayList())
        return ArrayOfInt128((1 until partitions.size).map { Int128.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt136(items: List<Int136>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int136>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt136> {
      override fun decode(source: String): ArrayOfInt136 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt136(ArrayList())
        return ArrayOfInt136((1 until partitions.size).map { Int136.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt144(items: List<Int144>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int144>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt144> {
      override fun decode(source: String): ArrayOfInt144 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt144(ArrayList())
        return ArrayOfInt144((1 until partitions.size).map { Int144.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt152(items: List<Int152>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int152>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt152> {
      override fun decode(source: String): ArrayOfInt152 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt152(ArrayList())
        return ArrayOfInt152((1 until partitions.size).map { Int152.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt160(items: List<Int160>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int160>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt160> {
      override fun decode(source: String): ArrayOfInt160 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt160(ArrayList())
        return ArrayOfInt160((1 until partitions.size).map { Int160.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt168(items: List<Int168>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int168>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt168> {
      override fun decode(source: String): ArrayOfInt168 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt168(ArrayList())
        return ArrayOfInt168((1 until partitions.size).map { Int168.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt176(items: List<Int176>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int176>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt176> {
      override fun decode(source: String): ArrayOfInt176 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt176(ArrayList())
        return ArrayOfInt176((1 until partitions.size).map { Int176.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt184(items: List<Int184>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int184>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt184> {
      override fun decode(source: String): ArrayOfInt184 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt184(ArrayList())
        return ArrayOfInt184((1 until partitions.size).map { Int184.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt192(items: List<Int192>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int192>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt192> {
      override fun decode(source: String): ArrayOfInt192 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt192(ArrayList())
        return ArrayOfInt192((1 until partitions.size).map { Int192.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt200(items: List<Int200>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int200>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt200> {
      override fun decode(source: String): ArrayOfInt200 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt200(ArrayList())
        return ArrayOfInt200((1 until partitions.size).map { Int200.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt208(items: List<Int208>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int208>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt208> {
      override fun decode(source: String): ArrayOfInt208 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt208(ArrayList())
        return ArrayOfInt208((1 until partitions.size).map { Int208.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt216(items: List<Int216>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int216>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt216> {
      override fun decode(source: String): ArrayOfInt216 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt216(ArrayList())
        return ArrayOfInt216((1 until partitions.size).map { Int216.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt224(items: List<Int224>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int224>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt224> {
      override fun decode(source: String): ArrayOfInt224 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt224(ArrayList())
        return ArrayOfInt224((1 until partitions.size).map { Int224.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt232(items: List<Int232>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int232>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt232> {
      override fun decode(source: String): ArrayOfInt232 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt232(ArrayList())
        return ArrayOfInt232((1 until partitions.size).map { Int232.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt240(items: List<Int240>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int240>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt240> {
      override fun decode(source: String): ArrayOfInt240 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt240(ArrayList())
        return ArrayOfInt240((1 until partitions.size).map { Int240.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt248(items: List<Int248>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int248>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt248> {
      override fun decode(source: String): ArrayOfInt248 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt248(ArrayList())
        return ArrayOfInt248((1 until partitions.size).map { Int248.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfInt256(items: List<Int256>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Int256>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfInt256> {
      override fun decode(source: String): ArrayOfInt256 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfInt256(ArrayList())
        return ArrayOfInt256((1 until partitions.size).map { Int256.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt8(items: List<UInt8>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt8>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt8> {
      override fun decode(source: String): ArrayOfUInt8 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt8(ArrayList())
        return ArrayOfUInt8((1 until partitions.size).map { UInt8.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt16(items: List<UInt16>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt16>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt16> {
      override fun decode(source: String): ArrayOfUInt16 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt16(ArrayList())
        return ArrayOfUInt16((1 until partitions.size).map { UInt16.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt24(items: List<UInt24>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt24>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt24> {
      override fun decode(source: String): ArrayOfUInt24 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt24(ArrayList())
        return ArrayOfUInt24((1 until partitions.size).map { UInt24.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt32(items: List<UInt32>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt32>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt32> {
      override fun decode(source: String): ArrayOfUInt32 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt32(ArrayList())
        return ArrayOfUInt32((1 until partitions.size).map { UInt32.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt40(items: List<UInt40>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt40>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt40> {
      override fun decode(source: String): ArrayOfUInt40 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt40(ArrayList())
        return ArrayOfUInt40((1 until partitions.size).map { UInt40.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt48(items: List<UInt48>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt48>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt48> {
      override fun decode(source: String): ArrayOfUInt48 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt48(ArrayList())
        return ArrayOfUInt48((1 until partitions.size).map { UInt48.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt56(items: List<UInt56>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt56>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt56> {
      override fun decode(source: String): ArrayOfUInt56 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt56(ArrayList())
        return ArrayOfUInt56((1 until partitions.size).map { UInt56.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt64(items: List<UInt64>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt64>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt64> {
      override fun decode(source: String): ArrayOfUInt64 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt64(ArrayList())
        return ArrayOfUInt64((1 until partitions.size).map { UInt64.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt72(items: List<UInt72>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt72>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt72> {
      override fun decode(source: String): ArrayOfUInt72 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt72(ArrayList())
        return ArrayOfUInt72((1 until partitions.size).map { UInt72.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt80(items: List<UInt80>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt80>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt80> {
      override fun decode(source: String): ArrayOfUInt80 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt80(ArrayList())
        return ArrayOfUInt80((1 until partitions.size).map { UInt80.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt88(items: List<UInt88>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt88>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt88> {
      override fun decode(source: String): ArrayOfUInt88 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt88(ArrayList())
        return ArrayOfUInt88((1 until partitions.size).map { UInt88.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt96(items: List<UInt96>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt96>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt96> {
      override fun decode(source: String): ArrayOfUInt96 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt96(ArrayList())
        return ArrayOfUInt96((1 until partitions.size).map { UInt96.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt104(items: List<UInt104>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt104>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt104> {
      override fun decode(source: String): ArrayOfUInt104 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt104(ArrayList())
        return ArrayOfUInt104((1 until partitions.size).map { UInt104.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt112(items: List<UInt112>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt112>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt112> {
      override fun decode(source: String): ArrayOfUInt112 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt112(ArrayList())
        return ArrayOfUInt112((1 until partitions.size).map { UInt112.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt120(items: List<UInt120>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt120>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt120> {
      override fun decode(source: String): ArrayOfUInt120 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt120(ArrayList())
        return ArrayOfUInt120((1 until partitions.size).map { UInt120.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt128(items: List<UInt128>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt128>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt128> {
      override fun decode(source: String): ArrayOfUInt128 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt128(ArrayList())
        return ArrayOfUInt128((1 until partitions.size).map { UInt128.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt136(items: List<UInt136>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt136>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt136> {
      override fun decode(source: String): ArrayOfUInt136 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt136(ArrayList())
        return ArrayOfUInt136((1 until partitions.size).map { UInt136.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt144(items: List<UInt144>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt144>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt144> {
      override fun decode(source: String): ArrayOfUInt144 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt144(ArrayList())
        return ArrayOfUInt144((1 until partitions.size).map { UInt144.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt152(items: List<UInt152>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt152>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt152> {
      override fun decode(source: String): ArrayOfUInt152 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt152(ArrayList())
        return ArrayOfUInt152((1 until partitions.size).map { UInt152.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt160(items: List<UInt160>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt160>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt160> {
      override fun decode(source: String): ArrayOfUInt160 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt160(ArrayList())
        return ArrayOfUInt160((1 until partitions.size).map { UInt160.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt168(items: List<UInt168>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt168>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt168> {
      override fun decode(source: String): ArrayOfUInt168 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt168(ArrayList())
        return ArrayOfUInt168((1 until partitions.size).map { UInt168.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt176(items: List<UInt176>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt176>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt176> {
      override fun decode(source: String): ArrayOfUInt176 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt176(ArrayList())
        return ArrayOfUInt176((1 until partitions.size).map { UInt176.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt184(items: List<UInt184>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt184>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt184> {
      override fun decode(source: String): ArrayOfUInt184 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt184(ArrayList())
        return ArrayOfUInt184((1 until partitions.size).map { UInt184.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt192(items: List<UInt192>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt192>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt192> {
      override fun decode(source: String): ArrayOfUInt192 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt192(ArrayList())
        return ArrayOfUInt192((1 until partitions.size).map { UInt192.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt200(items: List<UInt200>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt200>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt200> {
      override fun decode(source: String): ArrayOfUInt200 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt200(ArrayList())
        return ArrayOfUInt200((1 until partitions.size).map { UInt200.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt208(items: List<UInt208>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt208>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt208> {
      override fun decode(source: String): ArrayOfUInt208 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt208(ArrayList())
        return ArrayOfUInt208((1 until partitions.size).map { UInt208.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt216(items: List<UInt216>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt216>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt216> {
      override fun decode(source: String): ArrayOfUInt216 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt216(ArrayList())
        return ArrayOfUInt216((1 until partitions.size).map { UInt216.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt224(items: List<UInt224>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt224>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt224> {
      override fun decode(source: String): ArrayOfUInt224 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt224(ArrayList())
        return ArrayOfUInt224((1 until partitions.size).map { UInt224.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt232(items: List<UInt232>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt232>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt232> {
      override fun decode(source: String): ArrayOfUInt232 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt232(ArrayList())
        return ArrayOfUInt232((1 until partitions.size).map { UInt232.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt240(items: List<UInt240>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt240>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt240> {
      override fun decode(source: String): ArrayOfUInt240 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt240(ArrayList())
        return ArrayOfUInt240((1 until partitions.size).map { UInt240.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt248(items: List<UInt248>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt248>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt248> {
      override fun decode(source: String): ArrayOfUInt248 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt248(ArrayList())
        return ArrayOfUInt248((1 until partitions.size).map { UInt248.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfUInt256(items: List<UInt256>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<UInt256>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfUInt256> {
      override fun decode(source: String): ArrayOfUInt256 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfUInt256(ArrayList())
        return ArrayOfUInt256((1 until partitions.size).map { UInt256.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes1(items: List<Bytes1>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes1>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes1> {
      override fun decode(source: String): ArrayOfBytes1 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes1(ArrayList())
        return ArrayOfBytes1((1 until partitions.size).map { Bytes1.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes2(items: List<Bytes2>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes2>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes2> {
      override fun decode(source: String): ArrayOfBytes2 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes2(ArrayList())
        return ArrayOfBytes2((1 until partitions.size).map { Bytes2.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes3(items: List<Bytes3>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes3>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes3> {
      override fun decode(source: String): ArrayOfBytes3 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes3(ArrayList())
        return ArrayOfBytes3((1 until partitions.size).map { Bytes3.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes4(items: List<Bytes4>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes4>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes4> {
      override fun decode(source: String): ArrayOfBytes4 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes4(ArrayList())
        return ArrayOfBytes4((1 until partitions.size).map { Bytes4.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes5(items: List<Bytes5>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes5>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes5> {
      override fun decode(source: String): ArrayOfBytes5 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes5(ArrayList())
        return ArrayOfBytes5((1 until partitions.size).map { Bytes5.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes6(items: List<Bytes6>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes6>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes6> {
      override fun decode(source: String): ArrayOfBytes6 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes6(ArrayList())
        return ArrayOfBytes6((1 until partitions.size).map { Bytes6.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes7(items: List<Bytes7>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes7>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes7> {
      override fun decode(source: String): ArrayOfBytes7 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes7(ArrayList())
        return ArrayOfBytes7((1 until partitions.size).map { Bytes7.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes8(items: List<Bytes8>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes8>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes8> {
      override fun decode(source: String): ArrayOfBytes8 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes8(ArrayList())
        return ArrayOfBytes8((1 until partitions.size).map { Bytes8.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes9(items: List<Bytes9>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes9>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes9> {
      override fun decode(source: String): ArrayOfBytes9 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes9(ArrayList())
        return ArrayOfBytes9((1 until partitions.size).map { Bytes9.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes10(items: List<Bytes10>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes10>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes10> {
      override fun decode(source: String): ArrayOfBytes10 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes10(ArrayList())
        return ArrayOfBytes10((1 until partitions.size).map { Bytes10.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes11(items: List<Bytes11>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes11>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes11> {
      override fun decode(source: String): ArrayOfBytes11 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes11(ArrayList())
        return ArrayOfBytes11((1 until partitions.size).map { Bytes11.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes12(items: List<Bytes12>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes12>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes12> {
      override fun decode(source: String): ArrayOfBytes12 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes12(ArrayList())
        return ArrayOfBytes12((1 until partitions.size).map { Bytes12.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes13(items: List<Bytes13>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes13>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes13> {
      override fun decode(source: String): ArrayOfBytes13 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes13(ArrayList())
        return ArrayOfBytes13((1 until partitions.size).map { Bytes13.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes14(items: List<Bytes14>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes14>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes14> {
      override fun decode(source: String): ArrayOfBytes14 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes14(ArrayList())
        return ArrayOfBytes14((1 until partitions.size).map { Bytes14.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes15(items: List<Bytes15>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes15>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes15> {
      override fun decode(source: String): ArrayOfBytes15 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes15(ArrayList())
        return ArrayOfBytes15((1 until partitions.size).map { Bytes15.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes16(items: List<Bytes16>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes16>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes16> {
      override fun decode(source: String): ArrayOfBytes16 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes16(ArrayList())
        return ArrayOfBytes16((1 until partitions.size).map { Bytes16.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes17(items: List<Bytes17>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes17>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes17> {
      override fun decode(source: String): ArrayOfBytes17 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes17(ArrayList())
        return ArrayOfBytes17((1 until partitions.size).map { Bytes17.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes18(items: List<Bytes18>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes18>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes18> {
      override fun decode(source: String): ArrayOfBytes18 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes18(ArrayList())
        return ArrayOfBytes18((1 until partitions.size).map { Bytes18.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes19(items: List<Bytes19>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes19>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes19> {
      override fun decode(source: String): ArrayOfBytes19 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes19(ArrayList())
        return ArrayOfBytes19((1 until partitions.size).map { Bytes19.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes20(items: List<Bytes20>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes20>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes20> {
      override fun decode(source: String): ArrayOfBytes20 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes20(ArrayList())
        return ArrayOfBytes20((1 until partitions.size).map { Bytes20.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes21(items: List<Bytes21>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes21>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes21> {
      override fun decode(source: String): ArrayOfBytes21 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes21(ArrayList())
        return ArrayOfBytes21((1 until partitions.size).map { Bytes21.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes22(items: List<Bytes22>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes22>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes22> {
      override fun decode(source: String): ArrayOfBytes22 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes22(ArrayList())
        return ArrayOfBytes22((1 until partitions.size).map { Bytes22.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes23(items: List<Bytes23>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes23>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes23> {
      override fun decode(source: String): ArrayOfBytes23 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes23(ArrayList())
        return ArrayOfBytes23((1 until partitions.size).map { Bytes23.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes24(items: List<Bytes24>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes24>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes24> {
      override fun decode(source: String): ArrayOfBytes24 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes24(ArrayList())
        return ArrayOfBytes24((1 until partitions.size).map { Bytes24.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes25(items: List<Bytes25>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes25>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes25> {
      override fun decode(source: String): ArrayOfBytes25 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes25(ArrayList())
        return ArrayOfBytes25((1 until partitions.size).map { Bytes25.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes26(items: List<Bytes26>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes26>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes26> {
      override fun decode(source: String): ArrayOfBytes26 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes26(ArrayList())
        return ArrayOfBytes26((1 until partitions.size).map { Bytes26.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes27(items: List<Bytes27>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes27>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes27> {
      override fun decode(source: String): ArrayOfBytes27 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes27(ArrayList())
        return ArrayOfBytes27((1 until partitions.size).map { Bytes27.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes28(items: List<Bytes28>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes28>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes28> {
      override fun decode(source: String): ArrayOfBytes28 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes28(ArrayList())
        return ArrayOfBytes28((1 until partitions.size).map { Bytes28.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes29(items: List<Bytes29>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes29>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes29> {
      override fun decode(source: String): ArrayOfBytes29 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes29(ArrayList())
        return ArrayOfBytes29((1 until partitions.size).map { Bytes29.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes30(items: List<Bytes30>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes30>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes30> {
      override fun decode(source: String): ArrayOfBytes30 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes30(ArrayList())
        return ArrayOfBytes30((1 until partitions.size).map { Bytes30.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes31(items: List<Bytes31>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes31>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes31> {
      override fun decode(source: String): ArrayOfBytes31 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes31(ArrayList())
        return ArrayOfBytes31((1 until partitions.size).map { Bytes31.decode(partitions[it]) }.toList())
      }
    }
  }

  class ArrayOfBytes32(items: List<Bytes32>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bytes32>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBytes32> {
      override fun decode(source: String): ArrayOfBytes32 {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBytes32(ArrayList())
        return ArrayOfBytes32((1 until partitions.size).map { Bytes32.decode(partitions[it]) }.toList())
      }
    }
  }

  class Address(value: BigInteger) : SolidityBase.UInt(value, 160) {
    companion object : SolidityBase.Type.Decoder<Address> {
      override fun decode(source: String): Address = Address(BigInteger(source, 16))
    }
  }

  class ArrayOfAddress(items: List<Address>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Address>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfAddress> {
      override fun decode(source: String): ArrayOfAddress {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfAddress(ArrayList())
        return ArrayOfAddress((1 until partitions.size).map { Address.decode(partitions[it]) }.toList())
      }
    }
  }

  class Bool(value: Boolean) : SolidityBase.UInt(if (value) BigInteger.ONE else BigInteger.ZERO, 8) {
    companion object : SolidityBase.Type.Decoder<Bool> {
      override fun decode(source: String): Bool = Bool(BigInteger(source, 16) != BigInteger.ZERO)
    }
  }

  class ArrayOfBool(items: List<Bool>) : pm.gnosis.model.SolidityBase.ArrayOfStatic<Bool>(items) {
    companion object : SolidityBase.Type.Decoder<ArrayOfBool> {
      override fun decode(source: String): ArrayOfBool {
        val partitions = SolidityBase.partitionData(source)
        val contentSize = BigDecimal(partitions[0]).intValueExact() * 2
        if (contentSize == 0) return ArrayOfBool(ArrayList())
        return ArrayOfBool((1 until partitions.size).map { Bool.decode(partitions[it]) }.toList())
      }
    }
  }

  class Bytes(val bytes: ByteArray) : SolidityBase.DynamicType {
    init {
      if (BigInteger(bytes.size.toString(10)) > BigInteger.valueOf(2).pow(256)) throw Exception()
    }

    override fun encode(): String {
      val parts = encodeParts()
      return parts.static + parts.dynamic
    }

    override fun encodeParts(): SolidityBase.DynamicType.Parts {
      val length = bytes.size.toString(16).padStart(64, '0')
      val contents = bytes.toHex().padEndMultiple(64, '0')
      return SolidityBase.DynamicType.Parts(length, contents)
    }
  }
}
