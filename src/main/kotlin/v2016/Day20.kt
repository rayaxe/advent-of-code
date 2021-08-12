package v2016

import kotlin.math.max
import kotlin.math.min

class Day20 {
    companion object {
        fun part1(input: List<String>): Long {
            return mergeIfOverlapping(parse(input))
                .fold(0L) { acc, range -> if (acc < range.first) acc else range.last + 1L }
        }

        fun part2(input: List<String>, max: Long = 4294967295L): Long {
            val ranges = mergeIfOverlapping(parse(input)) + listOf((max + 1L)..(max + 1L))
            val initial = ranges.first().first to ranges.first().last + 1
            return ranges
                .drop(1)
                .fold(initial) { (sum, last), range -> sum + (range.first - last) to range.last + 1 }
                .first
        }

        private fun parse(input: List<String>): List<LongRange> {
            return input
                .map { it.split("-").let { (start, end) -> start.toLong()..end.toLong() } }
                .sortedBy { it.first }
        }

        private fun mergeIfOverlapping(ranges: List<LongRange>): List<LongRange> {
            val initial = mutableListOf<LongRange>() to ranges.first()
            return ranges
                .drop(1)
                .fold(initial) { (acc, prev), next -> mergeIfOverlapping(acc, prev, next) }
                .also { (acc, prev) -> acc.add(prev) }
                .let { (acc, _) -> acc }
        }

        private fun mergeIfOverlapping(acc: MutableList<LongRange>, prev: LongRange, next: LongRange) =
            if (prev.last >= next.first) {
                acc to min(prev.first, next.first)..max(prev.last, next.last)
            } else {
                acc.add(prev)
                acc to next
            }
    }
}
