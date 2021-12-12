package v2021

class Day12 {
    companion object {
        fun part1(input: List<String>): Long {
            return findPaths(input) { caveVisitCount, cave ->
                caveVisitCount.getOrDefault(cave, 0) + 1 < 2
            }
        }

        fun part2(input: List<String>): Long {
            return findPaths(input) { caveVisitCount, cave ->
                !(caveVisitCount.values.any { it > 1 } && caveVisitCount.getOrDefault(cave, 0) + 1 > 1)
            }
        }

        private fun findPaths(input: List<String>, canVisit: (Map<String, Int>, String) -> Boolean): Long {
            val cavesMap = parse(input)

            fun findPaths(path: List<String>, caveVisitCount: Map<String, Int>, cave: String): List<List<String>> {
                if (cave == "end") {
                    return listOf(path + "end")
                }
                val newCaveVisitCount = caveVisitCount.increase(cave)
                return cavesMap.getValue(cave)
                    .filter { neighbour -> neighbour != "start" && canVisit(newCaveVisitCount, neighbour) }
                    .flatMap { neighbour -> findPaths(path + cave, newCaveVisitCount, neighbour) }
            }

            return cavesMap
                .getValue("start")
                .flatMap { neighbour -> findPaths(listOf("start"), mapOf(), neighbour) }
                .size.toLong()
        }

        private fun Map<String, Int>.increase(cave: String): Map<String, Int> {
            return if (cave.first().isUpperCase()) {
                // Ignore visit count for large caves
                this
            } else {
                val caveVisitCount = toMutableMap()
                caveVisitCount[cave] = getOrDefault(cave, 0) + 1
                caveVisitCount
            }
        }

        private fun parse(input: List<String>): Map<String, List<String>> {
            val cavesMap = mutableMapOf<String, List<String>>()
            fun add(a: String, b: String) {
                cavesMap[a] = cavesMap.getOrDefault(a, listOf()) + b
                cavesMap[b] = cavesMap.getOrDefault(b, listOf()) + a
            }
            input.forEach { line -> line.split('-').also { (a, b) -> add(a, b) } }
            return cavesMap
        }
    }
}
