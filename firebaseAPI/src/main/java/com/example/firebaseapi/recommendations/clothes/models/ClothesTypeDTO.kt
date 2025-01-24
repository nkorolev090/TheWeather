package com.example.firebaseapi.recommendations.clothes.models

import com.example.firebaseapi.recommendations.clothes.models.enums.MainTypeEnumDTO
import com.example.firebaseapi.recommendations.clothes.models.enums.StyleEnumDTO
import com.example.firebaseapi.recommendations.clothes.models.enums.SubTypeEnumDTO

data class ClothesTypeDTO (
    val id: Long? = null,
    val mainType: MainTypeEnumDTO? = null,
    val subType: SubTypeEnumDTO? = null,
    val layer: Int? = null,
    val style: StyleEnumDTO? = null,
)

