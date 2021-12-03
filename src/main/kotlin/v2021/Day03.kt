package v2021

import kotlin.math.pow

class Day03 {
    companion object {
        fun part1(input: List<String>): Long {
            val zeroes: Array<Int> = Array(input[0].length) { 0 }
            input.forEach { it.forEachIndexed { index, c -> if (c == '0') zeroes[index]++ } }
            val gammaRate = zeroes
                .map { if (it >= input.size * .5) '0' else '1' }
                .joinToString("")
                .toLong(2)
            val max = 2.0.pow(input[0].length).toLong() - 1
            val epsilonRate = max - gammaRate
            return gammaRate * epsilonRate
        }

        fun part2(input: List<String>): Long {
            val oxygenGeneratorRating = rate(input) { a, b -> a > b }
            val co2ScrubberRating = rate(input) { a, b -> a <= b }
            return oxygenGeneratorRating * co2ScrubberRating
        }

        private fun rate(input: List<String>, compare: (Int, Int) -> Boolean): Long {
            val numbers = input.toMutableSet()
            for (i in input[0].indices) {
                val (zeroes, ones) = numbers.partition { it[i] == '0' }
                if (compare(zeroes.size, ones.size)) {
                    numbers.removeIf { it[i] == '1' }
                } else {
                    numbers.removeIf { it[i] == '0' }
                }
                if (numbers.size == 1) {
                    return numbers.first().toLong(2)
                }
            }
            error("No result found")
        }
    }
}
