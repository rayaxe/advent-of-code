package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day20Test {

    val input = listOf("5-8", "0-2", "4-7")

    @Test
    fun testDay20Part1() {
        assertEquals(3L, Day20.part1(listOf("5-8", "0-2", "4-7")))
    }

    @Test
    fun testDay20Part2() {
        assertEquals(2L, Day20.part2(listOf("5-8", "0-2", "4-7"), 9))
        assertEquals(4L, Day20.part2(listOf("5-7", "1-2", "4-7"), 9))
    }
}
