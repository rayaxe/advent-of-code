package v2016

import java.io.File
import java.nio.charset.Charset

fun main() {
//    println(Day01.part1(readLinesFromFile("day01.txt").first().split(", ")))
//    println(Day01.part2(readLinesFromFile("day01.txt").first().split(", ")))
//    println(Day02.part1(readLinesFromFile("day02.txt")))
//    println(Day02.part2(readLinesFromFile("day02.txt")))
//    println(Day03.part1(readLinesFromFile("day03.txt")))
//    println(Day03.part2(readLinesFromFile("day03.txt")))
//    println(Day04.part1(readLinesFromFile("day04.txt")))
//    println(Day04.part2(readLinesFromFile("day04.txt")))
//    println(Day05.part1("ugkcyxxp"))
//    println(Day05.part2("ugkcyxxp"))
//    println(Day06.part1(readLinesFromFile("day06.txt")))
//    println(Day06.part2(readLinesFromFile("day06.txt")))
//    println(Day07.part1(readLinesFromFile("day07.txt")))
//    println(Day07.part2(readLinesFromFile("day07.txt")))
//    println(Day08.run(readLinesFromFile("day08.txt")))
//    println(Day09.part1(readLinesFromFile("day09.txt").first()))
//    println(Day09.part2(readLinesFromFile("day09.txt").first()))
//    println(Day10.part1(readLinesFromFile("day10.txt")))
//    println(Day10.part2(readLinesFromFile("day10.txt")))
//    println(Day11.part1(readLinesFromFile("day11.txt")))
//    println(Day11.part2(readLinesFromFile("day11.txt")))
//    println(Day12.part1(readLinesFromFile("day12.txt")))
//    println(Day12.part2(readLinesFromFile("day12.txt")))
//    println(Day13.part1(1352, Pair(31, 39)))
//    println(Day13.part2(1352, 50))
//    println(Day14.part1("qzyelonm"))
//    println(Day14.part2("qzyelonm"))
//    println(Day15.part1(readLinesFromFile("day15.txt")))
//    println(Day15.part2(readLinesFromFile("day15.txt")))
//    println(Day16.part1("11011110011011101", 272))
//    println(Day16.part2("11011110011011101", 35651584))
//    println(Day17.part1("qljzarfv"))
//    println(Day17.part2("qljzarfv"))
//    println(Day18.part1(readLinesFromFile("day18.txt").first()))
//    println(Day18.part2(readLinesFromFile("day18.txt").first()))
//    println(Day19.part1(3004953))
//    println(Day19.part2(3004953))
//    println(Day20.part1(readLinesFromFile("day20.txt")))
//    println(Day20.part2(readLinesFromFile("day20.txt")))
//    println(Day21.part1("abcdefgh", readLinesFromFile("day21.txt")))
//    println(Day21.part2("fbgdceah", readLinesFromFile("day21.txt")))
    println(Day22.part1(readLinesFromFile("day22.txt")))
    println(Day22.part2(readLinesFromFile("day22.txt")))
//    println(Day23.part1(readLinesFromFile("day23.txt")))
//    println(Day23.part2(readLinesFromFile("day23.txt")))
//    println(Day24.part1(readLinesFromFile("day24.txt")))
//    println(Day24.part2(readLinesFromFile("day24.txt")))
//    println(Day25.part1(readLinesFromFile("day25.txt")))
//    println(Day25.part2(readLinesFromFile("day25.txt")))
}

private fun readLinesFromFile(filename: String) = File("src//main//resources//v2016//$filename")
    .readLines()

private fun readText(filename: String) = File("src//main//resources//v2016//$filename")
    .readText(Charset.forName("UTF-8"))

private fun readAndSplitByEmptyLines(filename: String) = File("src//main//resources//v2016//$filename")
    .readText(Charset.forName("UTF-8"))
    .split("\n\n")
    .map { it.split("\n") }
