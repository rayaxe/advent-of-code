package v2016

class Day19 {
    private class Elf(val number: Int) {
        lateinit var nextElf: Elf
        var numberOfPresents: Int = 1
    }

    companion object {
        fun part1(input: Int): Long {
            var elf = initElves(input)
            while (true) {
                val nextElf = elf.nextElf
                elf.numberOfPresents += nextElf.numberOfPresents
                elf.nextElf = nextElf.nextElf
                if (elf == elf.nextElf) {
                    return elf.number.toLong()
                }
                elf = elf.nextElf
            }
        }

        fun part2(input: Int): Long {
            val left = ArrayDeque<Int>()
            for (i in 1 until (input / 2) + 2) {
                left.addLast(i)
            }
            val right = ArrayDeque<Int>()
            for (i in input downTo (input / 2) + 2) {
                right.addLast(i)
            }
            while (right.isNotEmpty()) {
                if (left.size > right.size) {
                    left.removeLast()
                } else {
                    right.removeLast()
                }
                right.addFirst(left.removeFirst())
                left.addLast(right.removeLast())
            }
            return left.first().toLong()
        }

        private fun initElves(input: Int): Elf {
            var number = 1
            val firstElf = Elf(number)
            var elf = firstElf
            repeat(input - 1) {
                number++
                val nextElf = Elf(number)
                elf.nextElf = nextElf
                elf = nextElf
            }
            elf.nextElf = firstElf
            return firstElf
        }
    }
}
