package v2015

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day15Test {

    val input = "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8\n" +
            "Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"

    @Test
    fun testDay15Part1() {
        assertEquals(62842880L, Day15.part1(input.split("\n")))
    }

    @Test
    fun testDay15Part2() {
        assertEquals(57600000L, Day15.part2(input.split("\n")))
    }
}
