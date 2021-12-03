package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day03.Companion.part1
import v2021.Day03.Companion.part2

internal class Day03KtTest {

    private val input = ("00100\n" +
            "11110\n" +
            "10110\n" +
            "10111\n" +
            "10101\n" +
            "01111\n" +
            "00111\n" +
            "11100\n" +
            "10000\n" +
            "11001\n" +
            "00010\n" +
            "01010").split("\n")

    @Test
    fun testDay3Part1() {
        assertEquals(198L, part1(input))
    }

    @Test
    fun testDay3Part2() {
        assertEquals(230L, part2(input))
    }
}
