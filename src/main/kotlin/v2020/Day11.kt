package v2020

data class Seat(val state: Char, val nextState: Char)

private val directions = listOf(
    Pair(-1, -1), Pair(-1, 0), Pair(-1, 1), Pair(0, -1), Pair(0, 1), Pair(1, -1), Pair(1, 0), Pair(1, 1)
)

fun day11Part1(input: List<String>): Long {
    return simulate(parseSeats(input), ::countOccupiedAdjacent, 4)
}

fun day11Part2(input: List<String>): Long {
    return simulate(parseSeats(input), ::countOccupiedVisible, 5)
}

private fun simulate(
    seats: Array<Array<Seat>>,
    countOccupied: (Array<Array<Seat>>, Int, Int) -> Int,
    tolerance: Int
): Long {
    var hasChanged = true
    while (hasChanged) {
        applySeatRules(seats, countOccupied, tolerance)
        hasChanged = applySeatChanges(seats)
    }
    return seats
        .map { it.count { seat -> seat.state == '#' } }
        .sum()
        .toLong()
}

private fun applySeatRules(
    seats: Array<Array<Seat>>,
    countOccupied: (Array<Array<Seat>>, Int, Int) -> Int,
    tolerance: Int
) {
    for (y in seats.indices) {
        for (x in seats[0].indices) {
            val current = seats[y][x]

            // Floor (.) never changes
            if (current.state == '.') {
                continue
            }

            // Apply provided count function
            val count = countOccupied(seats, x, y)

            // If a seat is empty (L) and no occupied seats are counted, the seat becomes occupied.
            if (current.state == 'L' && count == 0) {
                seats[y][x] = Seat(current.state, '#')
            }

            // If a seat is occupied (#) and an intolerable amount of occupied seats are counted, the seat becomes empty.
            if (current.state == '#' && count >= tolerance) {
                seats[y][x] = Seat(current.state, 'L')
            }
        }
    }
}

private fun applySeatChanges(seats: Array<Array<Seat>>): Boolean {
    var hasChanged = false
    for (y in seats.indices) {
        for (x in seats[0].indices) {
            val current = seats[y][x]
            if (current.state != current.nextState) {
                hasChanged = true
            }
            seats[y][x] = Seat(current.nextState, current.nextState)
        }
    }
    return hasChanged
}

private fun countOccupiedAdjacent(seats: Array<Array<Seat>>, x: Int, y: Int): Int {
    var count = 0
    directions.forEach {
        val adjacentY = y + it.first
        val adjacentX = x + it.second
        if (adjacentY in seats.indices && adjacentX in seats[0].indices) {
            val current = seats[adjacentY][adjacentX].state
            if (current == '#') {
                count++
            }
        }
    }
    return count
}

fun countOccupiedVisible(seats: Array<Array<Seat>>, x: Int, y: Int): Int {
    return directions
        .map { direction -> occupiedSeatIsVisible(seats, x, y, direction) }
        .filter { it }
        .count()
}

private fun occupiedSeatIsVisible(
    seats: Array<Array<Seat>>,
    x: Int,
    y: Int,
    direction: Pair<Int, Int>
): Boolean {
    var nextY = y + direction.first
    var nextX = x + direction.second
    while ((nextY in seats.indices && nextX in seats[0].indices)) {
        if (seats[nextY][nextX].state == '#') {
            return true
        }
        if (seats[nextY][nextX].state == 'L') {
            return false
        }
        nextY += direction.first
        nextX += direction.second
    }
    return false
}

fun parseSeats(input: List<String>): Array<Array<Seat>> {
    return input.map {
        it.toCharArray()
            .map { state -> Seat(state, state) }
            .toTypedArray()
    }.toTypedArray()
}
