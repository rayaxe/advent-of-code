package v2015

import kotlin.math.ceil
import kotlin.math.max

class Day21 {
    private data class Item(val description: String, val cost: Int, val damage: Int, val armor: Int)
    private data class Gear(val description: String, val cost: Int, val damage: Int, val armor: Int) {
        companion object {
            operator fun invoke(vararg items: Item): Gear {
                val name = items.joinToString { it.description }
                val cost = items.sumBy { it.cost }
                val damage = items.sumBy { it.damage }
                val armor = items.sumBy { it.armor }
                return Gear(name, cost, damage, armor)
            }
        }
    }

    companion object {
        fun part1(input: List<String>): Long {
            val boss = parseBoss(input)
            gearCombinations().sortedBy { it.cost }.forEach { gear ->
                if (playerWins(gear, boss)) {
                    return gear.cost.toLong()
                }
            }
            error("Unexpected outcome!")
        }

        fun part2(input: List<String>): Long {
            val boss = parseBoss(input)
            gearCombinations().sortedByDescending { it.cost }.forEach { gear ->
                if (!playerWins(gear, boss)) {
                    return gear.cost.toLong()
                }
            }
            error("Unexpected outcome!")
        }

        private fun playerWins(gear: Gear, boss: List<Int>): Boolean {
            val (bossHitPoints, bossDamage, bossArmor) = boss
            val playerRounds = ceil(bossHitPoints.toDouble() / max(gear.damage - bossArmor, 1))
            val bossRounds = ceil(100.0 / max(bossDamage - gear.armor, 1))
            return playerRounds <= bossRounds
        }

        private fun gearCombinations(): List<Gear> {
            val (weapons, armors, rings) = parseItems()
            val combinations = mutableListOf<Gear>()
            for (weapon in weapons) {
                combinations.add(Gear(weapon))
                for (armor in armors) {
                    combinations.add(Gear(weapon, armor))
                    for (ring1 in rings) {
                        combinations.add(Gear(weapon, ring1))
                        combinations.add(Gear(weapon, armor, ring1))
                        for (ring2 in rings) {
                            if (ring1.cost < ring2.cost) {
                                combinations.add(Gear(weapon, ring1, ring2))
                                combinations.add(Gear(weapon, armor, ring1, ring2))
                            }
                        }
                    }
                }
            }
            return combinations
        }

        private fun parseBoss(input: List<String>): List<Int> {
            return input.take(3).map { it.split(": ").last().toInt() }
        }

        private fun parseItems(): List<List<Item>> {
            return itemsText.split("\n\n").map {
                it.split("\n").drop(1).map { line ->
                    val description = line.substring(0, 11).trim()
                    val cost = line.substring(12, 17).trim().toInt()
                    val damage = line.substring(18, 25).trim().toInt()
                    val armor = line.substring(26, 29).trim().toInt()
                    Item(description, cost, damage, armor)
                }
            }
        }

        private const val itemsText = "Weapons:    Cost  Damage  Armor\n" +
                "Dagger        8     4       0\n" +
                "Shortsword   10     5       0\n" +
                "Warhammer    25     6       0\n" +
                "Longsword    40     7       0\n" +
                "Greataxe     74     8       0\n" +
                "\n" +
                "Armor:      Cost  Damage  Armor\n" +
                "Leather      13     0       1\n" +
                "Chainmail    31     0       2\n" +
                "Splintmail   53     0       3\n" +
                "Bandedmail   75     0       4\n" +
                "Platemail   102     0       5\n" +
                "\n" +
                "Rings:      Cost  Damage  Armor\n" +
                "Damage +1    25     1       0\n" +
                "Damage +2    50     2       0\n" +
                "Damage +3   100     3       0\n" +
                "Defense +1   20     0       1\n" +
                "Defense +2   40     0       2\n" +
                "Defense +3   80     0       3"
    }
}
