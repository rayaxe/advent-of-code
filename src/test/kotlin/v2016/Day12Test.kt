package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day12Test {

    private val input = ("cpy 41 a\n" +
            "inc a\n" +
            "inc a\n" +
            "dec a\n" +
            "jnz a 2\n" +
            "dec a").split("\n")

    @Test
    fun testDay12Part1() {
        assertEquals(42L, Day12.part1(input))
    }

    @Test
    fun testDay12Part2() {
        assertEquals(42L, Day12.part2(input))
    }
}
