package v2017

class Day13 {
    companion object {
        fun part1(input: List<String>): Long {
            return calculateSeverity(parse(input)).second
        }

        fun part2(input: List<String>): Long {
            val layers = parse(input)
            var isCaught = true
            var delay = -1L
            while (isCaught) {
                delay++
                isCaught = calculateSeverity(layers, delay).first
            }
            return delay
        }

        private fun parse(input: List<String>) =
            input.map { line -> line.split(": ").let { it[0].toLong() to it[1].toLong() } }

        private fun calculateSeverity(layers: List<Pair<Long, Long>>, delay: Long = 0L): Pair<Boolean, Long> =
            layers.fold(false to 0) { (isCaughtAcc, severityAcc), (depth, range) ->
                val (isCaught, severity) = calculateSeverity(range, depth, delay)
                (isCaughtAcc || isCaught) to (severityAcc + severity)
            }

        private fun calculateSeverity(range: Long, depth: Long, delay: Long): Pair<Boolean, Long> {
            val scanCycle = range * 2 - 2
            return if ((depth + delay) % scanCycle == 0L) {
                true to depth * range
            } else {
                false to 0
            }
        }
    }
}
