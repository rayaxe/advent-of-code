import java.io.File
import java.nio.charset.Charset

fun main() {
//    println(day1Part1(readLinesFromFile("day1.txt")))
//    println(day1Part2(readLinesFromFile("day1.txt")))
//    println(day2Part1(readLinesFromFile("day2.txt")))
//    println(day2Part2(readLinesFromFile("day2.txt")))
//    println(day3Part1(readLinesFromFile("day3.txt")))
//    println(day3Part2(readLinesFromFile("day3.txt")))
//    println(day4Part1(readLinesFromFile("day4.txt")))
//    println(day4Part2(readLinesFromFile("day4.txt")))
//    println(day5Part1(readLinesFromFile("day5.txt")))
//    println(day5Part2(readLinesFromFile("day5.txt")))
//    println(day6Part1(readAndSplitByEmptyLines("day6.txt")))
//    println(day6Part2(readAndSplitByEmptyLines("day6.txt")))
//    println(day7Part1(readLinesFromFile("day7.txt")))
//    println(day7Part2(readLinesFromFile("day7.txt")))
//    println(day8Part1(readLinesFromFile("day8.txt")))
//    println(day8Part2(readLinesFromFile("day8.txt")))
//    println(day9Part1(readLinesFromFile("day9.txt"), 25))
//    println(day9Part2(readLinesFromFile("day9.txt"), 375054920))
//    println(day10Part1(readLinesFromFile("day10.txt")))
//    println(day10Part2(readLinesFromFile("day10.txt")))
//    println(day11Part1(readLinesFromFile("day11.txt")))
//    println(day11Part2(readLinesFromFile("day11.txt")))
//    println(day12Part1(readLinesFromFile("day12.txt")))
//    println(day12Part2(readLinesFromFile("day12.txt")))
//    println(day13Part1(readLinesFromFile("day13.txt")))
//    println(day13Part2(readLinesFromFile("day13.txt")))
    println(day14Part1(readLinesFromFile("day14.txt")))
//    println(day14Part2(readLinesFromFile("day14.txt")))
}

private fun readLinesFromFile(filename: String) = File("src//main//resources//$filename")
    .readLines()

private fun readAndSplitByEmptyLines(filename: String) = File("src//main//resources//$filename")
    .readText(Charset.forName("UTF-8"))
    .split("\n\n")
    .map { it.split("\n") }
