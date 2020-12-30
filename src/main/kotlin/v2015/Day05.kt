package v2015

fun day05Part1(input: List<String>): Long {
    return input.filter { isNice(it) }.count().toLong()
}

private fun isNice(string: String): Boolean {
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

fun day05Part2(input: List<String>): Long {
    return -1L
}
