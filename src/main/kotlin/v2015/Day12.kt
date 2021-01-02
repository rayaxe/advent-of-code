package v2015

class Day12 {

    companion object {
        private val numberRegex = """-?[0-9]+""".toRegex()

        fun part1(input: List<String>): Long {
            return input.map {
                numberRegex.findAll(it)
                    .map { matchResult -> matchResult.value.toLong() }
                    .sum()
            }.sum()
        }

        fun part2(input: List<String>): Long {
            return -1L
        }
    }
}
