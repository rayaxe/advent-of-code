package v2016

import util.md5
import util.toHex

class Day14 {
    companion object {
        fun part1(salt: String): Long {
            return findIndex(salt, 0)
        }

        fun part2(salt: String): Long {
            return findIndex(salt, 2016)
        }

        private fun findIndex(salt: String, stretchTimes: Int): Long {
            val threes = mutableListOf<Pair<Long, Char>>()
            val fives = mutableMapOf<Char, Long>()

            fun checkHash(index: Long) {
                var hash = md5(salt + index).toHex()
                repeat(stretchTimes) { hash = md5(hash).toHex() }
                var isFirstThree = true
                var i = 0
                while (i < hash.length - 2) {
                    if (hash[i] == hash[i + 1] && hash[i + 1] == hash[i + 2]) {
                        if (isFirstThree) {
                            threes.add(index to hash[i])
                            isFirstThree = false
                        }
                        if (i < hash.length - 5 && hash[i + 2] == hash[i + 3] && hash[i + 3] == hash[i + 4]) {
                            fives[hash[i]] = index
                        }
                        i += 3
                    } else {
                        i += 1
                    }
                }
            }

            fun isKey(index: Long, character: Char): Boolean =
                fives.containsKey(character) && fives.getValue(character) > index - 1000

            var index = 0L
            var numberOfKeys = 0
            while (true) {
                checkHash(index)

                if (threes.isNotEmpty()) {
                    val three = threes.first()
                    val isCurrentIndex = three.first == index - 1000

                    if (isCurrentIndex) {
                        if (isKey(index, three.second)) {
                            numberOfKeys++
                            if (numberOfKeys == 64) {
                                return index - 1000
                            }
                        }
                        threes.removeFirst()
                    }
                }

                index++
            }
        }
    }
}
