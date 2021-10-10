package v2017

class Day19 {
    private enum class Direction() {
        N, E, S, W;

        fun moves(): List<Direction> {
            return when (this) {
                N -> listOf(N, E, W)
                E -> listOf(E, S, N)
                S -> listOf(S, W, E)
                W -> listOf(W, N, S)
            }
        }
    }

    private class Packet(val x: Int, val y: Int, val direction: Direction) {
        fun move(direction: Direction): Packet {
            return when (direction) {
                Direction.N -> Packet(x, y - 1, direction)
                Direction.E -> Packet(x + 1, y, direction)
                Direction.S -> Packet(x, y + 1, direction)
                Direction.W -> Packet(x - 1, y, direction)
            }
        }
    }

    companion object {
        fun part1(input: List<String>): String {
            return solve(input).first
        }

        fun part2(input: List<String>): Int {
            return solve(input).second
        }

        private fun solve(input: List<String>): Pair<String, Int> {
            val x = input[0].indexOfFirst { it == '|' }
            var packet = Packet(x, 0, Direction.S)
            val letters = mutableListOf<Char>()
            var steps = 1
            while (true) {
                val (next, letter) = packet.direction.moves()
                    .map { packet.move(it) }
                    .filter { it.x in input[0].indices && it.y in input.indices }
                    .map { it to input[it.y][it.x] }
                    .firstOrNull { (p, _) -> input[p.y][p.x] != ' ' }
                    ?: break
                if (letter in 'A'..'Z') {
                    letters.add(letter)
                }
                packet = next
                steps++
            }
            return letters.joinToString("") to steps
        }
    }
}
