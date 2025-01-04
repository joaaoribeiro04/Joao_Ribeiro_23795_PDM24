package com.example.shoppinglistapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

val Purple80 = Color(0xFF7F3FBF)
val PurpleGrey40 = Color(0xFFB3A8D6)
val BackgroundLight = Color(0xFFF1F1F1)
val SurfaceLight = Color(0xFFFFFFFF)

private val LightColorScheme = lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey40,
    background = BackgroundLight,
    surface = SurfaceLight
)

@Composable
fun ShoppingListAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
