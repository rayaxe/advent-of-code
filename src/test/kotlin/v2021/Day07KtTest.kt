package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day07.Companion.part1
import v2021.Day07.Companion.part2

internal class Day07KtTest {

    private val input = ("16,1,2,0,4,2,7,1,2,14")

    @Test
    fun testDay7Part1() {
        assertEquals(37L, part1(input))
    }

    @Test
    fun testDay7Part2() {
        assertEquals(168L, part2(input))
    }
}
