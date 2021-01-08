package v2015

class Day20 {
    companion object {
        fun part1(presentsTarget: Int): Long {
            return visit(presentsTarget, 10) { _: Int, _: Int -> true }
        }

        fun part2(presentsTarget: Int): Long {
            return visit(presentsTarget, 11) { houseNumber: Int, elf: Int -> houseNumber <= elf * 50 }
        }

        private fun visit(presents: Int, presentsFactor: Int, shouldVisit: (Int, Int) -> Boolean): Long {
            val max = 1000000
            val houses = IntArray(max)
            for (elf in 1 until max) {
                var houseNumber = elf
                while (shouldVisit(houseNumber, elf) && houseNumber < max) {
                    houses[houseNumber] += elf * presentsFactor
                    houseNumber += elf
                }
            }
            return houses.indexOfFirst { it >= presents }.toLong()
        }
    }
}
