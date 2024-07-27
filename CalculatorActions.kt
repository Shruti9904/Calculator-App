package com.example.calculatorapp

sealed class CalculatorAction {
    data class Number (val number:Int) : CalculatorAction()
    data object Clear : CalculatorAction()
    data object Decimal : CalculatorAction()
    data object Delete : CalculatorAction()
    data object Calculate : CalculatorAction()
    data class Operations (val operator : CalculatorOperation) : CalculatorAction()
}

sealed class CalculatorOperation(
    val symbol : String
){
    data object Add : CalculatorOperation("+")
    data object Subtract : CalculatorOperation("-")
    data object Multiply : CalculatorOperation("*")
    data object Divide : CalculatorOperation("/")
}