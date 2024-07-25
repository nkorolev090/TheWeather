package com.example.clothesdb

import androidx.room.TypeConverter
import com.example.clothesdb.models.enums.MainEnumDBO
import com.example.clothesdb.models.enums.MainTypeEnumDBO
import com.example.clothesdb.models.enums.StyleEnumDBO
import com.example.clothesdb.models.enums.SubTypeEnumDBO

class TypeConverter {
    @TypeConverter
    fun fromMainTypeEnumDBO(mainTypeEnumDBO: MainTypeEnumDBO): String{
        return mainTypeEnumDBO.name
    }
    @TypeConverter
    fun toMainTypeEnumDBO(mainTypeEnumDBO: String): MainTypeEnumDBO{
        return MainTypeEnumDBO.valueOf(mainTypeEnumDBO)
    }

    @TypeConverter
    fun fromMainEnumDBO(mainEnumDBO: MainEnumDBO): String{
        return mainEnumDBO.name
    }
    @TypeConverter
    fun toMainEnumDBO(mainEnumDBO: String): MainEnumDBO{
        return MainEnumDBO.valueOf(mainEnumDBO)
    }

    @TypeConverter
    fun fromStyleEnumDBO(styleEnumDBO: StyleEnumDBO): String{
        return styleEnumDBO.name
    }
    @TypeConverter
    fun toStyleEnumDBO(styleEnumDBO: String): StyleEnumDBO{
        return StyleEnumDBO.valueOf(styleEnumDBO)
    }

    @TypeConverter
    fun fromSubTypeEnumDBO(subTypeEnumDBO: SubTypeEnumDBO): String{
        return subTypeEnumDBO.name
    }
    @TypeConverter
    fun toSubTypeEnumDBO(subTypeEnumDBO: String): SubTypeEnumDBO{
        return SubTypeEnumDBO.valueOf(subTypeEnumDBO)
    }
}