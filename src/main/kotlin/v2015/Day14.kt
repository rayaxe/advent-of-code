package v2015

import kotlin.math.floor
import kotlin.math.min

class Day14 {
    private class ReindeerStats(val name: String, val flySpeed: Int, val flySeconds: Int, val restSeconds: Int)

    companion object {
        fun part1(input: List<String>, durationSeconds: Int = 1000): Long {
            return input.map {
                with(parse(it)) {
                    val seconds = flySeconds + restSeconds
                    val remainingFlySeconds = min((durationSeconds % seconds), flySeconds)
                    val totalFlySeconds =
                        floor((durationSeconds / seconds * flySeconds).toDouble()) + remainingFlySeconds
                    (totalFlySeconds * flySpeed).toLong()
                }
            }.maxOrNull()!!
        }

        fun part2(input: List<String>): Long {
            return -1L
        }

        private val happinessRegex =
            """(.*) can fly ([0-9]+) km/s for ([0-9]+) seconds, but then must rest for ([0-9]+) seconds\.""".toRegex()

        private fun parse(text: String): ReindeerStats {
            val matchEntire = happinessRegex.matchEntire(text)
            return matchEntire
                ?.destructured
                ?.let { (name, flySpeed, flySeconds, restSeconds) ->
                    ReindeerStats(name, flySpeed.toInt(), flySeconds.toInt(), restSeconds.toInt())
                }
                ?: throw IllegalArgumentException("Bad input '$text'")
        }
    }
}
