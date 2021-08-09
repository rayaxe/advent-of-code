package v2016

class Day13 {

    private data class Coordinate(val x: Int, val y: Int)

    companion object {

        fun part1(favoriteNumber: Int, target: Pair<Int, Int>): Long {
            return solve(favoriteNumber, Int.MAX_VALUE)
                .first { (pos, _) -> pos.x == target.first && pos.y == target.second }
                .second.toLong()
        }

        fun part2(favoriteNumber: Int, maxSteps: Int): Long {
            return solve(favoriteNumber, maxSteps)
                .toList()
                .size.toLong()
        }

        private fun solve(favoriteNumber: Int, maxSteps: Int = 0): Sequence<Pair<Coordinate, Int>> {
            return sequence {
                val directions = listOf(Pair(0, -1), Pair(1, 0), Pair(0, 1), Pair(-1, 0))
                val visited = mutableSetOf<Coordinate>()
                val frontier = mutableListOf(Coordinate(1, 1) to 0)
                while (frontier.isNotEmpty()) {
                    val (coordinate, steps) = frontier.removeFirst()

                    if (!visited.add(coordinate)) {
                        continue
                    }

                    yield(coordinate to steps)

                    if (steps == maxSteps) {
                        continue
                    }

                    directions
                        .map { (dx, dy) -> Coordinate(coordinate.x + dx, coordinate.y + dy) }
                        .filter { it.isOpen(favoriteNumber) }
                        .forEach { frontier.add(it to steps + 1) }
                }
            }
        }

        private fun Coordinate.isOpen(favoriteNumber: Int): Boolean {
            return x >= 0 && y >= 0 && ((x * x + 3L * x + 2L * x * y + y + y * y) + favoriteNumber)
                .toString(2)
                .count { it == '1' }
                .let { it % 2 == 0 }
        }
    }
}
