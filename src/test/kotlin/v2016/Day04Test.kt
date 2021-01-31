package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day04Test {

    val input = ("aaaaa-bbb-z-y-x-123[abxyz]\n" +
            "a-b-c-d-e-f-g-h-987[abcde]\n" +
            "not-a-real-room-404[oarel]\n" +
            "totally-real-room-200[decoy]").split("\n")

    @Test
    fun testDay04Part1() {
        assertEquals(1514L, Day04.part1(input))
    }

    @Test
    fun testDay04Part2() {
        assertEquals(482L, Day04.part2(listOf("zadftbaxq-anvqof-efadmsq-482[afqdb]")))
    }
}
