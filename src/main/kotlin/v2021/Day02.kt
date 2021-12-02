package v2021

class Day02 {
    private data class Submarine(val horizontal: Long = 0, val depth: Long = 0, val aim: Long = 0)

    companion object {
        fun part1(input: List<String>): Long {
            return dive(
                input,
                { x -> copy(horizontal = horizontal + x) },
                { x -> copy(depth = depth + x) },
                { x -> copy(depth = depth - x) }
            )
        }

        fun part2(input: List<String>): Long {
            return dive(
                input,
                { x -> copy(horizontal = horizontal + x, depth = depth + aim * x) },
                { x -> copy(aim = aim + x) },
                { x -> copy(aim = aim - x) }
            )
        }

        private fun dive(
            input: List<String>,
            forward: Submarine.(Int) -> Submarine,
            down: Submarine.(Int) -> Submarine,
            up: Submarine.(Int) -> Submarine
        ): Long {
            return input.fold(Submarine()) { submarine, it ->
                val (command, units) = it.split(" ").let { (s1, s2) -> s1 to s2.toInt() }
                when (command) {
                    "forward" -> submarine.forward(units)
                    "down" -> submarine.down(units)
                    "up" -> submarine.up(units)
                    else -> error("Unexpected command: $command")
                }
            }.let { it.horizontal * it.depth }
        }
    }
}
