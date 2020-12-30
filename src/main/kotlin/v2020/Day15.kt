package v2020

fun day15Part1(input: String): Long {
    return playMemoryGame(input, 2020)
}

fun day15Part2(input: String): Long {
    return playMemoryGame(input, 30000000)
}

private fun playMemoryGame(input: String, limit: Int): Long {
    val memory = mutableMapOf<Long, MutableList<Long>>()
    var last = -1L
    var turn = 1L
    input.split(',').forEach {
        val number = it.toLong()
        memory[number] = mutableListOf(turn)
        last = number
        turn++
    }
    while (turn <= limit) {
        // Speak number
        last = if (memory.containsKey(last)) {
            val list = memory[last]!!
            if (list.size == 1) {
                0
            } else {
                list[1] - list[0]
            }
        } else {
            0
        }
        // Update memory
        if (memory.containsKey(last)) {
            var mutableList = memory[last]!!
            if (mutableList.size > 1) {
                mutableList = mutableListOf(mutableList[1], turn)
            } else {
                mutableList.add(turn)
            }
            memory[last] = mutableList
        } else {
            memory[last] = mutableListOf(turn)
        }
        turn++
    }
    return last
}
