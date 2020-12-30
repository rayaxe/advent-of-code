package v2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day12KtTest {

    @Test
    fun testDay12Part1() {
        val input = "F10\n" +
                "N3\n" +
                "F7\n" +
                "R90\n" +
                "F11"
        val values = input.split("\n")
        assertEquals(25, day12Part1(values))
    }

    @Test
    fun testDay12Part2() {
        val input = "F10\n" +
                "N3\n" +
                "F7\n" +
                "R90\n" +
                "F11"
        val values = input.split("\n")
        assertEquals(286, day12Part2(values))
    }
}
