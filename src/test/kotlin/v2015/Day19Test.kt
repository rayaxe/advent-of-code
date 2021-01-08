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
}
