package v2015

import kotlin.math.floor
import kotlin.math.min

class Day14 {
    private class Reindeer(val name: String, val flySpeed: Int, val flySeconds: Int, val restSeconds: Int)

    companion object {
        fun part1(input: List<String>, durationSeconds: Int = 1000): Long {
            val reindeers = input.map { parse(it) }
            return leaders(reindeers, durationSeconds).first().second
        }

        fun part2(input: List<String>, durationSeconds: Int = 1000): Long {
            val reindeers = input.map { parse(it) }
            val leaderScores = mutableMapOf<String, Long>()
            for (seconds in 1..durationSeconds) {
                leaders(reindeers, seconds).forEach { leader ->
                    leaderScores[leader.first] = leaderScores.getOrDefault(leader.first, 0L) + 1L
                }
            }
            return leaderScores.values.maxOrNull()!!
        }

        private fun leaders(reindeers: List<Reindeer>, durationSeconds: Int): List<Pair<String, Long>> {
            val results = reindeers.map { reindeer ->
                with(reindeer) {
                    val seconds = flySeconds + restSeconds
                    val remainingFlySeconds = min((durationSeconds % seconds), flySeconds)
                    val totalFlySeconds =
                        floor((durationSeconds / seconds * flySeconds).toDouble()) + remainingFlySeconds
                    name to (totalFlySeconds * flySpeed).toLong()
                }
            }
            val max = results.maxByOrNull { it.second }!!
            return results.filter { it.second == max.second }
        }

        private val happinessRegex =
            """(.*) can fly ([0-9]+) km/s for ([0-9]+) seconds, but then must rest for ([0-9]+) seconds\.""".toRegex()

        private fun parse(text: String): Reindeer {
            val matchEntire = happinessRegex.matchEntire(text)
            return matchEntire
                ?.destructured
                ?.let { (name, flySpeed, flySeconds, restSeconds) ->
                    Reindeer(name, flySpeed.toInt(), flySeconds.toInt(), restSeconds.toInt())
                }
                ?: throw IllegalArgumentException("Bad input '$text'")
        }
    }
}
