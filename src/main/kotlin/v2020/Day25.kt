package v2020

fun day25Part1(input: String): Long {
    val (doorPublicKey, cardPublicKey) = input.split("\n").map { it.toLong() }
    val doorLoopSize = determineLoopSize(doorPublicKey)
    val cardLoopSize = determineLoopSize(cardPublicKey)
    val encryptionKey1 = transform(doorPublicKey, cardLoopSize)
    val encryptionKey2 = transform(cardPublicKey, doorLoopSize)
    if (encryptionKey1 != encryptionKey2) {
        error("Encryption keys should match")
    }
    return encryptionKey1
}

fun determineLoopSize(publicKey: Long, subjectNumber: Long = 7L): Long {
    var value = 1L
    var loopSize = 1L
    while (loopSize < Integer.MAX_VALUE) {
        value *= subjectNumber
        value %= 20201227
        if (value == publicKey) return loopSize
        loopSize++
    }
    error("Could not determine loop size")
}

fun transform(subjectNumber: Long, loopSize: Long): Long {
    var value = 1L
    var i = 0L
    while (i < loopSize) {
        value *= subjectNumber
        value %= 20201227
        i++
    }
    return value
}
