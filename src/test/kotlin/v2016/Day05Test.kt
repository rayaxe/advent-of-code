package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

internal class Day05Test {

    @Disabled("Slow")
    @Tags(Tag("slow"))
    @Test
    fun testDay05Part1() {
        assertEquals("18f47a30", Day05.part1("abc"))
    }

    @Disabled("Slow")
    @Tags(Tag("slow"))
    @Test
    fun testDay05Part2() {
        assertEquals("05ace8e3", Day05.part2("abc"))
    }
}
