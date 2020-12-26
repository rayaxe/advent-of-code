import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day20KtTest {

    @Test
    fun testDay20Part1() {
        assertEquals(20899048083289L, day20Part1(input))
    }

    @Test
    fun testDetermineWaterRoughness() {
        val image = stringToImage(example.split("\n"))
        val newImage = rotateImageLeftFlipped(image)
        assertEquals(273L, determineWaterRoughness(newImage))
    }

    @Test
    fun testRotateImageRight() {
        val input = "ABC\n" +
                "DEF\n" +
                "GHI"
        val actual = rotateImageRight(stringToImage(input.split("\n")))
        val expected = "GDA\n" +
                "HEB\n" +
                "IFC"
        assertEquals(expected, imageToString(actual))
    }

    @Test
    fun testRotateImageDown() {
        val input = "ABC\n" +
                "DEF\n" +
                "GHI"
        val actual = rotateImageDown(stringToImage(input.split("\n")))
        val expected = "IHG\n" +
                "FED\n" +
                "CBA"
        assertEquals(expected, imageToString(actual))
    }

    @Test
    fun testRotateImageLeft() {
        val input = "ABC\n" +
                "DEF\n" +
                "GHI"
        val actual = rotateImageLeft(stringToImage(input.split("\n")))
        val expected = "CFI\n" +
                "BEH\n" +
                "ADG"
        assertEquals(expected, imageToString(actual))
    }

    @Test
    fun testRotateImageUpFlipped() {
        val input = "ABC\n" +
                "DEF\n" +
                "GHI"
        val actual = rotateImageUpFlipped(stringToImage(input.split("\n")))
        val expected = "CBA\n" +
                "FED\n" +
                "IHG"
        assertEquals(expected, imageToString(actual))
    }

    @Test
    fun testRotateImageLeftFlipped() {
        val input = "ABC\n" +
                "DEF\n" +
                "GHI"
        val actual = rotateImageLeftFlipped(stringToImage(input.split("\n")))
        val expected = "ADG\n" +
                "BEH\n" +
                "CFI"
        assertEquals(expected, imageToString(actual))
    }

    @Test
    fun testRotateImageDownFlipped() {
        val input = "ABC\n" +
                "DEF\n" +
                "GHI"
        val actual = rotateImageDownFlipped(stringToImage(input.split("\n")))
        val expected = "GHI\n" +
                "DEF\n" +
                "ABC"
        assertEquals(expected, imageToString(actual))
    }

    @Test
    fun testRotateImageRightFlipped() {
        val input = "CFI\n" +
                "BEH\n" +
                "ADG"
        val actual = rotateImageRightFlipped(stringToImage(input.split("\n")))
        val expected = "GHI\n" +
                "DEF\n" +
                "ABC"
        assertEquals(expected, imageToString(actual))
    }

    @Test
    fun testDay20Part2() {
        assertEquals(273L, day20Part2(input))
    }

    val input = "Tile 2311:\n" +
            "..##.#..#.\n" +
            "##..#.....\n" +
            "#...##..#.\n" +
            "####.#...#\n" +
            "##.##.###.\n" +
            "##...#.###\n" +
            ".#.#.#..##\n" +
            "..#....#..\n" +
            "###...#.#.\n" +
            "..###..###\n" +
            "\n" +
            "Tile 1951:\n" +
            "#.##...##.\n" +
            "#.####...#\n" +
            ".....#..##\n" +
            "#...######\n" +
            ".##.#....#\n" +
            ".###.#####\n" +
            "###.##.##.\n" +
            ".###....#.\n" +
            "..#.#..#.#\n" +
            "#...##.#..\n" +
            "\n" +
            "Tile 1171:\n" +
            "####...##.\n" +
            "#..##.#..#\n" +
            "##.#..#.#.\n" +
            ".###.####.\n" +
            "..###.####\n" +
            ".##....##.\n" +
            ".#...####.\n" +
            "#.##.####.\n" +
            "####..#...\n" +
            ".....##...\n" +
            "\n" +
            "Tile 1427:\n" +
            "###.##.#..\n" +
            ".#..#.##..\n" +
            ".#.##.#..#\n" +
            "#.#.#.##.#\n" +
            "....#...##\n" +
            "...##..##.\n" +
            "...#.#####\n" +
            ".#.####.#.\n" +
            "..#..###.#\n" +
            "..##.#..#.\n" +
            "\n" +
            "Tile 1489:\n" +
            "##.#.#....\n" +
            "..##...#..\n" +
            ".##..##...\n" +
            "..#...#...\n" +
            "#####...#.\n" +
            "#..#.#.#.#\n" +
            "...#.#.#..\n" +
            "##.#...##.\n" +
            "..##.##.##\n" +
            "###.##.#..\n" +
            "\n" +
            "Tile 2473:\n" +
            "#....####.\n" +
            "#..#.##...\n" +
            "#.##..#...\n" +
            "######.#.#\n" +
            ".#...#.#.#\n" +
            ".#########\n" +
            ".###.#..#.\n" +
            "########.#\n" +
            "##...##.#.\n" +
            "..###.#.#.\n" +
            "\n" +
            "Tile 2971:\n" +
            "..#.#....#\n" +
            "#...###...\n" +
            "#.#.###...\n" +
            "##.##..#..\n" +
            ".#####..##\n" +
            ".#..####.#\n" +
            "#..#.#..#.\n" +
            "..####.###\n" +
            "..#.#.###.\n" +
            "...#.#.#.#\n" +
            "\n" +
            "Tile 2729:\n" +
            "...#.#.#.#\n" +
            "####.#....\n" +
            "..#.#.....\n" +
            "....#..#.#\n" +
            ".##..##.#.\n" +
            ".#.####...\n" +
            "####.#.#..\n" +
            "##.####...\n" +
            "##..#.##..\n" +
            "#.##...##.\n" +
            "\n" +
            "Tile 3079:\n" +
            "#.#.#####.\n" +
            ".#..######\n" +
            "..#.......\n" +
            "######....\n" +
            "####.#..#.\n" +
            ".#...#.##.\n" +
            "#.#####.##\n" +
            "..#.###...\n" +
            "..#.......\n" +
            "..#.###..."

    val example = ".#.#..#.##...#.##..#####\n" +
            "###....#.#....#..#......\n" +
            "##.##.###.#.#..######...\n" +
            "###.#####...#.#####.#..#\n" +
            "##.#....#.##.####...#.##\n" +
            "...########.#....#####.#\n" +
            "....#..#...##..#.#.###..\n" +
            ".####...#..#.....#......\n" +
            "#..#.##..#..###.#.##....\n" +
            "#.####..#.####.#.#.###..\n" +
            "###.#.#...#.######.#..##\n" +
            "#.####....##..########.#\n" +
            "##..##.#...#...#.#.#.#..\n" +
            "...#..#..#.#.##..###.###\n" +
            ".#.#....#.##.#...###.##.\n" +
            "###.#...#..#.##.######..\n" +
            ".#.#.###.##.##.#..#.##..\n" +
            ".####.###.#...###.#..#.#\n" +
            "..#.#..#..#.#.#.####.###\n" +
            "#..####...#.#.#.###.###.\n" +
            "#####..#####...###....##\n" +
            "#.##..#..#...#..####...#\n" +
            ".#.###..##..##..####.##.\n" +
            "...###...##...#...#..###"
}
