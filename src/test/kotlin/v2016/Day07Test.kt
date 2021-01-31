package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day07Test {

    @ParameterizedTest(name = "Example #{index} - IP `{0}` supports TLS if {1}=1")
    @CsvSource(
        "abba[mnop]qrst, 1",
        "abcd[bddb]xyyx, 0",
        "aaaa[qwer]tyui, 0",
        "ioxxoj[asdfgh]zxcvbn, 1"
    )
    fun testDay07Part1(input: String, expected: Long) {
        assertEquals(expected, Day07.part1(listOf(input)))
    }

    @ParameterizedTest(name = "Example #{index} - IP `{0}` supports SSL if {1}=1")
    @CsvSource(
        "aba[bab]xyz, 1",
        "xyx[xyx]xyx, 0",
        "aaa[kek]eke, 1",
        "zazbz[bzb]cdb, 1"
    )
    fun testDay07Part2(input: String, expected: Long) {
        assertEquals(expected, Day07.part2(listOf(input)))
    }
}
