package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day12Test {
    private val input = listOf(
        "0 <-> 2",
        "1 <-> 1",
        "2 <-> 0, 3, 4",
        "3 <-> 2, 4",
        "4 <-> 2, 3, 6",
        "5 <-> 6",
        "6 <-> 4, 5",
    )

    @Test
    fun testDay12Part1() {
        assertEquals(6L, Day12.part1(input))
    }

    @Test
    fun testDay12Part2() {
        assertEquals(2L, Day12.part2(input))
    }
}
