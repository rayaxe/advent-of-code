package v2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day23KtTest {

    @ParameterizedTest(name = "Example #{index} - Labeling {0} with {1} moves produces {2}")
    @CsvSource("389125467, 10, 92658374", "389125467, 100, 67384529")
    fun testDay23Part1(labeling: String, moves: Int, expected: String) {
        assertEquals(expected, day23Part1(labeling, moves))
    }

    @Test
    fun testDay23Part2() {
        assertEquals(149245887792L, day23Part2("389125467"))
    }
}
