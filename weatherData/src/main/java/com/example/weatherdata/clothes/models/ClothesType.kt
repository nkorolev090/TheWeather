package com.example.weatherdata.clothes.models

import androidx.annotation.IntRange
import com.example.weatherdata.clothes.models.enums.ClothesSubTypeEnum
import com.example.weatherdata.clothes.models.enums.ClothesTypeEnum

data class ClothesType (
    val id: Long,
    val mainType: ClothesTypeEnum,
    val subType: ClothesSubTypeEnum,
    @IntRange(from = 0, to = 3) val layer: Int,
)

