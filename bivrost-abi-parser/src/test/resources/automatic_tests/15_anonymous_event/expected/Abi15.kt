package expected

import kotlin.String
import kotlin.collections.List
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase

class Abi15 {
    object Events {
        object Executed {
            const val EVENT_ID: String = "d3ecebd73eb8f84cd179cd91fe1e27c1a112b3c76ca5ceaca1661a808d6081aa"

            fun decode(topics: List<String>): Arguments {
                // Decode topics
                val source1 = SolidityBase.PartitionData.of(topics[1])
                val t1 = Solidity.UInt256.DECODER.decode(source1)
                return Arguments(t1)
            }

            data class Arguments(val transactionid: Solidity.UInt256)
        }
    }
}
