package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day15.Companion.part1
import v2021.Day15.Companion.part2

internal class Day15KtTest {

    private val input = ("1163751742\n" +
            "1381373672\n" +
            "2136511328\n" +
            "3694931569\n" +
            "7463417111\n" +
            "1319128137\n" +
            "1359912421\n" +
            "3125421639\n" +
            "1293138521\n" +
            "2311944581").split("\n")

    @Test
    fun testDay15Part1() {
        assertEquals(40L, part1(input))
    }

    @Test
    fun testDay15Part2() {
        assertEquals(315L, part2(input))
    }
}
