package v2015

@ExperimentalUnsignedTypes
@ExperimentalStdlibApi
class Day07 {

    private abstract class Gate
    private abstract class InputGate(val wire: String, val targetWire: String) : Gate()
    private abstract class DoubleInputGate(wire: String, val otherWire: String, targetWire: String) :
        InputGate(wire, targetWire)

    private abstract class ShiftGate(wire: String, val bitCount: Int, targetWire: String) : InputGate(wire, targetWire)
    private class Signal(val value: UShort) : Gate()
    private class Wire(wire: String, targetWire: String) : InputGate(wire, targetWire)
    private class And(wire: String, otherWire: String, targetWire: String) :
        DoubleInputGate(wire, otherWire, targetWire)

    private class Or(wire: String, otherWire: String, targetWire: String) : DoubleInputGate(wire, otherWire, targetWire)
    private class Lshift(wire: String, bitCount: Int, targetWire: String) : ShiftGate(wire, bitCount, targetWire)
    private class Rshift(wire: String, bitCount: Int, targetWire: String) : ShiftGate(wire, bitCount, targetWire)
    private class Not(wire: String, targetWire: String) : InputGate(wire, targetWire)

    companion object {
        fun part1(instructions: List<String>, targetWire: String = "a"): Long {
            val gates = parse(instructions).toMutableMap()
            return assemble(gates, targetWire).toLong()
        }

        fun part2(instructions: List<String>, targetWire: String = "a", bValue: UShort): Long {
            val gates = parse(instructions).toMutableMap()
            gates["b"] = Signal(bValue)
            return assemble(gates, targetWire).toLong()
        }

        private fun assemble(gates: MutableMap<String, Gate>, target: String = "a"): UShort {
            fun resolve(wire: String): Gate {
                return if (wire.toUShortOrNull() == null) gates.getValue(wire) else Signal(wire.toUShort())
            }

            fun connect(gate: Gate): UShort {
                val value =
                    when (gate) {
                        is Signal -> return gate.value
                        is Wire -> connect(resolve(gate.wire))
                        is And -> connect(resolve(gate.wire)) and connect(resolve(gate.otherWire))
                        is Or -> connect(resolve(gate.wire)) or connect(resolve(gate.otherWire))
                        is Lshift -> connect(resolve(gate.wire)) shl gate.bitCount
                        is Rshift -> connect(resolve(gate.wire)) shr gate.bitCount
                        is Not -> connect(resolve(gate.wire)).inv()
                        else -> error("Unknown gate: $gate")
                    }
                if (gate is InputGate) {
                    gates[gate.targetWire] = Signal(value)
                }
                return value
            }

            return connect(gates.getValue(target))
        }

        private fun parse(instructions: List<String>): Map<String, Gate> {
            return instructions.map { gateString ->
                val (instruction, targetWire) = gateString.split(" -> ")
                targetWire to when {
                    instruction.contains("AND") -> {
                        val (leftWire, rightWire) = instruction.split(" AND ")
                        And(leftWire, rightWire, targetWire)
                    }
                    instruction.contains("OR") -> {
                        val (leftWire, rightWire) = instruction.split(" OR ")
                        Or(leftWire, rightWire, targetWire)
                    }
                    instruction.contains("RSHIFT") -> {
                        val (wire, bitCount) = instruction.split(" RSHIFT ")
                        Rshift(wire, bitCount.toInt(), targetWire)
                    }
                    instruction.contains("LSHIFT") -> {
                        val (wire, bitCount) = instruction.split(" LSHIFT ")
                        Lshift(wire, bitCount.toInt(), targetWire)
                    }
                    instruction.contains("NOT") -> {
                        val wire = instruction.removePrefix("NOT ")
                        Not(wire, targetWire)
                    }
                    else -> {
                        Wire(instruction, targetWire)
                    }
                }
            }.toMap()
        }
    }
}

@ExperimentalUnsignedTypes
private val uShortMaxValueAsUInt = UShort.MAX_VALUE.toUInt()

@ExperimentalUnsignedTypes
@ExperimentalStdlibApi
infix fun UShort.shl(bitCount: Int): UShort {
    return this.rotateLeft(bitCount) and (uShortMaxValueAsUInt shl bitCount).toUShort()
}

@ExperimentalUnsignedTypes
@ExperimentalStdlibApi
infix fun UShort.shr(bitCount: Int): UShort {
    return this.rotateRight(bitCount) and (uShortMaxValueAsUInt shr bitCount).toUShort()
}
