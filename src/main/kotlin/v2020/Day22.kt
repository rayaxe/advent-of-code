package v2020

fun day22Part1(input: List<String>): Long {
    var (deck1, deck2) = parseDecks(input)
    while (deck1.isNotEmpty() && deck2.isNotEmpty()) {
        val first1 = deck1.first()
        val first2 = deck2.first()
        deck1 = deck1.drop(1).toMutableList()
        deck2 = deck2.drop(1).toMutableList()
        if (first1 > first2) {
            deck1.add(first1)
            deck1.add(first2)
        } else {
            deck2.add(first2)
            deck2.add(first1)
        }
    }
    return if (deck1.isNotEmpty()) score(deck1) else score(deck2)
}

fun day22Part2(input: List<String>): Long {
    val (startDeck1, startDeck2) = parseDecks(input)
    val (_, deck1, deck2) = play(startDeck1, startDeck2)
    return if (deck1.isNotEmpty()) score(deck1) else score(deck2)
}

private fun play(startDeck1: List<Long>, startDeck2: List<Long>): Triple<Boolean, List<Long>, List<Long>> {
    var deck1 = startDeck1
    var deck2 = startDeck2
    val playedRounds = mutableListOf<Pair<List<Long>, List<Long>>>()
    while (deck1.isNotEmpty() && deck2.isNotEmpty()) {
        if (playedRounds.any { (prevDeck1, prevDeck2) -> prevDeck1 == deck1 && prevDeck2 == deck2 }) {
            return Triple(true, deck1, deck2)
        }
        playedRounds.add(deck1 to deck2)
        val first1 = deck1.first()
        val first2 = deck2.first()
        deck1 = deck1.drop(1).toMutableList()
        deck2 = deck2.drop(1).toMutableList()
        if (first1 <= deck1.size && first2 <= deck2.size) {
            val (isWinner, subGameDeck1, _) = play(deck1.take(first1.toInt()), deck2.take(first2.toInt()))
            if (isWinner || subGameDeck1.isNotEmpty()) {
                deck1.add(first1)
                deck1.add(first2)
            } else {
                deck2.add(first2)
                deck2.add(first1)
            }
        } else if (first1 > first2) {
            deck1.add(first1)
            deck1.add(first2)
        } else {
            deck2.add(first2)
            deck2.add(first1)
        }
    }
    return Triple(false, deck1, deck2)
}

private fun parseDecks(input: List<String>): Pair<List<Long>, List<Long>> {
    var lines = input.drop(1).toMutableList()
    val deck1 = mutableListOf<Long>()
    val deck2 = mutableListOf<Long>()
    var first = true
    while (lines.isNotEmpty()) {
        val line = lines.first()
        lines = lines.drop(1).toMutableList()
        when {
            line.isEmpty() -> {
                first = false
                lines = lines.drop(1).toMutableList()
            }
            first -> deck1.add(line.toLong())
            else -> deck2.add(line.toLong())
        }
    }
    return Pair(deck1, deck2)
}

private fun score(deck: List<Long>): Long {
    var factor = 1L
    return deck.reversed().fold(0L) { acc, card -> acc + card * factor++ }
}
