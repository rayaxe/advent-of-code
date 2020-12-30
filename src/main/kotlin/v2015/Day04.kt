package v2015

import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

private fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))
private fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }

fun day04(secretKey: String, numberOfLeadingZeroes: Int = 5): Long {
    val leadingZeroes = "0".repeat(numberOfLeadingZeroes)
    var i = 1L
    while (i < Int.MAX_VALUE) {
        val hash = md5(secretKey + i).toHex()
        if (leadingZeroes == hash.substring(0, numberOfLeadingZeroes)) {
            return i
        }
        i++
    }
    error("No answer found for secret key: $secretKey")
}
