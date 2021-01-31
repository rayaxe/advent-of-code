package v2020

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class Day20KtTest {

    @Test
    fun testDay20Part1() {
        assertEquals(20899048083289L, day20Part1(input))
    }

    @Test
    fun testDay20Part2() {
        assertEquals(273L, day20Part2(input))
    }

    @Test
    fun testParseTileBorders() {
        val input = "Tile 0000:\n" +
                "ABC\n" +
                "DEF\n" +
                "GHI"
        val tile = parseTile(input.split("\n"))
        val border = { orientation: Orientation -> tile.borders.first { it.orientation == orientation }.border }
        assertEquals("ABC", border(Orientation.UP))
        assertEquals("CFI", border(Orientation.RIGHT))
        assertEquals("IHG", border(Orientation.DOWN))
        assertEquals("GDA", border(Orientation.LEFT))
        assertEquals("CBA", border(Orientation.UP_FLIPPED))
        assertEquals("ADG", border(Orientation.RIGHT_FLIPPED))
        assertEquals("GHI", border(Orientation.DOWN_FLIPPED))
        assertEquals("IFC", border(Orientation.LEFT_FLIPPED))
    }

    @Test
    fun testDetermineWaterRoughness() {
        val image = stringToImage(example.split("\n"))
        val newImage = rotateRightFlippedBorderUp(image)
        assertEquals(273L, determineWaterRoughness(newImage))
    }

    @ParameterizedTest(name = "Rotate {0} {1} should return {2}")
    @MethodSource("provideImageRotationExamples")
    fun testImageRotation(image: Array<Array<Char>>, orientation: Orientation, expected: Array<Array<Char>>) {
        val actual = rotate(image, orientation)
        assertEquals(imageToString(expected), imageToString(actual))
    }

    companion object {
        @JvmStatic
        fun provideImageRotationExamples(): Stream<Arguments> {
            val toImage = { input: String -> stringToImage(input.split("\n")) }
            val image = toImage("ABC\nDEF\nGHI")
            return Stream.of(
                Arguments.of(image, Orientation.UP, toImage("ABC\nDEF\nGHI")),
                Arguments.of(image, Orientation.LEFT, toImage("GDA\nHEB\nIFC")),
                Arguments.of(image, Orientation.DOWN, toImage("IHG\nFED\nCBA")),
                Arguments.of(image, Orientation.RIGHT, toImage("CFI\nBEH\nADG")),
                Arguments.of(image, Orientation.UP_FLIPPED, toImage("CBA\nFED\nIHG")),
                Arguments.of(image, Orientation.LEFT_FLIPPED, toImage("IFC\nHEB\nGDA")),
                Arguments.of(image, Orientation.DOWN_FLIPPED, toImage("GHI\nDEF\nABC")),
                Arguments.of(image, Orientation.RIGHT_FLIPPED, toImage("ADG\nBEH\nCFI"))
            )
        }
    }

    private val input = "Tile 2311:\n" +
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

    private val example = ".#.#..#.##...#.##..#####\n" +
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
