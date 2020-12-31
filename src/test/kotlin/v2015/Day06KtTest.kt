package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class Day06KtTest {

    @ParameterizedTest(name = "Example #{index} - Instructions {0} results in {1} lights lit")
    @MethodSource("providePart1Examples")
    fun testDay06Part1(input: List<String>, expected: Long) {
        assertEquals(expected, day06Part1(input))
    }

    @ParameterizedTest(name = "Example #{index} - Instructions {0} results in {1} total brightness")
    @MethodSource("providePart2Examples")
    fun testDay06Part2(input: List<String>, expected: Long) {
        assertEquals(expected, day06Part2(input))
    }

    private companion object {
        @JvmStatic
        fun providePart1Examples(): Stream<Arguments> {
            val turnOnAllLights = listOf("turn on 0,0 through 999,999")
            return Stream.of(
                Arguments.of(turnOnAllLights, 1_000_000L),
                Arguments.of(turnOnAllLights + "toggle 0,0 through 999,0", 999_000L),
                Arguments.of(turnOnAllLights + "turn off 499,499 through 500,500", 999_996L),
            )
        }

        @JvmStatic
        fun providePart2Examples(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(listOf("turn on 0,0 through 0,0"), 1L),
                Arguments.of(listOf("toggle 0,0 through 999,999"), 2_000_000L)
            )
        }
    }
}
