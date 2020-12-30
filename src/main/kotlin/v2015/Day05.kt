package v2015

fun day05Part1(input: List<String>): Long {
    return input.filter { isNiceWithOldRules(it) }.count().toLong()
}

fun day05Part2(input: List<String>): Long {
    return input.filter { isNice(it) }.count().toLong()
}

/**
 * A nice string —using old rules— is one with all of the following properties:
 * - It contains at least three vowels (`aeiou` only), like `aei`, `xazegov`, or `aeiouaeiouaeiou`.
 * - It contains at least one letter that appears twice in a row, like `xx`, `abcdde` (`dd`),
 *   or `aabbccdd` (`aa`, `bb`, `cc`, or `dd`).
 * - It does not contain the strings `ab`, `cd`, `pq`, or `xy`, even if they are part of one of the other requirements.
 */
private fun isNiceWithOldRules(string: String): Boolean {
    var vowelCount = 0L
    var hasRepeatingChar = false
    for (i in string.indices) {
        val char = string[i]
        if (i + 1 < string.length) {
            val nextChar = string[i + 1]

            when (char) {
                'a' -> if (nextChar == 'b') return false
                'c' -> if (nextChar == 'd') return false
                'p' -> if (nextChar == 'q') return false
                'x' -> if (nextChar == 'y') return false
            }
            if (!hasRepeatingChar) {
                hasRepeatingChar = char == nextChar
            }
        }
        if (char in "aeiou") {
            vowelCount++
        }
    }
    return vowelCount >= 3 && hasRepeatingChar
}

/**
 * A nice string —using new rules— is one with all of the following properties:
 * - It contains a pair of any two letters that appears at least twice in the string without overlapping,
 *   like `xyxy` (`xy`) or `aabcdefgaa` (`aa`), but not like `aaa` (`aa`, but it overlaps).
 * - It contains at least one letter which repeats with exactly one letter between them,
 *   like `xyx`, `abcdefeghi` (`efe`), or even `aaa`.
 */
private fun isNice(string: String): Boolean {
    val pairsOfLetters = mutableMapOf<String, Int>()
    var hasRepeatingPair = false
    var hasRepeatingLetterWithOneLetterBetween = false
    for (i in string.indices) {
        val char = string[i]
        if (i + 1 < string.length) {
            if (!hasRepeatingPair) {
                val pair = "" + char + string[i + 1]
                if (pairsOfLetters.containsKey(pair)) {
                    if (pairsOfLetters.getValue(pair) != i - 1) {
                        hasRepeatingPair = true
                    }
                } else {
                    pairsOfLetters[pair] = i
                }
            }
            if (!hasRepeatingLetterWithOneLetterBetween) {
                if (i + 2 < string.length) {
                    hasRepeatingLetterWithOneLetterBetween = string[i] == string[i + 2]
                }
            }
        }
        if (hasRepeatingPair && hasRepeatingLetterWithOneLetterBetween) {
            return true
        }
    }
    return false
}
