package v2021

class Day04 {
    companion object {
        fun part1(input: String): Long {
            return playBingo(input, true)
        }

        fun part2(input: String): Long {
            return playBingo(input, false)
        }

        private fun playBingo(input: String, hasOneWinner: Boolean): Long {
            val textBlocks = input.split("\n\n")
            val numbersCalled = textBlocks.first().split(",").map { it.toInt() }
            val boards = textBlocks.drop(1).map { parseBoard(it) to false }.toMutableList()

            for (numberCalled in numbersCalled) {
                for (boardIndex in boards.indices) {
                    val (lines, isWinner) = boards[boardIndex]
                    if (isWinner) {
                        continue
                    }
                    lines.forEachIndexed { i, line ->
                        line.forEachIndexed { pos, number ->
                            if (number.first == numberCalled) {
                                lines[i][pos] = number.copy(second = true)
                            }
                        }
                    }
                    if (lines.any { line -> line.all { it.second } }) {
                        boards[boardIndex] = lines to true
                        if (hasOneWinner || boards.count { it.second } == boards.size) {
                            return (sumUnmarked(lines) * numberCalled).toLong()
                        }
                    }
                }
            }
            error("No winner(s)")
        }

        private fun sumUnmarked(board: List<MutableList<Pair<Int, Boolean>>>): Int {
            return board.flatten().toSet().filter { !it.second }.sumOf { it.first }
        }

        private fun parseBoard(board: String): List<MutableList<Pair<Int, Boolean>>> {
            val numbers = board.split("\n").map { it.trim().split("\\s+".toRegex()) }
            val rows = numbers.map { row -> row.map { it.toInt() to false }.toMutableList() }
            val cols = numbers[0].indices.map { x -> numbers.map { it[x].toInt() to false }.toMutableList() }
            return rows + cols
        }
    }
}
