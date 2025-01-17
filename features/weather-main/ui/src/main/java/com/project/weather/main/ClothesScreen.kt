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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
fun ClothesMainScreen(modifier: Modifier = Modifier) {
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
                ClothesItem()
            }
        }
    }
}

@Composable
fun ClothesItem() {
    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Подобрали для вас",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Right,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "спортивный",
            fontSize = 13.sp,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Yellow,
            textAlign = TextAlign.Right,
            modifier = Modifier.padding(8.dp)
        )
//        ImageSlider(images = listOf(R.drawable.curtka_clth) )
        Box(
            modifier = Modifier
                .width(318.dp)
                .height(279.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFFF6F7F2))
                .border(2.dp, color = Color.White)
        ) {
            val imageBitmap: ImageBitmap = ImageBitmap.imageResource(R.drawable.girl_clth)
            Image(
                bitmap = imageBitmap,
                contentDescription = "DDD",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop

            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(13.dp)
                    .clip(RoundedCornerShape(35.dp))
                    .background(Color.White)
                    .border(4.dp, color = Color.White)
            ) {
                Text(   
                    text = "Кроп топ",
                    fontSize = 22.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    }

}

//@Composable
//fun ImageSlider(images: List<Int>) {
//    LazyRow (
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ){
//        items(images.size){ index ->
//            Box(
//                modifier = Modifier
//                    .width(318.dp)
//                    .height(279.dp)
//                    .clip(RoundedCornerShape(32.dp))
//                    .background(Color(0xFFF6F7F2))
//                    .border(2.dp, color = Color.White)
//            ){
//                val imageBitmap:ImageBitmap =  ImageBitmap.imageResource(id = images[index])
//                Image(
//                    bitmap = imageBitmap,
//                    contentDescription = "DDD",
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    contentScale = ContentScale.Crop
//                )
//            }
//        }
//    }
//
//}
//
//
