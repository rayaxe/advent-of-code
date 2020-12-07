import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day6KtTest {

    private val input = "abc\n" +
            "\n" +
            "a\n" +
            "b\n" +
            "c\n" +
            "\n" +
            "ab\n" +
            "ac\n" +
            "\n" +
            "a\n" +
            "a\n" +
            "a\n" +
            "a\n" +
            "\n" +
            "b"

    @Test
    fun testDay6Part1() {
        val answers = parse(input)
        assertEquals(11, day6Part1(answers))
    }

    @Test
    fun testDay6Part2() {
        val answers = parse(input)
        assertEquals(6, day6Part2(answers))
    }

    @Test
    fun testDay6Part2a() {
        val answers = parse("abc")
        assertEquals(3, day6Part2(answers))
    }

    @Test
    fun testDay6Part2b() {
        val answers = parse("a\nb\nc")
        assertEquals(0, day6Part2(answers))
    }

    @Test
    fun testDay6Part2c() {
        val answers = parse("ab\nac")
        assertEquals(1, day6Part2(answers))
    }

    @Test
    fun testDay6Part2d() {
        val answers = parse("a\na\na\na")
        assertEquals(1, day6Part2(answers))
    }

    @Test
    fun testDay6Part2e() {
        val answers = parse("b")
        assertEquals(1, day6Part2(answers))
    }

    private fun parse(input: String): List<List<String>> {
        return input
            .split("\n\n")
            .map { it.split("\n") }
    }
}
