package com.project.features.recommendations.clothes.models

import com.example.firebaseapi.clothes.models.enums.StyleEnum
import com.example.weatherdata.clothes.models.ClothesStyle

data class RecommendationStyleSectionUI(
    val styleText: StyleEnum,
    val clothesList: List<ClothesUI>,
)

fun ClothesStyle.toRecommendationStyleSectionUI() : RecommendationStyleSectionUI =
    RecommendationStyleSectionUI(
        styleText = this.styleType,
        clothesList = this.clothes.map { it.toClothesUI() }
    )