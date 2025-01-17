package com.example.weatherdata.clothes.models

import com.example.firebaseapi.clothes.models.enums.StyleEnum

data class ClothesStyle (
    val styleType: StyleEnum,
    val clothes: List<Clothes>
)