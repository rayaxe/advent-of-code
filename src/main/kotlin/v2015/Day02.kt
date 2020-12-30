package v2015

import java.util.Collections.min

fun day02Part1(input: List<String>): Long {
    return input.map { dimensions ->
        val (l, w, h) = dimensions.split("x").map { it.toLong() }
        val lw = l * w
        val wh = w * h
        val hl = h * l
        val surface = 2 * lw + 2 * wh + 2 * hl
        surface + min(listOf(lw, wh, hl))
    }.sum()
}

fun day02Part2(input: List<String>): Long {
    return -1L
}
