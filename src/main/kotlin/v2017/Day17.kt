package v2017

import java.util.*

class Day17 {
    companion object {
        fun part1(steps: Int): Long {
            val list = LinkedList<Int>()
            list.add(0)
            var index = 0
            (1..2017).forEach { value ->
                index = (index + steps) % list.size + 1
                list.add(index, value)
            }
            return list[(index + 1) % list.size].toLong()
        }

        fun part2(steps: Int): Long {
            var result = 0
            var index = 0
            (1..50_000_000).forEach { value ->
                index = (index + steps) % value + 1
                if (index == 1) {
                    result = value
                }
            }
            return result.toLong()
        }
    }
}
