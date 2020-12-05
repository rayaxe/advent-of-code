import java.io.File

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
    println(day5Part2(readLinesFromFile("day5.txt")))
}

private fun readLinesFromFile(filename: String) = File("src//main//resources//$filename").readLines()
