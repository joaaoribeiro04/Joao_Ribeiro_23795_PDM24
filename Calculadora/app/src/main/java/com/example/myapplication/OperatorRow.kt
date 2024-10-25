package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun OperatorRow(buttonModifier: Modifier, operators: List<String>, onOperatorClick: (String) -> Unit) {
    Row {
        operators.forEach { operator ->
            Box(
                modifier = buttonModifier
                    .background(Color.DarkGray)
                    .clickable { onOperatorClick(operator) },
                contentAlignment = Alignment.Center
            ) {
                Text(text = operator, fontSize = 32.sp, color = Color.White)
            }
        }
    }
}
