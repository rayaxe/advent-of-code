import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day14KtTest {

    @Test
    fun testDay14Part1() {
        val input = "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                "mem[8] = 11\n" +
                "mem[7] = 101\n" +
                "mem[8] = 0"
        val values = input.split("\n")
        assertEquals(165L, day14Part1(values))
    }

    @Test
    fun testDay14Part2() {
        val input = "mask = 000000000000000000000000000000X1001X\n" +
                "mem[42] = 100\n" +
                "mask = 00000000000000000000000000000000X0XX\n" +
                "mem[26] = 1"
        val values = input.split("\n")
        assertEquals(208, day14Part2(values))
    }
}
