package v2021

import kotlin.math.abs

class Day07 {
    companion object {
        fun part1(input: String): Long {
            return alignCrabs(input) { crab, position -> abs(crab - position) }
        }

        fun part2(input: String): Long {
            return alignCrabs(input) { crab, position -> triangularNumber(abs(crab - position)) }
        }

        private fun triangularNumber(n: Long): Long {
            return n * (n + 1) / 2
        }

        private fun alignCrabs(input: String, costs: (Long, Long) -> Long): Long {
            val crabs = input.split(",").map { it.toLong() }
            val min = crabs.minOrNull()!!
            val max = crabs.maxOrNull()!!
            return (min..max).minOf { position ->
                crabs.sumOf { crab -> costs(crab, position) }
            }
        }
    }
}
