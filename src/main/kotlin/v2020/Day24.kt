package v2020

private enum class HexDirection(val dx: Int, val dy: Int, val dz: Int) {
    E(1, -1, 0),
    W(-1, 1, 0),
    SE(0, -1, 1),
    SW(-1, 0, 1),
    NW(0, 1, -1),
    NE(1, 0, -1)
}

private data class HexTile(val x: Int = 0, val y: Int = 0, val z: Int = 0) {
    var isBlack = false
    var willBeBlack = false

    fun flip() {
        this.isBlack = !this.isBlack
    }

    fun update() {
        this.isBlack = this.willBeBlack
    }

    infix fun move(direction: HexDirection): HexTile {
        return with(direction) { HexTile(x + dx, y + dy, z + dz) }
    }
}

fun day24Part1(input: List<String>): Long {
    val tiles = mutableMapOf<HexTile, HexTile>()
    input.forEach { flip(tiles, it) }
    return tiles.count { it.value.isBlack }.toLong()
}

fun day24Part2(input: List<String>): Long {
    val tiles = mutableMapOf<HexTile, HexTile>()
    input.forEach { flip(tiles, it) }
    repeat(100) {
        addMissingNeighbors(tiles)
        tiles.values.forEach { applyRule(tiles, it) }
        tiles.values.forEach { it.update() }
    }
    return tiles.count { it.value.isBlack }.toLong()
}

private fun flip(tiles: MutableMap<HexTile, HexTile>, line: String) {
    var tile = HexTile()
    var i = 0
    while (i < line.length) {
        val direction = when (val char = line[i].toString()) {
            "e" -> HexDirection.E
            "w" -> HexDirection.W
            "s", "n" -> when (val chars = char + line[++i]) {
                "se" -> HexDirection.SE
                "sw" -> HexDirection.SW
                "nw" -> HexDirection.NW
                "ne" -> HexDirection.NE
                else -> error("Unexpected characters: $chars")
            }
            else -> error("Unexpected character: $char")
        }
        tile = tile move direction
        i++
    }
    if (tiles.containsKey(tile)) {
        tiles.getValue(tile).flip()
    } else {
        tiles[tile] = tile.also { tile.isBlack = true }
    }
}

private fun addMissingNeighbors(tiles: MutableMap<HexTile, HexTile>) {
    tiles.values.map { tile ->
        HexDirection.values().mapNotNull {
            val neighbor = tile move it
            if (tiles.containsKey(neighbor)) null else neighbor
        }
    }.flatten().forEach { tiles[it] = it }
}

private fun applyRule(tiles: MutableMap<HexTile, HexTile>, tile: HexTile) {
    val isBlackCount = HexDirection.values().count {
        val neighbor = tile move it
        tiles.getOrDefault(neighbor, HexTile()).isBlack
    }
    tile.willBeBlack = if (tile.isBlack) isBlackCount in 1..2 else isBlackCount == 2
}
