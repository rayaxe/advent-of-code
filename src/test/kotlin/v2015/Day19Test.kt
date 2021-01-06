package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day19Test {

    @ParameterizedTest(name = "Example #{index} - Molecule `{0}` results in {1} distinct molecules")
    @CsvSource("HOH, 4", "HOHOHO, 7")
    fun testDay19Part1(molecule: String, expected: Long) {
        val input = "H => HO\n" +
                "H => OH\n" +
                "O => HH"
        assertEquals(expected, Day19.part1(input.split("\n") + "" + molecule))
    }

    @ParameterizedTest(name = "Example #{index} - Molecule `{0}` can be made in {1} steps")
    @CsvSource("HOH, 3", "HOHOHO, 6")
    fun testDay19Part2(molecule: String, expected: Long) {
        val input = "e => H\n" +
                "e => O\n" +
                "H => HO\n" +
                "H => OH\n" +
                "O => HH"
        assertEquals(-1L, Day19.part2(input.split("\n") + "" + molecule))
    }
}
