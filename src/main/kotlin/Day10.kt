fun day10Part1(input: List<String>): Long {
    return adapters(input)
        .zipWithNext { a, b -> b - a }
        .groupBy { it }
        .let { (it.getValue(1L).size * it.getValue(3L).size).toLong() }
}

fun day10Part2(input: List<String>): Long {
    val adapters: List<Long> = adapters(input)
    val groupSizes = mutableListOf<Long>()
    var prev = 0L
    var count = 1L
    for (i in adapters.indices) {
        val cur = adapters[i]
        if (cur - 1 == prev) {
            count++
        } else {
            groupSizes.add(count)
            count = 1
        }
        prev = cur
    }
    return groupSizes.fold(1L) { acc, size -> acc * factor(size) }
}

private fun adapters(input: List<String>): List<Long> {
    val sortedList = input
        .map { it.toLong() }
        .sorted()
    return listOf(0L) + sortedList + (sortedList.last() + 3L)
}

private fun factor(groupSize: Long): Long {
    return when (groupSize) {
        1L, 2L -> 1L
        3L -> 2L
        4L -> 4L
        5L -> 7L
        else -> throw IllegalArgumentException("Unexpected group size: '$groupSize'")
    }
}
