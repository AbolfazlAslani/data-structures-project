package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.IranianSans
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.YekanBakh
import kotlin.math.log

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MainSource()
//            GameScreenPlay.GameFirstPage(txt = "salam")


        }
    }
}

@Composable
fun MainSource(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable(route = "menu") {
            MenuScreen(navController)
        }
        composable("game/{number}") { backStackEntry ->
            backStackEntry.arguments?.getString("number")?.let { GameScreenSet(it) }
        }
    }

}

@Composable
fun MenuScreen(

    navController: NavHostController

){
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




                WelcomeButtons(txt = "شروع",
                    navController
                )
                WelcomeButtons(txt = "مراحل",
                    navController
                )
                WelcomeButtons(txt = "راهنمایی",
                    navController
                )
                WelcomeButtons(txt = "خروج",
                    navController
                )

            }
        


    }

}


@Composable
fun WelcomeButtons(txt : String
                   , navController: NavHostController
){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = {
                  navController.navigate("game/$txt")
        },
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
fun GameScreenSet(txt: String){
    GameScreenPlay.GameFirstPage(txt = txt)

}

//@Preview(showBackground = true)
//@Composable
//fun GameScreenPreview() {
//    MenuScreen()
//}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MenuScreen()
//}


