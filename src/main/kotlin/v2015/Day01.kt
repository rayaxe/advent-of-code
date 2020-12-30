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
    var basementPosition: Long? = null
    input.foldIndexed(0L) { index, currentFloor, instruction ->
        val nextFloor = when (instruction) {
            '(' -> currentFloor + 1L
            ')' -> currentFloor - 1L
            else -> error("Unexpected instruction: $instruction")
        }
        if (nextFloor < 0L && basementPosition == null) {
            basementPosition = index + 1L
        }
        nextFloor
    }
    return basementPosition!!
}
