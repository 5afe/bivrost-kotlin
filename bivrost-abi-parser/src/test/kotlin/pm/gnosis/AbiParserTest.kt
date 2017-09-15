package pm.gnosis


import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase
import java.math.BigInteger


class AbiParserTest {
    @Test()
    fun testParseUIntNestedArray() {
        val type = AbiParser.mapType("uint[5][]")
        assertTrue("Should be a ParameterizedType", type is AbiParser.ArrayTypeHolder)
        val pType = type as AbiParser.ArrayTypeHolder
        assertEquals(pType.listType, SolidityBase.DynamicArray::class.asClassName())

        // First generic type
        val g1Type = pType.itemType
        assertTrue("Should be a ParameterizedType", g1Type is AbiParser.ArrayTypeHolder)
        val g1pType = g1Type as AbiParser.ArrayTypeHolder
        assertEquals(g1pType.listType, SolidityBase.FixedArray::class.asClassName())

        // Second generic type
        val g2Type = g1pType.itemType
        assertEquals(g2Type.toTypeName(), Solidity.UInt256::class.asTypeName())
    }

    @Test()
    fun testParsStringDynamicArray() {
        val type = AbiParser.mapType("string[]")
        assertTrue("Should be a ParameterizedType", type is AbiParser.ArrayTypeHolder)
        val pType = type as AbiParser.ArrayTypeHolder
        assertEquals(pType.listType, SolidityBase.DynamicArray::class.asClassName())

        // First generic type
        val g1Type = pType.itemType
        assertEquals(g1Type.toTypeName(), Solidity.String::class.asTypeName())
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
        Assert.assertArrayEquals(
                Solidity.Bytes10.DECODER.decode(testData).bytes,
                "1234567890".toByteArray())

        // Consume location of bytes (we don't need it)
        testData.consume()

        // Decode dynamic uint32 array
        assertEquals(
                SolidityBase.DynamicArray.Decoder(Solidity.UInt32.DECODER).decode(testData).items,
                listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16))))

        // Decode bytes
        Assert.assertArrayEquals(
                Solidity.Bytes.DECODER.decode(testData).items,
                "Hello, world!".toByteArray())
    }

    @Test
    fun testEncodeFunctionArguments() {
        /*
        f(uint,uint32[],bytes10,bytes)
        with values
        (0x123, [0x456, 0x789], "1234567890", "Hello, world!")
         */

        val arg1 = Solidity.UInt256(BigInteger("123", 16))
        val arg2 = SolidityBase.DynamicArray(
                listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16)))
        )
        val arg3 = Solidity.Bytes10("1234567890".toByteArray())
        val arg4 = Solidity.String("Hello, world!")
        val data = SolidityBase.encodeFunctionArguments(arg1, arg2, arg3, arg4)

        val expected = "0000000000000000000000000000000000000000000000000000000000000123" +
                "0000000000000000000000000000000000000000000000000000000000000080" +
                "3132333435363738393000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000000000000000e0" +
                "0000000000000000000000000000000000000000000000000000000000000002" +
                "0000000000000000000000000000000000000000000000000000000000000456" +
                "0000000000000000000000000000000000000000000000000000000000000789" +
                "000000000000000000000000000000000000000000000000000000000000000d" +
                "48656c6c6f2c20776f726c642100000000000000000000000000000000000000"
        System.out.println(data)
        System.out.println(expected)
        assertEquals(data, expected)
    }

}