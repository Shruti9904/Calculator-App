package com.example.calculatorapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    symbol : String,
    modifier: Modifier,
    onclick:()->Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier= Modifier
            .clickable { onclick() }
            .clip(CircleShape)
            .then(modifier)
    ){
        Text(text = symbol,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 35.sp
        )
    }
}
