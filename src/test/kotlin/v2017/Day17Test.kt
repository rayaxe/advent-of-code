package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day17Test {

    @Test
    fun testDay17Part1() {
        assertEquals(638, Day17.part1(3))
    }

    @Test
    fun testDay17Part2() {
        assertEquals(1222153, Day17.part2(3))
    }
}
