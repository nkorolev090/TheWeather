package com.example.firebaseapi.recommendations.advice.models

import com.example.firebaseapi.recommendations.models.TempModeDTO

data class AdviceDTO (
    val tempModeDTO: TempModeDTO? = null,
    val text: String? = null
)