package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day06Test {

    private val input = ("eedadn\n" +
            "drvtee\n" +
            "eandsr\n" +
            "raavrd\n" +
            "atevrs\n" +
            "tsrnev\n" +
            "sdttsa\n" +
            "rasrtv\n" +
            "nssdts\n" +
            "ntnada\n" +
            "svetve\n" +
            "tesnvt\n" +
            "vntsnd\n" +
            "vrdear\n" +
            "dvrsen\n" +
            "enarar").split("\n")

    @Test
    fun testDay06Part1() {
        assertEquals("easter", Day06.part1(input))
    }

    @Test
    fun testDay06Part2() {
        assertEquals("advent", Day06.part2(input))
    }
}
