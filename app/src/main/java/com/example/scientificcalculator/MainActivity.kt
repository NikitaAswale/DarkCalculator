package com.example.scientificcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.scientificcalculator.ui.CalculatorScreen
import com.example.scientificcalculator.ui.CalculatorViewModel
import com.example.scientificcalculator.ui.theme.DarkBackground
import com.example.scientificcalculator.ui.theme.ScientificCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScientificCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBackground
                ) {
                    val viewModel: CalculatorViewModel = hiltViewModel()
                    CalculatorScreen(viewModel = viewModel)
                }
            }
        }
    }
}