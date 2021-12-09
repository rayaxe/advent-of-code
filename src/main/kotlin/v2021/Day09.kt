package v2021

class Day09 {
    companion object {
        private val directions = listOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

        fun part1(input: List<String>): Long {
            val heightmap = parse(input)
            return findLowPoints(heightmap)
                .sumOf { it.second + 1 }
        }

        fun part2(input: List<String>): Long {
            val heightmap = parse(input)
            return findLowPoints(heightmap)
                .map { findBasinSize(it.first, heightmap) }
                .sortedDescending()
                .take(3)
                .reduce { acc, size -> acc * size }
        }

        private fun findBasinSize(lowPoint: Pair<Int, Int>, heightmap: Array<Array<Long>>): Long {
            val visited = mutableSetOf<Pair<Int, Int>>()
            val frontier = mutableListOf(lowPoint)
            while (frontier.isNotEmpty()) {
                val (y, x) = frontier.removeFirst()
                directions.forEach { (dy, dx) ->
                    val neighbour = y + dy to x + dx
                    val isWithinBasin =
                        neighbour.within(heightmap) && heightmap[neighbour.first][neighbour.second] != 9L
                    if (isWithinBasin && neighbour !in visited) {
                        visited.add(neighbour)
                        frontier.add(neighbour)
                    }
                }
            }
            return visited.size.toLong()
        }

        private fun findLowPoints(heightmap: Array<Array<Long>>): List<Pair<Pair<Int, Int>, Long>> {
            return heightmap.indices.flatMap { y ->
                heightmap[0].indices.mapNotNull { x ->
                    val point = heightmap[y][x]
                    val isLowPoint = directions.all { (dy, dx) ->
                        val neighbour = y + dy to x + dx
                        if (neighbour.within(heightmap)) {
                            point < heightmap[neighbour.first][neighbour.second]
                        } else {
                            true
                        }
                    }
                    if (isLowPoint) (y to x) to point else null
                }
            }
        }

        private fun Pair<Int, Int>.within(area: Array<Array<Long>>): Boolean =
            this.first in area.indices && this.second in area[0].indices

        private fun parse(input: List<String>): Array<Array<Long>> = input
            .map { row ->
                row.map { Character.getNumericValue(it).toLong() }.toTypedArray()
            }.toTypedArray()
    }
}
