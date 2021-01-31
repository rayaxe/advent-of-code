package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day21Test {

    val input = ("Hit Points: 103\nDamage: 9\nArmor: 2\n").split("\n")

    @Test
    fun testDay21Part1() {
        assertEquals(121L, Day21.part1(input))
    }

    @Test
    fun testDay21Part2() {
        assertEquals(201L, Day21.part2(input))
    }
}
