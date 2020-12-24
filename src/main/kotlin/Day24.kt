fun day24Part1(input: List<String>): Long {
    val directions = mutableListOf<String>()
    var i = 0
    while (i < input.size) {
        val first = input[i]
        when (input[i]) {
            "e" -> directions.add("e")
            "s" -> directions.add("s" + input[i++])
            "w" -> directions.add("w")
            "n" -> directions.add("n" + input[i++])
        }
        i++
    }
    return -1L
}

fun day24Part2(input: List<String>): Long {
    return -1L
}
