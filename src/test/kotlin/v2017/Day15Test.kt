package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day15Test {
    private val input = listOf("65", "8921")

    @Test
    fun testDay15Part1() {
        assertEquals(588L, Day15.part1(input))
    }

    @Test
    fun testDay15Part2() {
        assertEquals(309L, Day15.part2(input))
    }
}
