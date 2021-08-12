package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day21Test {

    val input = listOf(
        "swap position 4 with position 0",
        "swap letter d with letter b",
        "reverse positions 0 through 4",
        "rotate left 1 step",
        "move position 1 to position 4",
        "move position 3 to position 0",
        "rotate based on position of letter b",
        "rotate based on position of letter d",
    )

    @Test
    fun testDay21Part1() {
        assertEquals("decab", Day21.part1("abcde", input))
    }

    @Test
    fun testDay21Part2() {
        assertEquals("abcde", Day21.part2("decab", input))
    }

    @Test
    fun rotateLeft() {
        assertEquals("bcdea", Day21.rotateLeft("abcde".toCharArray(), 1).joinToString(""))
        assertEquals("cdeab", Day21.rotateLeft("abcde".toCharArray(), 2).joinToString(""))
    }

    @Test
    fun rotateLeftReversed() {
        assertEquals("abcde", Day21.rotateRight("bcdea".toCharArray(), 1).joinToString(""))
        assertEquals("abcde", Day21.rotateRight("cdeab".toCharArray(), 2).joinToString(""))
    }

    @Test
    fun rotateRight() {
        assertEquals("eabcd", Day21.rotateRight("abcde".toCharArray(), 1).joinToString(""))
        assertEquals("deabc", Day21.rotateRight("abcde".toCharArray(), 2).joinToString(""))
    }

    @Test
    fun rotateRighReversedt() {
        assertEquals("abcde", Day21.rotateLeft("eabcd".toCharArray(), 1).joinToString(""))
        assertEquals("abcde", Day21.rotateLeft("deabc".toCharArray(), 2).joinToString(""))
    }

    @Test
    fun rotateBasedOn() {
        assertEquals("ecabd", Day21.rotateBasedOn("abdec".toCharArray(), 'b').joinToString(""))
        assertEquals("decab", Day21.rotateBasedOn("ecabd".toCharArray(), 'd').joinToString(""))
    }

    @Test
    fun rotateBasedOnReversed() {
        assertEquals("abdec", Day21.rotateBasedOnReversed("ecabd".toCharArray(), 'b').joinToString(""))
        assertEquals("ecabd", Day21.rotateBasedOnReversed("decab".toCharArray(), 'd').joinToString(""))
    }

    @Test
    fun reverse() {
        assertEquals("abcde", Day21.reverse("edcba".toCharArray(), 0, 4).joinToString(""))
        assertEquals("abcde", Day21.reverse("adcbe".toCharArray(), 1, 3).joinToString(""))
    }

    @Test
    fun reverseReversed() {
        assertEquals("edcba", Day21.reverse("abcde".toCharArray(), 0, 4).joinToString(""))
        assertEquals("adcbe", Day21.reverse("abcde".toCharArray(), 1, 3).joinToString(""))
    }

    @Test
    fun move() {
        assertEquals("bdeac", Day21.move("bcdea".toCharArray(), 1, 4).joinToString(""))
        assertEquals("abdec", Day21.move("bdeac".toCharArray(), 3, 0).joinToString(""))
        assertEquals("acdbe", Day21.move("abcde".toCharArray(), 1, 3).joinToString(""))
        assertEquals("adbce", Day21.move("abcde".toCharArray(), 3, 1).joinToString(""))
    }

    @Test
    fun moveReversed() {
        assertEquals("bcdea", Day21.move("bdeac".toCharArray(), 4, 1).joinToString(""))
        assertEquals("bdeac", Day21.move("abdec".toCharArray(), 0, 3).joinToString(""))
        assertEquals("abcde", Day21.move("acdbe".toCharArray(), 3, 1).joinToString(""))
        assertEquals("abcde", Day21.move("adbce".toCharArray(), 1, 3).joinToString(""))
    }
}
