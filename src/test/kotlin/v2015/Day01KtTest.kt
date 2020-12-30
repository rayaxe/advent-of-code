package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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
    fun testDay23Part1(input: String, expected: Long) {
        assertEquals(expected, day01Part1(input))
    }

    @Test
    fun testDay23Part2() {
        assertEquals(149245887792L, day01Part2("389125467"))
    }
}
