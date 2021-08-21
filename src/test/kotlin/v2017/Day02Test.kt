package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day02Test {

    @Test
    fun testDay02Part1() {
        val input = listOf(
            "5 1 9 5",
            "7 5 3",
            "2 4 6 8"
        )
        assertEquals(18L, Day02.part1(input))
    }

    @Test
    fun testDay02Part2() {
        val input = listOf(
            "5 9 2 8",
            "9 4 7 3",
            "3 8 6 5"
        )
        assertEquals(9L, Day02.part2(input))
    }
}
