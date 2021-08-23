package v2017

class Day06 {
    companion object {
        private data class State(val state: String) {
            var cycle: Int = 0
        }

        private val whiteSpace = "\\s+".toRegex()

        fun part1(input: String): Long {
            val banks = input
                .split(whiteSpace)
                .map { it.toInt() }
                .toMutableList()
            return reallocate(banks).first.toLong()
        }

        fun part2(input: String): Long {
            val banks = input
                .split(whiteSpace)
                .map { it.toInt() }
                .toMutableList()
            return reallocate(banks).second.toLong()
        }

        private fun reallocate(banks: MutableList<Int>): Pair<Int, Int> {
            val visited = mutableSetOf<State>()
            var state: State
            var cycle = 0
            while (true) {
                state = State(banks.toString())
                state.cycle = cycle
                if (!visited.add(state)) {
                    break
                }
                cycle++
                redistribute(banks)
            }
            val cycleDifference = cycle - visited.first { it == state }.cycle
            return visited.size to cycleDifference
        }

        private fun redistribute(banks: MutableList<Int>) {
            var index = banks.indices.maxByOrNull { banks[it] }!!
            var blocks = banks[index].also { banks[index] = 0 }
            while (blocks > 0) {
                index++
                banks[index % banks.size]++
                blocks--
            }
        }
    }
}
