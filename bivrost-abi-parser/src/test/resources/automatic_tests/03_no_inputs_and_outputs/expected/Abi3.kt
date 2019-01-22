package expected

import kotlin.String

class Abi3 {
    object Function {
        const val METHOD_ID: String = "9d96e2df"

        fun encode(): String {
            return "0x" + METHOD_ID
        }
    }
}
