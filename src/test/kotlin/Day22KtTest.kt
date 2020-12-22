import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day22KtTest {
    val input = ("Player 1:\n" +
            "9\n" +
            "2\n" +
            "6\n" +
            "3\n" +
            "1\n" +
            "\n" +
            "Player 2:\n" +
            "5\n" +
            "8\n" +
            "4\n" +
            "7\n" +
            "10").split("\n")

    @Test
    fun testDay22Part1() {
        assertEquals(306L, day22Part1(input))
    }

    @Test
    fun testDay22Part2() {
        assertEquals(291L, day22Part2(input))
    }
}
