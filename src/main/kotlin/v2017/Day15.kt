package v2017

class Day15 {
    companion object {
        fun part1(input: List<String>): Long {
            val (firstA, firstB) = parse(input)
            val a = generator(firstA, 16807L)
            val b = generator(firstB, 48271L)
            val n = 40_000_000
            return duel(a, b, n)
        }

        fun part2(input: List<String>): Long {
            val (firstA, firstB) = parse(input)
            val a = generator(firstA, 16807L) { it % 4L == 0L }
            val b = generator(firstB, 48271L) { it % 8L == 0L }
            val n = 5_000_000
            return duel(a, b, n)
        }

        private fun parse(input: List<String>) = input.map { it.split(' ').last().toLong() }

        private fun generator(firstValue: Long, factor: Long, predicate: (Long) -> Boolean = { true }) = sequence {
            var number = firstValue
            while (true) {
                number = (number * factor) % 2147483647L
                if (predicate(number)) {
                    yield(number)
                }
            }
        }

        private fun duel(a: Sequence<Long>, b: Sequence<Long>, n: Int) = (a zip b)
            .map { (a, b) -> a.toUShort() == b.toUShort() }
            .drop(1)
            .take(n)
            .count { it }.toLong()
    }
}
