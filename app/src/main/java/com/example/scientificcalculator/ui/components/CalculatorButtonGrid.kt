package com.example.scientificcalculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scientificcalculator.ui.CalculatorAction
import com.example.scientificcalculator.ui.theme.AccentTeal
import com.example.scientificcalculator.ui.theme.DarkBackground
import com.example.scientificcalculator.ui.theme.DarkSurface
import com.example.scientificcalculator.ui.theme.DarkSurfaceVariant
import com.example.scientificcalculator.ui.theme.ErrorRed
import com.example.scientificcalculator.ui.theme.TextPrimary

private enum class ButtonType {
    NUMBER, OPERATOR, FUNCTION, EQUALS
}

private data class CalculatorButton(
    val label: String,
    val action: CalculatorAction,
    val type: ButtonType,
    val span: Int = 1
)

@Composable
fun CalculatorButtonGrid(
    onAction: (CalculatorAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val buttons = listOf(
        listOf(
            CalculatorButton("C", CalculatorAction.Clear, ButtonType.FUNCTION),
            CalculatorButton("( )", CalculatorAction.AppendParenthesis, ButtonType.FUNCTION),
            CalculatorButton("%", CalculatorAction.Append("%"), ButtonType.OPERATOR),
            CalculatorButton("÷", CalculatorAction.Append("÷"), ButtonType.OPERATOR)
        ),
        listOf(
            CalculatorButton("7", CalculatorAction.Append("7"), ButtonType.NUMBER),
            CalculatorButton("8", CalculatorAction.Append("8"), ButtonType.NUMBER),
            CalculatorButton("9", CalculatorAction.Append("9"), ButtonType.NUMBER),
            CalculatorButton("×", CalculatorAction.Append("×"), ButtonType.OPERATOR)
        ),
        listOf(
            CalculatorButton("4", CalculatorAction.Append("4"), ButtonType.NUMBER),
            CalculatorButton("5", CalculatorAction.Append("5"), ButtonType.NUMBER),
            CalculatorButton("6", CalculatorAction.Append("6"), ButtonType.NUMBER),
            CalculatorButton("−", CalculatorAction.Append("-"), ButtonType.OPERATOR)
        ),
        listOf(
            CalculatorButton("1", CalculatorAction.Append("1"), ButtonType.NUMBER),
            CalculatorButton("2", CalculatorAction.Append("2"), ButtonType.NUMBER),
            CalculatorButton("3", CalculatorAction.Append("3"), ButtonType.NUMBER),
            CalculatorButton("+", CalculatorAction.Append("+"), ButtonType.OPERATOR)
        ),
        listOf(
            CalculatorButton("0", CalculatorAction.Append("0"), ButtonType.NUMBER),
            CalculatorButton(".", CalculatorAction.Append("."), ButtonType.NUMBER),
            CalculatorButton("⌫", CalculatorAction.Backspace, ButtonType.FUNCTION),
            CalculatorButton("=", CalculatorAction.Equals, ButtonType.EQUALS)
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.forEach { button ->
                    CalculatorButtonItem(
                        button = button,
                        onClick = { onAction(button.action) },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun CalculatorButtonItem(
    button: CalculatorButton,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (button.type) {
        ButtonType.NUMBER -> DarkSurface
        ButtonType.OPERATOR -> DarkSurfaceVariant
        ButtonType.FUNCTION -> DarkSurfaceVariant
        ButtonType.EQUALS -> AccentTeal
    }
    val contentColor = when (button.type) {
        ButtonType.NUMBER -> TextPrimary
        ButtonType.OPERATOR -> AccentTeal
        ButtonType.FUNCTION -> ErrorRed
        ButtonType.EQUALS -> DarkBackground
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = button.label,
            fontSize = 24.sp,
            fontWeight = if (button.type == ButtonType.EQUALS) FontWeight.Bold else FontWeight.Medium,
            color = contentColor
        )
    }
}
