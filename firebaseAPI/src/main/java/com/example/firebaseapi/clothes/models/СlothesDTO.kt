package com.example.firebaseapi.clothes.models

data class ClothesDTO(
    val id: Long? = null,
    val color: String? = null,
    val name: String? = null,
    val material: String? = null,
    val size: String? = null,
    val imageURL: String? = null,
    val tempMode: TempModeDTO? = null,
    val clothesType: ClothesTypeDTO? = null,
    val season: String? = null,
)