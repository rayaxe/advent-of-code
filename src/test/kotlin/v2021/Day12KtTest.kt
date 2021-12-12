package v2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import v2021.Day12.Companion.part1
import v2021.Day12.Companion.part2

internal class Day12KtTest {

    private val inputExample1 = ("start-A\n" +
            "start-b\n" +
            "A-c\n" +
            "A-b\n" +
            "b-d\n" +
            "A-end\n" +
            "b-end").split("\n")

    private val inputExample2 = ("dc-end\n" +
            "HN-start\n" +
            "start-kj\n" +
            "dc-start\n" +
            "dc-HN\n" +
            "LN-dc\n" +
            "HN-end\n" +
            "kj-sa\n" +
            "kj-HN\n" +
            "kj-dc").split("\n")

    private val inputExample3 = ("fs-end\n" +
            "he-DX\n" +
            "fs-he\n" +
            "start-DX\n" +
            "pj-DX\n" +
            "end-zg\n" +
            "zg-sl\n" +
            "zg-pj\n" +
            "pj-he\n" +
            "RW-he\n" +
            "fs-DX\n" +
            "pj-RW\n" +
            "zg-RW\n" +
            "start-pj\n" +
            "he-WI\n" +
            "zg-he\n" +
            "pj-fs\n" +
            "start-RW").split("\n")

    @Test
    fun testDay12Part1() {
        assertEquals(10L, part1(inputExample1))
        assertEquals(19L, part1(inputExample2))
        assertEquals(226L, part1(inputExample3))
    }

    @Test
    fun testDay12Part2() {
        assertEquals(36L, part2(inputExample1))
        assertEquals(103L, part2(inputExample2))
        assertEquals(3509L, part2(inputExample3))
    }
}
