package com.example.weatherdata.clothes.repository

import android.util.Log
import com.example.clothesdb.ClothesDatabase
import com.example.clothesdb.models.ClothesDBO
import com.example.clothesdb.models.ClothesTypeDBO
import com.example.clothesdb.models.TempModeDBO
import com.example.clothesdb.models.enums.MainEnumDBO
import com.example.clothesdb.models.enums.MainTypeEnumDBO
import com.example.clothesdb.models.enums.StyleEnumDBO
import com.example.clothesdb.models.enums.SubTypeEnumDBO
import com.example.weatherdata.clothes.models.Clothes
import com.example.weatherdata.clothes.models.ClothesType
import com.example.weatherdata.clothes.models.TempMode
import com.example.weatherdata.weather.models.MainEnum
import javax.inject.Inject

class ClothesRepository @Inject constructor(
    private val database: ClothesDatabase,
){
    private val _clothesForDb = listOf(//mocked data
        Clothes(
            id = 0,
            color = "color1",
            name = "test name1",
            material = "material",
            size = "size1",
            tempMode = TempMode(0, 0.0, 20.0, MainEnum.CLOUDS),
            clothesType = ClothesType(0, MainTypeEnumDBO.HIGH, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.OFFICIAL),
            season = "season1"
        ),
        Clothes(
            id = 0,
            color = "color2",
            name = "test name2",
            material = "material2",
            size = "size2",
            tempMode = TempMode(0, 10.0, 20.0, MainEnum.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.LOW, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.SPORT),
            season = "season2"
        ),
        Clothes(
            id = 0,
            color = "color3",
            name = "test name3",
            material = "material3",
            size = "size3",
            tempMode = TempMode(0, -30.0, 10.0, MainEnum.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.SHOES, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.SPORT),
            season = "season3"
        ),
        Clothes(
            id = 0,
            color = "color4",
            name = "test name4",
            material = "material4",
            size = "size4",
            tempMode = TempMode(0, 20.0, 40.0, MainEnum.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.HIGH, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.OFFICIAL),
            season = "season4"
        ),
        Clothes(
            id = 0,
            color = "color5",
            name = "test name5",
            material = "material",
            size = "size5",
            tempMode = TempMode(0, 0.0, 15.0, MainEnum.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.HIGH, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.OFFICIAL),
            season = "season5"
        ),
        Clothes(
            id = 0,
            color = "color6",
            name = "test name6",
            material = "material6",
            size = "size6",
            tempMode = TempMode(0, 10.0, 30.0, MainEnum.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.LOW, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.SPORT),
            season = "season6"
        ),
        Clothes(
            id = 0,
            color = "color7",
            name = "test name7",
            material = "material7",
            size = "size7",
            tempMode = TempMode(0, 0.0, 300.0, MainEnum.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.SHOES, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.SPORT),
            season = "season7"
        ),
        Clothes(
            id = 0,
            color = "color8",
            name = "test name8",
            material = "material8",
            size = "size8",
            tempMode = TempMode(0, 20.0, 30.0, MainEnum.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.HIGH, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.OFFICIAL),
            season = "season8"
        )
    )

    suspend fun getAllClothes(): List<Clothes> {
       return database.clothesDao.getAll().map {
           var tempMode = database.tempModeDao.getFromId(it.tempModeId).first()
           var clothesType = database.clothesTypeDao.getFromId(it.clothesTypeId).first()
           if(tempMode == null || clothesType == null){
               return emptyList()
           }
           it.toClothes(tempMode, clothesType)
       }
    }

    suspend fun restoreClothesDatabase(){
        Log.d("ClothesRepo", "Restore")
        database.clothesDao.clean()
        for(item in _clothesForDb){
            insertClothes(item)
        }
    }

    suspend fun getClothesByMainType(mainType: MainTypeEnumDBO): List<Clothes>{
        var allClothes = database.clothesDao.getAll().map {
            var tempMode = database.tempModeDao.getFromId(it.tempModeId).first()
            var clothesType = database.clothesTypeDao.getFromId(it.clothesTypeId).first()
            if(tempMode == null || clothesType == null){
                return emptyList()
            }
            it.toClothes(tempMode, clothesType)
        }

        return allClothes.filter { clothes -> clothes.clothesType.mainType == mainType }
    }

    suspend fun insertClothes(clothes: Clothes) {
        var clothesTypeId = insertClothesType(clothes.clothesType)
        var tempModeId = insertTempMode(clothes.tempMode)

        database.clothesDao.insert(clothes.toClothesDBO(clothesTypeId, tempModeId))
    }

    suspend fun getAllClothesType(): List<ClothesType> {
        return database.clothesTypeDao.getAll().map { it.toClothesType() }
    }

    suspend fun insertClothesType(clothesType: ClothesType) : Long {
        return database.clothesTypeDao.insert(clothesType.toClothesTypeDBO())
    }

    suspend fun insertTempMode(tempMode: TempMode) : Long {
        return database.tempModeDao.insert(tempMode.toTempModeDBO())

    }
}

private fun ClothesTypeDBO.toClothesType(): ClothesType {
    return  ClothesType(
        id = id,
        mainType = mainType,
        subType = subType,
        layer = layer,
        style = style)
}

private fun TempMode.toTempModeDBO(): TempModeDBO {
    return TempModeDBO(
        id = id,
        low = low,
        high = high,
        main = main.toMainEnumDBO()
    )
}

private fun MainEnum.toMainEnumDBO(): MainEnumDBO {
    return when(this){
        MainEnum.CLEAR -> MainEnumDBO.CLEAR
        MainEnum.CLOUDS -> MainEnumDBO.CLOUDS
        MainEnum.RAIN -> MainEnumDBO.RAIN
    }
}

private fun ClothesType.toClothesTypeDBO(): ClothesTypeDBO {
    return ClothesTypeDBO(
        id = id,
        mainType = mainType,
        subType = subType,
        layer = layer,
        style = style
    )
}

private fun Clothes.toClothesDBO(tempModeId: Long = this.clothesType.id, clothesTypeId: Long = this.tempMode.id): ClothesDBO {
return ClothesDBO(
    id = id,
    color = color,
    name = name,
    material = material,
    size = size,
    tempModeId = tempModeId,
    clothesTypeId = clothesTypeId,
    season = season,
)
}

private fun ClothesDBO.toClothes(tempModeFromDb : TempModeDBO, clothesTypeFromDb : ClothesTypeDBO): Clothes {
    return Clothes(
        id = id,
        color = color,
        name = name,
        material = material,
        size = size,
        tempMode = tempModeFromDb.toTempMode(),
        clothesType = clothesTypeFromDb.toClothesType(),
        season = season
    )
}

private fun TempModeDBO.toTempMode(): TempMode {
    return TempMode(
        id = id,
        low = low,
        high = high,
        main = main.toMainEnum()
    )
}

private fun MainEnumDBO.toMainEnum(): MainEnum {
    return when(this){
        MainEnumDBO.CLEAR -> MainEnum.CLEAR
        MainEnumDBO.CLOUDS -> MainEnum.CLOUDS
        MainEnumDBO.RAIN -> MainEnum.RAIN
    }
}
