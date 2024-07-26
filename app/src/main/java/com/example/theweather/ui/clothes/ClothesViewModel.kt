package com.example.theweather.ui.clothes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothesdb.models.enums.MainEnumDBO
import com.example.clothesdb.models.enums.MainTypeEnumDBO
import com.example.clothesdb.models.enums.StyleEnumDBO
import com.example.clothesdb.models.enums.SubTypeEnumDBO
import com.example.weatherdata.clothes.models.Clothes
import com.example.weatherdata.clothes.models.ClothesType
import com.example.weatherdata.clothes.models.TempMode
import com.example.weatherdata.clothes.repository.ClothesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val repository: Provider<ClothesRepository>
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _clothes = Clothes(
        id = 0,
        color = "color",
        name = "name",
        size = "M",
        material = "material",
        tempMode = TempMode(1, 0.0, 0.0, MainEnumDBO.CLEAR),
        clothesType = ClothesType(1, MainTypeEnumDBO.HIGH, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.OFFICIAL)
    )
//    private val _clothesFromDb: LiveData<List<Clothes>>
//        get() {
//            TODO()
//        }


    init {
        testDb()
    }
    private fun testDb() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("VM","Request, DB:")

                repository.get().insertClothes(_clothes)
                val fromDb = repository.get().getAllClothes()

                Log.d("VM","From DB: " + fromDb[0].id + fromDb[0].name)
            }
        }
    }
}