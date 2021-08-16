package v2016

class Day22 {
    companion object {
        fun part1(input: List<String>): Long {
            val disks = input.drop(2)
                .map { parse(it).let { (_, _, used, avail) -> used to avail } }
            return countPairs(disks) - countSameNodes(disks)
        }

        fun part2(input: List<String>): Long {
            val disks = input.drop(2)
                .map { parse(it) }
                .toList()
                .sortedWith(compareBy({ (_, y, _, _) -> y }, { (x, _, _, _) -> x }))

//            printDisks(disks)

            val moveEmptyDisk = 35L
            val moveDataFromGoalNodeToAccessNode = 5 * 33L
            return moveEmptyDisk + moveDataFromGoalNodeToAccessNode
        }

        private fun printDisks(disks: List<List<Int>>) {
            for ((x, _, used, avail) in disks) {
                val usedPadded = if (used == 0) " _" else if (used > 400) " |" else used.toString().padStart(2)
                val availPadded = avail.toString().padStart(2)
                if (x == 0) println()
                print("$usedPadded/$availPadded ")
            }
            println()
        }

        private fun countPairs(disks: List<Pair<Int, Int>>): Long {
            val used = disks.map { it.first }.sortedBy { it }
            val avail = disks.map { it.second }.sortedBy { it }
            var count = 0L
            var i = used.size
            var j = avail.size
            while (i > 0 && j > 0) {
                if (used[i - 1] in 1..avail[j - 1]) {
                    count += i
                    j--
                } else {
                    i--
                }
            }
            return count
        }

        private fun countSameNodes(disks: List<Pair<Int, Int>>): Int = disks.count { it.first <= it.second }

        private val diskRegex = "/dev/grid/node-x([0-9]+)-y([0-9]+) +[0-9]+T +([0-9]+)T +([0-9]+)T +[0-9]+%".toRegex()

        private fun parse(text: String): List<Int> {
            return diskRegex.matchEntire(text)
                ?.destructured
                ?.let { (x, y, used, avail) -> listOf(x.toInt(), y.toInt(), used.toInt(), avail.toInt()) }
                ?: throw IllegalArgumentException("Bad input '$text'")
        }
    }
}
