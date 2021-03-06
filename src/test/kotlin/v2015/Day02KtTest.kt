package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day02KtTest {

    @ParameterizedTest(name = "Example #{index} - A present with dimensions {0} requires {1} square feet of wrapping paper")
    @CsvSource(
        "2x3x4, 58",
        "1x1x10, 43"
    )
    fun testDay02Part1(input: String, expected: Long) {
        assertEquals(expected, day02Part1(listOf(input)))
    }

    @ParameterizedTest(name = "Example #{index} - A present with dimensions {0} requires {1} square feet of ribbon")
    @CsvSource(
        "2x3x4, 34",
        "1x1x10, 14"
    )
    fun testDay02Part2(input: String, expected: Long) {
        assertEquals(expected, day02Part2(listOf(input)))
    }
}
