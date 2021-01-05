package v2015

class Day17 {
    companion object {
        fun part1(input: List<String>, targetLiters: Int = 150): Long {
            val containers = input.map { it.toInt() }
            val combinations = combinations(containers, targetLiters)
            return combinations.size.toLong()
        }

        fun part2(input: List<String>, targetLiters: Int = 150): Long {
            val containers = input.map { it.toInt() }
            val combinations = combinations(containers, targetLiters)
            val minimum = combinations.minOrNull()!!
            return combinations.count { it == minimum }.toLong()
        }

        private fun combinations(containers: List<Int>, targetLiters: Int): List<Int> {
            val combinations = mutableListOf<Int>()
            fun fill(containers: List<Int>, liters: Int, count: Int) {
                if (liters == 0) {
                    combinations.add(count)
                } else if (liters > 0 && containers.isNotEmpty()) {
                    val tail = containers.drop(1)
                    fill(tail, liters - containers.first(), count + 1)
                    fill(tail, liters, count)
                }
            }
            fill(containers, targetLiters, 0)
            return combinations
        }
    }
}
