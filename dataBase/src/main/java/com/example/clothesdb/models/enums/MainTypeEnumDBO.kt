package com.example.clothesdb.models.enums

import androidx.room.ColumnInfo

enum class MainTypeEnumDBO {
    @ColumnInfo("high")
    HIGH,
    @ColumnInfo("low")
    LOW,
    @ColumnInfo("shoes")
    SHOES,
}