package com.project.features.recommendations.advice

import com.example.weatherdata.advice.models.Advice

data class AdviceUI (
    val text: String,
)

fun Advice.toAdviceUI() : AdviceUI =
    AdviceUI(
        text = text
    )