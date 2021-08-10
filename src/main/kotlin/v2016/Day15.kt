package v2016

class Day15 {
    companion object {
        private val discRegex =
            "Disc #[1-9] has ([1-9]?[0-9]) positions; at time=0, it is at position ([1-9]?[0-9])\\.".toRegex()

        fun part1(input: List<String>): Long {
            val discs = input.map { parse(it) }
            return timeToPressButton(discs)
        }

        fun part2(input: List<String>): Long {
            val discs = input.map { parse(it) } + Pair(11L, 0L)
            return timeToPressButton(discs)
        }

        private fun timeToPressButton(discs: List<Pair<Long, Long>>): Long {
            var time = 0L
            while (true) {
                val isAligned = discs.mapIndexed { index, (number, offset) ->
                    (number + time + offset + index + 1L) % number == 0L
                }.all { it }
                if (isAligned) {
                    return time
                }
                time++
            }
        }

        private fun parse(text: String): Pair<Long, Long> {
            return discRegex.matchEntire(text)
                ?.destructured
                ?.let { (number, offset) -> Pair(number.toLong(), offset.toLong()) }
                ?: throw IllegalArgumentException("Bad input '$text'")
        }
    }
}
