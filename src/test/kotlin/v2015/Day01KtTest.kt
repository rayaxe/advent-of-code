package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day01KtTest {

    @ParameterizedTest(name = "Example #{index} - Instructions `{0}` result in floor {1}")
    @CsvSource(
        "(()), 0",
        "()(), 0",
        "(((, 3",
        "(()(()(, 3",
        "))(((((, 3",
        "()), -1",
        "))(, -1",
        "))), -3",
        ")())()), -3"
    )
    fun testDay01Part1(input: String, expected: Long) {
        assertEquals(expected, day01Part1(input))
    }

    @ParameterizedTest(name = "Example #{index} - Instructions `{0}` results in basement position {1}")
    @CsvSource(
        "), 1",
        "()()), 5"
    )
    fun testDay01Part2(input: String, expected: Long) {
        assertEquals(expected, day01Part2(input))
    }
}
