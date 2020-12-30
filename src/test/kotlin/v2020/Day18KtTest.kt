package v2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class Day18KtTest {

    @ParameterizedTest(name = "Example #{index} - `{0}` becomes `{1}`")
    @MethodSource("provideExpressionExamplesPart1")
    fun testDay18Part1(input: String, expected: Long) {
        assertEquals(expected, day18Part1(listOf(input)))
    }

    @ParameterizedTest(name = "Example #{index} - `{0}` becomes `{1}`")
    @MethodSource("provideExpressionExamplesPart2")
    fun testDay18Part2(input: String, expected: Long) {
        assertEquals(expected, day18Part2(listOf(input)))
    }

    private companion object {
        @JvmStatic
        fun provideExpressionExamplesPart1(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("1 + 2 * 3 + 4 * 5 + 6", 71L),
                Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51L),
                Arguments.of("2 * 3 + (4 * 5)", 26L),
                Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 437L),
                Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 12240L),
                Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 13632L)
            )
        }

        @JvmStatic
        fun provideExpressionExamplesPart2(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("1 + 2 * 3 + 4 * 5 + 6", 231L),
                Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51L),
                Arguments.of("2 * 3 + (4 * 5)", 46L),
                Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 1445L),
                Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 669060L),
                Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 23340L)
            )
        }
    }
}
