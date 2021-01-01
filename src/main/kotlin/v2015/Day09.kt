package v2015

import java.util.*

class Day09 {

    private class Location(val name: String, val others: MutableMap<String, Long> = mutableMapOf())

    companion object {
        fun part1(distances: List<String>): Long {
            return sumPermutationOfDistances(parse(distances)).minOrNull()!!
        }

        fun part2(distances: List<String>): Long {
            return sumPermutationOfDistances(parse(distances)).maxOrNull()!!
        }

        private fun sumPermutationOfDistances(distances: Map<String, Location>): List<Long> {
            return distances.values.toList().permutations()
                .map {
                    it.zipWithNext().fold(0L) { acc, (location1, location2) ->
                        acc + distances.getValue(location1.name).others.getValue(location2.name)
                    }
                }
        }

        private fun parse(input: List<String>): Map<String, Location> {
            val distances = mutableMapOf<String, Location>()
            input.forEach {
                val (locationPair, distance) = it.split(" = ")
                val (location1, location2) = locationPair.split(" to ")
                val distance1 = distances.getOrPut(location1) { Location(location1) }
                val distance2 = distances.getOrPut(location2) { Location(location2) }
                distance1.others[location2] = distance.toLong()
                distance2.others[location1] = distance.toLong()
            }
            return distances
        }
    }
}

fun <V> List<V>.permutations(): List<List<V>> {
    val result: MutableList<List<V>> = mutableListOf()
    fun generate(count: Int, list: List<V>) {
        if (count == 1) {
            result.add(list.toList())
        } else {
            for (i in 0 until count) {
                generate(count - 1, list)
                if (count % 2 == 0) {
                    Collections.swap(list, i, count - 1)
                } else {
                    Collections.swap(list, 0, count - 1)
                }
            }
        }
    }
    generate(count(), toList())
    return result
}
