package com.project.weather.main


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.ColorPainter

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.weather.main.ui.R

@Preview
@Composable
fun HelloMainScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.hello_bg),
                    contentScale = ContentScale.FillBounds
                )
                .padding(
                    PaddingValues(
                        top = 0.dp,
                        start = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ImageFromLocalFile()
                Spacer(modifier = Modifier.height(12.dp))
                DrawRectangle()
            }
        }
    }
}

@Composable
fun DrawRectangle() {
    Box(
        modifier = Modifier
            .width(400.dp)
            .height(430.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White)
            .border(2.dp, color = Color.White)


    ){
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "TheWeather",
                fontSize = 48.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,

            )
            Spacer(modifier = Modifier.height(68.dp))
            Text(
                text = buildAnnotatedString {
                    append("Добро пожаловать в наше\n")
                    append("приложение!Здесь вы погрузитесь\n")
                    append("в мир стиля и моды.")
                },
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,

                )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = {},
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                contentPadding = PaddingValues(),
                modifier = Modifier
                    .width(295.dp)
                    .height(60.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF0F62FE), Color(0xFF71DBEE))
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
            )  {
                Text("Get Started", fontSize =  20.sp, textAlign = TextAlign.Center)
            }
        }

    }

}
@Composable
fun ImageFromLocalFile(){
    val imageBitmap: ImageBitmap = ImageBitmap.imageResource(R.drawable.man_bg)
    Image(
        bitmap = imageBitmap,
        contentDescription = "DDD",
        modifier = Modifier.size(450.dp)
    )
}


