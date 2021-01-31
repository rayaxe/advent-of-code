package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day02Test {

    val input = ("ULL\n" +
            "RRDDD\n" +
            "LURDL\n" +
            "UUUUD").split('\n')

    @Test
    fun testDay02Part1() {
        assertEquals("1985", Day02.part1(input))
    }

    @Test
    fun testDay02Part2() {
        assertEquals("5DB3", Day02.part2(input))
    }
}
