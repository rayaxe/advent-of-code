package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day01Test {

    @ParameterizedTest(name = "Example #{index} - Following `{0}` ends {1} blocks away")
    @CsvSource(
        "R2, L3; 5",
        "R2, R2, R2; 2",
        "R5, L5, R5, R3; 12",
        delimiter = ';'
    )
    fun testDay01Part1(input: String, expected: Long) {
        assertEquals(expected, Day01.part1(input.split(", ")))
    }

    @Test
    fun testDay01Part2() {
        val input = "R8, R4, R4, R8".split(", ")
        assertEquals(4L, Day01.part2(input))
    }
}
