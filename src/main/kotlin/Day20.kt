private data class Tile(val id: Long, var borders: List<String>)

fun day20Part1(input: String): Long {
    val tiles = input.split("\n\n")
        .map { parse(it.split("\n")) }
    val result = tiles.map { it.id to 0L }.toMutableList()
    var i = 0
    while (i < tiles.size) {
        var j = i + 1
        while (j < tiles.size) {
            val matchingBorders = findMatchingBorders(tiles[i].borders, tiles[j].borders)
            if (matchingBorders) {
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
    val tiles = input.split("\n\n")
        .map { parse(it.split("\n")) }
    val result = matchBorders(tiles)
    return -1L
}

private fun matchBorders(tiles: List<Tile>): MutableList<Pair<Long, Long>> {
    val result = tiles.map { it.id to 0L }.toMutableList()
    var i = 0
    while (i < tiles.size) {
        var j = i + 1
        while (j < tiles.size) {
            val matchingBorders = findMatchingBorders(tiles[i].borders, tiles[j].borders)
            if (matchingBorders) {
                result[i] = Pair(result[i].first, result[i].second + 1)
                result[j] = Pair(result[j].first, result[j].second + 1)
            }
            j++
        }
        i++
    }
    return result
}

private fun findMatchingBorders(borders1: List<String>, borders2: List<String>): Boolean {
    return borders1.any { border1 -> borders2.any { border2 -> border1 == border2 } }
}

private fun parse(input: List<String>): Tile {
    val id = input.first().substring(5, 9).toLong()
    val imageInput = input.drop(1)
    val image = Array(10) { Array(10) { '.' } }
    for (y in imageInput.indices) {
        for (x in imageInput[0].indices) {
            image[y][x] = imageInput[y][x]
        }
    }
    val topBorder = image[0].joinToString("")
    val rightBorder = image.fold("") { acc, chars -> acc + chars[9] }
    val bottomBorder = image[9].joinToString("")
    val leftBorder = image.fold("") { acc, chars -> acc + chars[0] }
    val flippedTopBorder = topBorder.reversed()
    val flippedRightBorder = leftBorder.reversed()
    val flippedBottomBorder = bottomBorder.reversed()
    val flippedLeftBorder = rightBorder.reversed()
    return Tile(
        id,
        listOf(
            topBorder,
            rightBorder,
            bottomBorder,
            leftBorder,
            flippedTopBorder,
            flippedRightBorder,
            flippedBottomBorder,
            flippedLeftBorder
        )
    )
}
