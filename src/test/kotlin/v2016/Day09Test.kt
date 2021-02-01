package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day09Test {

    @ParameterizedTest(name = "Example #{index} - File `{0}` has a decompressed length of {1}")
    @CsvSource(
        "ADVENT, 6",
        "A(1x5)BC, 7",
        "(3x3)XYZ, 9",
        "A(2x2)BCD(2x2)EFG, 11",
        "(6x1)(1x3)A, 6",
        "X(8x2)(3x3)ABCY, 18"
    )
    fun testDay09Part1(input: String, expected: Long) {
        assertEquals(expected, Day09.part1(input))
    }

    @ParameterizedTest(name = "Example #{index} - File `{0}` has a decompressed length of {1}")
    @CsvSource(
        "(3x3)XYZ, 9",
        "X(8x2)(3x3)ABCY, 20",
        "(27x12)(20x12)(13x14)(7x10)(1x12)A, 241920",
        "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN, 445"
    )
    fun testDay09Part2(input: String, expected: Long) {
        assertEquals(expected, Day09.part2(input))
    }
}
