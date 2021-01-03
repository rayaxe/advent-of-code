package v2015

import util.permutations

class Day09 {
    private class Location(val name: String, val others: MutableMap<String, Long> = mutableMapOf())

    companion object {
        fun part1(distances: List<String>): Long {
            return sumPermutationsOfDistances(parse(distances)).minOrNull()!!
        }

        fun part2(distances: List<String>): Long {
            return sumPermutationsOfDistances(parse(distances)).maxOrNull()!!
        }

        private fun sumPermutationsOfDistances(distances: Map<String, Location>): List<Long> {
            return distances.values.permutations()
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
