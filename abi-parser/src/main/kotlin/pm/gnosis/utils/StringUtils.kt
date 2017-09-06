package pm.gnosis.utils

import org.bouncycastle.jcajce.provider.digest.Keccak
import kotlin.experimental.and

fun String.generateSolidityMethodId(): String {
    val sha3 = Keccak.Digest256()
    sha3.update(this.toByteArray())
    val buff = StringBuffer()
    for (b in sha3.digest()) {
        buff.append(String.format("%02x", b and 0xFF.toByte()))
    }
    return buff.toString().substring(0..7)
}
