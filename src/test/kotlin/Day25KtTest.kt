import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day25KtTest {

    @Test
    fun testCardPublicKey() {
        val cardPublicKey = 5764801L
        assertEquals(8L, determineLoopSize(cardPublicKey, 7L))
    }

    @Test
    fun testDoorPublicKey() {
        val doorPublicKey = 17807724L
        assertEquals(11L, determineLoopSize(doorPublicKey, 7L))
    }

    @Test
    fun testTransform() {
        val cardPublicKey = 5764801L
        val cardLoopSize = 8L
        val doorPublicKey = 17807724L
        val doorLoopSize = 11L
        assertEquals(14897079L, transform(doorPublicKey, cardLoopSize))
        assertEquals(14897079L, transform(cardPublicKey, doorLoopSize))
    }

    @Test
    fun testDay25Part1() {
        val input = "5764801\n17807724"
        assertEquals(14897079L, day25Part1(input))
    }
}
