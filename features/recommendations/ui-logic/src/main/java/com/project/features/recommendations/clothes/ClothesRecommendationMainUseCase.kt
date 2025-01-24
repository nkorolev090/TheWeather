package com.project.features.recommendations.clothes

import com.example.weathercommon.data.RequestResult
import com.example.weathercommon.data.map
import com.example.weatherdata.clothes.ClothesRepository
import com.project.features.recommendations.clothes.models.RecommendationStyleSectionUI
import com.project.features.recommendations.clothes.models.toRecommendationStyleSectionUI
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ClothesRecommendationMainUseCase @Inject constructor(
    private val clothesRepository: ClothesRepository
) {
    operator fun invoke(): Flow<RequestResult<List<RecommendationStyleSectionUI>>> =
        clothesRepository.getAllClothes()
            .map { requestResult ->
                requestResult.map { clothesStyles ->
                    clothesStyles.map { it.toRecommendationStyleSectionUI() } }
            }
}