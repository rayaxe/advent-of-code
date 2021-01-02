package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day12KtTest {

    @ParameterizedTest(name = "Example #{index} - `{0}` has a sum of `{1}`")
    @CsvSource(
        "[1,2,3]; 6",
        "{\"a\":2,\"b\":4}; 6",
        "[[[3]]]; 3",
        "{\"a\":{\"b\":4},\"c\":-1}; 3",
        "{\"a\":[-1,1]}; 0",
        "[-1,{\"a\":1}]; 0",
        "[]; 0",
        "{}; 0",
        delimiter = ';'
    )
    fun testDay12Part1(input: String, expected: Long) {
        assertEquals(expected, Day12.part1(listOf(input)))
    }
}
