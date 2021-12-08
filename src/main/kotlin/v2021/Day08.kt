package v2021

class Day08 {
    companion object {
        fun part1(input: List<String>): Long {
            return input
                .flatMap { it.split(" | ").let { (_, output) -> output }.split(" ") }
                .count { it.length in 2..4 || it.length == 7 }.toLong()
        }

        fun part2(input: List<String>): Long {
            return input
                .map { parse(it) }
                .sumOf { (signals, output) ->
                    output
                        .map { findUniquePatterns(signals)[it] }
                        .joinToString("")
                        .toLong()
                }
        }

        private fun findUniquePatterns(signals: List<Set<Char>>): Map<Set<Char>, Char> {
            val segmentsBySize = signals.groupBy { it.size }.toMap()

            val one = segmentsBySize.getValue(2).first()
            val four = segmentsBySize.getValue(4).first()
            val seven = segmentsBySize.getValue(3).first()
            val eight = segmentsBySize.getValue(7).first()

            val fiveSegments = segmentsBySize.getValue(5)
            val three = fiveSegments.first { it.containsAll(one) }
            val five = fiveSegments.first { it.containsAll(four - one) }
            val two = fiveSegments.first { it != three && it != five }

            val sixSegments = segmentsBySize.getValue(6)
            val nine = sixSegments.first { it.containsAll(three) }
            val zero = sixSegments.first { it.containsAll(one) && it != nine }
            val six = sixSegments.first { it != nine && it != zero }

            return mapOf(
                zero to '0',
                one to '1',
                two to '2',
                three to '3',
                four to '4',
                five to '5',
                six to '6',
                seven to '7',
                eight to '8',
                nine to '9',
            )
        }

        private fun parse(line: String): Pair<List<Set<Char>>, List<Set<Char>>> {
            return line
                .split(" | ")
                .map { it.split(" ") }
                .let { (signals, output) ->
                    signals.map {
                        it.toCharArray().toSet()
                    } to output.map {
                        it.toCharArray().toSet()
                    }
                }
        }
    }
}
