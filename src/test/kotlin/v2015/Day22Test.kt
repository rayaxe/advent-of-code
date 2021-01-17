package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day22Test {

    private val input = "Hit Points: 71\nDamage: 10"

    @Test
    fun testDay22Examples() {
        assertEquals(226L, Day22.play(10, 250, 13, 8, Day22.Difficulty.EASY))
        assertEquals(641L, Day22.play(10, 250, 14, 8, Day22.Difficulty.EASY))
    }

    @Test
    fun testDay22Part1() {
        assertEquals(1824L, Day22.part1(input.split("\n")))
    }

    @Test
    fun testDay22Part2() {
        assertEquals(1937L, Day22.part2(input.split("\n")))
    }
}
