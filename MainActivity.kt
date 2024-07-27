package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                MyCalculator(state = state){
                    viewModel.onAction(it)
                }
            }
        }
    }
}

@Composable
fun MyCalculator(state: CalculatorState,onAction: (CalculatorAction)->Unit){

    val buttonSpacing = 8.dp
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            val numberModifier = Modifier
                .background(colorResource(id = R.color.numberBg))
                .aspectRatio(1f)
                .weight(1f)
            Text(text = state.number1 + (state.operation?.symbol?:"") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontSize = 70.sp,
                color = Color.White,
                maxLines = 2
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(symbol = "AC",
                    modifier = Modifier
                        .background(Color.Gray)
                        .aspectRatio(2f)
                        .weight(2f)) {
                        onAction(CalculatorAction.Clear)
                }
                CalculatorButton(symbol = "Del",
                    modifier = Modifier
                        .background(Color.Gray)
                        .aspectRatio(1f)
                        .weight(1f)) {
                        onAction(CalculatorAction.Delete)
                }
                CalculatorButton(symbol = "/",
                    modifier = Modifier
                        .background(colorResource(id = R.color.Orange))
                        .aspectRatio(1f)
                        .weight(1f)) {
                    onAction(CalculatorAction.Operations(CalculatorOperation.Divide))
                }
            }
            Spacer(modifier = Modifier.height(buttonSpacing))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(symbol = "7",
                    modifier = numberModifier) {
                    onAction(CalculatorAction.Number(7))
                }
                CalculatorButton(symbol = "8",
                    modifier = numberModifier) {
                    onAction(CalculatorAction.Number(8))
                }
                CalculatorButton(symbol = "9",
                    modifier = numberModifier) {
                    onAction(CalculatorAction.Number(9))
                }
                CalculatorButton(symbol = "x",
                    modifier = Modifier
                        .background(colorResource(id = R.color.Orange))
                        .aspectRatio(1f)
                        .weight(1f)) {
                    onAction(CalculatorAction.Operations(CalculatorOperation.Multiply))
                }
            }
            Spacer(modifier = Modifier.height(buttonSpacing))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(symbol = "4",
                    modifier = numberModifier) {
                    onAction(CalculatorAction.Number(4))
                }
                CalculatorButton(symbol = "5",
                    modifier = numberModifier) {
                    onAction(CalculatorAction.Number(5))
                }
                CalculatorButton(symbol = "6",
                    modifier = numberModifier) {
                    onAction(CalculatorAction.Number(6))
                }
                CalculatorButton(symbol = "-",
                    modifier = Modifier
                        .background(colorResource(id = R.color.Orange))
                        .aspectRatio(1f)
                        .weight(1f)) {
                    onAction(CalculatorAction.Operations(CalculatorOperation.Subtract))
                }
            }
            Spacer(modifier = Modifier.height(buttonSpacing))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(symbol = "1",
                    modifier = numberModifier) {
                    onAction(CalculatorAction.Number(1))
                }
                CalculatorButton(symbol = "2",
                    modifier = numberModifier) {
                    onAction(CalculatorAction.Number(2))
                }
                CalculatorButton(symbol = "3",
                    modifier = numberModifier) {
                    onAction(CalculatorAction.Number(3))
                }
                CalculatorButton(symbol = "+",
                    modifier = Modifier
                        .background(colorResource(id = R.color.Orange))
                        .aspectRatio(1f)
                        .weight(1f)) {
                    onAction(CalculatorAction.Operations(CalculatorOperation.Add))
                }
            }
            Spacer(modifier = Modifier.height(buttonSpacing))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(symbol = "0",
                    modifier = Modifier
                        .background(colorResource(id = R.color.numberBg))
                        .aspectRatio(2f)
                        .weight(2f)) {
                    onAction(CalculatorAction.Number(0))
                }
                CalculatorButton(symbol = ".",
                    modifier = Modifier
                        .background(colorResource(id = R.color.numberBg))
                        .aspectRatio(1f)
                        .weight(1f)) {
                    onAction(CalculatorAction.Decimal)
                }
                CalculatorButton(symbol = "=",
                    modifier = Modifier
                        .background(colorResource(id = R.color.Orange))
                        .aspectRatio(1f)
                        .weight(1f)) {
                    onAction(CalculatorAction.Calculate)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyCalculatorPreview(){
    MyCalculator(state = CalculatorState()){

    }
}