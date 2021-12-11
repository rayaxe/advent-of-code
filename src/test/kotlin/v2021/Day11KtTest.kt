package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day11.Companion.part1
import v2021.Day11.Companion.part2

internal class Day11KtTest {

    private val input = ("5483143223\n" +
            "2745854711\n" +
            "5264556173\n" +
            "6141336146\n" +
            "6357385478\n" +
            "4167524645\n" +
            "2176841721\n" +
            "6882881134\n" +
            "4846848554\n" +
            "5283751526").split("\n")

    @Test
    fun testDay11Part1Example() {
        val example = listOf(
            "11111",
            "19991",
            "19191",
            "19991",
            "11111"
        )
        assertEquals(9L, part1(example, 2))
    }

    @Test
    fun testDay11Part1() {
        assertEquals(1656L, part1(input))
    }

    @Test
    fun testDay11Part2() {
        assertEquals(195L, part2(input))
    }
}
