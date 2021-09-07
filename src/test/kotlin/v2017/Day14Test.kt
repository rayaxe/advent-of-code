package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2017.Day14.Companion.toRow

internal class Day14Test {
    private val input = "flqrgnkx"

    @Test
    fun row() {
        assertEquals("#.#.....##....#........#.###", "a0c2017".toRow())
    }

    @Test
    fun testDay14Part1() {
        assertEquals(8108L, Day14.part1(input))
    }

    @Test
    fun testDay14Part2() {
        assertEquals(1242L, Day14.part2(input))
    }
}
