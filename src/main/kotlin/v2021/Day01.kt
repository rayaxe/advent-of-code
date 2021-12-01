package v2021

class Day01 {
    companion object {
        fun part1(input: List<String>): Long {
            return sweep(input)
        }

        fun part2(input: List<String>): Long {
            return sweep(input, 3)
        }

        private fun sweep(measurements: List<String>, windowSize: Int = 1): Long {
            return measurements
                .map { it.toInt() }
                .windowed(windowSize, 1)
                .zipWithNext()
                .fold(0) { acc, (a, b) -> acc + if (a.sum() < b.sum()) 1 else 0 }
        }
    }
}
