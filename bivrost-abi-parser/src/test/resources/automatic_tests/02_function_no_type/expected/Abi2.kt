package expected

import kotlin.String

class Abi2 {
    object Function {
        const val METHOD_ID: String = "9d96e2df"

        fun encode(): String = "0x" + METHOD_ID
    }
}
