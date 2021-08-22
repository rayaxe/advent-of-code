package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day04Test {

    @ParameterizedTest(name = "Example #{index} - `{0}` has {1} valid passphrases")
    @CsvSource(
        "aa bb cc dd ee, 1",
        "aa bb cc dd aa, 0",
        "aa bb cc dd aaa, 1",
    )
    fun testDay04Part1(input: String, expected: Long) {
        assertEquals(expected, Day04.part1(listOf(input)))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` has {1} valid passphrases")
    @CsvSource(
        "abcde fghij, 1",
        "abcde xyz ecdab, 0",
        "a ab abc abd abf abj, 1",
        "iiii oiii ooii oooi oooo, 1",
        "oiii ioii iioi iiio, 0",
    )
    fun testDay04Part2(input: String, expected: Long) {
        assertEquals(expected, Day04.part2(listOf(input)))
    }
}
