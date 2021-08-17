package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day24Test {

    private val input = ("###########\n" +
            "#0.1.....2#\n" +
            "#.#######.#\n" +
            "#4.......3#\n" +
            "###########").split("\n")

    @Test
    fun testDay24Part1() {
        assertEquals(14L, Day24.part1(input))
    }

    @Test
    fun testDay24Part2() {
        assertEquals(20L, Day24.part2(input))
    }
}
