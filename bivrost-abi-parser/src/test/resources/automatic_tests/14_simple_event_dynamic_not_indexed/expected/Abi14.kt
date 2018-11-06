package expected

import java.lang.IllegalArgumentException
import java.math.BigInteger
import kotlin.Boolean
import kotlin.String
import kotlin.collections.List
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase

class Abi14 {
    object Events {
        object Submission {
            const val EVENT_ID: String = "0c5212e9d002fa3e0c9bd8c78b6d4df3e94f4e956761bd40f0859c979600a2e7"

            fun decode(topics: List<String>, data: String): Arguments {
                // Decode topics
                if (topics.first() != EVENT_ID) throw IllegalArgumentException("topics[0] does not match event id")

                // Decode data
                val source = SolidityBase.PartitionData.of(data)
                val arg0Offset = BigInteger(source.consume(), 16).intValueExact()
                val arg0 = Solidity.Bytes.DECODER.decode(source.subData(arg0Offset))
                val arg1Offset = BigInteger(source.consume(), 16).intValueExact()
                val arg1 = Solidity.String.DECODER.decode(source.subData(arg1Offset))
                val arg2 = TupleA.DECODER.decode(source)
                return Arguments(arg0, arg1, arg2)
            }

            data class Arguments(
                    val bytes: Solidity.Bytes,
                    val string: Solidity.String,
                    val tuple: TupleA
            )
        }
    }

    data class TupleA(val x: Solidity.UInt256, val y: Solidity.UInt256) : SolidityBase.StaticType {
        override fun encode(): String = SolidityBase.encodeFunctionArguments(x, y)

        class Decoder : SolidityBase.TypeDecoder<TupleA> {
            override fun isDynamic(): Boolean = false
            override fun decode(source: SolidityBase.PartitionData): TupleA {
                val arg0 = Solidity.UInt256.DECODER.decode(source)
                val arg1 = Solidity.UInt256.DECODER.decode(source)
                return TupleA(arg0, arg1)
            }
        }
        companion object {
            val DECODER: Decoder = Decoder()
        }
    }
}
