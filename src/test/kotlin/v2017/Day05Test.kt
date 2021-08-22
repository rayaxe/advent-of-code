package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day05Test {
    private val input = listOf("0", "3", "0", "1", "-3")

    @Test
    fun testDay05Part1() {
        assertEquals(5L, Day05.part1(input))
    }

    @Test
    fun testDay02Part2() {
        assertEquals(10L, Day05.part2(input))
    }
}
