package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import com.example.myapplication.ui.theme.CalculatorDisplayColor
import com.example.myapplication.ui.theme.CalculatorTextColor

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
                .height(150.dp)
                .background(CalculatorDisplayColor)
                .padding(16.dp)
                .border(4.dp, Color.Black)
                .padding(24.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = display,
                fontSize = 48.sp,
                color = CalculatorTextColor,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val buttonModifier = Modifier
                .weight(1f)
                .aspectRatio(1f)

            NumberRow(buttonModifier, listOf("7", "8", "9", "0")) { number -> onNumberClick(number, display) { display = it } }
            NumberRow(buttonModifier, listOf("4", "5", "6", ".")) { number -> onNumberClick(number, display) { display = it } }
            NumberRow(buttonModifier, listOf("1", "2", "3", "")) { number -> onNumberClick(number, display) { display = it } }

            OperatorRow(buttonModifier, listOf("C", "+", "-", "*")) { operator ->
                when (operator) {
                    "C" -> {
                        display = "0"
                        currentOperation = null
                        firstOperand = ""
                        secondOperand = ""
                    }
                    else -> {
                        onOperatorClick(operator, display, currentOperation, firstOperand, secondOperand,
                            { display = it }, { currentOperation = it }, { firstOperand = it }, { secondOperand = it })
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val bottomButtonModifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray)

                Box(
                    modifier = bottomButtonModifier.clickable {
                        val result = display.toDouble() / 100
                        display = result.toString()
                    },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "%", fontSize = 32.sp, color = Color.White)
                }

                Box(
                    modifier = bottomButtonModifier.clickable {
                        val result = calculateResult(firstOperand.toDouble(), display.toDouble(), currentOperation)
                        display = result.toString()
                        currentOperation = null
                        firstOperand = ""
                        secondOperand = ""
                    },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "=", fontSize = 32.sp, color = Color.White)
                }
            } }}}




