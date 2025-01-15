package com.example.weatherdata.clothes.repository

import android.util.Log
import com.example.firebaseapi.clothes.ClothesAPI
import com.example.firebaseapi.clothes.models.ClothesDTO
import com.example.firebaseapi.clothes.models.enums.ClothesSubTypeEnum
import com.example.firebaseapi.clothes.models.enums.ClothesTypeEnum
import com.example.firebaseapi.clothes.models.enums.StyleEnum
import com.example.weathercommon.data.RequestResult
import com.example.weathercommon.data.StatusCodeEnum
import com.example.weathercommon.data.toRequestResult
import com.example.weathercommon.firebase.firestoreRequestFlow
import com.example.weatherdata.clothes.models.Clothes
import com.example.weatherdata.clothes.models.ClothesType
import com.example.weatherdata.clothes.models.TempMode
import com.example.weatherdata.clothes.toClothesDTO
import com.example.weatherdata.weather.models.MainEnum
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClothesRepository @Inject constructor(
    private val clothesAPI: ClothesAPI
) {
    private val _clothesForDb = listOf(//mocked data
        Clothes(
            id = 0,
            color = "color1",
            name = "test name1",
            material = "material",
            size = "size1",
            imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
            tempMode = TempMode(0, 0.0, 20.0, MainEnum.CLOUDS),
            clothesType = ClothesType(
                0,
                ClothesTypeEnum.LOW,
                ClothesSubTypeEnum.T_SHIRT,
                0,
                StyleEnum.OFFICIAL
            ),
            season = "season1"
        ),
        Clothes(
            id = 0,
            color = "color2",
            name = "test name2",
            material = "material2",
            size = "size2",
            imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
            tempMode = TempMode(0, 10.0, 20.0, MainEnum.CLEAR),
            clothesType = ClothesType(
                0,
                ClothesTypeEnum.LOW,
                ClothesSubTypeEnum.T_SHIRT,
                0,
                StyleEnum.OFFICIAL
            ),
            season = "season2"
        ),
        Clothes(
            id = 0,
            color = "color3",
            name = "test name3",
            material = "material3",
            size = "size3",
            imageURL = "https://cdn.sportmaster.ru/upload/mdm/media_content/resize/ca6/768_1024_9f7e/51158700299.jpg",
            tempMode = TempMode(0, -30.0, 10.0, MainEnum.CLEAR),
            clothesType = ClothesType(
                0,
                ClothesTypeEnum.LOW,
                ClothesSubTypeEnum.T_SHIRT,
                0,
                StyleEnum.OFFICIAL
            ),
            season = "season3"
        ),
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
                0,
                StyleEnum.OFFICIAL
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
                0,
                StyleEnum.OFFICIAL
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
                0,
                StyleEnum.OFFICIAL
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
                0,
                StyleEnum.OFFICIAL
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
                0,
                StyleEnum.OFFICIAL
            ),
            season = "season8"
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
        for (clothes in _clothesForDb) {
            clothesAPI.writeNewClothes(clothes.toClothesDTO())
        }

        val result = clothesAPI.getClothesRecommendation()

        Log.d("FIRESTORE", result.toString())
    }

    fun getAllClothes(): Flow<RequestResult<List<ClothesDTO>>> =
        firestoreRequestFlow {
            clothesAPI.getClothesRecommendation()
        }.map { requestResult ->
            requestResult.toRequestResult(
                successAction = { successResult ->
                    if (successResult.data.isEmpty()) {
                        RequestResult.Error(code = StatusCodeEnum.NO_CONTENT)
                    } else {
                        RequestResult.Success(successResult.data.filterNotNull())
                    }
                })
        }
}
