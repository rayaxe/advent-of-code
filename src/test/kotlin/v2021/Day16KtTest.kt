package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import v2021.Day16.Companion.part1
import v2021.Day16.Companion.part2

internal class Day16KtTest {

    @ParameterizedTest(name = "Example #{index} - Transmission `{0}` has a version sum of {1}")
    @CsvSource(
        "8A004A801A8002F478, 16",
        "620080001611562C8802118E34, 12",
        "C0015000016115A2E0802F182340, 23",
        "A0016C880162017C3686B18A3D4780, 31",
    )
    fun testDay16Part1(input: String, expected: Long) {
        assertEquals(expected, part1(input))
    }

    @ParameterizedTest(name = "Example #{index} - Transmission `{0}` has a version sum of {1}")
    @CsvSource(
        "C200B40A82, 3",
        "04005AC33890, 54",
        "880086C3E88112, 7",
        "CE00C43D881120, 9",
        "D8005AC2A8F0, 1",
        "F600BC2D8F, 0",
        "9C005AC2F8F0, 0",
        "9C0141080250320F1802104A08, 1",
    )
    fun testDay16Part2(input: String, expected: Long) {
        assertEquals(expected, part2(input))
    }
}
