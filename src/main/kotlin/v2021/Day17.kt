package v2021

class Day17 {
    companion object {
        private class Probe(var x: Int = 0, var y: Int = 0, var vx: Int = 0, var vy: Int = 0) {
            fun update() {
                x += vx
                y += vy
                if (vx > 0) vx -= 1
                if (vx < 0) vx += 1
                vy -= 1
            }
        }

        fun part1(input: String): Long {
            val (xRange, yRange) = parseTargetArea(input)
            return findHighest(xRange, yRange).filterNotNull().maxByOrNull { it }!!.toLong()
        }

        fun part2(input: String): Long {
            val (xRange, yRange) = parseTargetArea(input)
            return findHighest(xRange, yRange).filterNotNull().count().toLong()
        }

        private fun findHighest(xRange: IntRange, yRange: IntRange) = (yRange.first..1000).flatMap { vy ->
            (1..xRange.last).map { vx -> simulate(vx, vy, xRange, yRange) }
        }

        private fun simulate(vx: Int, vy: Int, xRange: IntRange, yRange: IntRange): Int? {
            val probe = Probe(vx = vx, vy = vy)
            var maxY = 0
            var step = 0
            while (probe.x < xRange.last && probe.y > yRange.first && step < 1000) {
                probe.update()
                if (probe.y > maxY) {
                    maxY = probe.y
                }
                if (probe.x in xRange && probe.y in yRange) {
                    return maxY
                }
                step++
            }
            return null
        }

        private fun parseTargetArea(input: String): Pair<IntRange, IntRange> {
            return input.removePrefix("target area: ")
                .split(", ")
                .map {
                    it.split("=")
                        .let { (_, range) ->
                            range.split("..")
                                .map { number -> number.toInt() }
                                .let { (start, end) -> start..end }
                        }
                }.let { (xRange, yRange) -> xRange to yRange }
        }
    }
}
