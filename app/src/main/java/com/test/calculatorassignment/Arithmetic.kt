package com.test.calculatorassignment

import java.lang.ArithmeticException
import java.util.*

class Arithmetic {

    @Throws(ArithmeticException::class)
    fun evaluate(expression: String): Int {
        val tokens = expression.toCharArray()
        val values = Stack<Double>()
        val ops = Stack<Char>()
        var i = 0
        while (i < tokens.size) {
            if (tokens[i] == ' ') {
                i++
                continue
            }
            if (tokens[i] in '0'..'9') {
                val sbuf = StringBuffer()

                while (i < tokens.size && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++])
                values.push(sbuf.toString().toDouble())
                i--
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/'
            ) {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))

                    values.push(applyOp(ops.pop(), values.pop(), values.pop()))

                ops.push(tokens[i])
            }
            i++
        }

        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()))

        return Math.round(values.pop()).toInt()
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        return !((op1 == '*' || op1 == '+') &&
                (op2 == '/' || op2 == '-'))
    }

    private fun applyOp(op: Char, b: Double, a: Double): Double {

        when (op) {
            '+' -> return a + b
            '-' -> return a - b
            '*' -> return a * b
            '/' -> {
                if (b == 0.0) throw UnsupportedOperationException(
                    "Cannot divide by zero"
                )
                return a / b
            }
        }
        return 0.0
    }
}