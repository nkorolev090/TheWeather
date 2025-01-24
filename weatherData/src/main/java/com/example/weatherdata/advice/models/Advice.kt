package com.example.weatherdata.advice.models

import com.example.firebaseapi.recommendations.advice.models.AdviceDTO
import com.example.weatherdata.clothes.models.TempMode
import com.example.weatherdata.clothes.toTempMode

data class Advice(
    val tempMode: TempMode,
    val text: String
)

fun AdviceDTO.toAdvice(): Advice? =
    tempModeDTO?.toTempMode()?.let {
        Advice(
            tempMode = it,
            text = text ?: ""
        )
    }

