import kotlin.experimental.and

fun String.padStartMultiple(multiple: Int, padChar: Char = ' ') =
        this.padStart(if (this.length % multiple != 0) this.length + multiple - this.length % multiple else 0, padChar)


private val hexArray = "0123456789ABCDEF".toCharArray()

fun ByteArray.toHex(): String {
    val hexChars = CharArray(this.size * 2)
    for (j in this.indices) {
        val v = (this[j] and 0xFF.toByte()).toInt()
        hexChars[j * 2] = hexArray[v ushr 4]
        hexChars[j * 2 + 1] = hexArray[v and 0x0F]
    }
    return String(hexChars)
}
