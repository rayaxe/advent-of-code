package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day11Test {

    @ParameterizedTest(name = "Example #{index} - `{0}` is `{1}` steps away")
    @CsvSource(
        "ne,ne,ne;3",
        "ne,ne,sw,sw;0",
        "ne,ne,s,s;2",
        "se,sw,se,sw,sw;3",
        delimiter = ';'
    )
    fun testDay11Part1(input: String, expected: Long) {
        assertEquals(expected, Day11.part1(input))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` is `{1}` steps away")
    @CsvSource(
        "ne,ne,ne;3",
        "ne,ne,sw,sw;2",
        "ne,ne,s,s;2",
        "se,sw,se,sw,sw;3",
        delimiter = ';'
    )
    fun testDay11Part2(input: String, expected: Long) {
        assertEquals(expected, Day11.part2(input))
    }
}
