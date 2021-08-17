package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day23Test {

    private val input = ("cpy 2 a\n" +
            "tgl a\n" +
            "tgl a\n" +
            "tgl a\n" +
            "cpy 1 a\n" +
            "dec a\n" +
            "dec a").split("\n")

    @Test
    fun testDay23Part1() {
        assertEquals(3L, Day23.part1(input))
    }
}
