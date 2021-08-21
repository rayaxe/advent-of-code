package v2017

class Day02 {
    companion object {
        fun part1(input: List<String>): Long {
            return input.sumOfRowsBy { numbers: List<Long> -> minMaxDifference(numbers) }
        }

        fun part2(input: List<String>): Long {
            return input.sumOfRowsBy { numbers: List<Long> -> evenlyDivide(numbers) }
        }

        private fun minMaxDifference(numbers: List<Long>): Long {
            return numbers.fold(numbers.first() to numbers.first()) { (min, max), number ->
                when {
                    number < min -> number to max
                    number > max -> min to number
                    else -> min to max
                }
            }.let { (min, max) -> max - min }
        }

        private fun evenlyDivide(numbers: List<Long>): Long {
            val sortedNumbers = numbers.sortedDescending().toMutableList()
            while (sortedNumbers.isNotEmpty()) {
                val dividend = sortedNumbers.removeFirst()
                for (divisor in sortedNumbers) {
                    val quotient = dividend / divisor.toDouble()
                    if (quotient.rem(1).equals(0.0)) {
                        return quotient.toLong()
                    }
                }
            }
            error("No evenly divide found")
        }

        private fun List<String>.sumOfRowsBy(rowFunction: (List<Long>) -> Long): Long {
            val whiteSpace = "\\s+".toRegex()
            return this.sumOf { row ->
                val numbers = row.split(whiteSpace).map { it.toLong() }
                rowFunction(numbers)
            }
        }
    }
}
