package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day09Test {

    @ParameterizedTest(name = "Example #{index} - `{0}`, score of `{1}`")
    @CsvSource(
        "{}; 1",
        "{{{}}}; 6",
        "{{},{}}; 5",
        "{{{},{},{{}}}}; 16",
        "{<{},{},{{}}>}; 1",
        "{<a>,<a>,<a>,<a>}; 1",
        "{{<a>},{<a>},{<a>},{<a>}}; 9",
        "{{<!>},{<!>},{<!>},{<a>}}; 3",
        "{{<ab>},{<ab>},{<ab>},{<ab>}}; 9",
        "{{<!!>},{<!!>},{<!!>},{<!!>}}; 9",
        "{{<a!>},{<a!>},{<a!>},{<ab>}}; 3",
        delimiter = ';'
    )
    fun testDay09Part1(input: String, expected: Long) {
        assertEquals(expected, Day09.part1(input))
    }
}
