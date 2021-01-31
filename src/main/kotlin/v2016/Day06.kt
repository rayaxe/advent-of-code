package v2016

class Day06 {
    companion object {
        fun part1(message: List<String>): String {
            return correct(message)
        }

        fun part2(message: List<String>): String {
            return correct(message, false)
        }

        private fun correct(input: List<String>, mostCommon: Boolean = true): String {
            val chars = Array<MutableList<Char>>(input[0].length) { mutableListOf() }
            input.forEach { it.forEachIndexed { index, char -> chars[index].add(char) } }
            return chars.fold("") { acc, candidates ->
                val occurrences = candidates.groupingBy { it }.eachCount()
                val letter = if (mostCommon) {
                    occurrences.maxByOrNull { it.value }!!
                } else {
                    occurrences.minByOrNull { it.value }!!
                }
                acc + letter.key
            }
        }
    }
}
