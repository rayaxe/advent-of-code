package v2020

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day05KtTest {

    @Test
    fun testDay5Part1_example1() {
        assertEquals(567, findSeatNumber("BFFFBBFRRR"))
    }

    @Test
    fun testDay5Part1_example2() {
        assertEquals(119, findSeatNumber("FFFBBBFRRR"))
    }

    @Test
    fun testDay5Part1_example3() {
        assertEquals(820, findSeatNumber("BBFFBBFRLL"))
    }
}
