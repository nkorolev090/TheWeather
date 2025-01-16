package com.project.features.clothes

import com.example.weathercommon.data.RequestResult
import com.example.weatherdata.clothes.models.TempMode
import com.example.weatherdata.clothes.repository.ClothesRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ClothesMainUseCase @Inject constructor(
    val clothesRepository: ClothesRepository
) {
    operator fun invoke(tempMode: TempMode) : Flow<RequestResult<>>
}