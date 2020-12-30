package v2015

fun day01Part1(input: String): Long {
    return input.fold(0L) { floor, instruction ->
        when (instruction) {
            '(' -> floor + 1L
            ')' -> floor - 1L
            else -> error("Unexpected instruction: $instruction")
        }
    }
}

fun day01Part2(input: String): Long {
    return -1L
}
