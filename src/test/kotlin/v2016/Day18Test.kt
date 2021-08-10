package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day18Test {

    @ParameterizedTest(name = "Example #{index} - Starting with row \"{0}\", going for a total of {1} rows, results in {2} safe tiles")
    @CsvSource(
        "..^^., 3, 6",
        ".^^.^.^^^^, 10, 38",
    )
    fun testDay18Part1(startRow: String, numberOfRows: Int, expected: Long) {
        assertEquals(expected, Day18.part1(startRow, numberOfRows))
    }
}
