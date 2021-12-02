package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day02.Companion.part1
import v2021.Day02.Companion.part2

internal class Day02KtTest {

    private val input = ("forward 5\n" +
            "down 5\n" +
            "forward 8\n" +
            "up 3\n" +
            "down 8\n" +
            "forward 2").split("\n")

    @Test
    fun testDay2Part1() {
        assertEquals(150L, part1(input))
    }

    @Test
    fun testDay2Part2() {
        assertEquals(900L, part2(input))
    }
}
