package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day16Test {
    private val input = listOf("s1", "x3/4", "pe/b")

    @Test
    fun testDay16Part1() {
        assertEquals("baedc", Day16.part1(input, 5))
    }

    @Test
    fun testDay16Part2() {
        assertEquals("abcde", Day16.part2(input, 5))
    }
}
