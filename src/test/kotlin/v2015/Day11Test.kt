package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day11Test {

    @ParameterizedTest(name = "Example #{index} - `{0}` becomes `{1}`")
    @CsvSource(
        "abcdefgh, abcdffaa",
        "ghijklmn, ghjaabcc",
        "iaaaaaaa, jaaaaabc"
    )
    fun testDay11Part1(input: String, expected: String) {
        assertEquals(expected, Day11.part1(input))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` contains non-overlapping pairs of letters `{1}`")
    @CsvSource(
        "aaaaaaaa, true",
        "aaabcdef, false",
        "aabbdefg, true",
        "aabcdezz, true",
    )
    fun testDay11ContainsNonOverlappingPairsOfLetters(input: String, expected: Boolean) {
        assertEquals(expected, Day11.containsNonOverlappingPairsOfLetters(input))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` contains three straight letters `{1}`")
    @CsvSource(
        "aaaaaaaa, false",
        "abcaaaaa, true"
    )
    fun testDay11ContainsThreeStraightLetters(input: String, expected: Boolean) {
        assertEquals(expected, Day11.containsThreeStraightLetters(input))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` contains valid letters `{1}`")
    @CsvSource(
        "aaaaaaai, aaaaaaaj",
        "iaaaaaaa, jaaaaaaa"
    )
    fun testDay11RemoveInvalidLetters(input: String, expected: String) {
        assertEquals(expected, Day11.removeInvalidLetters(input))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` has next password `{1}`")
    @CsvSource(
        "aaaaaaaa, aaaaaaab",
        "azzzzzzz, baaaaaaa"
    )
    fun testDay11NextPassword(input: String, expected: String) {
        assertEquals(expected, Day11.nextPassword(input))
    }
}
