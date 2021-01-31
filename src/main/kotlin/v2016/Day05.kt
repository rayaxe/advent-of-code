package v2016

import util.md5
import util.toHex

class Day05 {
    companion object {
        fun part1(doorId: String): String {
            var i = 0L
            var password = ""
            while (i < Int.MAX_VALUE) {
                val hash = md5(doorId + i).toHex()
                if (hash.substring(0, 5) == "00000") {
                    password += hash[5]
                    if (password.length == 8) {
                        return password
                    }
                }
                i++
            }
            error("No answer found for Door ID: $doorId")
        }

        fun part2(doorId: String): String {
            val password = CharArray(8) { '_' }
            var i = 0L
            while (i < Int.MAX_VALUE) {
                val hash = md5(doorId + i).toHex()
                if (hash.substring(0, 5) == "00000") {
                    val positionChar = hash[5]
                    if (positionChar in '0' until '8') {
                        val position = Character.getNumericValue(positionChar)
                        if (password[position] == '_') {
                            password[position] = hash[6]
                            if (!password.contains('_')) {
                                return password.joinToString("")
                            }
                        }
                    }
                }
                i++
            }
            error("No answer found for Door ID: $doorId")
        }
    }
}
