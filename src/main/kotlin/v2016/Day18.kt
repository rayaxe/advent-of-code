package v2016

class Day18 {
    companion object {
        fun part1(startRow: String, numberOfRows: Int = 40): Long {
            return countSafeTiles(startRow, numberOfRows)
        }

        fun part2(startRow: String, numberOfRows: Int = 400000): Long {
            return countSafeTiles(startRow, numberOfRows)
        }

        private fun countSafeTiles(startRow: String, numberOfRows: Int): Long {
            return generateRows(startRow)
                .take(numberOfRows)
                .map { row -> row.count { it == '.' } }
                .sum().toLong()
        }

        private fun generateRows(startRow: String): Sequence<String> {
            return generateSequence(startRow) { row ->
                (".$row.")
                    .windowed(3)
                    .map { if (it == "^^." || it == ".^^" || it == "^.." || it == "..^") '^' else '.' }
                    .joinToString("")
            }
        }
    }
}
