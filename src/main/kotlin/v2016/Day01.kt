package v2016

import kotlin.math.abs

class Day01 {
    companion object {
        fun part1(input: List<String>): Long {
            return visit(input)
        }

        fun part2(input: List<String>): Long {
            return visit(input, true)
        }

        private fun visit(input: List<String>, returnOnVisitedTwice: Boolean = false): Long {
            var orientation = Orientation.NORTH
            var x = 0L
            var y = 0L
            val visited = mutableSetOf(x to y)
            for (instruction in input) {
                orientation = when (val rotateInstruction = instruction.first()) {
                    'L' -> orientation.rotateLeft()
                    'R' -> orientation.rotateRight()
                    else -> error("Unexpected rotate instruction: $rotateInstruction")
                }
                val steps = instruction.drop(1).toInt()
                for (step in 1..steps) {
                    when (orientation) {
                        Orientation.NORTH -> y++
                        Orientation.EAST -> x++
                        Orientation.SOUTH -> y--
                        Orientation.WEST -> x--
                    }
                    if (returnOnVisitedTwice) {
                        if (visited.contains(x to y)) {
                            return abs(x) + abs(y)
                        } else {
                            visited.add(x to y)
                        }
                    }
                }
            }
            return abs(x) + abs(y)
        }
    }

    enum class Orientation {
        NORTH, EAST, SOUTH, WEST;

        fun rotateLeft(): Orientation {
            return when (this) {
                NORTH -> WEST
                EAST -> NORTH
                SOUTH -> EAST
                WEST -> SOUTH
            }
        }

        fun rotateRight(): Orientation {
            return when (this) {
                NORTH -> EAST
                EAST -> SOUTH
                SOUTH -> WEST
                WEST -> NORTH
            }
        }
    }
}
