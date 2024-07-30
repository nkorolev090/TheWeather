package com.example.theweather.ui.clothes.clothesRecommendations

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesdb.models.enums.StyleEnumDBO
import com.example.theweather.MAIN
import com.example.theweather.R
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
class ClothesRecommendationsViewModel @Inject constructor(
    private val repository: Provider<ClothesUseCase>
) : ViewModel() {

    var backBtnText = "Назад"

    var clothesList = MutableLiveData<List<ClothesUI>>().apply {
        value = null
    }

    var errorUI = MutableLiveData<ErrorUI>().apply {
        value = ErrorUI()
    }

    private fun getClothesList(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.get().getClothesListUseCase()

                Log.d("VM","Response: ")
                setupFields(response)
            }
        }
    }

    private fun setupFields(response: RequestResult<List<Clothes>>) {
        when (response) {
            is RequestResult.Success -> {
                var notNullData = checkNotNull(response.data)
                clothesList.postValue(notNullData.map { it.toClothesUI() })
                Log.d("VM", "Success: clothes ")
            }

            is RequestResult.Error -> {
                errorUI.postValue(ErrorUI(response.message.toString(), response.code.toString()) )
                Log.e("VM", "Error")
            }

            is RequestResult.InProgress -> {
                Log.i("VM", "InProgress")
            }
        }
    }
    public fun navToClothesFragment(){
        MAIN.navController.navigate(R.id.action_clothesRecommendationsFragment_to_navigation_clothes)
    }
}

private fun Clothes.toClothesUI(): ClothesUI {
    return ClothesUI(
        colorText = color,
        nameText = name,
        materialText = material,
        sizeText = size,
        seasonText = season,
        styleText = clothesType.style.styleEnumToString()
    )
}

private fun StyleEnumDBO.styleEnumToString(): String {//надо менять в дб на String и уносить enum
    return "Официальный"
}
