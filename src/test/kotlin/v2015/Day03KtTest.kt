package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day03KtTest {

    @ParameterizedTest(name = "Example #{index} - Moves `{0}` delivers presents to {1} houses")
    @CsvSource(
        ">, 2",
        "^>v<, 4",
        "^v^v^v^v^v, 2"
    )
    fun testDay02Part1(input: String, expected: Long) {
        assertEquals(expected, day03Part1(input))
    }
}
