package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day05.Companion.part1
import v2021.Day05.Companion.part2

internal class Day05KtTest {

    private val input = ("0,9 -> 5,9\n" +
            "8,0 -> 0,8\n" +
            "9,4 -> 3,4\n" +
            "2,2 -> 2,1\n" +
            "7,0 -> 7,4\n" +
            "6,4 -> 2,0\n" +
            "0,9 -> 2,9\n" +
            "3,4 -> 1,4\n" +
            "0,0 -> 8,8\n" +
            "5,5 -> 8,2").split("\n")

    @Test
    fun testDay5Part1() {
        assertEquals(5L, part1(input))
    }

    @Test
    fun testDay5Part2() {
        assertEquals(12L, part2(input))
    }
}
