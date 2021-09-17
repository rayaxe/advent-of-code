package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day18Test {
    private val input = listOf(
        "set a 1",
        "add a 2",
        "mul a a",
        "mod a 5",
        "snd a",
        "set a 0",
        "rcv a",
        "jgz a -1",
        "set a 1",
        "jgz a -2"
    )

    @Test
    fun testDay18Part1() {
        assertEquals(4L, Day18.part1(input))
    }

    @Test
    fun testDay18Part2() {
        assertEquals(-1L, Day18.part2(input))
    }
}
