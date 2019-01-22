package expected

import java.math.BigInteger
import kotlin.Boolean
import kotlin.String
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase
import pm.gnosis.utils.BigIntegerUtils

class Abi16 {
    object Malformed {
        const val METHOD_ID: String = "a76411a9"

        fun encode(c: TupleA): String {
            return "0x" + METHOD_ID + pm.gnosis.model.SolidityBase.encodeFunctionArguments(c)
        }

        fun decodeArguments(data: String): Arguments {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0Offset = BigIntegerUtils.exact(BigInteger(source.consume(), 16))
            val arg0 = TupleA.DECODER.decode(source.subData(arg0Offset))

            return Arguments(arg0)
        }

        data class Arguments(val c: TupleA)
    }

    data class TupleA(val bytesvar: Solidity.Bytes, val stringvar: Solidity.String) :
            SolidityBase.DynamicType {
        override fun encode(): String {
            return SolidityBase.encodeFunctionArguments(bytesvar, stringvar)
        }

        class Decoder : SolidityBase.TypeDecoder<TupleA> {
            override fun isDynamic(): Boolean = true
            override fun decode(source: SolidityBase.PartitionData): TupleA {
                val arg0Offset = BigIntegerUtils.exact(BigInteger(source.consume(), 16))
                val arg0 = Solidity.Bytes.DECODER.decode(source.subData(arg0Offset))
                val arg1Offset = BigIntegerUtils.exact(BigInteger(source.consume(), 16))
                val arg1 = Solidity.String.DECODER.decode(source.subData(arg1Offset))
                return TupleA(arg0, arg1)
            }
        }

        companion object {
            val DECODER: Decoder = Decoder()
        }
    }
}
