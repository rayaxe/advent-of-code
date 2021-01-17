package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day23Test {

    private val input = "inc a\n" +
            "jio a, +2\n" +
            "tpl a\n" +
            "inc a"

    @Test
    fun testDay23Part1() {
        assertEquals(2L, Day23.run(input.split("\n")).getValue("a"))
    }
}
