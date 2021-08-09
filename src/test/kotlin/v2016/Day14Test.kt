package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

internal class Day14Test {

    @Test
    fun testDay14Part1() {
        assertEquals(22728L, Day14.part1("abc"))
    }

    @Disabled("Slow")
    @Tags(Tag("slow"))
    @Test
    fun testDay14Part2() {
        assertEquals(22551L, Day14.part2("abc"))
    }
}
