package com.project.features.clothes.models

import com.example.weatherdata.clothes.models.Clothes

data class ClothesUI(
    val colorText: String,
    val nameText: String,
    val imageURL: String,
)

fun Clothes.toClothesUI() : ClothesUI {
    val name = this.clothesType.subType.toTypeString()
    ClothesUI(
        colorText = color,
        nameText = name,
        imageURL = imageURL
    )
}
