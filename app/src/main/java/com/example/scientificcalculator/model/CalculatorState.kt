package com.example.scientificcalculator.model

data class CalculatorState(
    val expression: String = "",
    val result: String = "",
    val isError: Boolean = false
)
