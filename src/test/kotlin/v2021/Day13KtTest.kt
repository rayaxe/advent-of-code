package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day13.Companion.part1
import v2021.Day13.Companion.part2

internal class Day13KtTest {

    private val input = ("6,10\n" +
            "0,14\n" +
            "9,10\n" +
            "0,3\n" +
            "10,4\n" +
            "4,11\n" +
            "6,0\n" +
            "6,12\n" +
            "4,1\n" +
            "0,13\n" +
            "10,12\n" +
            "3,4\n" +
            "3,0\n" +
            "8,4\n" +
            "1,10\n" +
            "2,14\n" +
            "8,10\n" +
            "9,0\n" +
            "\n" +
            "fold along y=7\n" +
            "fold along x=5").split("\n")

    @Test
    fun testDay13Part1() {
        assertEquals(17L, part1(input))
    }

    @Test
    fun testDay13Part2() {
        assertEquals(-1L, part2(input))
    }
}
