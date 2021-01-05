package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day17Test {

    val input = listOf("20", "15", "10", "5", "5")

    @Test
    fun testDay17Part1() {
        assertEquals(4L, Day17.part1(input, 25))
    }

    @Test
    fun testDay17Part2() {
        assertEquals(3L, Day17.part2(input, 25))
    }
}
