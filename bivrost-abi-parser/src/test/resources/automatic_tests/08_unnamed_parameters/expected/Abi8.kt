package expected

import java.math.BigInteger
import kotlin.String
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase

class Abi8 {
    object Function {
        const val METHOD_ID: String = "06da0736"

        fun encode(arg1: Solidity.Address): String = "0x" + METHOD_ID + pm.gnosis.model.SolidityBase.encodeFunctionArguments(arg1)

        fun decode(data: String): Return {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0Offset = BigInteger(source.consume(), 16).intValueExact()
            val arg0 = Solidity.Bytes.DECODER.decode(source.subData(arg0Offset))

            return Return(arg0)
        }

        fun decodeArguments(data: String): Arguments {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0 = Solidity.Address.DECODER.decode(source)

            return Arguments(arg0)
        }

        data class Return(val param0: Solidity.Bytes)

        data class Arguments(val param0: Solidity.Address)
    }
}
