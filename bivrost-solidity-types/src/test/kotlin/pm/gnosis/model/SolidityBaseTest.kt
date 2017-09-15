package pm.gnosis.model

import org.junit.Assert.*
import org.junit.Test
import pm.gnosis.exceptions.InvalidBitLengthException
import java.lang.reflect.InvocationTargetException
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
            val upperLimit = BigInteger.valueOf(2).pow(it)
            val constructor = Class.forName(formatClassName(Solidity.types["uint$it"]!!)).constructors[0]
            constructor.newInstance(upperLimit.minus(BigInteger.ONE))
            try {
                constructor.newInstance(upperLimit)
                fail("Expected InvalidBitLengthException")
            } catch (e: InvocationTargetException) {
                if (e.targetException !is InvalidBitLengthException) throw e
            }
        }
    }

    @Test
    fun testUIntDecoding() {
        assertEquals(BigInteger.ZERO,
                SolidityBase.decodeUInt("0000000000000000000000000000000000000000000000000000000000000000"))

        assertEquals(BigInteger.ONE,
                SolidityBase.decodeUInt("0000000000000000000000000000000000000000000000000000000000000001"))

        assertEquals(BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935"),
                SolidityBase.decodeUInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"))
    }

    @Test
    fun testBoolEncoding() {
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bool(false).encode())

        assertEquals("0000000000000000000000000000000000000000000000000000000000000001",
                Solidity.Bool(true).encode())
    }

    @Test
    fun testBoolDecoding() {
        assertEquals(false,
                SolidityBase.decodeBool("0000000000000000000000000000000000000000000000000000000000000000"))

        assertEquals(true,
                SolidityBase.decodeBool("0000000000000000000000000000000000000000000000000000000000000001"))
    }

    @Test
    fun testIntEncoding() {
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000",
                Solidity.Int8(BigInteger.ZERO).encode())

        assertEquals("0000000000000000000000000000000000000000000000000000000000000001",
                Solidity.Int8(BigInteger.ONE).encode())

        assertEquals("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
                Solidity.Int8(BigInteger.valueOf(-1)).encode())

        assertEquals("000000000000000000000000000000000000000000000000000000000000007f",
                Solidity.Int8(BigInteger.valueOf(127)).encode())

        assertEquals("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80",
                Solidity.Int8(BigInteger.valueOf(-128)).encode())
    }

    @Test
    fun testIntRange() {
        (8..256 step 8).forEach {
            val min = BigInteger.valueOf(2).pow(it - 1).negate()
            val max = BigInteger.valueOf(2).pow(it - 1) - BigInteger.ONE
            val constructor = Class.forName(formatClassName(Solidity.types["int$it"]!!)).constructors[0]
            constructor.newInstance(min)
            constructor.newInstance(max)
            try {
                constructor.newInstance(BigInteger.valueOf(2).pow(it))
                fail("Expected IllegalArgumentException")
            } catch (e: InvocationTargetException) {
                if (e.targetException !is IllegalArgumentException) throw e
            }

            try {
                constructor.newInstance(BigInteger.valueOf(2).pow(it))
                fail("Expected IllegalArgumentException")
            } catch (e: InvocationTargetException) {
                if (e.targetException !is IllegalArgumentException) throw e
            }
        }
    }

    @Test
    fun testIntDecoding() {
        assertEquals(BigInteger.ZERO,
                SolidityBase.decodeInt("0000000000000000000000000000000000000000000000000000000000000000"))

        assertEquals(BigInteger.ONE,
                SolidityBase.decodeInt("0000000000000000000000000000000000000000000000000000000000000001"))

        assertEquals(BigInteger.valueOf(-1),
                SolidityBase.decodeInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"))

        assertEquals(BigInteger.valueOf(127),
                SolidityBase.decodeInt("000000000000000000000000000000000000000000000000000000000000007f"))

        assertEquals(BigInteger.valueOf(-128),
                SolidityBase.decodeInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80"))
    }

    @Test
    fun testStaticBytesEncoding() {
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes1(byteArrayOf(0)).encode())

        assertEquals("0001000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes2(byteArrayOf(0, 1)).encode())

        assertEquals("0001020000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes3(byteArrayOf(0, 1, 2)).encode())

        assertEquals("6461766500000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes4("dave".toByteArray()).encode())
    }

    @Test
    fun testStaticBytesDecoding() {
        assertArrayEquals(byteArrayOf(0), SolidityBase.decodeStaticBytes("0000000000000000000000000000000000000000000000000000000000000000", 1))

        assertArrayEquals(byteArrayOf(0, 1), SolidityBase.decodeStaticBytes("0001000000000000000000000000000000000000000000000000000000000000", 2))

        assertArrayEquals(byteArrayOf(0, 1, 2), SolidityBase.decodeStaticBytes("0001020000000000000000000000000000000000000000000000000000000000", 3))

        assertArrayEquals("dave".toByteArray(), SolidityBase.decodeStaticBytes("6461766500000000000000000000000000000000000000000000000000000000", 4))

    }

    @Test
    fun testStaticBytesRange() {
        (1..32).forEach {
            val bytes = ByteArray(it, { it.toByte() })
            val constructor = Class.forName(formatClassName(Solidity.types["bytes$it"]!!)).constructors[0]
            constructor.newInstance(bytes)
            try {
                val oversizedBytes = ByteArray(it + 1, { it.toByte() })
                constructor.newInstance(oversizedBytes)
                fail("Expected IllegalArgumentException")
            } catch (e: InvocationTargetException) {
                if (e.targetException !is IllegalArgumentException) throw e
            }
        }
    }

    @Test
    fun testDynamicArrayEncoding() {
        assertEquals("000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789",
                SolidityBase.DynamicArray(listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16))), false).encode())

        assertEquals("000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000",
                SolidityBase.DynamicArray(listOf(Solidity.Bool(true), Solidity.Bool(false)), false).encode())

        assertEquals("0000000000000000000000000000000000000000000000000000000000000000",
                SolidityBase.DynamicArray(emptyList(), false).encode())
    }

    @Test
    fun testDynamicArrayDecoding() {
        var testData = "000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789"
        assertEquals(listOf(Solidity.UInt256(BigInteger("456", 16)), Solidity.UInt256(BigInteger("789", 16))),
                SolidityBase.DynamicArray.Decoder(Solidity.UInt256.DECODER).decode(SolidityBase.PartitionData.of(testData)).items)

        testData = "0000000000000000000000000000000000000000000000000000000000000003000000000000000000000000000000000000000000000000000000000000032100000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789"
        val source = SolidityBase.PartitionData.of(testData)
        val expected = SolidityBase.DynamicArray(listOf(
                SolidityBase.FixedArray(listOf(Solidity.UInt256(BigInteger("321", 16))), 1, false),
                SolidityBase.FixedArray(listOf(Solidity.UInt256(BigInteger("456", 16))), 1, false),
                SolidityBase.FixedArray(listOf(Solidity.UInt256(BigInteger("789", 16))), 1, false)
        ), false)
        assertEquals(expected, SolidityBase.DynamicArray.Decoder(SolidityBase.FixedArray.Decoder(Solidity.UInt256.DECODER, 1)).decode(source))
    }

    @Test
    fun testFixedArrayEncoding() {
        assertEquals("00000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789",
                SolidityBase.FixedArray(listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16))), 2, false).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000",
                SolidityBase.FixedArray(listOf(Solidity.Bool(true), Solidity.Bool(false)), 2, false).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                SolidityBase.FixedArray(emptyList(), 2, false).encode())
    }

    @Test
    fun testFixedArrayDecoding() {
        val testData = "00000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789"
        assertEquals(listOf(Solidity.UInt256(BigInteger("456", 16)), Solidity.UInt256(BigInteger("789", 16))),
                SolidityBase.FixedArray.Decoder(Solidity.UInt256.DECODER, 2).decode(SolidityBase.PartitionData.of(testData)).items)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testFixedArrayWithDynamicContentDecoding() {
        val testData = "00000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789"
        SolidityBase.FixedArray.Decoder(Solidity.Bytes.DECODER, 2).decode(SolidityBase.PartitionData.of(testData)).items
    }

    @Test
    fun testMultiTypeDecoding() {
        val testData = "00000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000000000000000321000000000000000000000000000000000000000000000000000000000000076500000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789"
        val source = SolidityBase.PartitionData.of(testData)
        assertEquals(listOf(Solidity.UInt256(BigInteger("321", 16)), Solidity.UInt256(BigInteger("765", 16))),
                SolidityBase.DynamicArray.Decoder(Solidity.UInt256.DECODER).decode(source).items)
        assertEquals(listOf(Solidity.UInt256(BigInteger("456", 16)), Solidity.UInt256(BigInteger("789", 16))),
                SolidityBase.FixedArray.Decoder(Solidity.UInt256.DECODER, 2).decode(source).items)
    }

    @Test
    fun testArrayOfStaticDecoding() {
        assertEquals(listOf(BigInteger("456", 16), BigInteger("789", 16)),
                SolidityBase.decodeArray("000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789", SolidityBase::decodeUInt))

        assertEquals(listOf(true, false),
                SolidityBase.decodeArray("000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000", SolidityBase::decodeBool))

        assertEquals(emptyList<SolidityBase.StaticType>(),
                SolidityBase.decodeArray("0000000000000000000000000000000000000000000000000000000000000000", SolidityBase::decodeBool))
    }

    @Test
    fun testDynamicBytesEncoding() {
        assertEquals("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000",
                Solidity.Bytes("Hello, world!".toByteArray()).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes(byteArrayOf(0)).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000020001000000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes(byteArrayOf(0, 1)).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000030001020000000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes(byteArrayOf(0, 1, 2)).encode())

        assertEquals("00000000000000000000000000000000000000000000000000000000000000046461766500000000000000000000000000000000000000000000000000000000",
                Solidity.Bytes("dave".toByteArray()).encode())
    }

    @Test
    fun testDynamicBytesDecoding() {
        assertArrayEquals("Hello, world!".toByteArray(),
                SolidityBase.decodeBytes(SolidityBase.PartitionData.of("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000")))

        assertArrayEquals(byteArrayOf(0),
                SolidityBase.decodeBytes(SolidityBase.PartitionData.of("00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000")))

        assertArrayEquals(byteArrayOf(0, 1),
                SolidityBase.decodeBytes(SolidityBase.PartitionData.of("00000000000000000000000000000000000000000000000000000000000000020001000000000000000000000000000000000000000000000000000000000000")))

        assertArrayEquals(byteArrayOf(0, 1, 2),
                SolidityBase.decodeBytes(SolidityBase.PartitionData.of("00000000000000000000000000000000000000000000000000000000000000030001020000000000000000000000000000000000000000000000000000000000")))

        assertArrayEquals("dave".toByteArray(),
                SolidityBase.decodeBytes(SolidityBase.PartitionData.of("00000000000000000000000000000000000000000000000000000000000000046461766500000000000000000000000000000000000000000000000000000000")))
    }

    @Test
    fun testStringDecoding() {
        assertEquals("Hello, world!",
                SolidityBase.decodeString(SolidityBase.PartitionData.of("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000")))
    }

    @Test
    fun testStringEncoding() {
        assertEquals("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000",
                Solidity.String("Hello, world!").encode())
    }

    @Test
    fun testDecodeFunctionArguments() {
        /*
        f(uint,uint32[],bytes10,bytes)
        with values
        (0x123, [0x456, 0x789], "1234567890", "Hello, world!")
         */
        val testData = SolidityBase.PartitionData.of("0000000000000000000000000000000000000000000000000000000000000123" +
                "0000000000000000000000000000000000000000000000000000000000000080" +
                "3132333435363738393000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000000000000000e0" +
                "0000000000000000000000000000000000000000000000000000000000000002" +
                "0000000000000000000000000000000000000000000000000000000000000456" +
                "0000000000000000000000000000000000000000000000000000000000000789" +
                "000000000000000000000000000000000000000000000000000000000000000d" +
                "48656c6c6f2c20776f726c642100000000000000000000000000000000000000")
        // Decode uint
        assertEquals(
                Solidity.UInt256.DECODER.decode(testData).value,
                BigInteger("123", 16))

        // Consume location of dynamic uint32 array (we don't need it)
        testData.consume()

        // Decode bytes10
        assertArrayEquals(
                Solidity.Bytes10.DECODER.decode(testData).bytes,
                "1234567890".toByteArray())

        // Consume location of bytes (we don't need it)
        testData.consume()

        // Decode dynamic uint32 array
        assertEquals(
                SolidityBase.DynamicArray.Decoder(Solidity.UInt32.DECODER).decode(testData).items,
                listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16))))

        // Decode bytes
        assertArrayEquals(
                Solidity.Bytes.DECODER.decode(testData).items,
                "Hello, world!".toByteArray())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testMalformedArraySizeDecoding() {
        assertEquals(listOf(BigInteger("456", 16), BigInteger("789", 16)),
                SolidityBase.decodeArray("000000000000000000000000000000000000000000000000000000000000000300000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789", SolidityBase::decodeUInt))
    }

    private fun formatClassName(clazz: String): String {
        val index = clazz.lastIndexOf(".")
        return clazz.replaceRange(index, index + 1, "$")
    }
}
