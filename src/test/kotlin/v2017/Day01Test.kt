package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day01Test {

    @ParameterizedTest(name = "Example #{index} - `{0}` produces a sum of {1}")
    @CsvSource(
        "1122, 3",
        "1111, 4",
        "1234, 0",
        "91212129, 9",
    )
    fun testDay01Part1(input: String, expected: Long) {
        assertEquals(expected, Day01.part1(input))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` produces a sum of {1}")
    @CsvSource(
        "1212, 6",
        "1221, 0",
        "123425, 4",
        "123123, 12",
        "12131415, 4",
    )
    fun testDay01Part2(input: String, expected: Long) {
        assertEquals(expected, Day01.part2(input))
    }
}
