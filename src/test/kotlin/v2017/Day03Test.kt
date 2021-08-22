package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day03Test {

    @ParameterizedTest(name = "Example #{index} - Data from square {0} is carried {1} steps")
    @CsvSource(
        "1, 0",
        "12, 3",
        "23, 2",
        "1024, 31",
    )
    fun testDay03Part1(input: Long, expected: Long) {
        assertEquals(expected, Day03.part1(input))
    }
}
