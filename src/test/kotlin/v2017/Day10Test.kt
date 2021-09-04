package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day10Test {

    @Test
    fun testDay10Part1() {
        assertEquals(12L, Day10.part1(listOf("3", "4", "1", "5"), 5))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` becomes `{1}`")
    @CsvSource(
        "'';a2582a3a0e66e6e86e3812dcb672a272",
        "AoC 2017;33efeb34ea91902bb2f59c9920caa6cd",
        "1,2,3;3efbe78a8d82f29979031a4aa0b16a9d",
        "1,2,4;63960835bcdc130f0b66d7ff4f6a5a8e",
        delimiter = ';'
    )
    fun testDay10Part2(input: String, expected: String) {
        assertEquals(expected, Day10.part2(input.map { it.toString() }, 256))
    }
}
