package v2020

fun day21Part1(input: List<String>): Long {
    val (ingredients, possibilitiesMap) = parse(input)
    val possibilities = possibilitiesMap.values.flatten().toSet()
    return ingredients.map { if (possibilities.contains(it)) 0L else 1L }.sum()
}

fun day21Part2(input: List<String>): String {
    val (_, possibilitiesMap) = parse(input)
    val found = mutableSetOf<String>()
    while (!possibilitiesMap.values.all { it.size == 1 }) {
        possibilitiesMap.values.forEach { if (it.size == 1) found.add(it.first()) else it.removeAll(found) }
    }
    return possibilitiesMap.toSortedMap().map { it.value.first() }.joinToString(",")
}

private fun parse(input: List<String>): Pair<List<String>, MutableMap<String, MutableSet<String>>> {
    val possibilitiesMap = mutableMapOf<String, MutableSet<String>>()
    val ingredients = input.map { food ->
        val index = food.indexOf("(")
        val ingredients = food.substring(0, index - 1).split(" ")
        val allergens = food.substring(index + 10, food.length - 1).split(", ")
        allergens.forEach {
            if (possibilitiesMap.containsKey(it)) {
                possibilitiesMap[it] = possibilitiesMap.getValue(it).intersect(ingredients).toMutableSet()
            } else {
                possibilitiesMap[it] = ingredients.toMutableSet()
            }
        }
        ingredients
    }.flatten()
    return Pair(ingredients, possibilitiesMap)
}
