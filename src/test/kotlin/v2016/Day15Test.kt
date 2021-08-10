package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day15Test {
    private val input = listOf("Disc #1 has 5 positions; at time=0, it is at position 4.",
            "Disc #2 has 2 positions; at time=0, it is at position 1.")

    @Test
    fun testDay15Part1() {
        assertEquals(5L, Day15.part1(input))
    }

    @Test
    fun testDay15Part2() {
        assertEquals(85L, Day15.part2(input))
    }
}
