package v2015

private class Actor(var x: Long = 0L, var y: Long = 0L) {
    fun key() = Pair(x, y)
}

fun day03Part1(input: String): Long {
    val santa = Actor()
    return visit(input, listOf(santa, santa)).values.count().toLong()
}

fun day03Part2(input: String): Long {
    val santa = Actor()
    val roboSanta = Actor()
    return visit(input, listOf(santa, roboSanta)).values.count().toLong()
}

private fun visit(input: String, actors: List<Actor>): MutableMap<Pair<Long, Long>, Long> {
    val houses = mutableMapOf<Pair<Long, Long>, Long>()
    houses[actors.first().key()] = actors.size.toLong()
    input.forEachIndexed { index, move ->
        val actor = actors[index % actors.size]
        with(actor) {
            when (move) {
                '^' -> y += 1
                '>' -> x += 1
                'v' -> y -= 1
                '<' -> x -= 1
                else -> error("Unexpected move: $move")
            }
        }
        houses[actor.key()] = houses.getOrDefault(actor.key(), 0L) + 1L
    }
    return houses
}
