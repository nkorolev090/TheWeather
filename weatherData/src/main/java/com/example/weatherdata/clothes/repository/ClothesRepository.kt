package com.example.weatherdata.clothes.repository

import android.util.Log
import com.example.firebaseapi.clothes.ClothesAPI
import com.example.firebaseapi.clothes.models.ClothesDTO
import com.example.weatherdata.clothes.models.enums.ClothesSubTypeEnum
import com.example.weatherdata.clothes.models.enums.ClothesTypeEnum
import com.example.firebaseapi.clothes.models.enums.StyleEnum
import com.example.firebaseapi.clothes.models.enums.StyleEnumDTO
import com.example.weathercommon.data.RequestResult
import com.example.weathercommon.data.StatusCodeEnum
import com.example.weathercommon.data.toRequestResult
import com.example.weathercommon.firebase.firestoreRequestFlow
import com.example.weatherdata.clothes.models.Clothes
import com.example.weatherdata.clothes.models.ClothesStyle
import com.example.weatherdata.clothes.models.ClothesType
import com.example.weatherdata.clothes.models.TempMode
import com.example.weatherdata.clothes.toClothes
import com.example.weatherdata.clothes.toClothesDTO
import com.example.weatherdata.clothes.toClothesDTOList
import com.example.weatherdata.clothes.toStyleEnum
import com.example.weatherdata.clothes.toStyleEnumDTO
import com.example.weatherdata.weather.models.MainEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.internal.filterList
import javax.inject.Inject

class ClothesRepository @Inject constructor(
    private val clothesAPI: ClothesAPI
) {
    private val _clothesForDb = listOf(//mocked data
        ClothesStyle(
            styleType = StyleEnum.OFFICIAL,
            clothes = listOf(
                Clothes(
                    id = 0,
                    color = "color4",
                    name = "test name4",
                    material = "material4",
                    size = "size4",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 20.0, 40.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season4"
                ),
                Clothes(
                    id = 0,
                    color = "color5",
                    name = "test name5",
                    material = "material",
                    size = "size5",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 0.0, 15.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season5"
                ),
                Clothes(
                    id = 0,
                    color = "color6",
                    name = "test name6",
                    material = "material6",
                    size = "size6",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 10.0, 30.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season6"
                ),
                Clothes(
                    id = 0,
                    color = "color7",
                    name = "test name7",
                    material = "material7",
                    size = "size7",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 0.0, 300.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season7"
                ),
                Clothes(
                    id = 0,
                    color = "color8",
                    name = "test name8",
                    material = "material8",
                    size = "size8",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 20.0, 30.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season8"
                )
            )
        ),
        ClothesStyle(
            styleType = StyleEnum.SPORT,
            clothes = listOf(
                Clothes(
                    id = 0,
                    color = "color4",
                    name = "test name4",
                    material = "material4",
                    size = "size4",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 20.0, 40.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season4"
                ),
                Clothes(
                    id = 0,
                    color = "color5",
                    name = "test name5",
                    material = "material",
                    size = "size5",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 0.0, 15.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season5"
                ),
                Clothes(
                    id = 0,
                    color = "color6",
                    name = "test name6",
                    material = "material6",
                    size = "size6",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 10.0, 30.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season6"
                ),
                Clothes(
                    id = 0,
                    color = "color7",
                    name = "test name7",
                    material = "material7",
                    size = "size7",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 0.0, 300.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season7"
                ),
                Clothes(
                    id = 0,
                    color = "color8",
                    name = "test name8",
                    material = "material8",
                    size = "size8",
                    imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
                    tempMode = TempMode(0, 20.0, 30.0, MainEnum.CLEAR),
                    clothesType = ClothesType(
                        0,
                        ClothesTypeEnum.LOW,
                        ClothesSubTypeEnum.T_SHIRT,
                        0
                    ),
                    season = "season8"
                )
            )
        )
    )

    //region DEPRECATED METHODS

    //    suspend fun getAllClothes(): List<Clothes> {
//       return database.clothesDao.getAll().map {
//           var tempMode = database.tempModeDao.getFromId(it.tempModeId).first()
//           var clothesType = database.clothesTypeDao.getFromId(it.clothesTypeId).first()
//           if(tempMode == null || clothesType == null){
//               return emptyList()
//           }
//           it.toClothes(tempMode, clothesType)
//       }
//    }
//
//    suspend fun restoreClothesDatabase(){
//        Log.d("ClothesRepo", "Restore")
//        database.clothesDao.clean()
//        for(item in _clothesForDb){
//            insertClothes(item)
//        }
//    }
//
//    suspend fun getClothesByMainType(mainType: MainTypeEnumDBO): List<Clothes>{
//        var allClothes = database.clothesDao.getAll().map {
//            var tempMode = database.tempModeDao.getFromId(it.tempModeId).first()
//            var clothesType = database.clothesTypeDao.getFromId(it.clothesTypeId).first()
//            if(tempMode == null || clothesType == null){
//                return emptyList()
//            }
//            it.toClothes(tempMode, clothesType)
//        }
//
//        return allClothes.filter { clothes -> clothes.clothesType.mainType == mainType }
//    }
//
//    suspend fun insertClothes(clothes: Clothes) {
//        var clothesTypeId = insertClothesType(clothes.clothesType)
//        var tempModeId = insertTempMode(clothes.tempMode)
//
//        database.clothesDao.insert(clothes.toClothesDBO(clothesTypeId, tempModeId))
//    }
//
//    suspend fun getAllClothesType(): List<ClothesType> {
//        return database.clothesTypeDao.getAll().map { it.toClothesType() }
//    }
//
//    suspend fun insertClothesType(clothesType: ClothesType) : Long {
//        return database.clothesTypeDao.insert(clothesType.toClothesTypeDBO())
//    }
//
//    suspend fun insertTempMode(tempMode: TempMode) : Long {
//        return database.tempModeDao.insert(tempMode.toTempModeDBO())
//
//    }
//}


//private fun ClothesTypeDTO.toClothesType(): ClothesType {
//    return  ClothesType(
//        id = id,
//        mainType = mainType,
//        subType = subType,
//        layer = layer,
//        style = style)
//}

    //endregion

    suspend fun testFirebase() {
//        val clothesDTOList = _clothesForDb.toClothesDTOList()
//        for (clothes in clothesDTOList) {
//            clothesAPI.writeNewClothes(clothes)
//        }

        getAllClothes().collect { result ->
            Log.d("FIRESTORE", result.data.toString())
        }
    }

    fun getAllClothes(): Flow<RequestResult<List<ClothesStyle>>> =
        firestoreRequestFlow {
            clothesAPI.getClothesRecommendation()
        }.map { requestResult ->
            requestResult.toRequestResult(
                successAction = { successResult ->
                    if (successResult.data.isEmpty()) {
                        RequestResult.Error(code = StatusCodeEnum.NO_CONTENT)
                    } else {
                        RequestResult.Success(successResult.data.toClothesStyle())
                    }
                })
        }
}

private fun List<ClothesDTO?>.toClothesStyle(): List<ClothesStyle> {
    val styleMap: MutableMap<StyleEnum, MutableList<Clothes>> = mutableMapOf()
    val clothesList = this.filterNotNull()

    clothesList.forEach { clothesDTO ->
        when (clothesDTO.clothesType?.style) {
            StyleEnumDTO.OFFICIAL -> styleMap.putOrCreate(clothesDTO, StyleEnum.OFFICIAL)
            StyleEnumDTO.SPORT -> styleMap.putOrCreate(clothesDTO, StyleEnum.SPORT)
            null -> Unit
        }
    }

    return styleMap.map { entry ->
        ClothesStyle(
            styleType = entry.key,
            clothes = entry.value
        )
    }
}

private fun MutableMap<StyleEnum, MutableList<Clothes>>.putOrCreate(
    clothesDTO: ClothesDTO,
    styleEnum: StyleEnum
) {
    val clothes = clothesDTO.toClothes() ?: return

    val styleItem = this[styleEnum]
    if (styleItem != null) {
        styleItem.add(clothes)
    } else {
        this[styleEnum] = mutableListOf(
            clothes
        )
    }
}
