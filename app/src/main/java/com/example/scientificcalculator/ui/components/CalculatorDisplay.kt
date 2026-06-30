package com.example.scientificcalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scientificcalculator.model.CalculatorState
import com.example.scientificcalculator.ui.theme.ErrorRed
import com.example.scientificcalculator.ui.theme.TextPrimary
import com.example.scientificcalculator.ui.theme.TextSecondary

@Composable
fun CalculatorDisplay(
    state: CalculatorState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = state.expression.ifBlank { "" },
            fontSize = 28.sp,
            fontWeight = FontWeight.Light,
            color = if (state.isError) ErrorRed else TextSecondary,
            textAlign = TextAlign.End,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = if (state.isError) "Error" else state.result.ifBlank { "0" },
            fontSize = 56.sp,
            fontWeight = FontWeight.Medium,
            color = if (state.isError) ErrorRed else TextPrimary,
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}
