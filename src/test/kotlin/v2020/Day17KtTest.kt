package v2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day17KtTest {
    val input = (".#.\n..#\n###").split("\n")

    @Test
    fun testDay17Part1() {
        assertEquals(112L, day17Part1(input))
    }

    @Test
    fun testDay17Part2() {
        assertEquals(848L, day17Part2(input))
    }
}
