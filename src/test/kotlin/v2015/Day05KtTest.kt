package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day05KtTest {

    @ParameterizedTest(name = "Example #{index} - `{0}` is {1} (1=nice; 0=naughty)")
    @CsvSource(
        "ugknbfddgicrmopn, 1",
        "aaa, 1",
        "jchzalrnumimnmhp, 0",
        "haegwjzuvuyypxyu, 0",
        "dvszwmarrgswjxmb, 0"
    )
    fun testDay05Part1(input: String, expected: Long) {
        assertEquals(expected, day05Part1(listOf(input)))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` is {1} (1=nice; 0=naughty)")
    @CsvSource(
        "qjhvhtzxzqqjkmpb, 1",
        "xxyxx, 1",
        "uurcxstgmygtbstg, 0",
        "ieodomkazucvgmuy, 0",
        "aaa, 0",
        "aaaa, 1"
    )
    fun testDay05Part2(input: String, expected: Long) {
        assertEquals(expected, day05Part2(listOf(input)))
    }
}
