package v2015

class Day24 {

    companion object {
        fun part1(input: List<String>): Long {
            return findQuantumEntanglement(input, 3)
        }

        fun part2(input: List<String>): Long {
            return findQuantumEntanglement(input, 4)
        }

        private fun findQuantumEntanglement(input: List<String>, numberOfGroups: Int): Long {
            val weights = input.map { it.toInt() }
            val targetWeight = weights.sum() / numberOfGroups
            return findCombinations(weights, listOf(), targetWeight)
                .sortedWith(compareBy({ it.size }, { it.quantumEntanglement() }))
                .first()
                .quantumEntanglement()
        }

        private fun findCombinations(weights: List<Int>, group: List<Int>, targetWeight: Int): List<List<Int>> {
            if (targetWeight == 0) {
                return listOf(group)
            }
            if (weights.isEmpty() || targetWeight < weights.first() || weights.sum() < targetWeight) {
                return listOf()
            }
            val options = weights.takeWhile { it <= targetWeight }
            return options.flatMap { weight ->
                findCombinations(options.takeWhile { it < weight }, group + weight, targetWeight - weight)
            }
        }

        private fun List<Int>.quantumEntanglement(): Long {
            return this.fold(1L) { acc, it -> acc * it }
        }
    }
}
