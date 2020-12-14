import kotlin.math.pow

private val addressAndValueRegex = "mem\\[([0-9]+)] = ([0-9]+)".toRegex()

fun day14Part1(input: List<String>): Long {
    val memory = mutableMapOf<Long, Long>()
    var mask = ""
    input.forEach { line ->
        if (line.substring(0, 4) == "mask") {
            mask = line.substring(7)
        } else {
            val addressAndValue = parse(line)
            val value = addressAndValue.second.toString(2).padStart(mask.length, '0')
            memory[addressAndValue.first] = mask.zip(value) { a, b -> if (a == 'X') b else a }
                .joinToString("")
                .toLong(2)
        }
    }
    return memory.values.sum()
}

fun day14Part2(input: List<String>): Long {
    val memory = mutableMapOf<Long, Long>()
    var mask = ""
    input.forEach { line ->
        if (line.substring(0, 4) == "mask") {
            mask = line.substring(7)
        } else {
            val addressAndValue = parse(line)
            val maskOverride = mask.map { if (it == 'X') '0' else it }.joinToString("").toLong(2)
            val addressOverride = addressAndValue.first.or(maskOverride).toString(2).padStart(mask.length, '0')
            val numberOfFloatingBits = mask.count { it == 'X' }
            val numberOfAddresses = 2.toDouble().pow(numberOfFloatingBits).toInt()
            val floatingBits = (0 until numberOfAddresses).joinToString("") {
                it.toString(2).padStart(numberOfFloatingBits, '0')
            }
            var i = 0
            while (i < numberOfAddresses * numberOfFloatingBits) {
                val address = mask.zip(addressOverride) { a, b -> if (a == 'X') floatingBits[i++] else b }
                    .joinToString("")
                    .toLong(2)
                memory[address] = addressAndValue.second
            }
        }
    }
    return memory.values.sum()
}

private fun parse(text: String): Pair<Long, Long> {
    return addressAndValueRegex.matchEntire(text)
        ?.destructured
        ?.let { (address, value) -> Pair(address.toLong(), value.toLong()) }
        ?: throw IllegalArgumentException("Bad input '$text'")
}
