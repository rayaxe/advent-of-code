package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day19Test {
    val input = listOf(
        "     |          ",
        "     |  +--+    ",
        "     A  |  C    ",
        " F---|----E|--+ ",
        "     |  |  |  D ",
        "     +B-+  +--+ "
    )

    @Test
    fun testDay19Part1() {
        assertEquals("ABCDEF", Day19.part1(input))
    }

    @Test
    fun testDay19Part2() {
        assertEquals(38, Day19.part2(input))
    }
}
