package com.example.theweather.ui.clothes.clothesRecommendations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theweather.MAIN
import com.example.theweather.R

class ClothesRecommendationsViewModel : ViewModel() {

    var backBtnText = "Назад"

    public fun navToClothesFragment(){
        MAIN.navController.navigate(R.id.action_clothesRecommendationsFragment_to_navigation_clothes)
    }
}