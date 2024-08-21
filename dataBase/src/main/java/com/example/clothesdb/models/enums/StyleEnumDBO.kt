package com.example.clothesdb.models.enums

import androidx.room.ColumnInfo

enum class StyleEnumDBO {
    @ColumnInfo("official")
    OFFICIAL,
    @ColumnInfo("sport")
    SPORT,
    @ColumnInfo("casual")
    CASUAL,
    @ColumnInfo("boho")
    BOHO,
    @ColumnInfo("minimalism")
    MINIMALISM,
    @ColumnInfo("street")
    STREET,
    @ColumnInfo("ethno")
    ETHNO,
    @ColumnInfo("retro")
    RETRO,
}
