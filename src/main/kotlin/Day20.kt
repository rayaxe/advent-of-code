import kotlin.math.sqrt

data class Tile(val id: Long, var borders: List<Border>, var matches: MutableList<Match> = mutableListOf()) {
    var image: Array<Array<Char>> = Array(1) { Array(1) { '.' } }
}

data class Match(val borderFrom: Border, var tileTo: Tile, var borderTo: Border)
data class Border(val orientation: Orientation, val border: String)

enum class Orientation {
    UP, RIGHT, DOWN, LEFT, UP_FLIPPED, RIGHT_FLIPPED, DOWN_FLIPPED, LEFT_FLIPPED;

    fun rotate(): Orientation {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
            UP_FLIPPED -> RIGHT_FLIPPED
            RIGHT_FLIPPED -> DOWN_FLIPPED
            DOWN_FLIPPED -> LEFT_FLIPPED
            LEFT_FLIPPED -> UP_FLIPPED
        }
    }
}

fun day20Part1(input: String): Long {
    val tiles = input.split("\n\n").map { parseTile(it.split("\n")) }
    val result = tiles.map { it.id to 0L }.toMutableList()
    var i = 0
    while (i < tiles.size) {
        var j = i + 1
        while (j < tiles.size) {
            val matchingBorders = findMatchingBorders(tiles[i], tiles[j])
            if (matchingBorders.first.isNotEmpty()) {
                result[i] = Pair(result[i].first, result[i].second + 1)
                result[j] = Pair(result[j].first, result[j].second + 1)
            }
            j++
        }
        i++
    }
    return result.filter { it.second == 2L }
        .map { it.first }
        .fold(1L) { acc, id -> acc * id }
}

fun day20Part2(input: String): Long {
    val tiles = input.split("\n\n").map { parseTile(it.split("\n")) }
    findAndPopulateBorderMatches(tiles)
    val puzzle = lineUp(tiles)
    val image = assemble(puzzle)
    return Orientation.values()
        .map { rotate(image, it) }
        .map { determineWaterRoughness(it) }
        .minOrNull()!!
}

private fun assemble(puzzle: Array<Array<Pair<Orientation, Tile>>>): Array<Array<Char>> {
    val newImage = Array(puzzle.size * 8) { Array(puzzle.size * 8) { '.' } }
    for (j in puzzle.indices) {
        for (i in puzzle[0].indices) {
            val (orientation, tile) = puzzle[j][i]
            val tileImage = rotate(tile.image, orientation)
            for (y in tileImage.indices) {
                for (x in tileImage[0].indices) {
                    newImage[y + 8 * j][x + 8 * i] = tileImage[y][x]
                }
            }
        }
    }
    return newImage
}

private const val seaMonster = "" +
        "                  # \n" +
        "#    ##    ##    ###\n" +
        " #  #  #  #  #  #   "
private val seaMonsterImage = stringToImage(seaMonster.split("\n"))

fun determineWaterRoughness(image: Array<Array<Char>>): Long {
    for (y in 0..(image.size - seaMonsterImage.size)) {
        for (x in 0..(image[0].size - seaMonsterImage[0].size)) {
            var isFound = true
            val coordinates = mutableListOf<Pair<Int, Int>>()
            for (j in seaMonsterImage.indices) {
                for (i in seaMonsterImage[0].indices) {
                    val seaMonsterChar = seaMonsterImage[j][i]
                    if (seaMonsterChar == '#') {
                        if (image[y + j][x + i] == '#' || image[y + j][x + i] == 'O') {
                            coordinates.add(y + j to x + i)
                        } else {
                            isFound = false
                        }
                    }
                }
            }
            if (isFound) {
                coordinates.forEach { (j, i) -> image[j][i] = 'O' }
            }
        }
    }

    var count = 0L
    for (y in image.indices) {
        for (x in image[0].indices) {
            if (image[y][x] == '#') {
                count++
            }
        }
    }
    return count
}

private fun lineUp(tiles: List<Tile>): Array<Array<Pair<Orientation, Tile>>> {
    val puzzleWidth = sqrt(tiles.size.toDouble()).toInt()
    val puzzle = Array(puzzleWidth) { Array(puzzleWidth) { Orientation.UP to Tile(-1L, listOf()) } }

    var tile = tiles.first { it.matches.size == 4 }
    var nextMatch = findTopLeftCornerMatch(tile.matches)
    puzzle[0][0] = nextMatch.borderFrom.orientation.rotate().rotate().rotate() to tile

    for (y in puzzle.indices) {
        for (x in puzzle[0].indices) {
            if (x == 0) {
                if (y == 0) {
                    // Skip start tile
                    continue
                }
                // Find and add first row tile
                val (prevOrientation, prevTile) = puzzle[y - 1][x]
                nextMatch = prevTile.matches.first { it.borderFrom.orientation == prevOrientation.rotate().rotate() }
                tile = nextMatch.tileTo
                puzzle[y][x] = nextMatch.borderTo.orientation to tile
                // Find next match
                nextMatch = tile.matches.first { it.borderFrom.orientation == nextMatch.borderTo.orientation.rotate() }
            } else {
                // Add other row tiles
                tile = nextMatch.tileTo
                puzzle[y][x] = nextMatch.borderTo.orientation.rotate() to tile
                // Find next match, except for last row tile
                if (x < puzzleWidth - 1) {
                    nextMatch =
                        tile.matches.first { it.borderFrom.orientation == nextMatch.borderTo.orientation.rotate().rotate() }
                }
            }
        }
    }
    return puzzle
}

private fun findTopLeftCornerMatch(borders: MutableList<Match>): Match {
    val (match1, match2) = borders
    return if (match1.borderFrom.orientation.rotate() == match2.borderFrom.orientation) match1 else match2
}

private fun findAndPopulateBorderMatches(tiles: List<Tile>) {
    var i = 0
    while (i < tiles.size) {
        var j = i + 1
        while (j < tiles.size) {
            val (matchingBorders1, matchingBorders2) = findMatchingBorders(tiles[i], tiles[j])
            tiles[i].matches.addAll(matchingBorders1)
            tiles[j].matches.addAll(matchingBorders2)
            j++
        }
        i++
    }
}

private fun findMatchingBorders(
    tile1: Tile,
    tile2: Tile
): Pair<MutableList<Match>, MutableList<Match>> {
    val matchingBorders1 = mutableListOf<Match>()
    val matchingBorders2 = mutableListOf<Match>()
    for (border1 in tile1.borders) {
        for (border2 in tile2.borders) {
            if (border1.border == border2.border.reversed()) {
                matchingBorders1.add(Match(border1, tile2, border2))
                matchingBorders2.add(Match(border2, tile1, border1))
            }
        }
    }
    return matchingBorders1 to matchingBorders2
}

fun rotate(image: Array<Array<Char>>, up: Orientation = Orientation.UP): Array<Array<Char>> {
    return when (up) {
        Orientation.UP -> image
        Orientation.LEFT -> rotateLeftBorderUp(image)
        Orientation.DOWN -> rotateDownBorderUp(image)
        Orientation.RIGHT -> rotateRightBorderUp(image)
        Orientation.UP_FLIPPED -> rotateUpFlippedBorderUp(image)
        Orientation.LEFT_FLIPPED -> rotateLeftFlippedBorderUp(image)
        Orientation.DOWN_FLIPPED -> rotateDownFlippedBorderUp(image)
        Orientation.RIGHT_FLIPPED -> rotateRightFlippedBorderUp(image)
    }
}

fun rotateLeftBorderUp(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[x][image.size - y - 1] = image[y][x]
    return newImage
}

fun rotateDownBorderUp(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[image.size - y - 1][image.size - x - 1]
    return newImage
}

fun rotateRightBorderUp(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[x][image.size - y - 1]
    return newImage
}

fun rotateUpFlippedBorderUp(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[y][image.size - x - 1]
    return newImage
}

fun rotateLeftFlippedBorderUp(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[image.size - x - 1][image.size - y - 1]
    return newImage
}

fun rotateDownFlippedBorderUp(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[image.size - y - 1][x]
    return newImage
}

fun rotateRightFlippedBorderUp(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[x][y]
    return newImage
}

fun crop(image: Array<Array<Char>>): Array<Array<Char>> {
    val height = image.size - 2
    val width = image[0].size - 2
    val newImage = Array(height) { Array(width) { '.' } }
    for (y in 0 until height) {
        for (x in 0 until width) {
            newImage[y][x] = image[y + 1][x + 1]
        }
    }
    return newImage
}

fun parseTile(input: List<String>): Tile {
    val id = input.first().substring(5, 9).toLong()
    val imageInput = input.drop(1)
    val imageWidth = imageInput[0].length
    val image = stringToImage(imageInput)
    val upBorder = image[0].joinToString("")
    val rightBorder = image.fold("") { acc, chars -> acc + chars[imageWidth - 1] }
    val downBorder = image[imageWidth - 1].joinToString("").reversed()
    val leftBorder = image.fold("") { acc, chars -> acc + chars[0] }.reversed()
    val upFlippedBorder = upBorder.reversed()
    val rightFlippedBorder = leftBorder.reversed()
    val downFlippedBorder = downBorder.reversed()
    val leftFlippedBorder = rightBorder.reversed()
    return Tile(
        id, listOf(
            Border(Orientation.UP, upBorder),
            Border(Orientation.RIGHT, rightBorder),
            Border(Orientation.DOWN, downBorder),
            Border(Orientation.LEFT, leftBorder),
            Border(Orientation.UP_FLIPPED, upFlippedBorder),
            Border(Orientation.RIGHT_FLIPPED, rightFlippedBorder),
            Border(Orientation.DOWN_FLIPPED, downFlippedBorder),
            Border(Orientation.LEFT_FLIPPED, leftFlippedBorder)
        )
    ).also { it.image = crop(image) }
}

fun stringToImage(string: List<String>): Array<Array<Char>> {
    val image = Array(string.size) { Array(string[0].length) { '.' } }
    for (y in string.indices) {
        for (x in string[0].indices) {
            image[y][x] = string[y][x]
        }
    }
    return image
}

fun imageToString(image: Array<Array<Char>>): String {
    return image.joinToString("\n") { it.joinToString("") }
}
