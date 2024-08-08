package com.example.theweather.ui.clothes.models

import com.example.clothesdb.models.enums.MainTypeEnumDBO
import com.example.weatherdata.clothes.models.ClothesType

class ClothesTypeUI (
    val title: String,
    val imageResId: Int,
    val clothesType: MainTypeEnumDBO
)