package com.project.features.recommendations.advice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weathercommon.data.RequestResult
import com.project.features.recommendations.ui.R

@Composable
fun AdviceContent(modifier: Modifier = Modifier) {
    AdviceContent(viewModel = viewModel(), modifier = modifier)
}

@Composable
internal fun AdviceContent(viewModel: AdviceMainViewModel, modifier: Modifier = Modifier){
    val state by viewModel.state.collectAsState()
    when(val currentState = state){
        is RequestResult.Error -> Unit
        is RequestResult.Loading -> Unit
        is RequestResult.Success -> SuccessAdviceContent(currentState.data, modifier)
    }
}

@Composable
private fun SuccessAdviceContent(advice: AdviceUI, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .background(Color.Transparent, RoundedCornerShape(CORNER_RADIUS.dp))
    ){
        Image(
            painter = painterResource(R.drawable.advice_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(CORNER_RADIUS.dp))
        )
        Text(
            text = advice.text,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 18.dp, start = 20.dp, end = 20.dp)
                .align(Alignment.Center)
        )
    }
}

private const val CORNER_RADIUS = 30