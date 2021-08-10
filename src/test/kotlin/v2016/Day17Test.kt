package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day17Test {

    @ParameterizedTest(name = "Example #{index} - With passcode \"{0}\", the shortest path would be \"{1}\"")
    @CsvSource(
        "ihgpwlah, DDRRRD",
        "kglvqrro, DDUDRLRRUDRD",
        "ulqzkmiv, DRURDRUDDLLDLUURRDULRLDUUDDDRR",
    )
    fun testDay17Part1(input: String, expected: String) {
        assertEquals(expected, Day17.part1(input))
    }

    @ParameterizedTest(name = "Example #{index} - With passcode \"{0}\", the longest path would take {1} steps")
    @CsvSource(
        "ihgpwlah, 370",
        "kglvqrro, 492",
        "ulqzkmiv, 830",
    )
    fun testDay17Part2(input: String, expected: Long) {
        assertEquals(expected, Day17.part2(input))
    }
}
