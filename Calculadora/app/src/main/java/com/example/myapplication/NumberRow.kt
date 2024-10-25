package com.example.myapplication

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
fun NumberRow(buttonModifier: Modifier, numbers: List<String>, onNumberClick: (String) -> Unit) {
    Row {
        numbers.forEach { number ->
            Box(
                modifier = buttonModifier.clickable { onNumberClick(number) },
                contentAlignment = Alignment.Center
            ) {
                Text(text = number, fontSize = 32.sp, color = Color.White)
            }
        }
    }
}
