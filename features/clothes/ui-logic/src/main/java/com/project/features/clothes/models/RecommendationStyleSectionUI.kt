package com.project.features.clothes.models

import com.example.weatherdata.clothes.models.ClothesStyle

data class RecommendationStyleSectionUI(
    val styleText: String,
    val clothesList: List<ClothesUI>,
)

fun ClothesStyle.toRecommendationStyleSectionUI() : RecommendationStyleSectionUI =
    RecommendationStyleSectionUI(
        styleText = this.styleType.toString(),
        clothesList = this.clothes.map { it.toClothesUI() }
    )