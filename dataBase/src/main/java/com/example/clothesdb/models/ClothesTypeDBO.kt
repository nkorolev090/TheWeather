package com.example.clothesdb.models

import androidx.annotation.IntRange
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.clothesdb.models.enums.MainTypeEnumDBO
import com.example.clothesdb.models.enums.StyleEnumDBO
import com.example.clothesdb.models.enums.SubTypeEnumDBO

@Entity(tableName = "clothesType")
data class ClothesTypeDBO (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("mainType") val mainType: MainTypeEnumDBO,
    @ColumnInfo("subType") val subType: SubTypeEnumDBO,
    @ColumnInfo("layer")
    @IntRange(from = 0, to = 3) val layer: Int,
    @ColumnInfo("style") val style: StyleEnumDBO,
)

