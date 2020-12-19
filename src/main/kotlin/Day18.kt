import java.util.*
import kotlin.math.max

fun day18Part1(input: List<String>): Long {
    return input
        .map { parse(it.replace(" ", "")) { _, _ -> true } }
        .sum()
}

fun day18Part2(input: List<String>): Long {
    return input
        .map { parse(it.replace(" ", "")) { token, top -> token == '*' && top == '+' } }
        .sum()
}

// Shunting-yard algorithm: https://en.m.wikipedia.org/wiki/Shunting-yard_algorithm
private fun parse(input: String, hasPrecedence: (Char, Char) -> Boolean): Long {
    val tokens = input.toMutableList()
    val operators: Deque<Char> = ArrayDeque<Char>()
    val output = mutableListOf<String>()
    while (tokens.isNotEmpty()) {
        val token = tokens.removeFirst()
        when {
            token.isDigit() -> output.add(token.toString())
            token in listOf('+', '*') -> {
                while (operators.isNotEmpty() && hasPrecedence(token, operators.peek()) && operators.peek() != '(') {
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
    return evaluate(output)
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
