fun day2Part1(passwords: List<String>): Int {
    return passwords.stream()
        .map { text -> parseFields(text) }
        .filter { (policy, password) -> isValid(password, policy) }
        .count().toInt()
}

fun day2Part2(passwords: List<String>): Int {
    return passwords.stream()
        .map { text -> parseFields(text) }
        .filter { (policy, password) -> isValidOfficial(password, policy) }
        .count().toInt()
}

private fun isValid(password: String, policy: Triple<Int, Int, String>): Boolean {
    val occurrences = password.count { policy.third.toCharArray().contains(it) }
    return policy.first <= occurrences && occurrences <= policy.second
}

private fun isValidOfficial(password: String, policy: Triple<Int, Int, String>): Boolean {
    val first = policy.third.contains(password[policy.first - 1])
    val second = policy.third.contains(password[policy.second - 1])
    return first.xor(second)
}

private fun parseFields(text: String): Pair<Triple<Int, Int, String>, String> {
    val regex = "([0-9]+)-([0-9]+) ([a-z]): (.*)".toRegex()
    return regex.matchEntire(text)
        ?.destructured
        ?.let { (min, max, letter, password) ->
            Pair(Triple(min.toInt(), max.toInt(), letter), password)
        }
        ?: throw IllegalArgumentException("Bad input '$text'")
}
