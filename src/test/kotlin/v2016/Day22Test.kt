package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day22Test {

    val input = listOf(
        "root@ebhq-gridcenter# df -",
        "Filesystem              Size  Used  Avail  Use%",
        "/dev/grid/node-x0-y0     30T   10T    20T   50%",
        "/dev/grid/node-x0-y1     60T   20T    40T   50%",
        "/dev/grid/node-x0-y2     90T   30T    60T   50%",
        "/dev/grid/node-x0-y3    120T   40T    80T   50%",
    )

    @Test
    fun testDay22Part1() {
        assertEquals(10L, Day22.part1(input))
    }
}
