package com.project.features.weather_main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.uikit.TheWeatherTheme
import com.project.weather.main.ui.R

private val SEARCH_VIEW_CORNER_RADIUS = 20.dp

@Composable
internal fun SearchBarView(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .clickable(onClick = onClick)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(
                    SEARCH_VIEW_CORNER_RADIUS
                )
            )
    ) {
        Image(
            painter = painterResource(R.drawable.search_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(SEARCH_VIEW_CORNER_RADIUS))
        )
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(10.dp)
                .size(20.dp)
                .align(Alignment.CenterStart)
        )
        Text(
            text = stringResource(R.string.search_bar_title),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun SearchBarPreview(){
    TheWeatherTheme {
        SearchBarView({})
    }
}