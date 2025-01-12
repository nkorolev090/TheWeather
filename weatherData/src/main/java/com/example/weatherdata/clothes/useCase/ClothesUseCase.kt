package com.example.weatherdata.clothes.useCase

import com.example.clothesdb.models.enums.MainTypeEnumDBO
import com.example.weathercommon.data.RequestResult
import com.example.weatherdata.clothes.models.Clothes
import com.example.weatherdata.clothes.repository.ClothesRepository
import com.example.weatherdata.weather.models.MainEnum
import javax.inject.Inject
import javax.inject.Provider

class ClothesUseCase @Inject constructor(
    private val repository: Provider<ClothesRepository>
) {
    suspend fun getClothesListUseCase(): RequestResult<List<Clothes>> {
        val data = repository.get().getAllClothes()
        return if (data.isEmpty()){
            RequestResult.Error(message = "Empty response from bd")
        }else{
            RequestResult.Success(data)
        }
    }

    public suspend fun getClothesByMainTypeUseCase(mainType: MainTypeEnumDBO, temp: Double, mainEnum: MainEnum): RequestResult<List<Clothes>>{
        val data = repository.get().getClothesByMainType(mainType)
        return if (data.isEmpty()){
            RequestResult.Error(message = "Empty response from bd")
        }else{
            var filteredValue = data.filter { clothes -> clothes.tempMode.low < temp && clothes.tempMode.high > temp && clothes.tempMode.main == mainEnum }
            RequestResult.Success(filteredValue)
        }
    }

    suspend fun restoreClothesUseCase(){
        repository.get().restoreClothesDatabase()
    }
}