package pm.gnosis


import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase


class AbiParserTest {
    @Test()
    fun testParseUIntNestedArray() {
        val type = AbiParser.mapType("uint[5][]")
        assertTrue("Should be a ParameterizedType", type is ParameterizedTypeName)
        val pType = type as ParameterizedTypeName
        assertEquals(pType.rawType, SolidityBase.DynamicArray::class.asClassName())
        assertEquals(pType.typeArguments.size, 1)

        // First generic type
        val g1Type = pType.typeArguments[0]
        assertTrue("Should be a ParameterizedType", g1Type is ParameterizedTypeName)
        val g1pType = g1Type as ParameterizedTypeName
        assertEquals(g1pType.rawType, SolidityBase.FixedArray::class.asClassName())
        assertEquals(g1pType.typeArguments.size, 1)

        // Second generic type
        val g2Type = g1pType.typeArguments[0]
        assertEquals(g2Type, Solidity.UInt256::class.asTypeName())
    }

    @Test()
    fun testParsStringDynamicArray() {
        val type = AbiParser.mapType("string[]")
        assertTrue("Should be a ParameterizedType", type is ParameterizedTypeName)
        val pType = type as ParameterizedTypeName
        assertEquals(pType.rawType, SolidityBase.DynamicArray::class.asClassName())
        assertEquals(pType.typeArguments.size, 1)

        // First generic type
        val g1Type = pType.typeArguments[0]
        assertEquals(g1Type, Solidity.String::class.asTypeName())
    }

}