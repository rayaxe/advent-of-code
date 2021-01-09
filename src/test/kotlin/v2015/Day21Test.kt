package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day21Test {

    val input = "Hit Points: 103\n" +
            "Damage: 9\n" +
            "Armor: 2\n"

    @Test
    fun testDay21Part1() {
        assertEquals(121L, Day21.part1(input.split("\n")))
    }

    @Test
    fun testDay21Part2() {
        assertEquals(-1L, Day21.part2(listOf()))
    }
}
