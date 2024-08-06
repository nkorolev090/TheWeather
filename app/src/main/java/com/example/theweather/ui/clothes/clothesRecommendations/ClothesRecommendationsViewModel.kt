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
import com.example.weatherdata.clothes.repository.ClothesRepository
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
    private val useCase: Provider<ClothesUseCase>
) : ViewModel() {

    val backBtnText = "Назад"
    val styleText = "Стиль"
    val seasonText = "Сезон"
    val materialText = "Материал"

    private var clothesList = MutableLiveData<List<ClothesUI>>().apply {
        value = null
    }

    public var currentClothes = MutableLiveData<ClothesUI>().apply {
        value = ClothesUI(
            colorText = "-",
            nameText = "-",
            materialText = "-",
            seasonText = "-",
            sizeText = "-",
            styleText = "-",
        )
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
                currentClothes.postValue(notNullData[0])
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
    return when(this){
        StyleEnumDBO.SPORT -> "Спортивный"
        StyleEnumDBO.OFFICIAL -> "Официальный"
    }
}
