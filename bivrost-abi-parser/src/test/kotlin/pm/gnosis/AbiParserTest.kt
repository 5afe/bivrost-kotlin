package pm.gnosis


import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import pm.gnosis.model.AbiRoot
import pm.gnosis.model.ParameterJson
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase
import java.math.BigInteger
import kotlin.reflect.KClass


class AbiParserTest {

    private fun testContext() = AbiParser.GeneratorContext(AbiRoot(ArrayList(), "Test"), AbiParser.ArraysMap("com.example"))

    private fun testParameter(type: String, name: String = "test", components: List<ParameterJson>? = null)
            = ParameterJson(name, type, components)

    private fun assertType(instance: Any, type: KClass<*>) {
        assertTrue("$instance should be a $type", type.isInstance(instance))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidArrayDefOpeningBracketStart() {
        mapType(testParameter("uint[[5][]"), testContext())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidArrayDefOpeningBracketMiddle() {
        mapType(testParameter("uint[5][[]"), testContext())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidArrayDefOpeningBracketEnd() {
        mapType(testParameter("uint[5][]["), testContext())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidArrayDefLetterAsSize() {
        mapType(testParameter("uint[a][]"), testContext())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidArrayDefClosingBracket() {
        mapType(testParameter("uint[5][]]"), testContext())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidArrayDefUnknownType() {
        mapType(testParameter("gnosis[1][]"), testContext())
    }

    @Test()
    fun testSimpleTypeHolder() {
        assertEquals("Unknown type should return null", SimpleTypeHolder.forType("unknown"), null)
        assertEquals("Tuple type should return null", SimpleTypeHolder.forType("tuple"), null)

        val bytes32 = SimpleTypeHolder.forType("bytes32")!!
        assertFalse("bytes32 should be static", bytes32.isDynamic())

        val uint = SimpleTypeHolder.forType("uint")!!
        assertFalse("uint should be static", uint.isDynamic())

        val int = SimpleTypeHolder.forType("int")!!
        assertFalse("int should be static", int.isDynamic())

        val bytes = SimpleTypeHolder.forType("bytes")!!
        assertTrue("bytes should be dynamic", bytes.isDynamic())

        val string = SimpleTypeHolder.forType("string")!!
        assertTrue("string should be dynamic", string.isDynamic())
    }

    @Test()
    fun testParseAliasTypes() {
        val uintType = mapType(testParameter("uint"), testContext())
        assertType(uintType, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), uintType.toTypeName())

        val intType = mapType(testParameter("int"), testContext())
        assertType(intType, SimpleTypeHolder::class)
        assertEquals(Solidity.Int256::class.asClassName(), intType.toTypeName())

        val byteType = mapType(testParameter("byte"), testContext())
        assertType(byteType, SimpleTypeHolder::class)
        assertEquals(Solidity.Bytes1::class.asClassName(), byteType.toTypeName())
    }

    @Test()
    fun testParseDynamicTupleType() {
        val components = listOf(testParameter("uint", "a"), testParameter("uint[]", "b"))
        val tupleType = mapType(testParameter("tuple", components = components), testContext())
        assertType(tupleType, TupleTypeHolder::class)
        val pTuple = tupleType as TupleTypeHolder
        assertEquals(2, pTuple.entries.size)
        assertEquals("a", pTuple.entries[0].first)
        assertType(pTuple.entries[0].second, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), pTuple.entries[0].second.toTypeName())

        assertEquals("b", pTuple.entries[1].first)
        assertType(pTuple.entries[1].second, VectorTypeHolder::class)
        val pArray = pTuple.entries[1].second as VectorTypeHolder
        assertType(pArray.itemType, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), pArray.itemType.toTypeName())

        assertEquals(true, tupleType.isDynamic())
        assertEquals("TupleA", tupleType.name)
        assertEquals(ClassName("", "TupleA"), tupleType.toTypeName())
    }

    @Test()
    fun testStaticDynamicTupleType() {
        val components = listOf(testParameter("uint", "a"), testParameter("uint[5]", "b"))
        val tupleType = mapType(testParameter("tuple", components = components), testContext())
        assertType(tupleType, TupleTypeHolder::class)
        val pTuple = tupleType as TupleTypeHolder
        assertEquals(2, pTuple.entries.size)
        assertEquals("a", pTuple.entries[0].first)
        assertType(pTuple.entries[0].second, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), pTuple.entries[0].second.toTypeName())

        assertEquals("b", pTuple.entries[1].first)
        assertType(pTuple.entries[1].second, ArrayTypeHolder::class)
        val pArray = pTuple.entries[1].second as ArrayTypeHolder
        assertType(pArray.itemType, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), pArray.itemType.toTypeName())
        assertEquals(5, pArray.capacity)

        assertEquals(false, tupleType.isDynamic())
        assertEquals("TupleA", tupleType.name)
        assertEquals(ClassName("", "TupleA"), tupleType.toTypeName())
    }

    @Test()
    fun testParseUIntNestedArray() {
        val type = mapType(testParameter("uint[5][]"), testContext())
        assertType(type, VectorTypeHolder::class)
        val pType = type as VectorTypeHolder
        assertEquals(SolidityBase.Vector::class.asClassName(), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertType(g1Type, ArrayTypeHolder::class)
        val g1pType = g1Type as ArrayTypeHolder
        assertEquals(ClassName("com.example.arrays", "Array5"), g1pType.listType)

        // Second generic type
        val g2Type = g1pType.itemType
        assertEquals(Solidity.UInt256::class.asTypeName(), g2Type.toTypeName())
    }

    @Test()
    fun testParseStringDynamicArray() {
        val type = mapType(testParameter("string[]"), testContext())
        assertType(type, VectorTypeHolder::class)
        val pType = type as VectorTypeHolder
        assertEquals(SolidityBase.Vector::class.asClassName(), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertEquals(Solidity.String::class.asTypeName(), g1Type.toTypeName())
    }

    @Test()
    fun testParseStringStaticArray() {
        val type = mapType(testParameter("string[5]"), testContext())
        assertType(type, ArrayTypeHolder::class)
        val pType = type as ArrayTypeHolder
        assertEquals(5, pType.capacity)
        assertEquals(ClassName("com.example.arrays", "Array5"), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertEquals(Solidity.String::class.asTypeName(), g1Type.toTypeName())
    }

    @Test()
    fun testParseBytesArray() {
        val type = mapType(testParameter("bytes[5]"), testContext())
        assertType(type, ArrayTypeHolder::class)
        val pType = type as ArrayTypeHolder
        assertEquals(5, pType.capacity)
        assertTrue(pType.isDynamic())
        assertEquals(ClassName("com.example.arrays", "Array5"), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertEquals(Solidity.Bytes::class.asTypeName(), g1Type.toTypeName())
        assertTrue(g1Type.isDynamic())
    }

    @Test()
    fun testParseBytesXArray() {
        val type = mapType(testParameter("bytes32[5]"), testContext())
        assertType(type, ArrayTypeHolder::class)
        val pType = type as ArrayTypeHolder
        assertEquals(5, pType.capacity)
        assertFalse(pType.isDynamic())
        assertEquals(ClassName("com.example.arrays", "Array5"), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertEquals(Solidity.Bytes32::class.asTypeName(), g1Type.toTypeName())
        assertFalse(g1Type.isDynamic())
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
                SolidityBase.Vector.Decoder(Solidity.UInt32.DECODER).decode(testData).items,
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
        val arg2 = SolidityBase.Vector(
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

        val arg1 = TestArray(
                listOf(Solidity.UInt32(BigInteger("456", 16)), Solidity.UInt32(BigInteger("789", 16))), 2
        )
        val arg2 = Solidity.String("Hello, world!")
        val arg3 = SolidityBase.Vector(
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

    private class TestArray<out T : SolidityBase.Type>(items: List<T>, capacity: Int) : SolidityBase.Array<T>(items, capacity)
}
