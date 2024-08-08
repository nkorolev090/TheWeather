package com.example.theweather.ui.clothes.models

import java.io.Serializable

data class ClothesUI(
    val colorText: String,
    val nameText: String,
    val materialText: String,
    val sizeText: String,
    val seasonText: String,
    val styleText: String
): Serializable