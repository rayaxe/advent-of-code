package v2021

class Day02 {
    private data class Submarine(val horizontal: Long = 0, val depth: Long = 0, val aim: Long = 0)

    companion object {
        fun part1(input: List<String>): Long {
            return move(
                input,
                { s: Submarine, x: Int -> s.copy(horizontal = s.horizontal + x) },
                { s: Submarine, x: Int -> s.copy(depth = s.depth + x) },
                { s: Submarine, x: Int -> s.copy(depth = s.depth - x) }
            )
        }

        fun part2(input: List<String>): Long {
            return move(
                input,
                { s: Submarine, x: Int -> s.copy(horizontal = s.horizontal + x, depth = s.depth + s.aim * x) },
                { s: Submarine, x: Int -> s.copy(aim = s.aim + x) },
                { s: Submarine, x: Int -> s.copy(aim = s.aim - x) }
            )
        }

        private fun move(
            input: List<String>,
            forward: (Submarine, Int) -> Submarine,
            down: (Submarine, Int) -> Submarine,
            up: (Submarine, Int) -> Submarine
        ): Long {
            return input.fold(Submarine()) { submarine, it ->
                val (command, units) = it.split(" ")
                when (command) {
                    "forward" -> forward(submarine, units.toInt())
                    "down" -> down(submarine, units.toInt())
                    "up" -> up(submarine, units.toInt())
                    else -> error("Unexpected command: $command")
                }
            }.let { it.horizontal * it.depth }
        }
    }
}
