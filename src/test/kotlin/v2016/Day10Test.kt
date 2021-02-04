package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10Test {

    private val input = ("value 5 goes to bot 2\n" +
            "bot 2 gives low to bot 1 and high to bot 0\n" +
            "value 3 goes to bot 1\n" +
            "bot 1 gives low to output 1 and high to bot 0\n" +
            "bot 0 gives low to output 2 and high to output 0\n" +
            "value 2 goes to bot 2").split("\n")

    @Test
    fun testDay10Part1() {
        assertEquals(2L, Day10.part1(input, 5L, 2L))
    }

    @Test
    fun testDay10Part2() {
        assertEquals(30L, Day10.part2(input))
    }
}
