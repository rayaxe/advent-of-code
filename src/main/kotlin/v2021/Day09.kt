package v2021

class Day09 {
    companion object {
        private val directions = listOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

        fun part1(input: List<String>): Long {
            val heights = parse(input)
            return findLowPoints(heights)
                .sumOf { it.second + 1 }
        }

        fun part2(input: List<String>): Long {
            val heights = parse(input)
            return findLowPoints(heights)
                .map { findBasinSize(it.first, heights) }
                .sortedDescending()
                .take(3)
                .reduce { acc, size -> acc * size }
        }

        private fun findBasinSize(lowPoint: Pair<Int, Int>, heights: Array<Array<Long>>): Long {
            val visited = mutableSetOf<Pair<Int, Int>>()
            val frontier = mutableListOf(lowPoint)
            while (frontier.isNotEmpty()) {
                val (y, x) = frontier.removeFirst()
                directions.forEach { (dy, dx) ->
                    val neighbour = y + dy to x + dx
                    val (ny, nx) = neighbour
                    val isInBasin = ny in heights.indices && nx in heights[0].indices && heights[ny][nx] != 9L
                    if (isInBasin && neighbour !in visited) {
                        visited.add(neighbour)
                        frontier.add(neighbour)
                    }
                }
            }
            return visited.size.toLong()
        }

        private fun findLowPoints(heights: Array<Array<Long>>): List<Pair<Pair<Int, Int>, Long>> {
            val rangeY = heights.indices
            val rangeX = heights[0].indices
            return rangeY.flatMap { y ->
                rangeX.mapNotNull { x ->
                    val point = heights[y][x]
                    val isLowPoint = directions.all { (dy, dx) ->
                        val neighbour = y + dy to x + dx
                        val (ny, nx) = neighbour
                        if (ny in rangeY && nx in rangeX) {
                            point < heights[ny][nx]
                        } else {
                            true
                        }
                    }
                    if (isLowPoint) ((y to x) to point) else null
                }
            }
        }

        private fun parse(input: List<String>): Array<Array<Long>> {
            val heights = input.map { row ->
                row.map { Character.getNumericValue(it).toLong() }.toTypedArray()
            }.toTypedArray()
            return heights
        }
    }
}
