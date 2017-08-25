package model

import java.lang.Exception
import java.math.BigInteger
import kotlin.Boolean
import kotlin.ByteArray
import kotlin.String
import kotlin.collections.Map
import kotlin.reflect.KClass
import utils.padEndMultiple
import utils.toHex

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

    class UInt8(value: BigInteger) : SolidityBase.UInt(value, 8)

    class UInt16(value: BigInteger) : SolidityBase.UInt(value, 16)

    class UInt24(value: BigInteger) : SolidityBase.UInt(value, 24)

    class UInt32(value: BigInteger) : SolidityBase.UInt(value, 32)

    class UInt40(value: BigInteger) : SolidityBase.UInt(value, 40)

    class UInt48(value: BigInteger) : SolidityBase.UInt(value, 48)

    class UInt56(value: BigInteger) : SolidityBase.UInt(value, 56)

    class UInt64(value: BigInteger) : SolidityBase.UInt(value, 64)

    class UInt72(value: BigInteger) : SolidityBase.UInt(value, 72)

    class UInt80(value: BigInteger) : SolidityBase.UInt(value, 80)

    class UInt88(value: BigInteger) : SolidityBase.UInt(value, 88)

    class UInt96(value: BigInteger) : SolidityBase.UInt(value, 96)

    class UInt104(value: BigInteger) : SolidityBase.UInt(value, 104)

    class UInt112(value: BigInteger) : SolidityBase.UInt(value, 112)

    class UInt120(value: BigInteger) : SolidityBase.UInt(value, 120)

    class UInt128(value: BigInteger) : SolidityBase.UInt(value, 128)

    class UInt136(value: BigInteger) : SolidityBase.UInt(value, 136)

    class UInt144(value: BigInteger) : SolidityBase.UInt(value, 144)

    class UInt152(value: BigInteger) : SolidityBase.UInt(value, 152)

    class UInt160(value: BigInteger) : SolidityBase.UInt(value, 160)

    class UInt168(value: BigInteger) : SolidityBase.UInt(value, 168)

    class UInt176(value: BigInteger) : SolidityBase.UInt(value, 176)

    class UInt184(value: BigInteger) : SolidityBase.UInt(value, 184)

    class UInt192(value: BigInteger) : SolidityBase.UInt(value, 192)

    class UInt200(value: BigInteger) : SolidityBase.UInt(value, 200)

    class UInt208(value: BigInteger) : SolidityBase.UInt(value, 208)

    class UInt216(value: BigInteger) : SolidityBase.UInt(value, 216)

    class UInt224(value: BigInteger) : SolidityBase.UInt(value, 224)

    class UInt232(value: BigInteger) : SolidityBase.UInt(value, 232)

    class UInt240(value: BigInteger) : SolidityBase.UInt(value, 240)

    class UInt248(value: BigInteger) : SolidityBase.UInt(value, 248)

    class UInt256(value: BigInteger) : SolidityBase.UInt(value, 256)

    class Int8(value: BigInteger) : SolidityBase.Int(value, 8)

    class Int16(value: BigInteger) : SolidityBase.Int(value, 16)

    class Int24(value: BigInteger) : SolidityBase.Int(value, 24)

    class Int32(value: BigInteger) : SolidityBase.Int(value, 32)

    class Int40(value: BigInteger) : SolidityBase.Int(value, 40)

    class Int48(value: BigInteger) : SolidityBase.Int(value, 48)

    class Int56(value: BigInteger) : SolidityBase.Int(value, 56)

    class Int64(value: BigInteger) : SolidityBase.Int(value, 64)

    class Int72(value: BigInteger) : SolidityBase.Int(value, 72)

    class Int80(value: BigInteger) : SolidityBase.Int(value, 80)

    class Int88(value: BigInteger) : SolidityBase.Int(value, 88)

    class Int96(value: BigInteger) : SolidityBase.Int(value, 96)

    class Int104(value: BigInteger) : SolidityBase.Int(value, 104)

    class Int112(value: BigInteger) : SolidityBase.Int(value, 112)

    class Int120(value: BigInteger) : SolidityBase.Int(value, 120)

    class Int128(value: BigInteger) : SolidityBase.Int(value, 128)

    class Int136(value: BigInteger) : SolidityBase.Int(value, 136)

    class Int144(value: BigInteger) : SolidityBase.Int(value, 144)

    class Int152(value: BigInteger) : SolidityBase.Int(value, 152)

    class Int160(value: BigInteger) : SolidityBase.Int(value, 160)

    class Int168(value: BigInteger) : SolidityBase.Int(value, 168)

    class Int176(value: BigInteger) : SolidityBase.Int(value, 176)

    class Int184(value: BigInteger) : SolidityBase.Int(value, 184)

    class Int192(value: BigInteger) : SolidityBase.Int(value, 192)

    class Int200(value: BigInteger) : SolidityBase.Int(value, 200)

    class Int208(value: BigInteger) : SolidityBase.Int(value, 208)

    class Int216(value: BigInteger) : SolidityBase.Int(value, 216)

    class Int224(value: BigInteger) : SolidityBase.Int(value, 224)

    class Int232(value: BigInteger) : SolidityBase.Int(value, 232)

    class Int240(value: BigInteger) : SolidityBase.Int(value, 240)

    class Int248(value: BigInteger) : SolidityBase.Int(value, 248)

    class Int256(value: BigInteger) : SolidityBase.Int(value, 256)

    class Bytes1(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 1)

    class Bytes2(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 2)

    class Bytes3(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 3)

    class Bytes4(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 4)

    class Bytes5(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 5)

    class Bytes6(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 6)

    class Bytes7(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 7)

    class Bytes8(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 8)

    class Bytes9(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 9)

    class Bytes10(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 10)

    class Bytes11(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 11)

    class Bytes12(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 12)

    class Bytes13(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 13)

    class Bytes14(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 14)

    class Bytes15(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 15)

    class Bytes16(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 16)

    class Bytes17(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 17)

    class Bytes18(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 18)

    class Bytes19(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 19)

    class Bytes20(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 20)

    class Bytes21(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 21)

    class Bytes22(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 22)

    class Bytes23(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 23)

    class Bytes24(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 24)

    class Bytes25(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 25)

    class Bytes26(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 26)

    class Bytes27(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 27)

    class Bytes28(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 28)

    class Bytes29(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 29)

    class Bytes30(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 30)

    class Bytes31(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 31)

    class Bytes32(byteArray: ByteArray) : SolidityBase.StaticBytes(byteArray, 32)

    class ArrayOfInt8(vararg items: Int8) : SolidityBase.ArrayOfStatic<Int8>(*items)

    class ArrayOfInt16(vararg items: Int16) : SolidityBase.ArrayOfStatic<Int16>(*items)

    class ArrayOfInt24(vararg items: Int24) : SolidityBase.ArrayOfStatic<Int24>(*items)

    class ArrayOfInt32(vararg items: Int32) : SolidityBase.ArrayOfStatic<Int32>(*items)

    class ArrayOfInt40(vararg items: Int40) : SolidityBase.ArrayOfStatic<Int40>(*items)

    class ArrayOfInt48(vararg items: Int48) : SolidityBase.ArrayOfStatic<Int48>(*items)

    class ArrayOfInt56(vararg items: Int56) : SolidityBase.ArrayOfStatic<Int56>(*items)

    class ArrayOfInt64(vararg items: Int64) : SolidityBase.ArrayOfStatic<Int64>(*items)

    class ArrayOfInt72(vararg items: Int72) : SolidityBase.ArrayOfStatic<Int72>(*items)

    class ArrayOfInt80(vararg items: Int80) : SolidityBase.ArrayOfStatic<Int80>(*items)

    class ArrayOfInt88(vararg items: Int88) : SolidityBase.ArrayOfStatic<Int88>(*items)

    class ArrayOfInt96(vararg items: Int96) : SolidityBase.ArrayOfStatic<Int96>(*items)

    class ArrayOfInt104(vararg items: Int104) : SolidityBase.ArrayOfStatic<Int104>(*items)

    class ArrayOfInt112(vararg items: Int112) : SolidityBase.ArrayOfStatic<Int112>(*items)

    class ArrayOfInt120(vararg items: Int120) : SolidityBase.ArrayOfStatic<Int120>(*items)

    class ArrayOfInt128(vararg items: Int128) : SolidityBase.ArrayOfStatic<Int128>(*items)

    class ArrayOfInt136(vararg items: Int136) : SolidityBase.ArrayOfStatic<Int136>(*items)

    class ArrayOfInt144(vararg items: Int144) : SolidityBase.ArrayOfStatic<Int144>(*items)

    class ArrayOfInt152(vararg items: Int152) : SolidityBase.ArrayOfStatic<Int152>(*items)

    class ArrayOfInt160(vararg items: Int160) : SolidityBase.ArrayOfStatic<Int160>(*items)

    class ArrayOfInt168(vararg items: Int168) : SolidityBase.ArrayOfStatic<Int168>(*items)

    class ArrayOfInt176(vararg items: Int176) : SolidityBase.ArrayOfStatic<Int176>(*items)

    class ArrayOfInt184(vararg items: Int184) : SolidityBase.ArrayOfStatic<Int184>(*items)

    class ArrayOfInt192(vararg items: Int192) : SolidityBase.ArrayOfStatic<Int192>(*items)

    class ArrayOfInt200(vararg items: Int200) : SolidityBase.ArrayOfStatic<Int200>(*items)

    class ArrayOfInt208(vararg items: Int208) : SolidityBase.ArrayOfStatic<Int208>(*items)

    class ArrayOfInt216(vararg items: Int216) : SolidityBase.ArrayOfStatic<Int216>(*items)

    class ArrayOfInt224(vararg items: Int224) : SolidityBase.ArrayOfStatic<Int224>(*items)

    class ArrayOfInt232(vararg items: Int232) : SolidityBase.ArrayOfStatic<Int232>(*items)

    class ArrayOfInt240(vararg items: Int240) : SolidityBase.ArrayOfStatic<Int240>(*items)

    class ArrayOfInt248(vararg items: Int248) : SolidityBase.ArrayOfStatic<Int248>(*items)

    class ArrayOfInt256(vararg items: Int256) : SolidityBase.ArrayOfStatic<Int256>(*items)

    class ArrayOfUInt8(vararg items: UInt8) : SolidityBase.ArrayOfStatic<UInt8>(*items)

    class ArrayOfUInt16(vararg items: UInt16) : SolidityBase.ArrayOfStatic<UInt16>(*items)

    class ArrayOfUInt24(vararg items: UInt24) : SolidityBase.ArrayOfStatic<UInt24>(*items)

    class ArrayOfUInt32(vararg items: UInt32) : SolidityBase.ArrayOfStatic<UInt32>(*items)

    class ArrayOfUInt40(vararg items: UInt40) : SolidityBase.ArrayOfStatic<UInt40>(*items)

    class ArrayOfUInt48(vararg items: UInt48) : SolidityBase.ArrayOfStatic<UInt48>(*items)

    class ArrayOfUInt56(vararg items: UInt56) : SolidityBase.ArrayOfStatic<UInt56>(*items)

    class ArrayOfUInt64(vararg items: UInt64) : SolidityBase.ArrayOfStatic<UInt64>(*items)

    class ArrayOfUInt72(vararg items: UInt72) : SolidityBase.ArrayOfStatic<UInt72>(*items)

    class ArrayOfUInt80(vararg items: UInt80) : SolidityBase.ArrayOfStatic<UInt80>(*items)

    class ArrayOfUInt88(vararg items: UInt88) : SolidityBase.ArrayOfStatic<UInt88>(*items)

    class ArrayOfUInt96(vararg items: UInt96) : SolidityBase.ArrayOfStatic<UInt96>(*items)

    class ArrayOfUInt104(vararg items: UInt104) : SolidityBase.ArrayOfStatic<UInt104>(*items)

    class ArrayOfUInt112(vararg items: UInt112) : SolidityBase.ArrayOfStatic<UInt112>(*items)

    class ArrayOfUInt120(vararg items: UInt120) : SolidityBase.ArrayOfStatic<UInt120>(*items)

    class ArrayOfUInt128(vararg items: UInt128) : SolidityBase.ArrayOfStatic<UInt128>(*items)

    class ArrayOfUInt136(vararg items: UInt136) : SolidityBase.ArrayOfStatic<UInt136>(*items)

    class ArrayOfUInt144(vararg items: UInt144) : SolidityBase.ArrayOfStatic<UInt144>(*items)

    class ArrayOfUInt152(vararg items: UInt152) : SolidityBase.ArrayOfStatic<UInt152>(*items)

    class ArrayOfUInt160(vararg items: UInt160) : SolidityBase.ArrayOfStatic<UInt160>(*items)

    class ArrayOfUInt168(vararg items: UInt168) : SolidityBase.ArrayOfStatic<UInt168>(*items)

    class ArrayOfUInt176(vararg items: UInt176) : SolidityBase.ArrayOfStatic<UInt176>(*items)

    class ArrayOfUInt184(vararg items: UInt184) : SolidityBase.ArrayOfStatic<UInt184>(*items)

    class ArrayOfUInt192(vararg items: UInt192) : SolidityBase.ArrayOfStatic<UInt192>(*items)

    class ArrayOfUInt200(vararg items: UInt200) : SolidityBase.ArrayOfStatic<UInt200>(*items)

    class ArrayOfUInt208(vararg items: UInt208) : SolidityBase.ArrayOfStatic<UInt208>(*items)

    class ArrayOfUInt216(vararg items: UInt216) : SolidityBase.ArrayOfStatic<UInt216>(*items)

    class ArrayOfUInt224(vararg items: UInt224) : SolidityBase.ArrayOfStatic<UInt224>(*items)

    class ArrayOfUInt232(vararg items: UInt232) : SolidityBase.ArrayOfStatic<UInt232>(*items)

    class ArrayOfUInt240(vararg items: UInt240) : SolidityBase.ArrayOfStatic<UInt240>(*items)

    class ArrayOfUInt248(vararg items: UInt248) : SolidityBase.ArrayOfStatic<UInt248>(*items)

    class ArrayOfUInt256(vararg items: UInt256) : SolidityBase.ArrayOfStatic<UInt256>(*items)

    class ArrayOfBytes1(vararg items: Bytes1) : SolidityBase.ArrayOfStatic<Bytes1>(*items)

    class ArrayOfBytes2(vararg items: Bytes2) : SolidityBase.ArrayOfStatic<Bytes2>(*items)

    class ArrayOfBytes3(vararg items: Bytes3) : SolidityBase.ArrayOfStatic<Bytes3>(*items)

    class ArrayOfBytes4(vararg items: Bytes4) : SolidityBase.ArrayOfStatic<Bytes4>(*items)

    class ArrayOfBytes5(vararg items: Bytes5) : SolidityBase.ArrayOfStatic<Bytes5>(*items)

    class ArrayOfBytes6(vararg items: Bytes6) : SolidityBase.ArrayOfStatic<Bytes6>(*items)

    class ArrayOfBytes7(vararg items: Bytes7) : SolidityBase.ArrayOfStatic<Bytes7>(*items)

    class ArrayOfBytes8(vararg items: Bytes8) : SolidityBase.ArrayOfStatic<Bytes8>(*items)

    class ArrayOfBytes9(vararg items: Bytes9) : SolidityBase.ArrayOfStatic<Bytes9>(*items)

    class ArrayOfBytes10(vararg items: Bytes10) : SolidityBase.ArrayOfStatic<Bytes10>(*items)

    class ArrayOfBytes11(vararg items: Bytes11) : SolidityBase.ArrayOfStatic<Bytes11>(*items)

    class ArrayOfBytes12(vararg items: Bytes12) : SolidityBase.ArrayOfStatic<Bytes12>(*items)

    class ArrayOfBytes13(vararg items: Bytes13) : SolidityBase.ArrayOfStatic<Bytes13>(*items)

    class ArrayOfBytes14(vararg items: Bytes14) : SolidityBase.ArrayOfStatic<Bytes14>(*items)

    class ArrayOfBytes15(vararg items: Bytes15) : SolidityBase.ArrayOfStatic<Bytes15>(*items)

    class ArrayOfBytes16(vararg items: Bytes16) : SolidityBase.ArrayOfStatic<Bytes16>(*items)

    class ArrayOfBytes17(vararg items: Bytes17) : SolidityBase.ArrayOfStatic<Bytes17>(*items)

    class ArrayOfBytes18(vararg items: Bytes18) : SolidityBase.ArrayOfStatic<Bytes18>(*items)

    class ArrayOfBytes19(vararg items: Bytes19) : SolidityBase.ArrayOfStatic<Bytes19>(*items)

    class ArrayOfBytes20(vararg items: Bytes20) : SolidityBase.ArrayOfStatic<Bytes20>(*items)

    class ArrayOfBytes21(vararg items: Bytes21) : SolidityBase.ArrayOfStatic<Bytes21>(*items)

    class ArrayOfBytes22(vararg items: Bytes22) : SolidityBase.ArrayOfStatic<Bytes22>(*items)

    class ArrayOfBytes23(vararg items: Bytes23) : SolidityBase.ArrayOfStatic<Bytes23>(*items)

    class ArrayOfBytes24(vararg items: Bytes24) : SolidityBase.ArrayOfStatic<Bytes24>(*items)

    class ArrayOfBytes25(vararg items: Bytes25) : SolidityBase.ArrayOfStatic<Bytes25>(*items)

    class ArrayOfBytes26(vararg items: Bytes26) : SolidityBase.ArrayOfStatic<Bytes26>(*items)

    class ArrayOfBytes27(vararg items: Bytes27) : SolidityBase.ArrayOfStatic<Bytes27>(*items)

    class ArrayOfBytes28(vararg items: Bytes28) : SolidityBase.ArrayOfStatic<Bytes28>(*items)

    class ArrayOfBytes29(vararg items: Bytes29) : SolidityBase.ArrayOfStatic<Bytes29>(*items)

    class ArrayOfBytes30(vararg items: Bytes30) : SolidityBase.ArrayOfStatic<Bytes30>(*items)

    class ArrayOfBytes31(vararg items: Bytes31) : SolidityBase.ArrayOfStatic<Bytes31>(*items)

    class ArrayOfBytes32(vararg items: Bytes32) : SolidityBase.ArrayOfStatic<Bytes32>(*items)

    class Address(value: BigInteger) : SolidityBase.UInt(value, 160)

    class ArrayOfAddress(vararg items: Address) : SolidityBase.ArrayOfStatic<Address>(*items)

    class Bool(value: Boolean) : SolidityBase.UInt(if (value) BigInteger.ONE else BigInteger.ZERO, 8)

    class ArrayOfBool(vararg items: Bool) : SolidityBase.ArrayOfStatic<Bool>(*items)

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
