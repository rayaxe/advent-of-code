package v2017

class Day12 {
    companion object {
        fun part1(input: List<String>): Long {
            return findProgramGroups(input).first().size.toLong()
        }

        fun part2(input: List<String>): Long {
            return findProgramGroups(input).size.toLong()
        }

        private fun findProgramGroups(input: List<String>): List<Set<Int>> {
            val pipesMap = input.associate { parse(it) }.toMutableMap()
            val groups = mutableListOf<Set<Int>>()
            while (pipesMap.isNotEmpty()) {
                val programs = mutableSetOf<Int>()
                val pipes = mutableListOf(pipesMap.keys.first())
                while (pipes.isNotEmpty()) {
                    val next = pipes.removeFirst()
                    programs.add(next)
                    pipes.addAll(pipesMap.remove(next)!!.filter { it !in programs && it !in pipes })
                }
                groups.add(programs)
            }
            return groups
        }

        private fun parse(text: String): Pair<Int, List<Int>> {
            val (id, ids) = text.split(" <-> ")
            return id.toInt() to ids.split(", ").map { it.toInt() }
        }
    }
}
