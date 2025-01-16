package com.example.weatherdata.clothes

import com.example.clothesdb.models.TempModeDBO
import com.example.clothesdb.models.enums.MainEnumDBO
import com.example.firebaseapi.clothes.models.ClothesDTO
import com.example.firebaseapi.clothes.models.ClothesTypeDTO
import com.example.firebaseapi.clothes.models.TempModeDTO
import com.example.weatherdata.clothes.models.enums.ClothesSubTypeEnum
import com.example.weatherdata.clothes.models.enums.ClothesTypeEnum
import com.example.firebaseapi.clothes.models.enums.MainEnumDTO
import com.example.firebaseapi.clothes.models.enums.MainTypeEnumDTO
import com.example.firebaseapi.clothes.models.enums.StyleEnum
import com.example.firebaseapi.clothes.models.enums.StyleEnumDTO
import com.example.firebaseapi.clothes.models.enums.SubTypeEnumDTO
import com.example.weatherdata.clothes.models.Clothes
import com.example.weatherdata.clothes.models.ClothesStyle
import com.example.weatherdata.clothes.models.ClothesType
import com.example.weatherdata.clothes.models.TempMode
import com.example.weatherdata.weather.models.MainEnum

//region DATA TO DTO
fun TempMode.toTempModeDTO(): TempModeDTO =
    TempModeDTO(
        id = id,
        low = low,
        high = high,
        main = main.toMainEnumDTO()
    )

fun MainEnum.toMainEnumDTO(): MainEnumDTO =
    when (this) {
        MainEnum.CLEAR -> MainEnumDTO.CLEAR
        MainEnum.CLOUDS -> MainEnumDTO.CLOUDS
        MainEnum.RAIN -> MainEnumDTO.RAIN
    }


fun ClothesType.toClothesTypeDTO(styleEnum: StyleEnum): ClothesTypeDTO =
    ClothesTypeDTO(
        id = id,
        mainType = mainType.toMainTypeEnumDTO(),
        subType = subType.toSubTypeEnumDTO(),
        layer = layer,
        style = styleEnum.toStyleEnumDTO()
    )

fun ClothesTypeEnum.toMainTypeEnumDTO(): MainTypeEnumDTO =
    when (this) {
        ClothesTypeEnum.HIGH -> MainTypeEnumDTO.HIGH
        ClothesTypeEnum.LOW -> MainTypeEnumDTO.LOW
        ClothesTypeEnum.SHOES -> MainTypeEnumDTO.SHOES
    }

fun ClothesSubTypeEnum.toSubTypeEnumDTO(): SubTypeEnumDTO =
    when (this) {
        ClothesSubTypeEnum.T_SHIRT -> SubTypeEnumDTO.T_SHIRT
    }

fun StyleEnum.toStyleEnumDTO(): StyleEnumDTO =
    when (this) {
        StyleEnum.OFFICIAL -> StyleEnumDTO.OFFICIAL
        StyleEnum.SPORT -> StyleEnumDTO.SPORT
    }

fun Clothes.toClothesDTO(styleEnum: StyleEnum): ClothesDTO =
    ClothesDTO(
        id = id,
        color = color,
        name = name,
        material = material,
        size = size,
        imageURL = imageURL,
        tempMode = tempMode.toTempModeDTO(),
        clothesType = clothesType.toClothesTypeDTO(styleEnum),
        season = season,
    )

fun List<ClothesStyle>.toClothesDTOList(): List<ClothesDTO> {
    val clothesList: MutableList<ClothesDTO> = mutableListOf()
    this.forEach { clothesStyle ->
        clothesList.addAll(clothesStyle
            .clothes.map {
                it.toClothesDTO(clothesStyle.styleType)
            }
        )
    }
    return clothesList
}

//endregion

//region DTO TO DATA

fun TempModeDTO.toTempMode(): TempMode? =
    try {
        TempMode(
            id = id!!,
            low = low!!,
            high = high!!,
            main = main!!.toMainEnum()
        )
    } catch (e: Exception) {
        null
    }


fun MainEnumDTO.toMainEnum(): MainEnum =
    when (this) {
        MainEnumDTO.CLEAR -> MainEnum.CLEAR
        MainEnumDTO.CLOUDS -> MainEnum.CLOUDS
        MainEnumDTO.RAIN -> MainEnum.RAIN
    }


fun ClothesTypeDTO.toClothesType(): ClothesType? =
    try {
        ClothesType(
            id = id!!,
            mainType = mainType!!.toClothesTypeEnum(),
            subType = subType!!.toClothesSubTypeEnum(),
            layer = layer!!,
        )
    } catch (e: Exception) {
        null
    }


fun MainTypeEnumDTO.toClothesTypeEnum(): ClothesTypeEnum =
    when (this) {
        MainTypeEnumDTO.HIGH -> ClothesTypeEnum.HIGH
        MainTypeEnumDTO.LOW -> ClothesTypeEnum.LOW
        MainTypeEnumDTO.SHOES -> ClothesTypeEnum.SHOES
    }

fun SubTypeEnumDTO.toClothesSubTypeEnum(): ClothesSubTypeEnum =
    when (this) {
        SubTypeEnumDTO.T_SHIRT -> ClothesSubTypeEnum.T_SHIRT
    }

fun StyleEnumDTO.toStyleEnum(): StyleEnum =
    when (this) {
        StyleEnumDTO.OFFICIAL -> StyleEnum.OFFICIAL
        StyleEnumDTO.SPORT -> StyleEnum.SPORT
    }

fun ClothesDTO.toClothes(): Clothes? =
    try {
        Clothes(
            id = id!!,
            color = color!!,
            name = name!!,
            material = material!!,
            size = size!!,
            imageURL = imageURL!!,
            tempMode = tempMode!!.toTempMode()!!,
            clothesType = clothesType!!.toClothesType()!!,
            season = season!!
        )
    } catch (e: Exception) {
        null
    }

//endregion

//region DBO TO DATA

//private fun ClothesDBO.toClothes(tempModeFromDb : TempModeDBO, clothesTypeFromDb : ClothesTypeDBO): Clothes {
//    return Clothes(
//        id = id,
//        color = color,
//        name = name,
//        material = material,
//        size = size,
//        tempMode = tempModeFromDb.toTempMode(),
//        clothesType = clothesTypeFromDb.toClothesType(),
//        season = season
//    )
//}

fun TempModeDBO.toTempMode(): TempMode =
    TempMode(
        id = id,
        low = low,
        high = high,
        main = main.toMainEnum()
    )

fun MainEnumDBO.toMainEnum(): MainEnum =
    when (this) {
        MainEnumDBO.CLEAR -> MainEnum.CLEAR
        MainEnumDBO.CLOUDS -> MainEnum.CLOUDS
        MainEnumDBO.RAIN -> MainEnum.RAIN
    }

//endregion