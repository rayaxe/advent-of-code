package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day20Test {

    @Test
    fun testDay20Part1() {
        assertEquals(786240L, Day20.part1(34000000))
    }

    @Test
    fun testDay20Part2() {
        assertEquals(831600L, Day20.part2(34000000))
    }
}
