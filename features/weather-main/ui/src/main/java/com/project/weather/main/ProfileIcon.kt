package com.project.weather.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.uikit.TheWeatherTheme
import com.project.weather.main.ui.R

@Composable
internal fun ProfileIcon(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .height(IntrinsicSize.Min)
        .width(IntrinsicSize.Min)
        .background(
            color = Color.Transparent,
            shape = CircleShape)
        .clickable(onClick = onClick)
    ){
        Image(
            painter = painterResource(R.drawable.search_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Icon(
            painter = painterResource(R.drawable.ic_profile),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
        )
    }
}

@Preview
@Composable
private fun ProfileIconPreview(){
    TheWeatherTheme {
        ProfileIcon({})
    }
}