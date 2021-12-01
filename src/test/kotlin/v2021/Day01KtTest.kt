package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day01.Companion.part1
import v2021.Day01.Companion.part2

internal class Day01KtTest {

    private val input = ("199\n" +
            "200\n" +
            "208\n" +
            "210\n" +
            "200\n" +
            "207\n" +
            "240\n" +
            "269\n" +
            "260\n" +
            "263").split("\n")

    @Test
    fun testDay1Part1() {
        assertEquals(7L, part1(input))
    }

    @Test
    fun testDay1Part2() {
        assertEquals(5L, part2(input))
    }
}
