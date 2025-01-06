package com.project.weather.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.weather.main.ui.R

private val TOP_BAR_RADIUS = 45.dp

@Composable
internal fun TopBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(175.dp)
            .background(
                Color.Yellow,
                RoundedCornerShape(bottomStart = TOP_BAR_RADIUS, bottomEnd = TOP_BAR_RADIUS)
            )
    ) {
        Image(
            painter = painterResource(R.drawable.map),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(bottomStart = TOP_BAR_RADIUS, bottomEnd = TOP_BAR_RADIUS))
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.inverseOnSurface,
                    RoundedCornerShape(bottomStart = TOP_BAR_RADIUS, bottomEnd = TOP_BAR_RADIUS)
                )
        )
    }
}
