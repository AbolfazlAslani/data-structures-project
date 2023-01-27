package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.layout.BoxScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Objects


object GameScreenPlay {

    @Composable

    fun GameFirstPage(txt: String){
        sotonCounter=0

        Scaffold(bottomBar = { GameBottomBar() }) {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xff353b48)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp)
                    ,

                ) {

                    BoxColoumn(1)
                    BoxColoumn(2)
                    BoxColoumn(3)
                    BoxColoumn(4)
                    BoxColoumn(5)


                }
                IdChecker(message)

            }
        }

    }

}



val boxChanginColorsObject: MutableMap<String, String> = mutableMapOf()


var sotonCounter : Int = 0 
@Composable
fun BoxColoumn(soton: Int){
        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            sotonCounter +=1
            MapBox(1, sotonCounter)
            MapBox(2, sotonCounter)
            MapBox(3, sotonCounter)
            MapBox(4, sotonCounter)
            MapBox(5, sotonCounter)
            MapBox(6, sotonCounter)
            MapBox(7, sotonCounter)
            MapBox(8, sotonCounter)


        }





}



var message by mutableStateOf("")
@Composable
fun MapBox(radif: Int,sotons : Int){
    var number: Int by remember { mutableStateOf(0) }

    val coloring = remember {
        mutableStateListOf(
            Color(0xff44bd32),
            Color(0xffe1b12c),
            Color(0xff273c75),
            Color(0xffe84118),
            Color(0xff00a8ff),
            Color(0xff8c7ae6),
            Color(0xff8e44ad),
            Color.White
            )
    }
    var selected by remember { mutableStateOf(false) }
    var colorrest by remember {
        mutableStateOf(Color.White)
    }


    Button(
        onClick = {
//            message = "$radif:$sotons"
//            message = boxChanginColorsObject.toString()
            selected = !selected
            colorrest = coloring[number.toString().toInt()]

            if(number <7){
                number += 1
            }
            else{
                number = 0
            }
            // dont play the white color in the Game !
            if(colorrest != Color.White){
                boxChanginColorsObject["$radif:$sotons"] = "$colorrest"

            }
            // checks neighbours
            gameEngine(boxChanginColorsObject)

        },
        modifier = Modifier
            .padding(15.dp)
            .size(45.dp)
            .clip(RoundedCornerShape(10.dp))
            ,
            colors = ButtonDefaults.buttonColors(backgroundColor =colorrest)
    ){

        // This Shows Every Buttons Position
//        Text("$radif:$sotons", fontSize = 13.sp)
    }

}

@Composable
fun IdChecker(ids:String){
    if(ids == "trymore"){
        Text(
            text = "Try More ! \uD83D\uDE14",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color(0xffff6b6b),
            modifier = Modifier
                .wrapContentHeight(Alignment.Bottom)
                .height(150.dp)
        )
    }
    else if (ids == "goodjob"){
        Text(
            text = "Keep Going ! \uD83D\uDC4C",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color(0xff1dd1a1),
            modifier = Modifier
                .wrapContentHeight(Alignment.Bottom)
                .height(150.dp)
        )
    }
    else{
        Text(
            text = "Start Playing By Pressing Rectangles ! \uD83E\uDD73",
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = Color(0xff2e86de),
            modifier = Modifier
                .wrapContentHeight(Alignment.Bottom)
                .height(150.dp)
        )
    }

    
}

@Composable
fun GameBottomBar(){
    BottomAppBar(backgroundColor = Color(0xff7f8fa6)) {
        Row(
            modifier = Modifier
                .padding(3.dp)
            ,


            ) {

            ColorBar(color = Color(0xff44bd32))
            ColorBar(color = Color(0xffe1b12c))
            ColorBar(color = Color(0xff273c75))
            ColorBar(color = Color(0xffe84118))
            ColorBar(color = Color(0xff00a8ff))
            ColorBar(color = Color(0xff8c7ae6))
            ColorBar(color =Color(0xff8e44ad) )







        }
    }

}
@Composable
fun ColorBar(color: Color){

    Button(onClick ={
    }, modifier = Modifier
        .padding(6.dp)
        .size(40.dp),
        colors = ButtonDefaults.buttonColors(color)){
    }

}

// this is a engine that checks colors side by side
fun gameEngine(map: MutableMap<String,String>){

        val radifeMorabaeClickShode =map.keys.elementAt(map.keys.size - 1).toString()[0].toString().toInt()
        val sotoneMorabaeClickShode =map.keys.elementAt(map.keys.size - 1).toString()[2].toString().toInt()

        val downCondition =
            map["$radifeMorabaeClickShode:$sotoneMorabaeClickShode"]  ==
                    map[(radifeMorabaeClickShode - 1).toString() + ":" + sotoneMorabaeClickShode]
        val upCondition =
            map["$radifeMorabaeClickShode:$sotoneMorabaeClickShode"]  ==
                map[(radifeMorabaeClickShode + 1).toString() + ":" + sotoneMorabaeClickShode]

        val leftCondition =
            map["$radifeMorabaeClickShode:$sotoneMorabaeClickShode"]  ==
                map[radifeMorabaeClickShode.toString() + ":" + (sotoneMorabaeClickShode+1).toString()]
        val rightCondition =
         map["$radifeMorabaeClickShode:$sotoneMorabaeClickShode"]  ==
                map[radifeMorabaeClickShode.toString() + ":" + (sotoneMorabaeClickShode-1).toString()]

        val downRightCondition =
            map["$radifeMorabaeClickShode:$sotoneMorabaeClickShode"]  ==
                map[(radifeMorabaeClickShode -1).toString() + ":" + (sotoneMorabaeClickShode-1).toString()]

        val upRightCondition =
            map["$radifeMorabaeClickShode:$sotoneMorabaeClickShode"]  ==
                map[(radifeMorabaeClickShode +1).toString() + ":" + (sotoneMorabaeClickShode-1).toString()]

        val downLeftCondition =
            map["$radifeMorabaeClickShode:$sotoneMorabaeClickShode"]  ==
                map[(radifeMorabaeClickShode -1).toString() + ":" + (sotoneMorabaeClickShode+1).toString()]

        val upLeftCondition =
            map["$radifeMorabaeClickShode:$sotoneMorabaeClickShode"]  ==
                map[(radifeMorabaeClickShode +1).toString() + ":" + (sotoneMorabaeClickShode+1).toString()]

        if (
            downCondition || upCondition ||
            leftCondition|| rightCondition ||
            downRightCondition || upRightCondition||
            upLeftCondition || downLeftCondition
        ){
            message = "trymore"
        }
        else{
            message = "goodjob"
        }
//        message = "$radifeMorabaeClickShode:$sotoneMorabaeClickShode"

//    if (map["1:3"] == map["1:4"]){
//        message = "dobare talash kon"
//
//    }
//    else{
//        message = "bazi kon"
//    }

}



@Preview(showBackground = true)
@Composable
fun MainPreview() {

    GameScreenPlay.GameFirstPage("Salam")
}






