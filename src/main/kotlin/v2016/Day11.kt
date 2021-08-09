package v2016

class Day11 {
    companion object {
        fun part1(input: List<String>): Long {
            val floors = parseInput(input)
            return numberOfSteps(floors)
        }

        fun part2(input: List<String>): Long {
            val floors = parseInput(input).toMutableList()
            floors[0] = floors[0] + "ELG" + "ELM" + "DIG" + "DIM"
            return numberOfSteps(floors.toList())
        }

        private fun numberOfSteps(floors: List<List<String>>): Long {
            val initialAreaConfig = AreaConfig(floors, 0, 0)
            val visited = mutableSetOf(initialAreaConfig.state)
            val frontier = mutableListOf(initialAreaConfig)
            while (true) {
                frontier.removeFirst().step().forEach {
                    if (it.isFinal()) {
                        return it.steps.toLong()
                    }
                    if (it.state !in visited) {
                        visited.add(it.state)
                        frontier.add(it)
                    }
                }
            }
        }

        private fun parseInput(input: List<String>) = input.map { parseInputLine(it) }

        private fun parseInputLine(inputLine: String): List<String> {
            if ("nothing" in inputLine) {
                return emptyList()
            }
            return inputLine
                .trimEnd('.')
                .split(" a ")
                .drop(1)
                .map { parseObjects(it.split(" ")) }
                .toList()
        }

        private fun parseObjects(objects: List<String>): String {
            val element = objects[0].split("-")[0].take(2).toUpperCase()
            val objectType = objects[1].first().toUpperCase()
            return element + objectType
        }
    }

    private class AreaConfig(val floors: List<List<String>>, val elevatorFloor: Int, val steps: Int) {
        val state = state(floors, elevatorFloor)

        /**
         * A state is a unique area configuration. For example: "G0M0_G0M1_G0M1_G2M2_G2M2_E0".
         */
        private fun state(floors: List<List<String>>, elevatorFloor: Int): String {
            val objectsState = floors.flatMapIndexed { floorNumber: Int, objects: List<String> ->
                objects.map { it.take(2) to (it[2] to floorNumber) }
            }.groupBy({ it.first }, { it.second }).values
                .map { pairs -> pairs.sortedBy { it.first }.let { it[0].second to it[1].second } }
                .sortedWith(compareBy({ it.first }, { it.second }))
                .joinToString("_") { "G" + it.first + "M" + it.second }
            return objectsState + "_E$elevatorFloor"
        }

        fun isFinal(): Boolean = (0..2).all { floors[it].isEmpty() }

        private fun isValid(): Boolean = floors.none { floor ->
            val generators = floor.filter { it.endsWith("G") }
            val microchips = floor.filter { it.endsWith("M") }

            val unshieldedMicrochips =
                microchips.filter { chip -> generators.none { it.dropLast(1) == chip.dropLast(1) } }

            generators.isNotEmpty() && unshieldedMicrochips.isNotEmpty()
        }

        private fun move(elevatorItems: List<String>, targetFloor: Int): AreaConfig {
            val newFloors = floors.mapIndexed { floorNumber, items ->
                when (floorNumber) {
                    elevatorFloor -> items.minus(elevatorItems)
                    targetFloor -> items.plus(elevatorItems)
                    else -> items
                }
            }
            return AreaConfig(newFloors, targetFloor, steps + 1)
        }

        fun step(): List<AreaConfig> {
            val validAreaConfigs = mutableListOf<AreaConfig>()

            fun addValidAreaConfigs(elevatorItemCombinations: List<List<String>>, targetFloor: Int) {
                elevatorItemCombinations.forEach { elevatorItems ->
                    val areaConfig = move(elevatorItems, targetFloor)
                    if (areaConfig.isValid()) {
                        validAreaConfigs.add(areaConfig)
                    }
                }
            }

            val elevatorCombinations = selectElevatorCombinations()
            if (elevatorFloor > 0) {
                addValidAreaConfigs(elevatorCombinations, elevatorFloor - 1)
            }
            if (elevatorFloor < 3) {
                addValidAreaConfigs(elevatorCombinations, elevatorFloor + 1)
            }

            return validAreaConfigs
        }

        private fun selectElevatorCombinations(): List<List<String>> {
            val items = floors[elevatorFloor]
            val singleItems = items.map { listOf(it) }.toList()
            val doubleItems = mutableListOf<List<String>>()
            val remainder = items.toMutableList()
            while (remainder.isNotEmpty()) {
                val head = remainder.removeFirst()
                remainder.forEach { doubleItems.add(listOf(head, it)) }
            }
            return singleItems + doubleItems
        }
    }
}
