package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day24Test {

    @Test
    fun testDay24Part1() {
        val input = (1..11).filter { it != 6 }.map { it.toString() }
        assertEquals(99L, Day24.part1(input))
    }
}
