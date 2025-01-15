package com.example.firebaseapi.clothes.models

import com.example.firebaseapi.clothes.models.enums.MainEnumDTO

data class TempModeDTO(
    val id: Long? = null,
    val low: Double? = null,
    val high: Double? = null,
    val main: MainEnumDTO? = null,
)