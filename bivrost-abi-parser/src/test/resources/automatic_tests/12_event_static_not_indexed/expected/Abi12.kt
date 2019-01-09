package expected

import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.collections.List
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase

class Abi12 {
    object Events {
        object Submission {
            const val EVENT_ID: String = "c0ba8fe4b176c1714197d43b9cc6bcf797a4a7461c5fe8d0ef6e184ae7601e51"

            fun decode(topics: List<String>, data: String): Arguments {
                // Decode topics
                if (topics.first() != EVENT_ID) throw IllegalArgumentException("topics[0] does not match event id")

                // Decode data
                val source = SolidityBase.PartitionData.of(data)
                val arg0 = Solidity.UInt256.DECODER.decode(source)
                return Arguments(arg0)
            }

            data class Arguments(val transactionid: Solidity.UInt256)
        }
    }
}
