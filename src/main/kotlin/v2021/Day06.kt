package v2021

class Day06 {
    companion object {
        fun part1(input: String, days: Int = 80): Long {
            val fish = input.split(",").map { it.toInt() }
            return simulate(fish, days)
        }

        fun part2(input: String): Long {
            return part1(input, 256)
        }

        private fun simulate(fish: List<Int>, days: Int): Long {
            var fishMap = fish
                .groupBy { it }
                .mapValues { it.value.size.toLong() }

            repeat(days) {
                val nextDay = fishMap
                    .filterKeys { it > 0 }
                    .mapKeys { it.key - 1 }
                    .toMutableMap()
                val fishReproducing = fishMap.getOrDefault(0, 0)
                if (fishReproducing > 0) {
                    nextDay[6] = nextDay.getOrDefault(6, 0) + fishReproducing
                    nextDay[8] = nextDay.getOrDefault(8, 0) + fishReproducing
                }
                fishMap = nextDay
            }

            return fishMap.values.sum()
        }
    }
}
