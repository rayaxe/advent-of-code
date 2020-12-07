private fun union() = { acc: Set<Char>, set: Set<Char> -> acc union set }
private fun intersect() = { acc: Set<Char>, set: Set<Char> -> acc intersect set }

fun day6Part1(answerGroups: List<List<String>>): Long {
    return sumAnswers(answerGroups, union())

}

fun day6Part2(answerGroups: List<List<String>>): Long {
    return sumAnswers(answerGroups, intersect())
}

private fun sumAnswers(answerGroups: List<List<String>>, operation: (Set<Char>, Set<Char>) -> Set<Char>): Long {
    return answerGroups.map {
        it.map { answerGroup -> answerGroup.toSet() }.reduce(operation).size
    }.sum().toLong()
}
