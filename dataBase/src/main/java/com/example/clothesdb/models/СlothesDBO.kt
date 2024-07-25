package com.example.clothesdb.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "clothes", foreignKeys = [
    ForeignKey(entity = ClothesDBO::class, parentColumns = ["id"], childColumns = ["tempModeId"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
    ForeignKey(entity = ClothesDBO::class, parentColumns = ["id"], childColumns = ["clothesTypeId"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
])
data class ClothesDBO(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("color") val color: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("material") val material: String,
    @ColumnInfo("size") val size: String,
    @ColumnInfo("tempModeId") val tempModeId: Long,
    @ColumnInfo("clothesTypeId") val clothesTypeId: Long,
)