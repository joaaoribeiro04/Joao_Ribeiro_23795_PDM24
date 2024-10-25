package com.example.myapplication

fun onNumberClick(number: String, display: String, updateDisplay: (String) -> Unit) {
    if (display == "0") {
        updateDisplay(number)
    } else {
        updateDisplay(display + number)
    }
}

fun onOperatorClick(
    operator: String,
    display: String,
    currentOperation: Char?,
    firstOperand: String,
    secondOperand: String,
    updateDisplay: (String) -> Unit,
    updateCurrentOperation: (Char?) -> Unit,
    updateFirstOperand: (String) -> Unit,
    updateSecondOperand: (String) -> Unit
) {
    when (operator) {
        "=" -> {
            val result = calculateResult(firstOperand.toDouble(), display.toDouble(), currentOperation)
            updateDisplay(result.toString())
            updateCurrentOperation(null)
            updateFirstOperand("")
            updateSecondOperand("")
        }
        else -> {
            updateCurrentOperation(operator[0])
            updateFirstOperand(display)
            updateDisplay("0")
        }
    }
}

fun calculateResult(first: Double, second: Double, operation: Char?): Double {
    return when (operation) {
        '+' -> first + second
        '-' -> first - second
        '*' -> first * second
        '/' -> first / second
        else -> second
    }
}
