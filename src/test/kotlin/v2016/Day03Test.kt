package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day03Test {

    @Test
    fun testDay03Part1() {
        assertEquals(0L, Day03.part1(listOf("5 10 25")))
    }

    @Test
    fun testDay03Part2() {
        val input = ("101 301 501\n" +
                "102 302 502\n" +
                "103 303 503\n" +
                "201 401 601\n" +
                "202 402 602\n" +
                "203 403 603").split('\n')
        assertEquals(6L, Day03.part2(input))
    }
}
