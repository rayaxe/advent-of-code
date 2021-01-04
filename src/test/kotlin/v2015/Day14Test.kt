package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day14Test {

    val input = "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.\n" +
            "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."

    @Test
    fun testDay14Part1() {
        assertEquals(1120L, Day14.part1(input.split("\n")))
    }

    @Test
    fun testDay14Part2() {
        assertEquals(-1L, Day14.part2(input.split("\n")))
    }
}
