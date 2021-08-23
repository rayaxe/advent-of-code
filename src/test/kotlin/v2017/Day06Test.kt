package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day06Test {
    private val input = "0   2   7   0"

    @Test
    fun testDay06Part1() {
        assertEquals(5L, Day06.part1(input))
    }

    @Test
    fun testDay06Part2() {
        assertEquals(4L, Day06.part2(input))
    }
}
