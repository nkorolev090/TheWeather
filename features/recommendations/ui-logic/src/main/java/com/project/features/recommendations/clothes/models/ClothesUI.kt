package com.project.features.recommendations.clothes.models

import com.example.weatherdata.clothes.models.Clothes

data class ClothesUI(
    val colorText: String,
    val nameText: String,
    val imageURL: String,
)

fun Clothes.toClothesUI() : ClothesUI {
    //val name = this.clothesType.subType.toTypeString()
    return ClothesUI(
        colorText = color,
        nameText = name,
        imageURL = imageURL
    )
}
