package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day13Test {

    @Test
    fun testDay13Part1() {
        assertEquals(11L, Day13.part1(10, Pair(7, 4)))
    }

    @Test
    fun testDay13Part2() {
        assertEquals(151L, Day13.part2(10, 50))
    }
}
