import java.lang.IllegalArgumentException
import kotlin.streams.toList

fun day5Part1(seats: List<String>): Long {
    return seats.stream()
        .map { findSeatNumber(it) }
        .toList()
        .maxOrNull() ?: 0
}

fun day5Part2(seats: List<String>): Long {
    val numbers = seats
        .map { findSeatNumber(it) }
        .sorted()
    var next = numbers[0]
    for (seat in numbers) {
        if (seat != next) {
            break
        }
        next++
    }
    return next
}

fun findSeatNumber(seat: String): Long {
    val row = find(seat.substring(0..6), 0, 127)
    val col = find(seat.substring(7..9), 0, 7)
    return row * 8L + col
}

private tailrec fun find(tail: String, lower: Int, upper: Int): Int {
    if (tail.isEmpty()) {
        return lower
    }
    val head = tail[0]
    val half = ((upper - lower) / 2) + 1
    val bounds = when (head) {
        'F', 'L' -> Pair(lower, upper - half)
        'B', 'R' -> Pair(lower + half, upper)
        else -> throw IllegalArgumentException("Unexpected character: '$head'")
    }
    return find(tail.substring(1 until tail.length), bounds.first, bounds.second)
}
