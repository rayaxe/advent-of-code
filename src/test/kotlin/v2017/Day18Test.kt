package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day18Test {

    @Test
    fun testDay18Part1() {
        val input = listOf(
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
        assertEquals(4L, Day18.part1(input))
    }

    @Test
    fun testDay18Part2() {
        val input = listOf(
            "snd 1",
            "snd 2",
            "snd p",
            "rcv a",
            "rcv b",
            "rcv c",
            "rcv d"
        )
        assertEquals(3L, Day18.part2(input))
    }
}
