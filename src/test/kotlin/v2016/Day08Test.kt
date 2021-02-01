package v2016

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day08Test {

    val input = ("rect 3x2\n" +
            "rotate column x=1 by 1\n" +
            "rotate row y=0 by 4\n" +
            "rotate column x=1 by 1").split("\n")

    @Test
    fun testDay08() {
        assertEquals(6L, Day08.run(input, 7, 3))
    }
}
