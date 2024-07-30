package com.example.weatherdata.clothes.useCase

import com.example.weatherdata.clothes.models.Clothes
import com.example.weatherdata.clothes.repository.ClothesRepository
import com.example.weatherdata.weather.repository.RequestResult
import javax.inject.Inject
import javax.inject.Provider

class ClothesUseCase @Inject constructor(
    private val repository: Provider<ClothesRepository>
) {
    public suspend fun getClothesListUseCase(): RequestResult<List<Clothes>>{
        val data = repository.get().getAllClothes()
        return if (data.isEmpty()){
            RequestResult.Error(message = "Empty response from bd")
        }else{
            RequestResult.Success(data)
        }
    }
}