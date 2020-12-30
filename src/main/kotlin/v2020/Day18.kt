package v2020

import java.util.*
import kotlin.math.max

fun day18Part1(input: List<String>): Long {
    return parse(input) { _, _ -> true }
}

fun day18Part2(input: List<String>): Long {
    return parse(input) { token, top -> token == '*' && top == '+' }
}

private fun parse(input: List<String>, hasLowerPrecedence: (Char, Char) -> Boolean): Long {
    return input
        .map { it.replace(" ", "") }
        .map { parseExpression(it, hasLowerPrecedence) }
        .map { evaluate(it) }
        .sum()
}

// Shunting-yard algorithm: https://en.m.wikipedia.org/wiki/Shunting-yard_algorithm
// Only supports relevant operations
private fun parseExpression(input: String, hasLowerPrecedence: (Char, Char) -> Boolean): MutableList<String> {
    val tokens = input.toMutableList()
    val operators: Deque<Char> = ArrayDeque<Char>()
    val output = mutableListOf<String>()
    while (tokens.isNotEmpty()) {
        val token = tokens.removeFirst()
        when {
            token.isDigit() -> output.add(token.toString())
            token in listOf('+', '*') -> {
                while (operators.isNotEmpty() && hasLowerPrecedence(
                        token,
                        operators.peek()
                    ) && operators.peek() != '('
                ) {
                    output.add(operators.pop().toString())
                }
                operators.push(token)
            }
            token == '(' -> operators.push(token)
            token == ')' -> {
                while (operators.isNotEmpty() && operators.peek() != '(') {
                    output.add(operators.pop().toString())
                }
                if (operators.peek() == '(') {
                    operators.pop()
                }
            }
        }
    }
    while (operators.isNotEmpty()) {
        output.add(operators.remove().toString())
    }
    return output
}

// Evaluate Reverse Polish notation (RPN)
// Read output from left to right and apply operation when possible
private fun evaluate(output: MutableList<String>): Long {
    var i = 0
    while (output.size > 1) {
        val first = output[i]
        val second = output[i + 1]
        val third = output[i + 2]
        if (first.toLongOrNull() != null && second.toLongOrNull() != null && third in listOf("+", "*")) {
            if (third == "+") {
                output[i] = (first.toLongOrNull()!! + second.toLongOrNull()!!).toString()
            } else if (third == "*") {
                output[i] = (first.toLongOrNull()!! * second.toLongOrNull()!!).toString()
            }
            output.removeAt(i + 1)
            output.removeAt(i + 1)
            i = max(0, i - 1)
        } else {
            i++
        }
    }
    return output.first().toLong()
}
