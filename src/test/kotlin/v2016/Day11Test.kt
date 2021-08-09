package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day11Test {

    private val input = ("The first floor contains a hydrogen-compatible microchip and a lithium-compatible microchip.\n" +
            "The second floor contains a hydrogen generator.\n" +
            "The third floor contains a lithium generator.\n" +
            "The fourth floor contains nothing relevant.").split("\n")

    @Test
    fun testDay11Part1() {
        assertEquals(11L, Day11.part1(input))
    }
}
