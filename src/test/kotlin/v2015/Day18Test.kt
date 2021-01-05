package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day18Test {

    val input = ".#.#.#\n" +
            "...##.\n" +
            "#....#\n" +
            "..#...\n" +
            "#.#..#\n" +
            "####.."

    @Test
    fun testDay18Part1() {
        assertEquals(4L, Day18.part1(input.split("\n"), 4))
    }

    @Test
    fun testDay18Part2() {
        assertEquals(17L, Day18.part2(input.split("\n"), 5))
    }
}
