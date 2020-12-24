private class Cup(var label: Long) {
    var next: Cup = this
}

fun day23Part1(input: String, moves: Int = 100): String {
    val cups = play(input, moves, false)
    var result = ""
    var current = cups.getValue(1L).next
    repeat(cups.size - 1) { result += current.label; current = current.next }
    return result
}

fun day23Part2(input: String, moves: Int = 10_000_000): Long {
    val cups = play(input, moves, true)
    return cups.getValue(1L).next.label * cups.getValue(1L).next.next.label
}

private fun play(input: String, moves: Int, extend: Boolean): MutableMap<Long, Cup> {
    val firstCups = input.map { Character.getNumericValue(it).toLong() }.toList()
    var firstCup = firstCups.first()
    val cups = mutableMapOf<Long, Cup>()
    var current = Cup(firstCup)
    cups[firstCup] = current
    val prev = current
    for (label in firstCups.drop(1)) {
        current.next = Cup(label)
        current = current.next
        cups[label] = current
        current.next = prev
        if (label > firstCup) {
            firstCup = label
        }
    }
    if (extend) {
        for (i in (firstCup + 1)..1_000_000) {
            current.next = Cup(i)
            current = current.next
            cups[i] = current
        }
    }
    current.next = prev
    current = prev
    var move = 0
    while (move < moves) {
        current = move(cups, current)
        move++
    }
    return cups
}

private fun move(cups: Map<Long, Cup>, current: Cup): Cup {
    val first = current.next
    val last = current.next.next.next
    current.next = last.next
    var destination = current.label - 1
    while (destination < 1 || destination == first.label || destination == first.next.label || destination == last.label) {
        destination = if (destination < 1) cups.size.toLong() else destination - 1
    }
    val destinationCup = cups.getValue(destination)
    last.next = destinationCup.next
    destinationCup.next = first
    return current.next
}
