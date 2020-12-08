import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day8KtTest {

    @Test
    fun testDay8Part1() {
        val input = "nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6"
        val values = input.split("\n")
        assertEquals(5, day8Part1(values))
    }

    @Test
    fun testDay8Part2() {
        val input = "nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6"
        val values = input.split("\n")
        assertEquals(8, day8Part2(values))
    }
}
