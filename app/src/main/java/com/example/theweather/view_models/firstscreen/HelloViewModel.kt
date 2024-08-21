package com.example.theweather.view_models.firstscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HelloViewModel:ViewModel() {
    val navigateToSecondActivity: Any
        get() {
            TODO()
        }
    private val __navigateToSecondActivity = MutableLiveData<Boolean>()
        get()= field

    fun onContinueButtonClick(){
        __navigateToSecondActivity.value=true
    }

    fun onNavigationComplete(){
        __navigateToSecondActivity.value=false
    }
}