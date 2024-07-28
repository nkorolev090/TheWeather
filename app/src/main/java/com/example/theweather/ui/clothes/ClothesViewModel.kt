package com.example.theweather.ui.clothes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theweather.MAIN
import com.example.theweather.R
import com.example.theweather.ui.clothes.models.ClothesTypeUI
import com.example.weatherdata.clothes.repository.ClothesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val repository: Provider<ClothesRepository>
) : ViewModel() {

    private var _clothesTypes: List<ClothesTypeUI> = listOf(
        ClothesTypeUI("Верх", R.drawable.clothes_type_top),
        ClothesTypeUI("Низ", R.drawable.clothes_type_low),
        ClothesTypeUI("Обувь", R.drawable.clothes_type_shoes2)
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

    public fun navToClothesRecommendations(clothesType: ClothesTypeUI){
        MAIN.navController.navigate(R.id.action_navigation_clothes_to_clothesRecommendationsFragment)
    }
}