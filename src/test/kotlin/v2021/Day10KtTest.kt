package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import v2021.Day10.Companion.findIllegalCharOrRemainingLines
import v2021.Day10.Companion.part1
import v2021.Day10.Companion.part2

internal class Day10KtTest {

    private val input = ("[({(<(())[]>[[{[]{<()<>>\n" +
            "[(()[<>])]({[<{<<[]>>(\n" +
            "{([(<{}[<>[]}>{[]{[(<()>\n" +
            "(((({<>}<{<{<>}{[]{[]{}\n" +
            "[[<[([]))<([[{}[[()]]]\n" +
            "[{[{({}]{}}([{[{{{}}([]\n" +
            "{<[[]]>}<{[{[{[]{()[[[]\n" +
            "[<(<(<(<{}))><([]([]()\n" +
            "<{([([[(<>()){}]>(<<{{\n" +
            "<{([{{}}[<[[[<>{}]]]>[]]").split("\n")

    @ParameterizedTest(name = "Example #{index} - Expected `{1}`")
    @CsvSource(
        "{([(<{}[<>[]}>{[]{[(<()>,}",
        "[[<[([]))<([[{}[[()]]],)",
        "[{[{({}]{}}([{[{{{}}([],]",
        "[<(<(<(<{}))><([]([](),)",
        "<{([([[(<>()){}]>(<<{{,>"
    )
    fun testDay10Part1Example(line: String, expected: Char) {
        assertEquals(expected, findIllegalCharOrRemainingLines(line.toMutableList()).first)
    }

    @Test
    fun testDay10Part1() {
        assertEquals(26397L, part1(input))
    }

    @Test
    fun testDay10Part2() {
        assertEquals(288957L, part2(input))
    }
}
