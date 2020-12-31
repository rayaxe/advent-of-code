package v2015

import kotlin.math.max

private class Instruction(val operation: Operation, val xs: IntRange, val ys: IntRange) {
    enum class Operation { TURN_ON, TOGGLE, TURN_OFF }
}
private typealias LightAction = (Array<Array<Int>>, Int, Int) -> Unit

fun day06Part1(instructions: List<String>): Long {
    val turnOn: LightAction = { grid, y, x -> grid[y][x] = 1 }
    val toggle: LightAction = { grid, y, x -> grid[y][x] = 1 - grid[y][x] }
    val turnOff: LightAction = { grid, y, x -> grid[y][x] = 0 }
    return follow(instructions, turnOn, toggle, turnOff)
        .fold(0) { acc, lights -> acc + lights.count { it == 1 } }
}

fun day06Part2(instructions: List<String>): Long {
    val turnOn: LightAction = { grid, y, x -> grid[y][x] = grid[y][x] + 1 }
    val toggle: LightAction = { grid, y, x -> grid[y][x] = grid[y][x] + 2 }
    val turnOff: LightAction = { grid, y, x -> grid[y][x] = max(0, grid[y][x] - 1) }
    return follow(instructions, turnOn, toggle, turnOff)
        .fold(0) { acc, lights -> acc + lights.sum() }
}

private fun follow(
    input: List<String>,
    turnOn: LightAction,
    toggle: LightAction,
    turnOff: LightAction
): Array<Array<Int>> {
    val grid: Array<Array<Int>> = Array(1_000) { Array(1_000) { 0 } }
    input.forEach {
        val instruction = parse(it)
        for (y in instruction.ys) {
            for (x in instruction.xs) {
                when (instruction.operation) {
                    Instruction.Operation.TURN_ON -> turnOn(grid, y, x)
                    Instruction.Operation.TOGGLE -> toggle(grid, y, x)
                    Instruction.Operation.TURN_OFF -> turnOff(grid, y, x)
                }
            }
        }
    }
    return grid
}

private val instructionRegex = "(.*) ([0-9]+),([0-9]+) through ([0-9]+),([0-9]+)".toRegex()
private fun parse(text: String): Instruction {
    return instructionRegex.matchEntire(text)
        ?.destructured
        ?.let { (operationText, minX, minY, maxX, maxY) ->
            val operation = when (operationText) {
                "turn on" -> Instruction.Operation.TURN_ON
                "toggle" -> Instruction.Operation.TOGGLE
                "turn off" -> Instruction.Operation.TURN_OFF
                else -> error("Unexpected operation: $operationText")
            }
            Instruction(operation, minX.toInt()..maxX.toInt(), minY.toInt()..maxY.toInt())
        }
        ?: throw IllegalArgumentException("Bad input '$text'")
}
