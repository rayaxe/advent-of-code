package v2021

class Day12 {
    companion object {
        fun part1(input: List<String>): Long {
            return findPaths(input) { visited, cave ->
                visited.getOrDefault(cave, 0) + 1 < 2
            }
        }

        fun part2(input: List<String>): Long {
            return findPaths(input) { visited, cave ->
                !(visited.values.any { it > 1 } && visited.getOrDefault(cave, 0) + 1 > 1)
            }
        }

        private fun findPaths(input: List<String>, canVisit: (Map<String, Int>, String) -> Boolean): Long {
            val cavesMap = parse(input)

            fun findPaths(path: List<String>, visited: Map<String, Int>, cave: String): List<List<String>> {
                if (cave == "end") {
                    return listOf(path + "end")
                }
                val newVisited = visited.increase(cave)
                return cavesMap.getValue(cave)
                    .filter { it != "start" && canVisit(newVisited, it) }
                    .flatMap { findPaths(path + cave, newVisited, it) }
            }

            return cavesMap
                .getValue("start")
                .flatMap { neighbour -> findPaths(listOf("start"), mapOf(), neighbour) }
                .size.toLong()
        }

        private fun Map<String, Int>.increase(cave: String): Map<String, Int> {
            return if (cave.first().isUpperCase()) {
                this
            } else {
                val newVisited = toMutableMap()
                newVisited[cave] = getOrDefault(cave, 0) + 1
                newVisited
            }
        }

        private fun parse(input: List<String>): Map<String, List<String>> {
            val cavesMap = mutableMapOf<String, List<String>>()
            fun add(a: String, b: String) {
                cavesMap[a] = cavesMap.getOrDefault(a, listOf()) + b
                cavesMap[b] = cavesMap.getOrDefault(b, listOf()) + a
            }
            input.forEach { line -> line.split('-').also { add(it[0], it[1]) } }
            return cavesMap
        }
    }
}
