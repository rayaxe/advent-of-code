package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day09.Companion.part1
import v2021.Day09.Companion.part2

internal class Day09KtTest {

    private val input = ("2199943210\n" +
            "3987894921\n" +
            "9856789892\n" +
            "8767896789\n" +
            "9899965678").split("\n")

    @Test
    fun testDay9Part1() {
        assertEquals(15L, part1(input))
    }

    @Test
    fun testDay9Part2() {
        assertEquals(1134L, part2(input))
    }
}
