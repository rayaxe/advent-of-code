package v2016

import util.md5
import util.toHex

class Day17 {
    private data class Room(val x: Int, val y: Int)
    private enum class Direction(val char: Char, val dx: Int, val dy: Int) {
        UP('U', 0, -1),
        DOWN('D', 0, 1),
        LEFT('L', -1, 0),
        RIGHT('R', 1, 0),
    }

    companion object {
        fun part1(input: String): String {
            return solve(input).first()
        }

        fun part2(input: String): Long {
            return solve(input).maxByOrNull { it.length }!!.length.toLong()
        }

        private fun solve(input: String): Sequence<String> {
            val openDoors = 'b'..'f'
            val directions = listOf(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
            val frontier = mutableListOf(input to Room(0, 0))
            return sequence {
                while (frontier.isNotEmpty()) {
                    val (passcode, room) = frontier.removeFirst()
                    if (room.x == 3 && room.y == 3) {
                        yield(passcode.drop(input.length))
                    } else {
                        md5(passcode).toHex().take(4).withIndex()
                            .filter { it.value in openDoors }
                            .map { directions[it.index] }
                            .map { direction -> direction.char to Room(room.x + direction.dx, room.y + direction.dy) }
                            .filter { (_, newRoom) -> newRoom.x in 0..3 && newRoom.y in 0..3 }
                            .forEach { (char, newRoom) -> frontier.add(passcode + char to newRoom) }
                    }
                }
            }
        }
    }
}
