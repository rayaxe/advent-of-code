package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import v2021.Day06.Companion.part1
import v2021.Day06.Companion.part2

internal class Day06KtTest {

    private val input = "3,4,3,1,2"

    @ParameterizedTest(name = "Example #{index} - After {0} days there would be a total of {1} fish")
    @CsvSource("18, 26", "80, 5934")
    fun testDay6Part1(days: Int, expected: Long) {
        assertEquals(expected, part1(input, days))
    }

    @Test
    fun testDay6Part2() {
        assertEquals(26984457539L, part2(input))
    }
}
