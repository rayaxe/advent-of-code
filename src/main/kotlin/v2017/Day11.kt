package v2017

import kotlin.math.abs

class Day11 {
    private enum class Direction { N, NE, SE, S, SW, NW }

    // Hexagons using cube coordinates
    // https://www.redblobgames.com/grids/hexagons/
    private data class Hex(val x: Int, val y: Int, val z: Int) {
        fun move(direction: Direction): Hex {
            return when (direction) {
                Direction.N -> Hex(x, y + 1, z - 1)
                Direction.NE -> Hex(x + 1, y, z - 1)
                Direction.SE -> Hex(x + 1, y - 1, z)
                Direction.S -> Hex(x, y - 1, z + 1)
                Direction.SW -> Hex(x - 1, y, z + 1)
                Direction.NW -> Hex(x - 1, y + 1, z)
            }
        }

        fun distance(): Int {
            return (abs(x) + abs(y) + abs(z)) / 2
        }
    }

    companion object {
        fun part1(input: String): Long {
            return parseDirections(input)
                .fold(Hex(0, 0, 0)) { acc, direction -> acc.move(direction) }
                .distance().toLong()
        }

        fun part2(input: String): Long {
            var hex = Hex(0, 0, 0)
            return parseDirections(input)
                .map { direction -> hex = hex.move(direction); hex }
                .maxOf { it.distance() }.toLong()
        }

        private fun parseDirections(input: String) = input.split(",")
            .map {
                when (it) {
                    "n" -> Direction.N
                    "ne" -> Direction.NE
                    "se" -> Direction.SE
                    "s" -> Direction.S
                    "sw" -> Direction.SW
                    "nw" -> Direction.NW
                    else -> error("Unknown direction: $it")
                }
            }
    }
}
