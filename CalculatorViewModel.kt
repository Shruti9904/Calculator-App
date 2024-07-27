package com.example.calculatorapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class CalculatorState(
    val number1 : String = "",
    val number2 : String = "",
    val operation: CalculatorOperation? = null
)

class CalculatorViewModel : ViewModel(){
    private var _state by mutableStateOf(CalculatorState())
    var state by mutableStateOf(_state)

    fun onAction(calculatorAction: CalculatorAction){
        when (calculatorAction) {
            is CalculatorAction.Number -> {
                enterNumber(calculatorAction.number)
            }

            is CalculatorAction.Operations -> {
                enterOperation(calculatorAction.operator)
            }

            is CalculatorAction.Calculate -> {
                performCalculation()
            }

            is CalculatorAction.Decimal -> {
                enterDecimal()
            }

            is CalculatorAction.Delete -> {
                performDeletion()
            }

            is CalculatorAction.Clear -> {
                state = CalculatorState()
            }
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operation == null){
            if(state.number1.length >= 8){
                return
            }
            state=state.copy(number1 = state.number1 + number.toString() )
            return
        }
        if(state.number2.length >= 8){
            return
        }
        state=state.copy(number2 = state.number2 + number.toString() )
        return
    }
    private fun enterOperation(operation: CalculatorOperation) {
        if(state.number1.isNotBlank()){
            state = state.copy(operation = operation)
        }
    }
    private fun enterDecimal() {
        if(state.number2.isNotBlank() && !state.number2.contains(".")){
            state = state.copy(number2 = state.number2.plus("."))
        }else if(state.operation != null){
            state = state.copy(number2 = "0.")
        }else if(state.number1.isNotBlank() && !state.number1.contains(".")){
            state = state.copy(number1 = state.number1.plus("."))
        }else if(state.number1.isBlank()){
            state = state.copy(number1 = "0.")
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if(number1 != null && number2 !=null){
            val result = when(state.operation){
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Divide -> number1/number2
                is CalculatorOperation.Multiply ->number1*number2
                null -> return
            }
            state=state.copy(
                number1=result.toString().take(15),
                number2 = "",
                operation = null
            )
        }


    }
    private fun performDeletion() {
        if(state.number2.isNotBlank()){
            state = state.copy(number2 = state.number2.dropLast(1))
        }else if(state.operation != null){
            state = state.copy(operation = null)
        }else if(state.number1.isNotBlank()){
            state = state.copy(number1 = state.number1.dropLast(1))
        }
    }

}