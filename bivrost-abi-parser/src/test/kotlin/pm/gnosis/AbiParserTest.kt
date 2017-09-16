package pm.gnosis


import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase
import java.math.BigInteger
import kotlin.reflect.KClass


class AbiParserTest {

    private fun assertType(instance: Any, type: KClass<*>) {
        assertTrue("$instance should be a $type", type.isInstance(instance))
    }

    @Test()
    fun testParseUIntNestedArray() {
        val type = AbiParser.mapType("uint[5][]")
        assertType(type, AbiParser.VectorTypeHolder::class)
        val pType = type as AbiParser.VectorTypeHolder
        assertEquals(SolidityBase.VectorST::class.asClassName(), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertType(g1Type, AbiParser.ArrayTypeHolder::class)
        val g1pType = g1Type as AbiParser.ArrayTypeHolder
        assertEquals(SolidityBase.ArrayST::class.asClassName(), g1pType.listType)

        // Second generic type
        val g2Type = g1pType.itemType
        assertEquals(Solidity.UInt256::class.asTypeName(), g2Type.toTypeName())
    }

    @Test()
    fun testParsStringDynamicArray() {
        val type = AbiParser.mapType("string[]")
        assertType(type, AbiParser.VectorTypeHolder::class)
        val pType = type as AbiParser.VectorTypeHolder
        assertEquals(SolidityBase.VectorDT::class.asClassName(), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertEquals(Solidity.String::class.asTypeName(), g1Type.toTypeName())
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
                SolidityBase.VectorST.Decoder(Solidity.UInt32.DECODER).decode(testData).items,
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
        val arg2 = SolidityBase.VectorST(
                listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16)))
        )
        val arg3 = Solidity.Bytes10("1234567890".toByteArray())
        val arg4 = Solidity.String("Hello, world!")
        val data = SolidityBase.encodeFunctionArguments(arg1, arg2, arg3, arg4)

        val expected = "" +
                "0000000000000000000000000000000000000000000000000000000000000123" +
                "0000000000000000000000000000000000000000000000000000000000000080" +
                "3132333435363738393000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000000000000000e0" +
                "0000000000000000000000000000000000000000000000000000000000000002" +
                "0000000000000000000000000000000000000000000000000000000000000456" +
                "0000000000000000000000000000000000000000000000000000000000000789" +
                "000000000000000000000000000000000000000000000000000000000000000d" +
                "48656c6c6f2c20776f726c642100000000000000000000000000000000000000"
        assertEquals(data, expected)
    }

    @Test
    fun testEncodeFunctionArgumentsWithStaticArray() {
        /*
        f(uint32[2],bytes,uint32[])
        with values
        ([0x456, 0x789], "Hello, world!", [0x123])
         */

        val arg1 = SolidityBase.ArrayST(
                listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16))), 2
        )
        val arg2 = Solidity.String("Hello, world!")
        val arg3 = SolidityBase.VectorST(
                listOf(Solidity.UInt32(BigInteger("123", 16)))
        )
        val data = SolidityBase.encodeFunctionArguments(arg1, arg2, arg3)

        val expected = "" +
                // uint32[2]
                "0000000000000000000000000000000000000000000000000000000000000456" +
                "0000000000000000000000000000000000000000000000000000000000000789" +
                // Pointer to bytes
                "0000000000000000000000000000000000000000000000000000000000000080" +
                // Pointer to uint32[]
                "00000000000000000000000000000000000000000000000000000000000000c0" +

                // Dynamic Part
                // bytes -> "Hello world!"
                "000000000000000000000000000000000000000000000000000000000000000d" +
                "48656c6c6f2c20776f726c642100000000000000000000000000000000000000" +
                // uint32[] -> [0x123]
                "0000000000000000000000000000000000000000000000000000000000000001" +
                "0000000000000000000000000000000000000000000000000000000000000123"
        assertEquals(data, expected)
    }

}