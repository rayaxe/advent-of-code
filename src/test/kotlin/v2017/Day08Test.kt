package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day08Test {
    private val input = listOf(
        "b inc 5 if a > 1",
        "a inc 1 if b < 5",
        "c dec -10 if a >= 1",
        "c inc -20 if c == 10",
    )

    @Test
    fun testDay08Part1() {
        assertEquals(1L, Day08.part1(input))
    }

    @Test
    fun testDay08Part2() {
        assertEquals(10L, Day08.part2(input))
    }
}
