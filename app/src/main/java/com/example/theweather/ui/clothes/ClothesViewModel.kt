package com.example.theweather.ui.clothes

import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesdb.models.enums.MainTypeEnumDBO
import com.example.theweather.MAIN
import com.example.theweather.R
import com.example.theweather.ui.clothes.models.ClothesTypeUI
import com.example.weatherdata.clothes.repository.ClothesRepository
import com.example.weatherdata.clothes.useCase.ClothesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val useCase: Provider<ClothesUseCase>
) : ViewModel() {

    private var _clothesTypes: List<ClothesTypeUI> = listOf(
        ClothesTypeUI("Верх", R.drawable.clothes_type_top, MainTypeEnumDBO.HIGH),
        ClothesTypeUI("Низ", R.drawable.clothes_type_low, MainTypeEnumDBO.LOW),
        ClothesTypeUI("Обувь", R.drawable.clothes_type_shoes2, MainTypeEnumDBO.SHOES)
    )
    var clothesTypes = MutableLiveData<List<ClothesTypeUI>>().apply {
        value = _clothesTypes
    }

//    val text: LiveData<String> = _text

//    private val _clothes = Clothes(
//        id = 0,
//        color = "color",
//        name = "name",
//        size = "M",
//        material = "material",
//        tempMode = TempMode(1, 0.0, 0.0, MainEnumDBO.CLEAR),
//        clothesType = ClothesType(1, MainTypeEnumDBO.HIGH, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.OFFICIAL)
//    )
//    private val _clothesFromDb: LiveData<List<Clothes>>
//        get() {
//            TODO()
//        }



//    init {
//
//    }

//    private fun testDb() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                Log.d("VM","Request, DB:")
//
//                repository.get().insertClothes(_clothes)
//                val fromDb = repository.get().getAllClothes()
//
//                Log.d("VM","From DB: " + fromDb[0].id + fromDb[0].name)
//            }
//        }
//    }

    fun onBtnRestoreClick(){
        Log.d("ClothesVM", "Restore")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCase.get().restoreClothesUseCase()
            }
        }
    }
    public fun navToClothesRecommendations(clothesType: ClothesTypeUI){
        val bundle = bundleOf("clothesType" to clothesType.clothesType.toString())
        MAIN.navController.navigate(R.id.action_navigation_clothes_to_clothesRecommendationsListFragment, bundle)
    }
}