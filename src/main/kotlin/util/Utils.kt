package util

fun greatestCommonDivisor(a: Long, b: Long): Long {
    var gcd = 1L
    var i = 1L
    while (i <= a && i <= b) {
        if (a % i == 0L && b % i == 0L) {
            gcd = i
        }
        ++i
    }
    return gcd
}

fun leastCommonMultiple(a: Long, b: Long): Long {
    return a * b / greatestCommonDivisor(a, b)
}
