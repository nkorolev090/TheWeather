package com.example.clothesdb.models.enums

import androidx.room.ColumnInfo

enum class MainEnumDBO{
    @ColumnInfo("Clouds")
    CLOUDS,

    @ColumnInfo("Clear")
    CLEAR,

    @ColumnInfo("Rain")
    RAIN,
}