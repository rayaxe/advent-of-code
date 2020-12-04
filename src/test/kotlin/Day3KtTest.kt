import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day3KtTest {

    private val input = "..##.......\n" +
            "#...#...#..\n" +
            ".#....#..#.\n" +
            "..#.#...#.#\n" +
            ".#...##..#.\n" +
            "..#.##.....\n" +
            ".#.#.#....#\n" +
            ".#........#\n" +
            "#.##...#...\n" +
            "#...##....#\n" +
            ".#..#...#.#"

    @Test
    fun testDay3Part1() {
        val map = input.split("\n")
        val treeCount = day3Part1(map)
        assertEquals(7, treeCount)
    }

    @Test
    fun testDay3Part2() {
        val map = input.split("\n")
        val treeCount = day3Part2(map)
        assertEquals(336, treeCount)
    }
}
