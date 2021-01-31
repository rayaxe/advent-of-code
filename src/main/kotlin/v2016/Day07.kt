package v2016

class Day07 {
    companion object {
        fun part1(input: List<String>): Long {
            return input.count { ip ->
                val (supernets, hypernets) = parse(ip)
                hypernets.none { containsAbba(it) } && supernets.any { containsAbba(it) }
            }.toLong()
        }

        fun part2(input: List<String>): Long {
            return input.count { ip ->
                val (supernets, hypernets) = parse(ip)
                val supernetAbaList = supernets.flatMap { findAbaList(it) }
                val hypernetAbaList = hypernets.flatMap { findAbaList(it, true) }
                supernetAbaList.intersect(hypernetAbaList).isNotEmpty()
            }.toLong()
        }

        private fun containsAbba(sequence: String): Boolean {
            var i = 0
            while (i < sequence.length - 3) {
                val a1 = sequence[i]
                val b1 = sequence[i + 1]
                val b2 = sequence[i + 2]
                val a2 = sequence[i + 3]
                if (a1 == a2 && b1 == b2 && a1 != b1) {
                    return true
                }
                i++
            }
            return false
        }

        private fun findAbaList(sequence: String, invertAba: Boolean = false): List<String> {
            val result = mutableListOf<String>()
            var i = 0
            while (i < sequence.length - 2) {
                val a1 = sequence[i]
                val b1 = sequence[i + 1]
                val a2 = sequence[i + 2]
                if (a1 == a2 && a1 != b1) {
                    if (invertAba) {
                        val aba = "" + b1 + a1 + b1
                        result.add(aba)
                    } else {
                        val aba = sequence.substring(i, i + 3)
                        result.add(aba)
                    }
                }
                i++
            }
            return result.toList()
        }

        private fun parse(input: String): Pair<List<String>, List<String>> {
            val supernets = mutableListOf<String>()
            val hypernets = mutableListOf<String>()
            var ip = input
            while (ip.isNotEmpty()) {
                val hypernetStart = ip.indexOf('[')
                if (hypernetStart == -1) {
                    break
                }
                val hypernetEnd = ip.indexOf(']')
                supernets.add(ip.substring(0, hypernetStart))
                hypernets.add(ip.substring(hypernetStart + 1, hypernetEnd))
                ip = ip.drop(hypernetEnd + 1)
            }
            supernets.add(ip)
            return supernets.toList() to hypernets.toList()
        }
    }
}
