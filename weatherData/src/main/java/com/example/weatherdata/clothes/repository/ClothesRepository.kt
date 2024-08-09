package com.example.weatherdata.clothes.repository

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
            tempMode = TempMode(0, 0.0, 0.0, MainEnumDBO.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.HIGH, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.OFFICIAL),
            season = "season1"
        ),
        Clothes(
            id = 0,
            color = "color2",
            name = "test name2",
            material = "material2",
            size = "size2",
            tempMode = TempMode(0, 0.0, 0.0, MainEnumDBO.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.LOW, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.SPORT),
            season = "season2"
        ),
        Clothes(
            id = 0,
            color = "color3",
            name = "test name3",
            material = "material3",
            size = "size3",
            tempMode = TempMode(0, 0.0, 0.0, MainEnumDBO.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.SHOES, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.SPORT),
            season = "season3"
        ),
        Clothes(
            id = 0,
            color = "color4",
            name = "test name4",
            material = "material4",
            size = "size4",
            tempMode = TempMode(0, 0.0, 0.0, MainEnumDBO.CLEAR),
            clothesType = ClothesType(0, MainTypeEnumDBO.HIGH, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.OFFICIAL),
            season = "season4"
        )
    )

    suspend fun getAllClothes(): List<Clothes> {
        //////////////////////////////////for mock data
        var clothes = database.clothesDao.getAll()
        if(clothes.isEmpty()){
            for(item in _clothesForDb){
                insertClothes(item)
            }
        }
        //////////////////////////////////

       return database.clothesDao.getAll().map {
           var tempMode = database.tempModeDao.getFromId(it.tempModeId).first()
           var clothesType = database.clothesTypeDao.getFromId(it.clothesTypeId).first()
           if(tempMode == null || clothesType == null){
               return emptyList()
           }
           it.toClothes(tempMode, clothesType)
       }
    }

    suspend fun getClothesByMainType(mainType: MainTypeEnumDBO): List<Clothes>{
        var clothes = database.clothesDao.getAll()
        if(clothes.isEmpty()){
            for(item in _clothesForDb){
                insertClothes(item)
            }
        }
        //////////////////////////////////

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
        main = main
    )
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
        main = main
    )
}
