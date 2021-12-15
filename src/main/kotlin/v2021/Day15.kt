package v2021

import java.util.*
import kotlin.math.abs

class Day15 {
    companion object {
        private val directions = listOf(0 to -1, 1 to 0, 0 to 1, -1 to 0)

        fun part1(input: List<String>): Long {
            val map = parse(input)
            return astar(map)
        }

        fun part2(input: List<String>): Long {
            val map = enlarge(parse(input))
            return astar(map)
        }

        private fun astar(map: Array<Array<Long>>): Long {
            val start = 0 to 0
            val goal = (map.size - 1) to (map[0].size - 1)
            val open = priorityQueueBy<Pair<Pair<Int, Int>, Long>> { it.second }
            open.add(start to 0)
            val cameFrom = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
            val gScore = mutableMapOf<Pair<Int, Int>, Long>()
            gScore[start] = 0L
            val fScore = mutableMapOf<Pair<Int, Int>, Long>()
            fScore[start] = manhattan(start, goal)

            while (open.isNotEmpty()) {
                val (current, totalRisk) = open.poll()
                if (current == goal) {
                    return totalRisk
                }
                directions.forEach { (dy, dx) ->
                    val ny = current.first + dy
                    val nx = current.second + dx
                    if (ny >= 0 && ny < map.size && nx >= 0 && nx < map[0].size) {
                        val neighbor = ny to nx
                        val cost = map[ny][nx]
                        val tentativeGScore = gScore.getValue(current) + cost
                        if (tentativeGScore < gScore.getOrDefault(neighbor, Long.MAX_VALUE)) {
                            cameFrom[neighbor] = current
                            gScore[neighbor] = tentativeGScore
                            fScore[neighbor] = tentativeGScore + manhattan(neighbor, goal)
                            open.add(neighbor to fScore.getValue(neighbor))
                        }
                    }
                }
            }
            return -1L
        }

        private fun manhattan(start: Pair<Int, Int>, goal: Pair<Int, Int>): Long {
            return (abs(start.first - goal.first) + abs(start.second - goal.second)).toLong()
        }

        private fun enlarge(map: Array<Array<Long>>): Array<Array<Long>> {
            val tileHeight = map.size
            val tileWidth = map[0].size
            val newMap = Array(tileHeight * 5) { Array(tileWidth * 5) { 0L } }
            map.indices.forEach { y ->
                map[0].indices.forEach { x ->
                    newMap[y][x] = map[y][x]
                }
            }
            (0..4).forEach { tileY ->
                (0..4).forEach { tileX ->
                    if (tileY > 0 || tileX > 0) {
                        val prevTileY = if (tileX == 0) tileY - 1 else tileY
                        val prevTileX = if (tileX > 0) tileX - 1 else tileX
                        map.indices.forEach { y ->
                            map[0].indices.forEach { x ->
                                val prev = newMap[prevTileY * tileHeight + y][prevTileX * tileWidth + x]
                                newMap[tileY * tileHeight + y][tileX * tileWidth + x] =
                                    if (prev < 9) prev + 1 else 1
                            }
                        }
                    }
                }
            }
            return newMap
        }

        private fun parse(input: List<String>): Array<Array<Long>> {
            return input.map { row ->
                row.map { Character.getNumericValue(it).toLong() }.toTypedArray()
            }.toTypedArray()
        }

        /**
         * Thanks https://github.com/JiriBakker/advent-of-code-kotlin/ :)
         */
        private fun <T> priorityQueueBy(valueSelector: (T) -> Comparable<*>): PriorityQueue<T> {
            return PriorityQueue { a, b -> (valueSelector(a) as Comparable<Any>).compareTo(valueSelector(b)) }
        }
    }
}
