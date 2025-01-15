package com.example.weatherdata.clothes.models

import androidx.annotation.IntRange
import com.example.firebaseapi.clothes.models.enums.ClothesSubTypeEnum
import com.example.firebaseapi.clothes.models.enums.ClothesTypeEnum
import com.example.firebaseapi.clothes.models.enums.StyleEnum

data class ClothesType (
    val id: Long,
    val mainType: ClothesTypeEnum,
    val subType: ClothesSubTypeEnum,
    @IntRange(from = 0, to = 3) val layer: Int,
    val style: StyleEnum,
)

