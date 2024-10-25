package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorApp() {
    var display by remember { mutableStateOf("0") }
    var currentOperation by remember { mutableStateOf<Char?>(null) }
    var firstOperand by remember { mutableStateOf("") }
    var secondOperand by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = display,
                fontSize = 48.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val buttonModifier = Modifier
                .size(80.dp)
                .background(Color.Gray)

            NumberRow(buttonModifier, listOf("7", "8", "9")) { number ->
                onNumberClick(number, display) { display = it }
            }
            NumberRow(buttonModifier, listOf("4", "5", "6")) { number ->
                onNumberClick(number, display) { display = it }
            }
            NumberRow(buttonModifier, listOf("1", "2", "3")) { number ->
                onNumberClick(number, display) { display = it }
            }
            NumberRow(buttonModifier, listOf("0", ".")) { number ->
                onNumberClick(number, display) { display = it }
            }

            OperatorRow(buttonModifier, listOf("C", "+", "-", "*", "/", "=")) { operator ->
                when (operator) {
                    "C" -> {
                        // Limpa todos os valores
                        display = "0"
                        currentOperation = null
                        firstOperand = ""
                        secondOperand = ""
                    }
                    else -> {
                        onOperatorClick(
                            operator, display, currentOperation, firstOperand, secondOperand,
                            { display = it }, { currentOperation = it }, { firstOperand = it }, { secondOperand = it }
                        )
                    }
                }
            }
        }
    }
}
