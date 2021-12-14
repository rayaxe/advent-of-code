package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day14.Companion.part1
import v2021.Day14.Companion.part2

internal class Day14KtTest {

    private val input = ("NNCB\n" +
            "\n" +
            "CH -> B\n" +
            "HH -> N\n" +
            "CB -> H\n" +
            "NH -> C\n" +
            "HB -> C\n" +
            "HC -> B\n" +
            "HN -> C\n" +
            "NN -> C\n" +
            "BH -> H\n" +
            "NC -> B\n" +
            "NB -> B\n" +
            "BN -> B\n" +
            "BB -> N\n" +
            "BC -> B\n" +
            "CC -> N\n" +
            "CN -> C").split("\n")

    @Test
    fun testDay14Part1() {
        assertEquals(1588L, part1(input))
    }

    @Test
    fun testDay14Part2() {
        assertEquals(2188189693529L, part2(input))
    }
}
