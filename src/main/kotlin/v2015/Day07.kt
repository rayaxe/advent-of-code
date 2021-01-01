package v2015

@ExperimentalUnsignedTypes
@ExperimentalStdlibApi
class Day07 {

    private abstract class Gate(val targetRef: String)
    private abstract class SingleInputGate(val wire: String, targetRef: String) : Gate(targetRef)
    private abstract class DoubleInputGate(wire: String, val otherWire: String, targetRef: String) :
        SingleInputGate(wire, targetRef)

    private abstract class ShiftGate(wire: String, val bitCount: Int, targetRef: String) :
        SingleInputGate(wire, targetRef)

    private class Signal(val value: UShort, targetRef: String) : Gate(targetRef)
    private class Wire(wire: String, targetRef: String) : SingleInputGate(wire, targetRef)
    private class And(wire: String, otherWire: String, targetRef: String) : DoubleInputGate(wire, otherWire, targetRef)
    private class Or(wire: String, otherWire: String, targetRef: String) : DoubleInputGate(wire, otherWire, targetRef)
    private class Lshift(wire: String, bitCount: Int, targetRef: String) : ShiftGate(wire, bitCount, targetRef)
    private class Rshift(wire: String, bitCount: Int, targetRef: String) : ShiftGate(wire, bitCount, targetRef)
    private class Not(wire: String, targetRef: String) : SingleInputGate(wire, targetRef)

    companion object {
        fun part1(instructions: List<String>, targetRef: String = "a"): Long {
            val gateMap = parse(instructions).toMutableMap()
            return connect(gateMap, gateMap.getValue(targetRef)).toLong()
        }

        fun part2(input: List<String>): Long {
            return -1L
        }

        private fun connect(gateMap: MutableMap<String, Gate>, gate: Gate): UShort {
            val signal = when (gate) {
                is Signal -> {
                    gate
                }
                is Wire -> {
                    Signal(connect(gateMap, getRef(gateMap, gate.wire, gate.targetRef)), gate.targetRef)
                }
                is And -> {
                    Signal(
                        connect(gateMap, getRef(gateMap, gate.wire, gate.targetRef)) and
                                connect(gateMap, getRef(gateMap, gate.otherWire, gate.targetRef)),
                        gate.targetRef
                    )
                }
                is Or -> {
                    Signal(
                        connect(gateMap, getRef(gateMap, gate.wire, gate.targetRef)) or
                                connect(gateMap, getRef(gateMap, gate.otherWire, gate.targetRef)),
                        gate.targetRef
                    )
                }
                is Lshift -> {
                    Signal(
                        connect(gateMap, getRef(gateMap, gate.wire, gate.targetRef)) shl gate.bitCount,
                        gate.targetRef
                    )
                }
                is Rshift -> {
                    Signal(
                        connect(gateMap, getRef(gateMap, gate.wire, gate.targetRef)) shr gate.bitCount,
                        gate.targetRef
                    )
                }
                is Not -> {
                    Signal(
                        connect(gateMap, getRef(gateMap, gate.wire, gate.targetRef)).inv(),
                        gate.targetRef
                    )
                }
                else -> {
                    error("Unknown gate: $gate")
                }
            }
            gateMap[gate.targetRef] = signal
            return signal.value
        }

        private fun getRef(gateMap: Map<String, Gate>, wire: String, targetRef: String): Gate {
            return if (wire.toUShortOrNull() == null) {
                gateMap.getValue(wire)
            } else {
                Signal(wire.toUShort(), targetRef)
            }
        }

        private fun parse(instructions: List<String>): Map<String, Gate> {
            return instructions.map { gateString ->
                val (instruction, targetRef) = gateString.split(" -> ")
                targetRef to when {
                    instruction.contains("AND") -> {
                        val (leftRef, rightRef) = instruction.split(" AND ")
                        And(leftRef, rightRef, targetRef)
                    }
                    instruction.contains("OR") -> {
                        val (leftRef, rightRef) = instruction.split(" OR ")
                        Or(leftRef, rightRef, targetRef)
                    }
                    instruction.contains("RSHIFT") -> {
                        val (ref, bitCount) = instruction.split(" RSHIFT ")
                        Rshift(ref, bitCount.toInt(), targetRef)
                    }
                    instruction.contains("LSHIFT") -> {
                        val (ref, bitCount) = instruction.split(" LSHIFT ")
                        Lshift(ref, bitCount.toInt(), targetRef)
                    }
                    instruction.contains("NOT") -> {
                        val ref = instruction.removePrefix("NOT ")
                        Not(ref, targetRef)
                    }
                    else -> {
                        Wire(instruction, targetRef)
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
