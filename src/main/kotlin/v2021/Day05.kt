package v2021

import kotlin.math.max
import kotlin.math.min

class Day05 {
    companion object {
        fun part1(input: List<String>): Long {
            return count(input) { (from, to) -> from.first == to.first || from.second == to.second }
        }

        fun part2(input: List<String>): Long {
            return count(input) { true }
        }

        private fun count(input: List<String>, filter: (Pair<Pair<Int, Int>, Pair<Int, Int>>) -> Boolean): Long {
            val vents: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
            parse(input).filter { filter(it) }.forEach {
                val (x1, y1) = it.first
                val (x2, y2) = it.second
                val dx = direction(x1, x2)
                val dy = direction(y1, y2)
                var (x, y) = it.first
                while (x in min(x1, x2)..max(x1, x2) && y in min(y1, y2)..max(y1, y2)) {
                    vents[x to y] = (vents[x to y] ?: 0) + 1
                    x += dx
                    y += dy
                }
            }
            return vents.values.count { it > 1 }.toLong()
        }

        private fun direction(a: Int, b: Int) = if (a == b) 0 else if (a < b) 1 else -1

        private fun parse(input: List<String>) = input
            .map { it.split(" -> ") }
            .map {
                it.map { coordinate ->
                    coordinate
                        .split(",")
                        .let { (x, y) -> x.toInt() to y.toInt() }
                }.let { (from, to) -> from to to }
            }
    }
}
