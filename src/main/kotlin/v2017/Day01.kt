package v2017

class Day01 {
    companion object {
        fun part1(input: String): Long {
            val offset = 1
            return inverseCaptcha(input, offset)
        }

        fun part2(input: String): Long {
            val offset = input.length / 2
            return inverseCaptcha(input, offset)
        }

        private fun inverseCaptcha(input: String, offset: Int): Long {
            var sum = 0L
            for (i in input.indices) {
                if (input[i] == input[(i + offset) % input.length]) {
                    sum += Character.getNumericValue(input[i])
                }
            }
            return sum
        }
    }
}
