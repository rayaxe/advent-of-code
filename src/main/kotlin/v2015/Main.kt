package v2015

import java.io.File
import java.nio.charset.Charset

fun main() {
//    println(day01Part1(readLinesFromFile("day01.txt").first()))
//    println(day01Part2(readLinesFromFile("day01.txt").first()))
//    println(day02Part1(readLinesFromFile("day02.txt")))
//    println(day02Part2(readLinesFromFile("day02.txt")))
//    println(day03Part1(readLinesFromFile("day03.txt").first()))
    println(day03Part2(readLinesFromFile("day03.txt").first()))
//    println(day04Part1(readLinesFromFile("day04.txt")))
//    println(day04Part2(readLinesFromFile("day04.txt")))
//    println(day05Part1(readLinesFromFile("day05.txt")))
//    println(day05Part2(readLinesFromFile("day05.txt")))
//    println(day06Part1(readLinesFromFile("day06.txt")))
//    println(day06Part2(readLinesFromFile("day06.txt")))
//    println(day07Part1(readLinesFromFile("day07.txt")))
//    println(day07Part2(readLinesFromFile("day07.txt")))
//    println(day08Part1(readLinesFromFile("day08.txt")))
//    println(day08Part2(readLinesFromFile("day08.txt")))
//    println(day09Part1(readLinesFromFile("day09.txt")))
//    println(day09Part2(readLinesFromFile("day09.txt")))
//    println(day10Part1(readLinesFromFile("day10.txt")))
//    println(day10Part2(readLinesFromFile("day10.txt")))
//    println(day11Part1(readLinesFromFile("day11.txt")))
//    println(day11Part2(readLinesFromFile("day11.txt")))
//    println(day12Part1(readLinesFromFile("day12.txt")))
//    println(day12Part2(readLinesFromFile("day12.txt")))
//    println(day13Part1(readLinesFromFile("day13.txt")))
//    println(day13Part2(readLinesFromFile("day13.txt")))
//    println(day14Part1(readLinesFromFile("day14.txt")))
//    println(day14Part2(readLinesFromFile("day14.txt")))
//    println(day15Part1(readLinesFromFile("day15.txt")))
//    println(day15Part2(readLinesFromFile("day15.txt")))
//    println(day16Part1(readLinesFromFile("day16.txt")))
//    println(day16Part2(readLinesFromFile("day16.txt")))
//    println(day17Part1(readLinesFromFile("day17.txt")))
//    println(day17Part2(readLinesFromFile("day17.txt")))
//    println(day18Part1(readLinesFromFile("day18.txt")))
//    println(day18Part2(readLinesFromFile("day18.txt")))
//    println(day19Part1(readLinesFromFile("day19.txt")))
//    println(day19Part2(readLinesFromFile("day19.txt")))
//    println(day20Part1(readLinesFromFile("day20.txt")))
//    println(day20Part2(readLinesFromFile("day20.txt")))
//    println(day21Part1(readLinesFromFile("day21.txt")))
//    println(day21Part2(readLinesFromFile("day21.txt")))
//    println(day22Part1(readLinesFromFile("day22.txt")))
//    println(day22Part2(readLinesFromFile("day22.txt")))
//    println(day23Part1(readLinesFromFile("day23.txt")))
//    println(day23Part2(readLinesFromFile("day23.txt")))
//    println(day24Part1(readLinesFromFile("day24.txt")))
//    println(day24Part2(readLinesFromFile("day24.txt")))
//    println(day25Part1(readLinesFromFile("day25.txt")))
//    println(day25Part2(readLinesFromFile("day25.txt")))
}

private fun readLinesFromFile(filename: String) = File("src//main//resources//v2015//$filename")
    .readLines()

private fun readText(filename: String) = File("src//main//resources//v2015//$filename")
    .readText(Charset.forName("UTF-8"))

private fun readAndSplitByEmptyLines(filename: String) = File("src//main//resources//v2015//$filename")
    .readText(Charset.forName("UTF-8"))
    .split("\n\n")
    .map { it.split("\n") }
