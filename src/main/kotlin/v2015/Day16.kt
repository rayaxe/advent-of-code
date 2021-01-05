package v2015

class Day16 {
    companion object {
        private val tickerTapeMessage = "children: 3\n" +
                "cats: 7\n" +
                "samoyeds: 2\n" +
                "pomeranians: 3\n" +
                "akitas: 0\n" +
                "vizslas: 0\n" +
                "goldfish: 5\n" +
                "trees: 3\n" +
                "cars: 2\n" +
                "perfumes: 1"

        fun part1(input: List<String>): Long {
            return matchAunt(input) { 0 }
        }

        fun part2(input: List<String>): Long {
            return matchAunt(input) {
                when (it) {
                    "cats", "trees" -> 1
                    "pomeranians", "goldfish" -> -1
                    else -> 0
                }
            }
        }

        private fun matchAunt(input: List<String>, compareTo: (String) -> Int): Long {
            val detectedCompounds = parseDetectedCompounds()
            return input.map { parse(it) }
                .first { (_, compounds) ->
                    compounds.all {
                        it.value.compareTo(detectedCompounds.getValue(it.key)) == compareTo(it.key)
                    }
                }.first
        }

        private fun parseDetectedCompounds() = tickerTapeMessage.split("\n").map {
            val (compound, value) = it.split(": ")
            compound to value.toInt()
        }.toMap()

        private val auntRegex =
            """Sue ([0-9]+): ([a-z]+): ([0-9]+), ([a-z]+): ([0-9]+), ([a-z]+): ([0-9]+)""".toRegex()

        private fun parse(text: String): Pair<Long, Map<String, Int>> {
            val matchEntire = auntRegex.matchEntire(text)
            return matchEntire
                ?.destructured
                ?.let { (number, compound1, compound1Value, compound2, compound2Value, compound3, compound3Value) ->
                    return number.toLong() to mapOf(
                        compound1 to compound1Value.toInt(),
                        compound2 to compound2Value.toInt(),
                        compound3 to compound3Value.toInt()
                    )
                }
                ?: throw IllegalArgumentException("Bad input '$text'")
        }
    }
}
