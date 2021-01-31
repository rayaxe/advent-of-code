package v2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class Day15KtTest {

    @ParameterizedTest(name = "Example #{index} - Given the starting numbers `{0}` the 2020th number spoken is `{1}`")
    @MethodSource("provideMemoryGameExamplesPart1")
    fun testDay15Part1(input: String, expected: Long) {
        assertEquals(expected, day15Part1(input))
    }

    @Disabled("Slow")
    @Tags(Tag("slow"))
    @ParameterizedTest(name = "Example #{index} - Given the starting numbers `{0}` the 30000000th number spoken is `{1}`")
    @MethodSource("provideMemoryGameExamplesPart2")
    fun testDay15Part2(input: String, expected: Long) {
        assertEquals(expected, day15Part2(input))
    }

    companion object {
        @JvmStatic
        fun provideMemoryGameExamplesPart1(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("0,3,6", 436L),
                Arguments.of("1,3,2", 1L),
                Arguments.of("2,1,3", 10L),
                Arguments.of("1,2,3", 27L),
                Arguments.of("2,3,1", 78L),
                Arguments.of("3,2,1", 438L),
                Arguments.of("3,1,2", 1836L)
            )
        }

        @JvmStatic
        fun provideMemoryGameExamplesPart2(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("0,3,6", 175594L),
                Arguments.of("1,3,2", 2578L),
                Arguments.of("2,1,3", 3544142L),
                Arguments.of("1,2,3", 261214L),
                Arguments.of("2,3,1", 6895259L),
                Arguments.of("3,2,1", 18L),
                Arguments.of("3,1,2", 362L)
            )
        }
    }
}
