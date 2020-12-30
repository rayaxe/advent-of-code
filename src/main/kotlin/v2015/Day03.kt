package v2015

fun day03Part1(input: String): Long {
    val houses = mutableMapOf<Pair<Long, Long>, Long>()
    var x = 0L
    var y = 0L
    houses[x to y] = 1L
    input.forEach { move ->
        when (move) {
            '^' -> y += 1
            '>' -> x += 1
            'v' -> y -= 1
            '<' -> x -= 1
            else -> error("Unexpected move: $move")
        }
        val key = x to y
        houses[key] = houses.getOrDefault(key, 0L) + 1L
    }
    return houses.values.count().toLong()
}

fun day03Part2(input: String): Long {
    return -1L
}
