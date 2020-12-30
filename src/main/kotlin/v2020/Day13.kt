package v2020

import util.leastCommonMultiple

private data class BusLine(val id: Long, val offset: Long)

fun day13Part1(input: List<String>): Long {
    val timestamp = input.first().toLong()
    return input.drop(1).first().split(',')
        .filter { it != "x" }
        .map { it.toLong() }
        .map { it to it - (timestamp % it) }
        .minByOrNull { (_, waitTime) -> waitTime }!!
        .let { it.first * it.second }
}

fun day13Part2(input: List<String>): Long {
    return input.drop(1).first().split(',')
        .withIndex()
        .filter { it.value != "x" }
        .map { BusLine(it.value.toLong(), it.index.toLong()) }
        .reduce { acc, busLine -> findEarliestTime(acc, busLine) }
        .offset
}

private fun findEarliestTime(current: BusLine, next: BusLine): BusLine {
    var earliestTime = current.offset
    while (earliestTime < Long.MAX_VALUE) {
        val matchesSubsequentMinuteRequirement = 0L == (earliestTime + next.offset) % next.id
        if (matchesSubsequentMinuteRequirement) {
            return BusLine(leastCommonMultiple(current.id, next.id), earliestTime)
        }
        earliestTime += current.id
    }
    throw IllegalStateException("Could not find earliest time")
}
