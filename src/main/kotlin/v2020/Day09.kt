package v2020

fun day09Part1(input: List<String>, preamble: Int): Long {
    var numbers = input.map { it.toLong() }.toMutableList()
    while (true) {
        val sums = sums(numbers.take(preamble))
        val current = numbers[preamble]
        if (!sums.contains(current)) {
            return current
        }
        numbers = numbers.subList(1, numbers.size)
    }
}

private fun sums(numbers: List<Long>): Set<Long> {
    val sums = mutableSetOf<Long>()
    for (i in numbers.indices) {
        for (j in i + 1 until numbers.size) {
            sums.add(numbers[i] + numbers[j])
        }
    }
    return sums
}

fun day09Part2(input: List<String>, invalidNumber: Long): Long {
    var numbers = input.map { it.toLong() }.toMutableList()
    var windowMin = 0
    var windowMax = 1
    var sum = 0L
    while (true) {
        when {
            sum < invalidNumber -> {
                sum += numbers[windowMax]
                windowMax++
            }
            sum > invalidNumber -> {
                numbers = numbers.subList(1, numbers.size)
                windowMin = 0
                windowMax = 1
                sum = 0
            }
            else -> {
                val window = numbers.subList(windowMin, windowMax - 1)
                return window.minOrNull()!! + window.maxOrNull()!!
            }
        }
    }
}
