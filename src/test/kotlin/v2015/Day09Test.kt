package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day09Test {

    private val distances = "London to Dublin = 464\n" +
            "London to Belfast = 518\n" +
            "Dublin to Belfast = 141"

    @Test
    fun testDay09Part1() {
        assertEquals(605L, Day09.part1(distances.split("\n")))
    }

    @Test
    fun testDay09Part2() {
        assertEquals(982L, Day09.part2(distances.split("\n")))
    }
}
