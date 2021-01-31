package v2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class Day13KtTest {

    @Test
    fun testDay13Part1() {
        val input = "939\n7,13,x,x,59,x,31,19"
        val values = input.split("\n")
        assertEquals(295L, day13Part1(values))
    }

    @Test
    fun testDay13Part2() {
        val input = "939\n7,13,x,x,59,x,31,19"
        val values = input.split("\n")
        assertEquals(1068781L, day13Part2(values))
    }

    @ParameterizedTest(name = "Example #{index} - Bus lines [{0}] should result in earliest timestamp {1}")
    @MethodSource("provideBusLineExamples")
    fun testDay13Part2_examples(input: String, expected: Long) {
        assertEquals(expected, day13Part2(listOf("ignoredValue") + input))
    }

    companion object {
        @JvmStatic
        fun provideBusLineExamples(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("17,x,13,19", 3417L),
                Arguments.of("67,7,59,61", 754018L),
                Arguments.of("67,x,7,59,61", 779210L),
                Arguments.of("67,7,x,59,61", 1261476L),
                Arguments.of("1789,37,47,1889", 1202161486L)
            )
        }
    }
}
