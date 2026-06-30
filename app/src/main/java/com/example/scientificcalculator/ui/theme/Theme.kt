package com.example.scientificcalculator.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val CalculatorColorScheme = darkColorScheme(
    primary = AccentTeal,
    onPrimary = DarkBackground,
    secondary = AccentTealDark,
    onSecondary = TextPrimary,
    tertiary = AccentTeal,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    error = ErrorRed,
    onError = TextPrimary
)

@Composable
fun ScientificCalculatorTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CalculatorColorScheme,
        typography = Typography,
        content = content
    )
}