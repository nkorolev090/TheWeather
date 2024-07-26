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
    suspend fun getAllClothes(): List<Clothes> {
       return database.clothesDao.getAll().map { it.toClothes() }
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
    clothesTypeId = clothesTypeId
)
}

private fun ClothesDBO.toClothes(): Clothes {
    return Clothes(
        id = id,
        color = color,
        name = name,
        material = material,
        size = size,
        tempMode =  TempMode(0, 0.0, 0.0, MainEnumDBO.CLEAR),//mock
        clothesType = ClothesType(0, MainTypeEnumDBO.HIGH, SubTypeEnumDBO.T_SHIRT, 0, StyleEnumDBO.OFFICIAL)//mock
    )
}
