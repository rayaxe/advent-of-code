package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day08Test {

    private val list = listOf("\"\"", "\"abc\"", "\"aaa\\\"aaa\"", "\"\\x27\"")

    @Test
    fun testDay08Part1() {
        assertEquals(12L, Day08.part1(list))
    }

    @Test
    fun testDay08Part2() {
        assertEquals(19L, Day08.part2(list))
    }
}
