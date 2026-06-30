package com.example.scientificcalculator.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scientificcalculator.domain.CalculatorEngine
import com.example.scientificcalculator.model.CalculatorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val engine: CalculatorEngine
) : ViewModel() {

    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Append -> append(action.symbol)
            CalculatorAction.Clear -> clear()
            CalculatorAction.Backspace -> backspace()
            CalculatorAction.Equals -> evaluate()
            is CalculatorAction.AppendParenthesis -> appendParenthesis()
        }
    }

    private fun append(symbol: String) {
        _state.update { current ->
            if (current.isError) {
                CalculatorState(expression = symbol)
            } else {
                current.copy(expression = current.expression + symbol, isError = false)
            }
        }
    }

    private fun appendParenthesis() {
        _state.update { current ->
            val expr = if (current.isError) "" else current.expression
            val openCount = expr.count { it == '(' }
            val closeCount = expr.count { it == ')' }
            val nextChar = if (openCount > closeCount && (expr.isEmpty() || expr.last().isDigit() || expr.last() == ')')) {
                ")"
            } else {
                "("
            }
            CalculatorState(expression = expr + nextChar, isError = false)
        }
    }

    private fun clear() {
        _state.update { CalculatorState() }
    }

    private fun backspace() {
        _state.update { current ->
            if (current.isError) {
                CalculatorState()
            } else {
                current.copy(expression = current.expression.dropLast(1), isError = false)
            }
        }
    }

    private fun evaluate() {
        val expression = _state.value.expression
        if (expression.isBlank()) return
        viewModelScope.launch {
            val result = engine.evaluate(expression)
            _state.update { current ->
                if (result == "Error") {
                    current.copy(result = "", isError = true)
                } else {
                    current.copy(result = result, isError = false)
                }
            }
        }
    }
}

sealed interface CalculatorAction {
    data class Append(val symbol: String) : CalculatorAction
    data object AppendParenthesis : CalculatorAction
    data object Clear : CalculatorAction
    data object Backspace : CalculatorAction
    data object Equals : CalculatorAction
}
