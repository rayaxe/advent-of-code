package util

import java.security.MessageDigest

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

fun <V> Collection<V>.permutations(): List<List<V>> {
    val result: MutableList<List<V>> = mutableListOf()
    fun generate(count: Int, list: MutableList<V>) {
        if (count == 1) {
            result.add(list.toList())
        } else {
            for (i in 0 until count) {
                generate(count - 1, list)
                if (count % 2 == 0) {
                    list.swap(i, count - 1)
                } else {
                    list.swap(0, count - 1)
                }
            }
        }
    }
    generate(count(), toMutableList())
    return result
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(Charsets.UTF_8))
fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }
