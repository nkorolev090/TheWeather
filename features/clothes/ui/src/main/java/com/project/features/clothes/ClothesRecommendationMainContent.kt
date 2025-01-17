package com.project.features.clothes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import com.example.weathercommon.data.RequestResult
import com.project.features.clothes.models.ClothesUI
import com.project.features.clothes.models.RecommendationStyleSectionUI
import com.project.features.clothes.ui.R
import com.project.uikit.TheWeatherTheme

@Composable
fun ClothesRecommendationMainContent(modifier: Modifier = Modifier) {
    ClothesRecommendationMainContent(modifier = modifier, viewModel = viewModel())
}

@Composable
fun ClothesRecommendationMainContent(
    modifier: Modifier = Modifier,
    viewModel: ClothesRecommendationMainViewModel
) {
    val state by viewModel.state.collectAsState()
    val currentState = state
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when(currentState){
            is RequestResult.Error -> Unit
            is RequestResult.Loading -> Unit
            is RequestResult.Success -> {
                SuccessRecommendationMainContent(
                    currentState.data,
                    Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun SuccessRecommendationMainContent(sectionsList: List<RecommendationStyleSectionUI>, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .padding(start = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.pickedUpForYou),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        for (section in sectionsList){
            RecommendationStyleSection(section)
        }
    }
}

@Composable
fun RecommendationStyleSection(styleSectionUI: RecommendationStyleSectionUI, modifier: Modifier = Modifier){
    Column(
        modifier = modifier
    ) {
        Text(
            text = styleSectionUI.styleText,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(start = 50.dp)
        )
        ClothesSlider(
            styleSectionUI.clothesList,
            Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun ClothesSlider(clothesList: List<ClothesUI>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        for (clothes in clothesList) {
            item { ClothesItem(clothes) }
        }
    }
}

@Composable
fun ClothesItem(clothesUI: ClothesUI, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .height(IntrinsicSize.Min)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(40.dp)
            )
    ) {
        var isImageVisible by remember { mutableStateOf(true) }
        if (isImageVisible) {
            AsyncImage(
                model = clothesUI.imageURL,
                onState = { state ->
                    if (state is AsyncImagePainter.State.Error) {
                        isImageVisible = false
                        Log.d("ERROR", state.result.throwable.localizedMessage)
                    }
                },
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .clip(RoundedCornerShape(40.dp))
            )
        }
        Box(
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.BottomStart)
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
        ) {
            Text(
                text = clothesUI.nameText,
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview
@Composable
fun ClothesItemPreview() {
    TheWeatherTheme {
        ClothesItem(
            clothesUI = ClothesUI(
                "",
                nameText = "Кроп топ",
                imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg"
            ),
        )
    }
}



