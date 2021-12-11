package v2021

class Day11 {
    companion object {
        private val directions = (-1..1).flatMap { y -> (-1..1).map { x -> y to x } } - (0 to 0)

        fun part1(input: List<String>, steps: Int = 100): Long {
            val octopuses = parse(input)
            return simulate(octopuses, steps)
        }

        fun part2(input: List<String>, steps: Int = 1000): Long {
            val octopuses = parse(input)
            return simulate(octopuses, steps, true)
        }

        private fun simulate(
            octopuses: Array<Array<Long>>,
            steps: Int,
            returnOnSimultaneousFlash: Boolean = false
        ): Long {
            var sum = 0L
            repeat(steps) {
                val flashed = flash(octopuses)

                val numberOfFlashes = flashed.count()
                if (returnOnSimultaneousFlash && numberOfFlashes == octopuses.size * octopuses[0].size) {
                    return it + 1L
                }

                sum += numberOfFlashes

                flashed.forEach { (y, x) -> octopuses[y][x] = 0 }
            }
            return sum
        }

        private fun flash(octopuses: Array<Array<Long>>): MutableList<Pair<Int, Int>> {
            val flashers = increaseAllAndReturnFlashers(octopuses).toMutableList()

            fun flash(flasher: Pair<Int, Int>): List<Pair<Int, Int>> {
                return directions.mapNotNull { (dy, dx) ->
                    val (ny, nx) = (flasher.first + dy) to (flasher.second + dx)
                    if (ny to nx !in flashers && ny in octopuses.indices && nx in octopuses[0].indices) {
                        octopuses.increaseAndReturnOptionalFlasher(ny, nx)
                    } else {
                        null
                    }
                }
            }

            val flashed = mutableListOf<Pair<Int, Int>>()
            while (flashers.isNotEmpty()) {
                val flasher = flashers.removeFirst()
                if (flasher !in flashed) {
                    val newFlashers = flash(flasher)
                    flashers.addAll(newFlashers)
                    flashed.add(flasher)
                }
            }
            return flashed
        }

        private fun increaseAllAndReturnFlashers(octopuses: Array<Array<Long>>): List<Pair<Int, Int>> {
            return octopuses.indices.flatMap { y ->
                octopuses[0].indices.mapNotNull { x ->
                    octopuses.increaseAndReturnOptionalFlasher(y, x)
                }
            }
        }

        private fun Array<Array<Long>>.increaseAndReturnOptionalFlasher(y: Int, x: Int): Pair<Int, Int>? {
            val energy = this[y][x] + 1
            this[y][x] = energy
            return if (energy > 9L) y to x else null
        }

        private fun parse(input: List<String>): Array<Array<Long>> = input
            .map { row ->
                row.map { Character.getNumericValue(it).toLong() }.toTypedArray()
            }.toTypedArray()
    }
}
