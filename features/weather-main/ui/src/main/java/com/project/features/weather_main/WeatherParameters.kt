package com.project.features.weather_main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.uikit.TheWeatherTheme
import com.project.features.weather_main.models.WeatherParameterUI
import com.project.weather.main.ui.R

@Composable
internal fun WeatherParameters(
    parameter1: WeatherParameterUI,
    parameter2: WeatherParameterUI,
    parameter3: WeatherParameterUI,
    parameter4: WeatherParameterUI,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
    ) {
        ParametersColumn(parameter1, parameter2, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(13.dp))
        ParametersColumn(parameter3, parameter4, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ParametersColumn(
    parameter1: WeatherParameterUI,
    parameter2: WeatherParameterUI,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
    ) {
        ParameterView(
            parameter1,
            modifier = Modifier
                .weight(parameter1.toSpaceWeight())
        )

        Spacer(modifier = Modifier.height(13.dp))

        ParameterView(
            parameter2,
            modifier = Modifier
                .weight(parameter2.toSpaceWeight())
        )
    }
}

@Composable
private fun ParameterView(
    parameter: WeatherParameterUI,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(Color.Transparent, RoundedCornerShape(CORNER_RADIUS))
            .height(IntrinsicSize.Min)
    ) {
        Image(
            painter = painterResource(parameter.toBackgroundId()),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(CORNER_RADIUS))
        )
        Text(
            text = parameter.toAnnotatedString(),
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            minLines = 2,
            maxLines = 2,
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun ParameterViewPreview(){
    TheWeatherTheme {
        ParameterView(
            parameter = WeatherParameterUI.Humidity("20%","влажность"),
        )
    }
}

@Composable
private fun WeatherParameterUI.toAnnotatedString(): AnnotatedString =
    when (this) {
        is WeatherParameterUI.FeelsLikeTemperature -> buildAnnotatedString {
            withStyle(SpanStyle(
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = MaterialTheme.typography.titleSmall.fontWeight)
            ) {
                append(this@toAnnotatedString.mediumText)
            }
        }

        is WeatherParameterUI.Humidity -> buildAnnotatedString {
            withStyle(SpanStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                baselineShift = BaselineShift.Superscript)
            ) {
                append(this@toAnnotatedString.largeText)
            }
            append("\n")
            withStyle(SpanStyle(
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = MaterialTheme.typography.titleSmall.fontWeight)
            ) {
                append(this@toAnnotatedString.mediumText)
            }
        }

        is WeatherParameterUI.Temperature -> buildAnnotatedString {
            withStyle(SpanStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight)
            ) {
                append(this@toAnnotatedString.largeText)
            }
        }

        is WeatherParameterUI.WindSpeed -> buildAnnotatedString {
            withStyle(SpanStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight)
            ) {
                append(this@toAnnotatedString.largeText)
            }
            append("\n")
            withStyle(SpanStyle(
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight)
            ) {
                append(this@toAnnotatedString.smallText)
            }
        }
    }

private fun WeatherParameterUI.toSpaceWeight(): Float =
    when (this) {
        is WeatherParameterUI.FeelsLikeTemperature -> 3.5f
        is WeatherParameterUI.Humidity -> 5.5f
        is WeatherParameterUI.Temperature -> 5.5f
        is WeatherParameterUI.WindSpeed -> 3.5f
    }

private fun WeatherParameterUI.toBackgroundId(): Int =
    when (this) {
        is WeatherParameterUI.FeelsLikeTemperature -> R.drawable.gradient5
        is WeatherParameterUI.Humidity -> R.drawable.gradient1
        is WeatherParameterUI.Temperature -> R.drawable.gradient4
        is WeatherParameterUI.WindSpeed -> R.drawable.gradient2
    }

private val CORNER_RADIUS = 30.dp

private val parameters = listOf(
    WeatherParameterUI.Humidity("20%","влажность"),
    WeatherParameterUI.WindSpeed("5 м/с","скорость ветра"),
    WeatherParameterUI.FeelsLikeTemperature("ощущается как: +18℃ "),
    WeatherParameterUI.Temperature("+21℃ "),
)

@Preview
@Composable
private fun WeatherParameterPreview(){
    TheWeatherTheme {
        WeatherParameters(parameters[0], parameters[1], parameters[2], parameters[3], modifier = Modifier.height(IntrinsicSize.Min).fillMaxWidth())
    }
}