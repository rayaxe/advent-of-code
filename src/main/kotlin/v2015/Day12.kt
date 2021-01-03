package v2015

import JSONBaseVisitor
import JSONLexer
import JSONParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode

class Day12 {
    companion object {
        private val numberRegex = """-?[0-9]+""".toRegex()

        fun part1(input: List<String>): Long {
            return input.map {
                numberRegex.findAll(it)
                    .map { matchResult -> matchResult.value.toLong() }
                    .sum()
            }.sum()
        }

        /**
         * JSON grammar is defined in `src/main/antlr4/JSON.g4`. Run `mvn antlr4:antlr4` to generate Java classes.
         */
        fun part2(input: List<String>): Long {
            val lexer = JSONLexer(CharStreams.fromString(input.first()))
            val parser = JSONParser(CommonTokenStream(lexer))
            val tree: ParseTree = parser.value()
            return sumVisitor().visit(tree)
        }

        /**
         * Parse tree visitor that sums the numbers it finds, ignoring any object (and all of its children) which has
         * any property with the value "red".
         */
        private fun sumVisitor() = object : JSONBaseVisitor<Long>() {
            /**
             * Return number value for Number node.
             */
            override fun visitNumber(ctx: JSONParser.NumberContext): Long {
                return ctx.text.toLong()
            }

            /**
             * Return 0 for non-Number node.
             */
            override fun defaultResult(): Long {
                return 0L
            }

            /**
             * Sum results.
             */
            override fun aggregateResult(aggregate: Long, nextResult: Long): Long {
                return aggregate + nextResult
            }

            /**
             * Ignore any object (and all of its children) which has any property with the value "red".
             */
            override fun shouldVisitNextChild(node: RuleNode, currentResult: Long): Boolean {
                when (node) {
                    is JSONParser.ObjContext -> {
                        node.children.forEach { child ->
                            if (child is JSONParser.PairContext) {
                                val value = child.getChild(2).getChild(0)
                                if (value is JSONParser.TextContext && value.text == "\"red\"") {
                                    return false
                                }
                            }
                        }
                        return true
                    }
                    else -> return true
                }
            }
        }
    }
}
