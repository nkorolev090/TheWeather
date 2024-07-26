package com.example.weatherdata.clothes.models

import androidx.annotation.IntRange
import com.example.clothesdb.models.enums.MainTypeEnumDBO
import com.example.clothesdb.models.enums.StyleEnumDBO
import com.example.clothesdb.models.enums.SubTypeEnumDBO

data class ClothesType (
    val id: Long,
    val mainType: MainTypeEnumDBO,
    val subType: SubTypeEnumDBO,
    @IntRange(from = 0, to = 3) val layer: Int,
    val style: StyleEnumDBO,
)

