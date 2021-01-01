package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@ExperimentalStdlibApi
@ExperimentalUnsignedTypes
internal class Day07KtTest {

    private val circuit = "123 -> x\n" +
            "456 -> y\n" +
            "x AND y -> d\n" +
            "x OR y -> e\n" +
            "x LSHIFT 2 -> f\n" +
            "y RSHIFT 2 -> g\n" +
            "NOT x -> h\n" +
            "NOT y -> i"

    @ParameterizedTest(name = "Example #{index} - Signal `{0}` is {1}")
    @CsvSource(
        "d, 72",
        "e, 507",
        "f, 492",
        "g, 114",
        "h, 65412",
        "i, 65079",
        "x, 123",
        "y, 456",
    )
    fun testDay07Part1(wire: String, expected: Long) {
        val instructions = circuit.split("\n")
        assertEquals(expected, Day07.part1(instructions, wire))
    }

    @Test
    fun testUShortShiftLeft() {
        assertEquals(2.toUShort(), 1.toUShort() shl 1)
        assertEquals(65534.toUShort(), 65535.toUShort() shl 1)
    }

    @Test
    fun testUShortShiftRight() {
        assertEquals(0.toUShort(), 1.toUShort() shr 1)
        assertEquals(32767.toUShort(), 65535.toUShort() shr 1)
    }
}
