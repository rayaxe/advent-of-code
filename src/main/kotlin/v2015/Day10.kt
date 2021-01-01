package v2015

class Day10 {
    companion object {
        fun part1(input: String): Long {
            return repeatLookAndSay(input, 40)
        }

        fun part2(input: String): Long {
            return repeatLookAndSay(input, 50)
        }

        private fun repeatLookAndSay(input: String, times: Int): Long {
            var result = input
            repeat(times) { result = lookAndSay(result) }
            return result.length.toLong()
        }

        fun lookAndSay(input: String): String {
            val sequence = StringBuilder()
            var current = input.first()
            var count = 1
            for (i in 1 until input.length) {
                current = input[i]
                val prev = input[i - 1]
                if (prev == current) {
                    count++
                } else {
                    sequence.append(count).append(prev)
                    count = 1
                }
            }
            return sequence.append(count).append(current).toString()
        }
    }
}
