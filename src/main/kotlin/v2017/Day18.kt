package v2017

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Day18 {
    companion object {
        fun part1(input: List<String>): Long {
            val instructions = input.map { it.split(' ') }

            val registers = mutableMapOf<Char, Long>()
            fun String.getValue(): Long {
                return this.toLongOrNull() ?: registers.getValue(this.first())
            }

            var pointer = 0L
            val sounds = mutableListOf<Long>()
            while (pointer in instructions.indices) {
                val instruction = instructions[pointer.toInt()]
                val operation = instruction[0]
                val register = instruction[1].first()
                val registerValue = registers.getOrDefault(register, 0)
                when (operation) {
                    "snd" -> sounds.add(registers.getValue(register))
                    "set" -> registers[register] = instruction[2].getValue()
                    "add" -> registers[register] = registerValue + instruction[2].getValue()
                    "mul" -> registers[register] = registerValue * instruction[2].getValue()
                    "mod" -> registers[register] = registerValue % instruction[2].getValue()
                    "rcv" -> {
                        if (registerValue != 0L) {
                            registers[register] = registerValue
                            return sounds.last().toLong()
                        }
                    }
                    "jgz" -> {
                        if (registerValue > 0L) {
                            pointer += instruction[2].getValue() - 1
                        }
                    }
                    else -> error("Unsupported operation: $operation")
                }
                pointer += 1
            }
            return -1L
        }

        fun part2(input: List<String>): Long {
            val instructions = input.map { it.split(' ') }
            val programA = Program(0L, instructions)
            val programB = Program(1L, instructions)
            runBlocking {
                val channelA = Channel<Long>(UNLIMITED)
                val channelB = Channel<Long>(UNLIMITED)
                val a = launch {
                    programA.run(channelA, channelB)
                }
                val b = launch {
                    programB.run(channelB, channelA)
                }
                launch {
                    delay(500)
                    while (true) {
                        if (channelA.isEmpty && channelB.isEmpty) {
                            a.cancel()
                            b.cancel()
                            cancel()
                            return@launch
                        }
                    }
                }
            }
            return programB.sendCount
        }

        private class Program(val p: Long, val instructions: List<List<String>>) {
            var sendCount = 0L

            suspend fun run(input: ReceiveChannel<Long>, output: SendChannel<Long>) {
                val registers = mutableMapOf<Char, Long>()
                registers['p'] = p
                fun String.getValue(): Long {
                    return this.toLongOrNull() ?: registers.getValue(this.first())
                }

                var pointer = 0L
                while (pointer in instructions.indices) {
                    val instruction = instructions[pointer.toInt()]
                    val operation = instruction[0]
                    val register = instruction[1].first()
                    when (operation) {
                        "snd" -> {
                            output.trySend(instruction[1].getValue())
                            sendCount++
                        }
                        "set" -> registers[register] = instruction[2].getValue()
                        "add" -> registers[register] = registers.getOrDefault(register, 0) + instruction[2].getValue()
                        "mul" -> registers[register] = registers.getOrDefault(register, 0) * instruction[2].getValue()
                        "mod" -> registers[register] = registers.getOrDefault(register, 0) % instruction[2].getValue()
                        "rcv" -> registers[register] = input.receive()
                        "jgz" -> {
                            if (instruction[1].getValue() > 0L) {
                                pointer += instruction[2].getValue() - 1
                            }
                        }
                        else -> error("Unsupported operation: $operation")
                    }
                    pointer += 1
                }
            }
        }
    }
}
