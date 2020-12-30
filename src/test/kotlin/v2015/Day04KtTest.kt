package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day04KtTest {

    @ParameterizedTest(name = "Example #{index} - With secret key `{0}` the answer is {1}")
    @CsvSource(
        "abcdef, 609043",
        "pqrstuv, 1048970"
    )
    fun testDay04Part1(secretKey: String, expected: Long) {
        assertEquals(expected, day04(secretKey))
    }
}
