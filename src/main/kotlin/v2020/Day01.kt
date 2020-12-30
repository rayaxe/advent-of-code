package v2020

fun day01Part1(entries: List<String>): Int {
    val (_, second, third) = find2020(entries, 0)!!
    return second * third
}

fun day01Part2(entries: List<String>): Int {
    val (a, b, c) = entries.stream()
        .map { first -> find2020(entries, first.toInt()) }
        .filter { a -> a != null }
        .map { a -> a!! }
        .filter { (a, b, c) -> a + b + c == 2020 }
        .findFirst().get()
    return a * b * c
}

private fun find2020(entries: List<String>, offset: Int): Triple<Int, Int, Int>? {
    val visited = mutableSetOf<Int>()
    val number = entries.stream()
        .mapToInt { entry -> entry.toInt() }
        .peek { entry -> visited.add(entry) }
        .filter { entry -> visited.contains(2020 - offset - entry) }
        .findFirst()
    return if (number.isPresent) {
        Triple(offset, number.asInt, 2020 - offset - number.asInt)
    } else {
        null
    }
}
