package pm.gnosis.model

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import pm.gnosis.exceptions.InvalidBitLengthException
import java.lang.reflect.InvocationTargetException
import java.math.BigInteger

class SolidityBaseTest {
    @Test
    fun testUIntEncoding() {
        assertEquals(
            "0000000000000000000000000000000000000000000000000000000000000000",
            Solidity.UInt256(BigInteger.ZERO).encode()
        )

        assertEquals(
            "0000000000000000000000000000000000000000000000000000000000000001",
            Solidity.UInt256(BigInteger.ONE).encode()
        )

        //Max unsigned integer
        assertEquals(
            "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
            Solidity.UInt256(BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935")).encode()
        )
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
        assertEquals(
            BigInteger.ZERO,
            SolidityBase.decodeUInt("0000000000000000000000000000000000000000000000000000000000000000")
        )

        assertEquals(
            BigInteger.ONE,
            SolidityBase.decodeUInt("0000000000000000000000000000000000000000000000000000000000000001")
        )

        assertEquals(
            BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935"),
            SolidityBase.decodeUInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
        )
    }

    @Test
    fun testUIntDecodingWithPartitionData() {
        assertEquals(
            Solidity.UInt32(BigInteger.ZERO),
            Solidity.UInt32.DECODER.decode(SolidityBase.PartitionData.of("0000000000000000000000000000000000000000000000000000000000000000"))
        )

        assertEquals(
            Solidity.UInt8(BigInteger.ONE),
            Solidity.UInt8.DECODER.decode(SolidityBase.PartitionData.of("0000000000000000000000000000000000000000000000000000000000000001"))
        )

        assertEquals(
            Solidity.UInt256(BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935")),
            Solidity.UInt256.DECODER.decode(SolidityBase.PartitionData.of("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"))
        )
    }

    @Test
    fun testBoolEncoding() {
        assertEquals(
            "0000000000000000000000000000000000000000000000000000000000000000",
            Solidity.Bool(false).encode()
        )

        assertEquals(
            "0000000000000000000000000000000000000000000000000000000000000001",
            Solidity.Bool(true).encode()
        )
    }

    @Test
    fun testBoolDecoding() {
        assertEquals(
            false,
            SolidityBase.decodeBool("0000000000000000000000000000000000000000000000000000000000000000")
        )

        assertEquals(
            true,
            SolidityBase.decodeBool("0000000000000000000000000000000000000000000000000000000000000001")
        )
    }

    @Test
    fun testBoolDecodingWithPartitionData() {
        assertEquals(
            Solidity.Bool(false),
            Solidity.Bool.DECODER.decode(SolidityBase.PartitionData.of("0000000000000000000000000000000000000000000000000000000000000000"))
        )

        assertEquals(
            Solidity.Bool(true),
            Solidity.Bool.DECODER.decode(SolidityBase.PartitionData.of("0000000000000000000000000000000000000000000000000000000000000001"))
        )
    }

    @Test
    fun testIntEncoding() {
        assertEquals(
            "0000000000000000000000000000000000000000000000000000000000000000",
            Solidity.Int8(BigInteger.ZERO).encode()
        )

        assertEquals(
            "0000000000000000000000000000000000000000000000000000000000000001",
            Solidity.Int8(BigInteger.ONE).encode()
        )

        assertEquals(
            "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",
            Solidity.Int8(BigInteger.valueOf(-1)).encode()
        )

        assertEquals(
            "000000000000000000000000000000000000000000000000000000000000007f",
            Solidity.Int8(BigInteger.valueOf(127)).encode()
        )

        assertEquals(
            "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80",
            Solidity.Int8(BigInteger.valueOf(-128)).encode()
        )
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
        assertEquals(
            BigInteger.ZERO,
            SolidityBase.decodeInt("0000000000000000000000000000000000000000000000000000000000000000")
        )

        assertEquals(
            BigInteger.ONE,
            SolidityBase.decodeInt("0000000000000000000000000000000000000000000000000000000000000001")
        )

        assertEquals(
            BigInteger.valueOf(-1),
            SolidityBase.decodeInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff")
        )

        assertEquals(
            BigInteger.valueOf(127),
            SolidityBase.decodeInt("000000000000000000000000000000000000000000000000000000000000007f")
        )

        assertEquals(
            BigInteger.valueOf(-128),
            SolidityBase.decodeInt("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80")
        )
    }

    @Test
    fun testIntDecodingWithPartitionData() {
        assertEquals(
            Solidity.Int32(BigInteger.ZERO),
            Solidity.Int32.DECODER.decode(SolidityBase.PartitionData.of("0000000000000000000000000000000000000000000000000000000000000000"))
        )

        assertEquals(
            Solidity.Int8(BigInteger.ONE),
            Solidity.Int8.DECODER.decode(SolidityBase.PartitionData.of("0000000000000000000000000000000000000000000000000000000000000001"))
        )

        assertEquals(
            Solidity.Int256(BigInteger.valueOf(-1)),
            Solidity.Int256.DECODER.decode(SolidityBase.PartitionData.of("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"))
        )

        assertEquals(
            Solidity.Int256(BigInteger.valueOf(127)),
            Solidity.Int256.DECODER.decode(SolidityBase.PartitionData.of("000000000000000000000000000000000000000000000000000000000000007f"))
        )

        assertEquals(
            Solidity.Int256(BigInteger.valueOf(-128)),
            Solidity.Int256.DECODER.decode(SolidityBase.PartitionData.of("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff80"))
        )
    }

    @Test
    fun testStaticBytesEncoding() {
        assertEquals(
            "0000000000000000000000000000000000000000000000000000000000000000",
            Solidity.Bytes1(byteArrayOf(0)).encode()
        )

        assertEquals(
            "0001000000000000000000000000000000000000000000000000000000000000",
            Solidity.Bytes2(byteArrayOf(0, 1)).encode()
        )

        assertEquals(
            "0001020000000000000000000000000000000000000000000000000000000000",
            Solidity.Bytes3(byteArrayOf(0, 1, 2)).encode()
        )

        assertEquals(
            "6461766500000000000000000000000000000000000000000000000000000000",
            Solidity.Bytes4("dave".toByteArray()).encode()
        )
    }

    @Test
    fun testStaticBytesDecoding() {
        assertArrayEquals(byteArrayOf(0), SolidityBase.decodeStaticBytes("0000000000000000000000000000000000000000000000000000000000000000", 1))

        assertArrayEquals(byteArrayOf(0, 1), SolidityBase.decodeStaticBytes("0001000000000000000000000000000000000000000000000000000000000000", 2))

        assertArrayEquals(byteArrayOf(0, 1, 2), SolidityBase.decodeStaticBytes("0001020000000000000000000000000000000000000000000000000000000000", 3))

        assertArrayEquals("dave".toByteArray(), SolidityBase.decodeStaticBytes("6461766500000000000000000000000000000000000000000000000000000000", 4))
    }

    @Test
    fun testStaticBytesDecodingWithPartitionData() {
        assertEquals(
            Solidity.Bytes1(byteArrayOf(0)),
            Solidity.Bytes1.DECODER.decode(SolidityBase.PartitionData.of("0000000000000000000000000000000000000000000000000000000000000000"))
        )

        assertEquals(
            Solidity.Bytes2(byteArrayOf(0, 1)),
            Solidity.Bytes2.DECODER.decode(SolidityBase.PartitionData.of("0001000000000000000000000000000000000000000000000000000000000000"))
        )

        assertEquals(
            Solidity.Bytes3(byteArrayOf(0, 1, 2)),
            Solidity.Bytes3.DECODER.decode(SolidityBase.PartitionData.of("0001020000000000000000000000000000000000000000000000000000000000"))
        )

        assertEquals(
            Solidity.Bytes4("dave".toByteArray()),
            Solidity.Bytes4.DECODER.decode(SolidityBase.PartitionData.of("6461766500000000000000000000000000000000000000000000000000000000"))
        )
    }

    @Test
    fun testStaticBytesRange() {
        (1..32).forEach { bytesSize ->
            val bytes = ByteArray(bytesSize) { it.toByte() }
            val constructor = Class.forName(formatClassName(Solidity.types["bytes$bytesSize"]!!)).constructors[0]
            constructor.newInstance(bytes)
            try {
                val oversizedBytes = ByteArray(bytesSize + 1) { it.toByte() }
                constructor.newInstance(oversizedBytes)
                fail("Expected IllegalArgumentException")
            } catch (e: InvocationTargetException) {
                if (e.targetException !is IllegalArgumentException) throw e
            }
        }
    }

    @Test
    fun testDynamicArrayEncoding() {
        assertEquals(
            "000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789",
            SolidityBase.Vector(listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16)))).encode()
        )

        assertEquals(
            "000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000",
            SolidityBase.Vector(listOf(Solidity.Bool(true), Solidity.Bool(false))).encode()
        )

        assertEquals(
            "0000000000000000000000000000000000000000000000000000000000000000",
            SolidityBase.Vector(emptyList()).encode()
        )
    }

    @Test
    fun testZeroLengthArray() {
        val testData = ""
        val source = SolidityBase.PartitionData.of(testData)
        val expected = TestArray<Solidity.UInt256>(emptyList(), 0)
        // Check that the expected does correct encoding
        assertEquals("", expected.encode())
        // Check that the decoding returns the expected
        assertEquals(expected, TestArray.Decoder(Solidity.UInt256.DECODER, 0).decode(source))
    }

    @Test
    fun testDynamicArrayDecoding() {
        var testData =
            "000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789"
        assertEquals(
            listOf(Solidity.UInt256(BigInteger("456", 16)), Solidity.UInt256(BigInteger("789", 16))),
            SolidityBase.Vector.Decoder(Solidity.UInt256.DECODER).decode(SolidityBase.PartitionData.of(testData)).items
        )

        testData =
                "0000000000000000000000000000000000000000000000000000000000000003000000000000000000000000000000000000000000000000000000000000032100000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789"
        val source = SolidityBase.PartitionData.of(testData)
        val expected = SolidityBase.Vector(
            listOf(
                TestArray(listOf(Solidity.UInt256(BigInteger("321", 16))), 1),
                TestArray(listOf(Solidity.UInt256(BigInteger("456", 16))), 1),
                TestArray(listOf(Solidity.UInt256(BigInteger("789", 16))), 1)
            )
        )
        assertEquals(expected, SolidityBase.Vector.Decoder(TestArray.Decoder(Solidity.UInt256.DECODER, 1)).decode(source))
    }

    @Test
    fun testFixedArrayEncoding() {
        assertEquals(
            "00000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789",
            TestArray(listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16))), 2).encode()
        )

        assertEquals(
            "00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000",
            TestArray(listOf(Solidity.Bool(true), Solidity.Bool(false)), 2).encode()
        )

        assertEquals(
            "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
            TestArray(listOf(Solidity.Bytes1(kotlin.ByteArray(0)), Solidity.Bytes1(kotlin.ByteArray(0))), 2).encode()
        )
    }

    @Test
    fun testFixedArrayDecoding() {
        val testData =
            "00000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789"
        assertEquals(
            listOf(Solidity.UInt256(BigInteger("456", 16)), Solidity.UInt256(BigInteger("789", 16))),
            TestArray.Decoder(Solidity.UInt256.DECODER, 2).decode(SolidityBase.PartitionData.of(testData)).items
        )
    }

    @Test
    fun testMultiTypeDecoding() {
        val testData =
            "00000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000003210000000000000000000000000000000000000000000000000000000000000765"
        val source = SolidityBase.PartitionData.of(testData)
        assertEquals(
            listOf(Solidity.UInt256(BigInteger("456", 16)), Solidity.UInt256(BigInteger("789", 16))),
            TestArray.Decoder(Solidity.UInt256.DECODER, 2).decode(source).items
        )
        assertEquals(
            listOf(Solidity.UInt256(BigInteger("321", 16)), Solidity.UInt256(BigInteger("765", 16))),
            SolidityBase.Vector.Decoder(Solidity.UInt256.DECODER).decode(source).items
        )
    }

    @Test
    fun testArrayOfStaticDecoding() {
        assertEquals(
            listOf(BigInteger("456", 16), BigInteger("789", 16)),
            SolidityBase.decodeArray(
                "000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789",
                SolidityBase::decodeUInt
            )
        )

        assertEquals(
            listOf(true, false),
            SolidityBase.decodeArray(
                "000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000",
                SolidityBase::decodeBool
            )
        )

        assertEquals(
            emptyList<SolidityBase.StaticType>(),
            SolidityBase.decodeArray("0000000000000000000000000000000000000000000000000000000000000000", SolidityBase::decodeBool)
        )
    }

    @Test
    fun testDynamicBytesEncoding() {
        assertEquals(
            "000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000",
            Solidity.Bytes("Hello, world!".toByteArray()).encode()
        )

        assertEquals(
            "00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000",
            Solidity.Bytes(byteArrayOf(0)).encode()
        )

        assertEquals(
            "00000000000000000000000000000000000000000000000000000000000000020001000000000000000000000000000000000000000000000000000000000000",
            Solidity.Bytes(byteArrayOf(0, 1)).encode()
        )

        assertEquals(
            "00000000000000000000000000000000000000000000000000000000000000030001020000000000000000000000000000000000000000000000000000000000",
            Solidity.Bytes(byteArrayOf(0, 1, 2)).encode()
        )

        assertEquals(
            "00000000000000000000000000000000000000000000000000000000000000046461766500000000000000000000000000000000000000000000000000000000",
            Solidity.Bytes("dave".toByteArray()).encode()
        )
    }

    @Test
    fun testDynamicBytesDecoding() {
        assertArrayEquals(
            "Hello, world!".toByteArray(),
            SolidityBase.decodeBytes(SolidityBase.PartitionData.of("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000"))
        )

        assertArrayEquals(
            byteArrayOf(0),
            SolidityBase.decodeBytes(SolidityBase.PartitionData.of("00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000"))
        )

        assertArrayEquals(
            byteArrayOf(0, 1),
            SolidityBase.decodeBytes(SolidityBase.PartitionData.of("00000000000000000000000000000000000000000000000000000000000000020001000000000000000000000000000000000000000000000000000000000000"))
        )

        assertArrayEquals(
            byteArrayOf(0, 1, 2),
            SolidityBase.decodeBytes(SolidityBase.PartitionData.of("00000000000000000000000000000000000000000000000000000000000000030001020000000000000000000000000000000000000000000000000000000000"))
        )

        assertArrayEquals(
            "dave".toByteArray(),
            SolidityBase.decodeBytes(SolidityBase.PartitionData.of("00000000000000000000000000000000000000000000000000000000000000046461766500000000000000000000000000000000000000000000000000000000"))
        )
    }

    @Test
    fun testStringDecoding() {
        assertEquals(
            "Hello, world!",
            SolidityBase.decodeString(SolidityBase.PartitionData.of("000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000"))
        )
    }

    @Test
    fun testDecodeEncodeDynamicStringArray() {
        val items = listOf(
            Solidity.String("Hi"), Solidity.String("I"), Solidity.String("want"),
            Solidity.String("to"), Solidity.String("learn"), Solidity.String("Solidity")
        )
        val encoded = SolidityBase.Vector(items).encode()
        assertEquals("Encoded string not correct!", ENCODED_DYNAMIC_STRING_ARRAY, encoded)
        val decoded = SolidityBase.Vector.Decoder(Solidity.String.DECODER).decode(SolidityBase.PartitionData.of(encoded))
        assertEquals(items.size, decoded.items.size)
        for (i in 0 until items.size) {
            assertEquals(items[i].value, decoded.items[i].value)
        }
    }

    @Test
    fun testDecodeEncodeDynamicTuple() {
        val uint32s = listOf(
            Solidity.UInt32(BigInteger("456", 16)),
            Solidity.UInt32(BigInteger("789", 16))
        )
        val items = listOf(
            Solidity.UInt256(BigInteger("123", 16)),
            SolidityBase.Vector(uint32s),
            Solidity.Bytes10("1234567890".toByteArray()),
            Solidity.Bytes("Hello, world!".toByteArray())
        )
        val encoded = SolidityBase.encodeTuple(items)
        assertEquals("Encoded string not correct!", ENCODED_SOLIDITY_EXAMPLE_TUPLE, encoded)

        val source = SolidityBase.PartitionData.of(encoded)
        assertEquals(Solidity.UInt256.DECODER.decode(source), items[0])
        val offsetUint32s = BigInteger(source.consume(), 16).intValueExact()
        assertEquals(SolidityBase.Vector.Decoder(Solidity.UInt32.DECODER).decode(source.subData(offsetUint32s)), items[1])
        assertEquals(Solidity.Bytes10.DECODER.decode(source), items[2])
        val offsetBytes = BigInteger(source.consume(), 16).intValueExact()
        Assert.assertArrayEquals(Solidity.Bytes.DECODER.decode(source.subData(offsetBytes)).items, (items[3] as Solidity.Bytes).items)
    }

    @Test
    fun testDecodeEncodeDynamicTupleNested() {
        val uints1 = SolidityBase.Vector(
            listOf(
                Solidity.UInt256(BigInteger("1", 16)),
                Solidity.UInt256(BigInteger("2", 16))
            )
        )
        val uints2 = SolidityBase.Vector(
            listOf(
                Solidity.UInt256(BigInteger("3", 16))
            )
        )
        val uintsArrays = SolidityBase.Vector(listOf(uints1, uints2))
        val strings = SolidityBase.Vector(listOf("one", "two", "three").map { Solidity.String(it) })
        val items = listOf(uintsArrays, strings)
        val encoded = SolidityBase.encodeTuple(items)
        assertEquals("Encoded string not correct!", ENCODED_SOLIDITY_EXAMPLE_TUPLE_NESTED, encoded)

        val source = SolidityBase.PartitionData.of(encoded)

        val offsetUints = BigInteger(source.consume(), 16).intValueExact()
        assertEquals(
            SolidityBase.Vector.Decoder(SolidityBase.Vector.Decoder(Solidity.UInt256.DECODER))
                .decode(source.subData(offsetUints)), items[0]
        )

        val offsetStrings = BigInteger(source.consume(), 16).intValueExact()
        assertEquals(
            SolidityBase.Vector.Decoder(Solidity.String.DECODER)
                .decode(source.subData(offsetStrings)), items[1]
        )
    }

    @Test
    fun testDecodeEncodeStaticStringArray() {
        val items = listOf(
            Solidity.String("Hi"), Solidity.String("I"), Solidity.String("want"),
            Solidity.String("to"), Solidity.String("learn"), Solidity.String("Solidity")
        )
        val encoded = TestArray(items, 6).encode()
        assertEquals("Encoded string not correct!", ENCODED_STATIC_STRING_ARRAY, encoded)
        val decoded = TestArray.Decoder(Solidity.String.DECODER, 6).decode(SolidityBase.PartitionData.of(encoded))
        assertEquals(items.size, decoded.items.size)
        for (i in 0 until items.size) {
            assertEquals(items[i].value, decoded.items[i].value)
        }
    }

    @Test
    fun testStringEncoding() {
        assertEquals(
            "000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c642100000000000000000000000000000000000000",
            Solidity.String("Hello, world!").encode()
        )
    }

    @Test(expected = Exception::class)
    fun testMalformedArraySizeDecoding() {
        assertEquals(
            listOf(BigInteger("456", 16), BigInteger("789", 16)),
            SolidityBase.decodeArray(
                "000000000000000000000000000000000000000000000000000000000000000300000000000000000000000000000000000000000000000000000000000004560000000000000000000000000000000000000000000000000000000000000789",
                SolidityBase::decodeUInt
            )
        )
    }

    // Web3py and Web3js add an 0-bytes body to empty arrays, we should still correctly decode the data
    @Test
    fun testMalformedBytes() {
        val source = SolidityBase.PartitionData.of(ENCODED_MALFORMED_BYTES_TUPLE)
        // This would be the code generated for a tuple (bytes, string)
        val bytesOffset = BigInteger(source.consume(), 16).intValueExact()
        Assert.assertArrayEquals(
            Solidity.Bytes(byteArrayOf()).items,
            Solidity.Bytes.DECODER.decode(source.subData(bytesOffset)).items
        )
        val stringOffset = BigInteger(source.consume(), 16).intValueExact()
        assertEquals(
            Solidity.String("Broken"),
            Solidity.String.DECODER.decode(source.subData(stringOffset))
        )
    }

    private fun formatClassName(clazz: String): String {
        val index = clazz.lastIndexOf(".")
        return clazz.replaceRange(index, index + 1, "$")
    }

    private class TestArray<out T : SolidityBase.Type>(items: List<T>, capacity: Int) : SolidityBase.Array<T>(items, capacity) {

        class Decoder<out T : SolidityBase.Type>(val itemDecoder: SolidityBase.TypeDecoder<T>, val capacity: Int) :
            SolidityBase.TypeDecoder<TestArray<T>> {
            override fun isDynamic(): Boolean = itemDecoder.isDynamic()
            override fun decode(source: SolidityBase.PartitionData): TestArray<T> =
                TestArray(SolidityBase.decodeList(source, capacity, itemDecoder), capacity)
        }
    }

    companion object {

        const val ENCODED_MALFORMED_BYTES_TUPLE = "" +
                // Location of first empty bytes
                "0000000000000000000000000000000000000000000000000000000000000040" +
                // Location of String "Broken"
                "0000000000000000000000000000000000000000000000000000000000000080" +

                // Length of empty bytes
                "0000000000000000000000000000000000000000000000000000000000000000" +
                // Empty body of empty bytes
                "0000000000000000000000000000000000000000000000000000000000000000" +

                // Length of "Broken"
                "0000000000000000000000000000000000000000000000000000000000000006" +
                // Byte string of "Broken"
                "42726f6b656e0000000000000000000000000000000000000000000000000000"

        // Encoded string of ["Hi", "I", "want", "to", "learn", "Solidity"]
        const val ENCODED_STATIC_STRING_ARRAY = "" +
                // Location of String "Hi"
                "00000000000000000000000000000000000000000000000000000000000000c0" +
                // Location of String "I"
                "0000000000000000000000000000000000000000000000000000000000000100" +
                // Location of String "want"
                "0000000000000000000000000000000000000000000000000000000000000140" +
                // Location of String "to"
                "0000000000000000000000000000000000000000000000000000000000000180" +
                // Location of String "learn"
                "00000000000000000000000000000000000000000000000000000000000001c0" +
                // Location of String "Solidity"
                "0000000000000000000000000000000000000000000000000000000000000200" +

                // Length of "Hi"
                "0000000000000000000000000000000000000000000000000000000000000002" +
                // Byte string of "Hi"
                "4869000000000000000000000000000000000000000000000000000000000000" +

                // Length of "I"
                "0000000000000000000000000000000000000000000000000000000000000001" +
                // Byte string of "I"
                "4900000000000000000000000000000000000000000000000000000000000000" +

                // Length of "want"
                "0000000000000000000000000000000000000000000000000000000000000004" +
                // Byte string of "Want"
                "77616e7400000000000000000000000000000000000000000000000000000000" +

                // Length of "to"
                "0000000000000000000000000000000000000000000000000000000000000002" +
                // Byte string of "to"
                "746f000000000000000000000000000000000000000000000000000000000000" +

                // Length of "learn"
                "0000000000000000000000000000000000000000000000000000000000000005" +
                // Byte string of "learn"
                "6c6561726e000000000000000000000000000000000000000000000000000000" +

                // Length of "Solidity"
                "0000000000000000000000000000000000000000000000000000000000000008" +
                // Byte string of "Solidity"
                "536f6c6964697479000000000000000000000000000000000000000000000000"

        const val ENCODED_DYNAMIC_STRING_ARRAY = "" +
                // Array length
                "0000000000000000000000000000000000000000000000000000000000000006" +
                ENCODED_STATIC_STRING_ARRAY

        const val ENCODED_SOLIDITY_EXAMPLE_TUPLE = "" +
                "0000000000000000000000000000000000000000000000000000000000000123" +
                "0000000000000000000000000000000000000000000000000000000000000080" +
                "3132333435363738393000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000000000000000e0" +
                "0000000000000000000000000000000000000000000000000000000000000002" +
                "0000000000000000000000000000000000000000000000000000000000000456" +
                "0000000000000000000000000000000000000000000000000000000000000789" +
                "000000000000000000000000000000000000000000000000000000000000000d" +
                "48656c6c6f2c20776f726c642100000000000000000000000000000000000000"

        const val ENCODED_SOLIDITY_EXAMPLE_TUPLE_NESTED = "" +
                "0000000000000000000000000000000000000000000000000000000000000040" +
                "0000000000000000000000000000000000000000000000000000000000000140" +
                "0000000000000000000000000000000000000000000000000000000000000002" +
                "0000000000000000000000000000000000000000000000000000000000000040" +
                "00000000000000000000000000000000000000000000000000000000000000a0" +
                "0000000000000000000000000000000000000000000000000000000000000002" +
                "0000000000000000000000000000000000000000000000000000000000000001" +
                "0000000000000000000000000000000000000000000000000000000000000002" +
                "0000000000000000000000000000000000000000000000000000000000000001" +
                "0000000000000000000000000000000000000000000000000000000000000003" +
                "0000000000000000000000000000000000000000000000000000000000000003" +
                "0000000000000000000000000000000000000000000000000000000000000060" +
                "00000000000000000000000000000000000000000000000000000000000000a0" +
                "00000000000000000000000000000000000000000000000000000000000000e0" +
                "0000000000000000000000000000000000000000000000000000000000000003" +
                "6f6e650000000000000000000000000000000000000000000000000000000000" +
                "0000000000000000000000000000000000000000000000000000000000000003" +
                "74776f0000000000000000000000000000000000000000000000000000000000" +
                "0000000000000000000000000000000000000000000000000000000000000005" +
                "7468726565000000000000000000000000000000000000000000000000000000"

    }
}
