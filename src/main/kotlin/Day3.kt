fun day3Part1(map: List<String>): Long {
    return countTrees(map, 3, 1)
}

fun day3Part2(map: List<String>): Long {
    return countTrees(map, 1, 1) *
            countTrees(map, 3, 1) *
            countTrees(map, 5, 1) *
            countTrees(map, 7, 1) *
            countTrees(map, 1, 2)
}

private fun countTrees(map: List<String>, dx: Int, dy: Int): Long {
    val mapWidth = map[0].length
    var x = 0
    var y = 0
    var count = 0L
    while (y < map.size) {
        if (map[y][x] == '#') {
            count++
        }
        x += dx
        x %= mapWidth
        y += dy
    }
    return count
}
