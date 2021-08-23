package v2017

class Day07 {
    private data class Program(val name: String, val weight: Int, val refs: List<String>)

    companion object {
        fun part1(input: List<String>): String {
            val programs = input.associate { parse(it) }
            return findRoot(programs)
        }

        fun part2(input: List<String>): Long {
            val programs = input.associate { parse(it) }
            var result = 0L
            fun sumWeight(program: Program): Int {
                if (program.refs.isEmpty()) {
                    return program.weight
                }
                val weights = program.refs.map { it to sumWeight(programs.getValue(it)) }
                val weightGroups = weights.groupBy { it.second }
                return if (weightGroups.size == 1) {
                    program.weight + weights.sumOf { it.second }
                } else {
                    val (unbalanced, unbalancedWeight) = weightGroups.values.first { it.size == 1 }.first()
                    val (_, balancedWeight) = weightGroups.values.first { it.size != 1 }.first()
                    if (result == 0L) {
                        result = programs.getValue(unbalanced).weight + (balancedWeight - unbalancedWeight).toLong()
                    }
                    0
                }
            }
            sumWeight(programs.getValue(findRoot(programs))).toLong()
            return result
        }

        private fun findRoot(programs: Map<String, Program>): String {
            val programsWithChildren = programs.filter { (_, program) -> program.refs.isNotEmpty() }
            val refs = programsWithChildren.values.flatMap { it.refs }.toSet()
            return programsWithChildren.keys.first { it !in refs }
        }

        private fun parse(line: String): Pair<String, Program> {
            val parts = line.split(" -> ")
            val namePart = parts[0]
            val i1 = namePart.indexOf('(')
            val i2 = namePart.indexOf(')')
            val name = namePart.substring(0, i1 - 1)
            val weight = namePart.substring(i1 + 1, i2).toInt()
            val refs = if (parts.size > 1) parts[1].split(", ") else listOf()
            return name to Program(name, weight, refs)
        }
    }
}
