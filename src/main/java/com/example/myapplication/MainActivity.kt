package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.IranianSans
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.YekanBakh

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MainScreen()

        }
    }
}

@Composable
fun MainScreen(){
    androidx.compose.material.Surface(
        color = Color(36, 55, 99) ,
        modifier = Modifier.fillMaxSize())
    {
            Text(text = "بازی نقشه ها",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top= 80.dp)
            ,
                style = TextStyle(
                    fontSize = 60.sp,
                    fontFamily = YekanBakh

                )
            ,
                color = Color.White

            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center


            ) {
                
                WelcomeButtons(txt = "شروع")
                WelcomeButtons(txt = "مراحل")
                WelcomeButtons(txt = "راهنمایی")
                WelcomeButtons(txt = "خروج")

            }
        


    }

}


@Composable
fun WelcomeButtons(txt : String){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = {  },
        interactionSource = interactionSource,

        modifier = Modifier
            .padding(15.dp)
            .width(250.dp)
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isPressed) Color.Gray else Color(255, 236, 183)
        ),


        ) {
        Text(text = txt, style = TextStyle(
            fontSize = 30.sp,
            fontFamily = YekanBakh

        )

        )
    }

}


@Composable
fun GameScreen(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Red
    ) {

    }

}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}


