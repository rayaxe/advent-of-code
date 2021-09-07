package v2017

import v2017.Day10.Companion.knotHash

class Day14 {
    companion object {
        fun part1(input: String): Long {
            return grid(input).fold(0) { acc, row -> acc + row.count { it == '#' } }
        }

        fun part2(input: String): Long {
            val grid = grid(input)
            val visited = mutableSetOf<Pair<Int, Int>>()
            val directions = listOf(0 to -1, 1 to 0, 0 to 1, -1 to 0)

            fun floodFill(square: Pair<Int, Int>) {
                val frontier = mutableListOf(square)
                while (frontier.isNotEmpty()) {
                    val (y, x) = frontier.removeFirst()
                    directions.forEach { (dy, dx) ->
                        val neighbour = (y + dy) to (x + dx)
                        if (neighbour.first in 0..127 && neighbour.second in 0..127 && neighbour !in visited) {
                            visited.add(neighbour)
                            if (grid[neighbour.first][neighbour.second] == '#') {
                                frontier.add(neighbour)
                            }
                        }
                    }
                }
            }

            var groupCount = 0L
            for (y in 0..127) {
                for (x in 0..127) {
                    if (y to x in visited) {
                        continue
                    }
                    visited.add(y to x)
                    if (grid[y][x] == '#') {
                        groupCount++
                        floodFill(y to x)
                    }
                }
            }

            return groupCount
        }

        private fun grid(input: String): List<String> = (0..127)
            .map { rowNumber -> "$input-$rowNumber".map { it.toInt() } + listOf(17, 31, 73, 47, 23) }
            .map { lengths -> knotHash(256, lengths).toRow() }

        fun String.toRow(): String {
            return this.map { it.convert() }.joinToString("")
        }

        private fun Char.convert(): String {
            return when (this) {
                '0' -> "...."
                '1' -> "...#"
                '2' -> "..#."
                '3' -> "..##"
                '4' -> ".#.."
                '5' -> ".#.#"
                '6' -> ".##."
                '7' -> ".###"
                '8' -> "#..."
                '9' -> "#..#"
                'a' -> "#.#."
                'b' -> "#.##"
                'c' -> "##.."
                'd' -> "##.#"
                'e' -> "###."
                'f' -> "####"
                else -> error("Unexpected character: $this")
            }
        }
    }
}
