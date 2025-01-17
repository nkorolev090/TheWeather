package com.example.weatherdata.clothes.models

data class Clothes(
     val id: Long,
     val color: String,
     val name: String,
     val material: String,
     val size: String,
     val imageURL: String,
     val tempMode: TempMode,
     val clothesType: ClothesType,
     val season: String
)