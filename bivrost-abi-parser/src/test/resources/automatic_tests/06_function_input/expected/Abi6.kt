package expected

import kotlin.String
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase

class Abi6 {
    object Function {
        const val METHOD_ID: String = "06da0736"

        fun encode(owner: Solidity.Address): String {
            return "0x" + METHOD_ID + pm.gnosis.model.SolidityBase.encodeFunctionArguments(owner)
        }

        fun decodeArguments(data: String): Arguments {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0 = Solidity.Address.DECODER.decode(source)

            return Arguments(arg0)
        }

        data class Arguments(
            val owner: Solidity.Address
        )
    }
}
