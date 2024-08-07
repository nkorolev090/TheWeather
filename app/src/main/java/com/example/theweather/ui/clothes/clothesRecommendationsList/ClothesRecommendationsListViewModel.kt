package com.example.theweather.ui.clothes.clothesRecommendationsList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theweather.ui.clothes.clothesRecommendations.toClothesUI
import com.example.theweather.ui.clothes.models.ClothesUI
import com.example.theweather.ui.home.models.ErrorUI
import com.example.weatherdata.clothes.models.Clothes
import com.example.weatherdata.clothes.useCase.ClothesUseCase
import com.example.weatherdata.weather.repository.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ClothesRecommendationsListViewModel @Inject constructor(
    private val useCase: Provider<ClothesUseCase>
) : ViewModel() {

    var clothesList = MutableLiveData<List<ClothesUI>>().apply {
        value = emptyList()
    }

    var errorUI = MutableLiveData<ErrorUI>().apply {
        value = ErrorUI()
    }
    init {
        getClothesList()
    }
    private fun getClothesList(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = useCase.get().getClothesListUseCase()

                Log.d("VM","Response: ")
                setupFields(response)
            }
        }
    }

    private fun setupFields(response: RequestResult<List<Clothes>>) {
        when (response) {
            is RequestResult.Success -> {
                var notNullData = checkNotNull(response.data).map { it.toClothesUI() }
                clothesList.postValue(notNullData)
                Log.d("VM", "Success: clothes ")
            }

            is RequestResult.Error -> {
                errorUI.postValue(ErrorUI(response.message.toString(), response.code.toString()) )
                Log.e("VM", "Error: clothes")
            }

            is RequestResult.InProgress -> {
                Log.i("VM", "InProgress")
            }
        }
    }
}