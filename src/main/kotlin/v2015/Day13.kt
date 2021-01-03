package v2015

import util.permutations

class Day13 {
    private class Happiness(val name: String, val others: MutableMap<String, Long> = mutableMapOf())

    companion object {
        fun part1(input: List<String>): Long {
            val happinessMap = parse(input)
            return sumPermutationsOfHappiness(happinessMap).maxOrNull()!!
        }

        fun part2(input: List<String>): Long {
            val happinessMap = parse(input)
            addYourself(happinessMap)
            return sumPermutationsOfHappiness(happinessMap).maxOrNull()!!
        }

        private fun sumPermutationsOfHappiness(happinessMap: Map<String, Happiness>): List<Long> {
            return happinessMap.values.permutations()
                .map { names ->
                    val seatingArrangement = names + names.first()
                    seatingArrangement.zipWithNext().fold(0L) { acc, (name1, name2) ->
                        val happiness1 = happinessMap.getValue(name1.name).others.getValue(name2.name)
                        val happiness2 = happinessMap.getValue(name2.name).others.getValue(name1.name)
                        acc + happiness1 + happiness2
                    }
                }
        }

        private fun addYourself(happinessMap: MutableMap<String, Happiness>) {
            val myName = "yourself"
            happinessMap.values.forEach { it.others[myName] = 0L }
            val myOthers = happinessMap.keys.map { it to 0L }.toMap(mutableMapOf())
            happinessMap[myName] = Happiness(myName, myOthers)
        }

        private fun parse(input: List<String>): MutableMap<String, Happiness> {
            val happinessMap = mutableMapOf<String, Happiness>()
            input.forEach {
                val (name1, happinessUnits, name2) = parse(it)
                val happiness = happinessMap.getOrPut(name1) { Happiness(name1) }
                happiness.others[name2] = happinessUnits
            }
            return happinessMap
        }

        private val happinessRegex =
            """(.*) would (gain|lose) ([0-9]+) happiness units by sitting next to (.*)\.""".toRegex()

        private fun parse(text: String): Triple<String, Long, String> {
            val matchEntire = happinessRegex.matchEntire(text)
            return matchEntire
                ?.destructured
                ?.let { (name1, gainOrLose, happinessUnits, name2) ->
                    val happiness = if (gainOrLose == "gain") happinessUnits.toLong() else -happinessUnits.toLong()
                    Triple(name1, happiness, name2)
                }
                ?: throw IllegalArgumentException("Bad input '$text'")
        }
    }
}
