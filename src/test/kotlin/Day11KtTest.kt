import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day11KtTest {

    @Test
    fun testDay11Part1() {
        val input = "L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL"
        val values = input.split("\n")
        assertEquals(37, day11Part1(values))
    }

    @Test
    fun testDay11Part2_visibleExample1() {
        val input = ".......#.\n" +
                "...#.....\n" +
                ".#.......\n" +
                ".........\n" +
                "..#L....#\n" +
                "....#....\n" +
                ".........\n" +
                "#........\n" +
                "...#....."
        assertEquals(8, testCountVisibleOccupied(input, 3, 4))
    }

    @Test
    fun testDay11Part2_visibleExample2() {
        val input = ".............\n" +
                ".L.L.#.#.#.#.\n" +
                "............."
        assertEquals(0, testCountVisibleOccupied(input, 1, 1))
    }

    @Test
    fun testDay11Part2_visibleExample3() {
        val input = ".##.##.\n" +
                "#.#.#.#\n" +
                "##...##\n" +
                "...L...\n" +
                "##...##\n" +
                "#.#.#.#\n" +
                ".##.##."
        assertEquals(0, testCountVisibleOccupied(input, 3, 3))
    }

    private fun testCountVisibleOccupied(values: String, y: Int, x: Int): Int {
        val input = values.split("\n")
        val seats = parseSeats(input)
        return countOccupiedVisible(seats, y, x)
    }

    @Test
    fun testDay11Part2() {
        val input = "L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL"
        val values = input.split("\n")
        assertEquals(26, day11Part2(values))
    }
}
