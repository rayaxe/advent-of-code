package v2016

import util.permutations

class Day24 {
    private data class Coordinate(val x: Int, val y: Int)
    private data class Node(val isOpen: Boolean = true, val number: Int = -1)

    companion object {
        fun part1(input: List<String>): Long {
            val nodes = parse(input)
            return findShortestPath(nodes, ::findOpenPaths)
        }

        fun part2(input: List<String>): Long {
            val nodes = parse(input)
            return findShortestPath(nodes, ::findClosedPaths)
        }

        private fun findShortestPath(areaMap: Map<Coordinate, Node>, findPaths: (List<Int>) -> List<List<Int>>): Long {
            val locations = findLocations(areaMap)
            val distances = calculateDistances(areaMap, locations.toMutableList())
            return findPaths(locations.indices.toList())
                .map { it.zipWithNext().fold(0) { acc, (from, to) -> acc + distances[from]!![to]!! } }
                .minByOrNull { it }!!.toLong()
        }

        private fun findOpenPaths(locations: List<Int>): List<List<Int>> =
            locations
                .drop(1)
                .permutations()
                .map { listOf(0) + it }

        private fun findClosedPaths(locations: List<Int>): List<List<Int>> =
            locations
                .drop(1)
                .permutations()
                .map { listOf(0) + it + 0 }

        private fun calculateDistances(
            nodes: Map<Coordinate, Node>,
            locations: MutableList<Pair<Int, Coordinate>>
        ): MutableMap<Int, MutableMap<Int, Int>> {
            val distances = locations.indices
                .map { it to mutableMapOf<Int, Int>() }
                .toMap()
                .toMutableMap()
            while (locations.isNotEmpty()) {
                val (numberFrom, from) = locations.removeFirst()
                for ((numberTo, to) in locations) {
                    val distance = calculateDistance(nodes, from, to)
                    distances[numberFrom]!![numberTo] = distance
                    distances[numberTo]!![numberFrom] = distance
                }
            }
            return distances
        }

        private fun calculateDistance(nodes: Map<Coordinate, Node>, from: Coordinate, to: Coordinate): Int {
            val visited = mutableSetOf(from)
            val frontier = mutableListOf(from to 0)
            while (true) {
                val (coordinate, distance) = frontier.removeFirst()
                val neighbors = listOf(0 to -1, 1 to 0, 0 to 1, -1 to 0)
                    .map { (dx, dy) -> Coordinate(coordinate.x + dx, coordinate.y + dy) }
                    .filter { it !in visited && nodes.getValue(it).isOpen }
                for (neighbor in neighbors) {
                    if (neighbor == to) {
                        return distance + 1
                    }
                    visited.add(neighbor)
                    frontier.add(neighbor to distance + 1)
                }
            }
        }

        private fun findLocations(nodes: Map<Coordinate, Node>) = nodes
            .filter { it.value.number > -1 }
            .toList()
            .map { it.second.number to it.first }
            .sortedBy { it.first }

        private fun parse(rows: List<String>): Map<Coordinate, Node> {
            return rows.flatMapIndexed { y: Int, row: String ->
                row.mapIndexed { x: Int, node: Char ->
                    Coordinate(x, y) to when (node) {
                        '#' -> Node(false)
                        '.' -> Node(true)
                        else -> Node(true, Character.getNumericValue(node))
                    }
                }
            }.toMap()
        }
    }
}
