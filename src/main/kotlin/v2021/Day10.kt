package v2021

class Day10 {
    companion object {
        fun part1(input: List<String>): Long {
            return input
                .map { findIllegalCharOrRemainingLines(it.toMutableList()) }
                .groupingBy { (illegalChar, _) -> illegalChar }
                .eachCount()
                .map { (char, count) -> scoreIllegal(char) * count }
                .sum()
        }

        fun part2(input: List<String>): Long {
            return input
                .map { findIllegalCharOrRemainingLines(it.toMutableList()) }
                .filter { (illegalChar, _) -> illegalChar == null }
                .map { (_, chars) -> chars.fold(0L) { acc, char -> acc * 5 + scoreIncomplete(pair(char)) } }
                .sorted()
                .let { it[it.size / 2] }
        }

        fun findIllegalCharOrRemainingLines(chars: MutableList<Char>): Pair<Char?, List<Char>> {
            val stack = mutableListOf<Char>()
            while (chars.isNotEmpty()) {
                when (val char = chars.removeFirst()) {
                    '(', '[', '{', '<' -> stack.add(0, char)
                    // Return early on first illegal character
                    else -> if (pair(char) != stack.removeFirst()) return char to stack
                }
            }
            return null to stack
        }

        private fun scoreIllegal(char: Char?): Long {
            return when (char) {
                ')' -> 3L
                ']' -> 57L
                '}' -> 1197L
                '>' -> 25137L
                else -> 0L
            }
        }

        private fun scoreIncomplete(char: Char?): Long {
            return when (char) {
                ')' -> 1L
                ']' -> 2L
                '}' -> 3L
                '>' -> 4L
                else -> 0L
            }
        }

        private fun pair(char: Char): Char {
            return when (char) {
                '(' -> ')'
                '[' -> ']'
                '{' -> '}'
                '<' -> '>'
                ')' -> '('
                ']' -> '['
                '}' -> '{'
                '>' -> '<'
                else -> error("Unexpected char: $char")
            }
        }
    }
}
