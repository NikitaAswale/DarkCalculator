package com.example.scientificcalculator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.scientificcalculator.ui.components.CalculatorButtonGrid
import com.example.scientificcalculator.ui.components.CalculatorDisplay

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        CalculatorDisplay(
            state = state,
            modifier = Modifier.weight(0.35f)
        )
        CalculatorButtonGrid(
            onAction = viewModel::onAction,
            modifier = Modifier.weight(0.55f)
        )
    }
}
