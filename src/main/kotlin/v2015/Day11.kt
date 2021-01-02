package v2015

class Day11 {
    companion object {
        fun part1(input: String): String {
            var password = input
            var isValid = false
            while (!isValid) {
                password = nextPassword(password)
                password = removeInvalidLetters(password)
                isValid = containsThreeStraightLetters(password) && containsNonOverlappingPairsOfLetters(password)
            }
            return password
        }

        fun part2(input: String): String {
            return part1(part1(input))
        }

        fun containsNonOverlappingPairsOfLetters(password: String): Boolean {
            var i = 0
            var count = 0
            while (i < password.length - 1) {
                if (password[i] == password[i + 1]) {
                    count++
                    if (count == 2) {
                        return true
                    }
                    i++
                }
                i++
            }
            return false
        }

        fun containsThreeStraightLetters(password: String): Boolean {
            for (i in 0 until password.length - 2) {
                val first = password[i]
                val second = password[i + 1] - 1
                val third = password[i + 2] - 2
                if (first == second && second == third) {
                    return true
                }
            }
            return false
        }

        fun removeInvalidLetters(password: String): String {
            val next = StringBuilder(password)
            for (i in next.indices) {
                if (next[i] == 'i' || next[i] == 'o' || next[i] == 'l') {
                    next[i] = next[i] + 1
                    for (j in i + 1 until next.length) {
                        next[j] = 'a'
                    }
                    break
                }
            }
            return next.toString()
        }

        fun nextPassword(password: String): String {
            val next = StringBuilder(password).reverse()
            for (i in next.indices) {
                if (next[i] == 'z') {
                    next[i] = 'a'
                } else {
                    next[i] = next[i] + 1
                    break
                }
            }
            return next.reverse().toString()
        }
    }
}
