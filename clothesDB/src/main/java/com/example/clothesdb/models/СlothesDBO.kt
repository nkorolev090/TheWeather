package com.example.clothesdb.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clothes")
data class ClothesDBO(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("clothesType") val clothesType: ClothesTypeDBO,
    @ColumnInfo("color") val color: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("material") val material: String,
    @ColumnInfo("size") val size: String,
    @ColumnInfo("tempModeId") val tempModeId: Long,
)