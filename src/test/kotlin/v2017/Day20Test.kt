package v2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day20Test {

    @ParameterizedTest(name = "Example #{index} - `{0}` particle  `{1}` will stay closest")
    @CsvSource(
        "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>|p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>; 0",
        "p=<1295,-1969,-800>, v=<-33,59,15>, a=<-1,1,1>|p=<430,891,209>, v=<3,-10,-8>, a=<-1,-1,0>; 1",
        delimiter = ';'
    )
    fun testDay20Part1(input: String, expected: Int) {
        assertEquals(expected, Day20.part1(input.split('|')))
    }

    @Test
    fun testDay20Part2() {
        val input = listOf(
            "p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>",
            "p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>",
            "p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>",
            "p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>"
        )
        assertEquals(1, Day20.part2(input))
    }
}
