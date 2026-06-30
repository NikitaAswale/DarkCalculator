package com.example.scientificcalculator.domain

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Pure Kotlin arithmetic expression evaluator using a recursive descent parser.
 * Supports: +, -, *, /, %, parentheses, decimal numbers.
 */
class CalculatorEngine {

    fun evaluate(expression: String): String {
        if (expression.isBlank()) return ""
        return try {
            val parser = Parser(expression)
            val result = parser.parse()
            parser.expectEnd()
            formatResult(result)
        } catch (e: ArithmeticException) {
            "Error"
        } catch (e: Exception) {
            "Error"
        }
    }

    private fun formatResult(value: BigDecimal): String {
        val stripped = value.stripTrailingZeros()
        val plain = stripped.toPlainString()
        return if (plain.length > 15) {
            stripped.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString()
        } else {
            plain
        }
    }

    private class Parser(private val input: String) {
        private var pos = 0

        fun parse(): BigDecimal {
            return parseExpression()
        }

        fun expectEnd() {
            skipWhitespace()
            if (pos < input.length) throw IllegalArgumentException("Unexpected character at $pos")
        }

        private fun parseExpression(): BigDecimal {
            var result = parseTerm()
            while (true) {
                skipWhitespace()
                when (peek()) {
                    '+' -> { pos++; result = result.add(parseTerm()) }
                    '-' -> { pos++; result = result.subtract(parseTerm()) }
                    else -> return result
                }
            }
        }

        private fun parseTerm(): BigDecimal {
            var result = parseFactor()
            while (true) {
                skipWhitespace()
                when (peek()) {
                    '*', '×' -> { pos++; result = result.multiply(parseFactor()) }
                    '/', '÷' -> {
                        pos++
                        val divisor = parseFactor()
                        if (divisor.compareTo(BigDecimal.ZERO) == 0) throw ArithmeticException("Division by zero")
                        result = result.divide(divisor, 20, RoundingMode.HALF_UP)
                    }
                    '%' -> { pos++; result = result.remainder(parseFactor()) }
                    else -> return result
                }
            }
        }

        private fun parseFactor(): BigDecimal {
            skipWhitespace()
            when (peek()) {
                '-' -> { pos++; return parseFactor().negate() }
                '+' -> { pos++; return parseFactor() }
                '(' -> {
                    pos++
                    val result = parseExpression()
                    skipWhitespace()
                    expect(')')
                    return result
                }
            }
            return parseNumber()
        }

        private fun parseNumber(): BigDecimal {
            skipWhitespace()
            val start = pos
            while (pos < input.length && (input[pos].isDigit() || input[pos] == '.')) {
                pos++
            }
            if (pos == start) throw IllegalArgumentException("Expected number at $pos")
            return BigDecimal(input.substring(start, pos))
        }

        private fun peek(): Char? = if (pos < input.length) input[pos] else null

        private fun skipWhitespace() {
            while (pos < input.length && input[pos].isWhitespace()) pos++
        }

        private fun expect(c: Char) {
            if (peek() != c) throw IllegalArgumentException("Expected '$c' at $pos")
            pos++
        }
    }
}
