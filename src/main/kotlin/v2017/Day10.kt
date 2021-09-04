package v2017

import util.swap

class Day10 {
    companion object {
        fun part1(input: List<String>, size: Int = 256): Long {
            val lengths = input.map { it.toInt() }
            return knotHash(size, lengths).let { (a, b) -> (a * b).toLong() }
        }

        fun part2(input: List<String>, size: Int = 256): String {
            val lengths = input.map { it.first().toInt() } + listOf(17, 31, 73, 47, 23)
            val sparseHash = knotHash(size, lengths, 64)
            val denseHash = sparseHash
                .chunked(16)
                .map { it.reduce { acc, element -> acc xor element } }
            return denseHash.toHex()
        }

        private fun knotHash(size: Int, lengthsInput: List<Int>, rounds: Int = 1): List<Int> {
            val elements = (0 until size).toMutableList()
            var index = 0
            var skipSize = 0
            fun hash() {
                val lengths = lengthsInput.toMutableList()
                while (lengths.isNotEmpty()) {
                    val length = lengths.removeFirst()
                    if (length > size) {
                        continue
                    }
                    var start = index
                    var end = (index + length - 1).modWrapped(size)
                    var reverseSize = length
                    while (reverseSize > 1) {
                        elements.swap(start, end)
                        start = (start + 1).modWrapped(size)
                        end = (end - 1).modWrapped(size)
                        reverseSize -= 2
                    }
                    index = (index + length + skipSize).modWrapped(size)
                    skipSize += 1
                }
            }
            repeat(rounds) { hash() }
            return elements
        }

        private fun Int.modWrapped(size: Int): Int {
            return ((this % size) + size) % size
        }

        private fun List<Int>.toHex() = this.joinToString("") { "%02x".format(it) }
    }
}
