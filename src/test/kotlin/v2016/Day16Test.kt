package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day16Test {

    @ParameterizedTest(name = "Example #{index} - \"{0}\" becomes \"{1}\"")
    @CsvSource(
        "1, 100",
        "0, 001",
        "11111, 11111000000",
        "111100001010, 1111000010100101011110000",
    )
    fun generateData(input: String, expected: String) {
        val actual = Day16.generateData(input, input.length * 2 + 1)
        assertEquals(expected, actual)
    }

    @Test
    fun testDay16Part1() {
        assertEquals("100", Day16.part1("110010110100", 12))
        assertEquals("01100", Day16.part1("10000", 20))
    }

    @Test
    fun testDay16Part2() {
        assertEquals("100", Day16.part2("110010110100", 12))
    }
}
