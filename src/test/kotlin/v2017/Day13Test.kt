package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day13Test {
    private val input = listOf(
        "0: 3",
        "1: 2",
        "4: 4",
        "6: 4",
    )

    @Test
    fun testDay13Part1() {
        assertEquals(24L, Day13.part1(input))
    }

    @Test
    fun testDay13Part2() {
        assertEquals(10L, Day13.part2(input))
    }
}
