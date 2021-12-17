package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day17.Companion.part1
import v2021.Day17.Companion.part2

internal class Day17KtTest {

    private val input = "target area: x=20..30, y=-10..-5"
    private val puzzleInput = "target area: x=81..129, y=-150..-108"

    @Test
    fun testDay17Part1() {
        assertEquals(45L, part1(input))
        assertEquals(11175L, part1(puzzleInput))
    }

    @Test
    fun testDay17Part2() {
        assertEquals(112L, part2(input))
        assertEquals(3540L, part2(puzzleInput))
    }
}
