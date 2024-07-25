package com.example.weatherdata.clothes.repository

import com.example.clothesdb.ClothesDatabase
import javax.inject.Inject

class ClothesRepository @Inject constructor(
    private val database: ClothesDatabase,
){
}