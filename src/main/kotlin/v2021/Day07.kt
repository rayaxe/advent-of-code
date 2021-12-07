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

        private fun alignCrabs(input: String, costsFun: (Int, Long) -> Long): Long {
            val crabs = input.split(",").map { it.toInt() }
            val fuel = mutableListOf<Long>()
            for (position in 0..1000L) {
                val costs = crabs.fold(0L) { acc, crab -> acc + costsFun(crab, position) }
                fuel.add(costs)
            }
            return fuel.minOrNull()!!
        }
    }
}
