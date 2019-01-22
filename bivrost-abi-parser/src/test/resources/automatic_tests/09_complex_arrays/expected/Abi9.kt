package expected

import expected.arrays.Array5
import expected.arrays.Array7
import java.math.BigInteger
import kotlin.Boolean
import kotlin.String
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase
import pm.gnosis.utils.BigIntegerUtils

class Abi9 {
    object Owners {
        const val METHOD_ID: String = "9f767eb7"

        fun encode(c: SolidityBase.Vector<TupleA>, arg2:
                SolidityBase.Vector<SolidityBase.Vector<Array7<Array5<Solidity.UInt256>>>>): String
                {
            return "0x" + METHOD_ID + pm.gnosis.model.SolidityBase.encodeFunctionArguments(c, arg2)
        }

        fun decode(data: String): Return {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0 = TupleB.DECODER.decode(source)
            val arg1Offset = BigIntegerUtils.exact(BigInteger(source.consume(), 16))
            val arg1 = SolidityBase.Vector.Decoder(TupleB.DECODER).decode(source.subData(arg1Offset))

            return Return(arg0, arg1)
        }

        fun decodeArguments(data: String): Arguments {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0Offset = BigIntegerUtils.exact(BigInteger(source.consume(), 16))
            val arg0 = SolidityBase.Vector.Decoder(TupleA.DECODER).decode(source.subData(arg0Offset))
            val arg1Offset = BigIntegerUtils.exact(BigInteger(source.consume(), 16))
            val arg1 = SolidityBase.Vector.Decoder(SolidityBase.Vector.Decoder(Array7.Decoder(Array5.Decoder(Solidity.UInt256.DECODER)))).decode(source.subData(arg1Offset))

            return Arguments(arg0, arg1)
        }

        data class Return(val param0: TupleB, val param1: SolidityBase.Vector<TupleB>)

        data class Arguments(val c: SolidityBase.Vector<TupleA>, val param1:
                SolidityBase.Vector<SolidityBase.Vector<Array7<Array5<Solidity.UInt256>>>>)
    }

    data class TupleA(
        val a: Solidity.UInt256,
        val b: Solidity.UInt256,
        val param2: SolidityBase.Vector<SolidityBase.Vector<Array7<Array5<Solidity.UInt256>>>>
    ) : SolidityBase.DynamicType {
        override fun encode(): String {
            return SolidityBase.encodeFunctionArguments(a, b, param2)
        }

        class Decoder : SolidityBase.TypeDecoder<TupleA> {
            override fun isDynamic(): Boolean = true
            override fun decode(source: SolidityBase.PartitionData): TupleA {
                val arg0 = Solidity.UInt256.DECODER.decode(source)
                val arg1 = Solidity.UInt256.DECODER.decode(source)
                val arg2Offset = BigIntegerUtils.exact(BigInteger(source.consume(), 16))
                val arg2 = SolidityBase.Vector.Decoder(SolidityBase.Vector.Decoder(Array7.Decoder(Array5.Decoder(Solidity.UInt256.DECODER)))).decode(source.subData(arg2Offset))
                return TupleA(arg0, arg1, arg2)
            }
        }

        companion object {
            val DECODER: Decoder = Decoder()
        }
    }

    data class TupleB(val x: Solidity.UInt256, val y: Solidity.UInt256) : SolidityBase.StaticType {
        override fun encode(): String {
            return SolidityBase.encodeFunctionArguments(x, y)
        }

        class Decoder : SolidityBase.TypeDecoder<TupleB> {
            override fun isDynamic(): Boolean = false
            override fun decode(source: SolidityBase.PartitionData): TupleB {
                val arg0 = Solidity.UInt256.DECODER.decode(source)
                val arg1 = Solidity.UInt256.DECODER.decode(source)
                return TupleB(arg0, arg1)
            }
        }

        companion object {
            val DECODER: Decoder = Decoder()
        }
    }
}
