package v2017

class Day05 {
    companion object {
        fun part1(input: List<String>): Long {
            return jump(input) { jumps, pointer ->
                jumps[pointer]++
            }
        }

        fun part2(input: List<String>): Long {
            return jump(input) { jumps, pointer ->
                when {
                    jumps[pointer] < 3 -> jumps[pointer]++
                    else -> jumps[pointer]--
                }
            }
        }

        private fun jump(input: List<String>, jumpModifier: (MutableList<Int>, Int) -> Unit): Long {
            val jumps = input.map { it.toInt() }.toMutableList()
            var pointer = 0
            var steps = 0L
            while (pointer < jumps.size) {
                pointer += jumps[pointer].also { jumpModifier(jumps, pointer) }
                steps++
            }
            return steps
        }
    }
}
