package v2015

import kotlin.math.max

class Day15 {
    private class Ingredient(
        val capacity: Int,
        val durability: Int,
        val flavor: Int,
        val texture: Int,
        val calories: Int
    )

    companion object {
        fun part1(input: List<String>): Long {
            val ingredients = input.map { parse(it) }
            return bestScore(ingredients).maxByOrNull { it.first }!!.first
        }

        fun part2(input: List<String>): Long {
            val ingredients = input.map { parse(it) }
            return bestScore(ingredients).filter { it.second == 500L }.maxByOrNull { it.first }!!.first
        }

        private fun bestScore(ingredients: List<Ingredient>) =
            combinationsOfSum(ingredients.count())
                .map { combination ->
                    var capacity = 0L
                    var durability = 0L
                    var flavor = 0L
                    var texture = 0L
                    var calories = 0L
                    for (i in combination.indices) {
                        val teaspoons = combination[i].toLong()
                        capacity += teaspoons * ingredients[i].capacity
                        durability += teaspoons * ingredients[i].durability
                        flavor += teaspoons * ingredients[i].flavor
                        texture += teaspoons * ingredients[i].texture
                        calories += teaspoons * ingredients[i].calories
                    }
                    capacity = max(capacity, 0L)
                    durability = max(durability, 0L)
                    flavor = max(flavor, 0L)
                    texture = max(texture, 0L)
                    capacity * durability * flavor * texture to calories
                }

        private fun combinationsOfSum(numberCount: Int, sum: Int = 100): MutableList<List<Int>> {
            if (numberCount !in 2..4) {
                error("Unsupported number count: $numberCount")
            }
            val list = mutableListOf<List<Int>>()
            loopA@ for (a in 0..sum) {
                loopB@ for (b in 0..sum) {
                    if (a + b > sum) continue@loopA
                    loopC@ for (c in 0..sum) {
                        if (a + b + c > sum) continue@loopB
                        for (d in 0..sum) {
                            if (a + b + c + d > sum) continue@loopC
                            if (a + b + c + d == sum) {
                                if (numberCount == 2) list.add(listOf(c, d))
                                if (numberCount == 3) list.add(listOf(b, c, d))
                                if (numberCount == 4) list.add(listOf(a, b, c, d))
                            }
                            if (c == sum && numberCount == 2) {
                                return list
                            }
                            if (b == sum && numberCount == 3) {
                                return list
                            }
                        }
                    }
                }
            }
            return list
        }

        private val ingredientRegex =
            """.*: capacity (-?[0-9]+), durability (-?[0-9]+), flavor (-?[0-9]+), texture (-?[0-9]+), calories (-?[0-9]+)""".toRegex()

        private fun parse(text: String): Ingredient {
            val matchEntire = ingredientRegex.matchEntire(text)
            return matchEntire
                ?.destructured
                ?.let { (capacity, durability, flavor, texture, calories) ->
                    Ingredient(
                        capacity.toInt(),
                        durability.toInt(),
                        flavor.toInt(),
                        texture.toInt(),
                        calories.toInt()
                    )
                }
                ?: throw IllegalArgumentException("Bad input '$text'")
        }
    }
}
