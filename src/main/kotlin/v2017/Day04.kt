package v2017

class Day04 {
    companion object {
        fun part1(input: List<String>): Long {
            return count(input) { it }

        }

        fun part2(input: List<String>): Long {
            return count(input) { it.toCharArray().sorted().joinToString() }
        }

        private fun count(input: List<String>, identityFunction: (String) -> String): Long {
            return input.count { words ->
                val set = mutableSetOf<String>()
                words.split(' ').all { set.add(identityFunction(it)) }
            }.toLong()
        }
    }
}
