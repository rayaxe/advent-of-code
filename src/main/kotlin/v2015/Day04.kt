package v2015

import util.md5
import util.toHex

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
