import kotlin.math.sqrt

data class Tile(
    val id: Long,
    var borders: List<Border>,
    var isCorner: Boolean = false,
    var matches: MutableList<Match> = mutableListOf()
) {
    var image: Array<Array<Char>> = Array(10) { Array(10) { '.' } }
    var newImage: Array<Array<Char>> = Array(10) { Array(10) { '.' } }
}

data class Match(val borderFrom: Border, var tileTo: Tile, var borderTo: Border)
data class Border(val orientation: Orientation, val border: String)

enum class Orientation(val isFlipped: Boolean = false) {
    UP, RIGHT, DOWN, LEFT, UP_FLIPPED(true), RIGHT_FLIPPED(true), DOWN_FLIPPED(true), LEFT_FLIPPED(true);

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
    tiles.forEach { if (it.matches.size == 4) it.isCorner = true }
    val puzzle = assemble(tiles)
    for (y in puzzle.indices) {
        for (x in puzzle[0].indices) {
            val (orientation, tile) = puzzle[y][x]
            tile.newImage = rotate(tile.image, orientation)
            tile.newImage = crop(tile.newImage)
        }
    }
    val newImage = Array(puzzle.size * 8) { Array(puzzle.size * 8) { '.' } }
    for (j in puzzle.indices) {
        for (i in puzzle[0].indices) {
            val tileImage = puzzle[j][i].second.newImage
            for (y in tileImage.indices) {
                for (x in tileImage[0].indices) {
                    newImage[y + 8 * j][x + 8 * i] = tileImage[y][x]
                }
            }
        }
    }
    return Orientation.values()
        .map { rotate(newImage, it) }
        .map { determineWaterRoughness(it) }
        .minOrNull()!!
}

private const val seaMonsterPattern = "" +
        "                  # \n" +
        "#    ##    ##    ###\n" +
        " #  #  #  #  #  #   "
private val seaMonsterImage = stringToImage(seaMonsterPattern.split("\n"))

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

private fun assemble(tiles: List<Tile>): Array<Array<Pair<Orientation, Tile>>> {
    val puzzleWidth = sqrt(tiles.size.toDouble()).toInt()
    val puzzle = Array(puzzleWidth) { Array(puzzleWidth) { Orientation.UP to Tile(-1L, listOf()) } }

    var currentTile = tiles.first { it.isCorner }
    var currentMatch = findTopLeftCornerMatch(currentTile.matches)
    puzzle[0][0] = currentMatch.borderFrom.orientation.rotate().rotate().rotate() to currentTile

    for (y in puzzle.indices) {
        for (x in puzzle[0].indices) {
            if (x == 0) {
                if (y == 0) {
                    continue
                }
                val (prevOrientation, prevTile) = puzzle[y - 1][x]
                currentMatch = prevTile.matches.first { it.borderFrom.orientation == prevOrientation.rotate().rotate() }
                currentTile = currentMatch.tileTo
                puzzle[y][x] = currentMatch.borderTo.orientation to currentTile
                currentMatch =
                    currentTile.matches.first { it.borderFrom.orientation == currentMatch.borderTo.orientation.rotate() }
            } else {
                currentTile = currentMatch.tileTo
                puzzle[y][x] = currentMatch.borderTo.orientation.rotate() to currentTile
                if (x < puzzleWidth - 1) {
                    currentMatch =
                        currentTile.matches.first { it.borderFrom.orientation == currentMatch.borderTo.orientation.rotate().rotate() }
                }
            }
        }
    }
    return puzzle
}

private fun findTopLeftCornerMatch(borders: MutableList<Match>): Match {
    val (match1, match2) = borders.filter { match -> match.borderFrom.orientation.isFlipped }
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

private fun rotate(image: Array<Array<Char>>, target: Orientation = Orientation.UP): Array<Array<Char>> {
    return when (target) {
        Orientation.UP -> image
        Orientation.RIGHT -> rotateImageRight(image)
        Orientation.DOWN -> rotateImageDown(image)
        Orientation.LEFT -> rotateImageLeft(image)
        Orientation.UP_FLIPPED -> rotateImageUpFlipped(image)
        Orientation.LEFT_FLIPPED -> rotateImageLeftFlipped(image)
        Orientation.DOWN_FLIPPED -> rotateImageDownFlipped(image)
        Orientation.RIGHT_FLIPPED -> rotateImageRightFlipped(image)
    }
}

fun rotateImageRight(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[x][image.size - y - 1] = image[y][x]
    return newImage
}

fun rotateImageDown(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[image.size - y - 1][image.size - x - 1]
    return newImage
}

fun rotateImageLeft(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[x][image.size - y - 1]
    return newImage
}

fun rotateImageUpFlipped(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[y][image.size - x - 1]
    return newImage
}

fun rotateImageLeftFlipped(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[x][y]
    return newImage
}

fun rotateImageDownFlipped(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[image.size - y - 1][x]
    return newImage
}

fun rotateImageRightFlipped(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size) { Array(image[0].size) { '.' } }
    for (y in image.indices) for (x in image[0].indices) newImage[y][x] = image[image.size - x - 1][image.size - y - 1]
    return newImage
}

fun crop(image: Array<Array<Char>>): Array<Array<Char>> {
    val newImage = Array(image.size - 2) { Array(image[0].size - 2) { '.' } }
    for (y in 0..7) {
        for (x in 0..7) {
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
    ).also { it.image = image }
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
