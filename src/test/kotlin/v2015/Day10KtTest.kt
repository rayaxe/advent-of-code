package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day10KtTest {

    @ParameterizedTest(name = "Example #{index} - `{0}` becomes `{1}`")
    @CsvSource(
        "1, 11",
        "11, 21",
        "21, 1211",
        "1211, 111221",
        "111221, 312211"
    )
    fun testDay10Part1(input: String, expected: Long) {
        assertEquals(expected, Day10.lookAndSay(input).toLong())
    }
}
