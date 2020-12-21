import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day21KtTest {

    private val input = ("mxmxvkd kfcds sqjhc nhms (contains dairy, fish)\n" +
            "trh fvjkl sbzzf mxmxvkd (contains dairy)\n" +
            "sqjhc fvjkl (contains soy)\n" +
            "sqjhc mxmxvkd sbzzf (contains fish)").split("\n")

    @Test
    fun testDay21Part1() {
        assertEquals(5L, day21Part1(input))
    }

    @Test
    fun testDay21Part2() {
        assertEquals("mxmxvkd,sqjhc,fvjkl", day21Part2(input))
    }
}
