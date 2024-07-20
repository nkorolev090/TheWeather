package com.example.clothesdb.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.clothesdb.models.enums.MainEnumDBO

@Entity
data class TempModeDBO(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("low") val low: Double,
    @ColumnInfo("high") val high: Double,
    @ColumnInfo("main") val main: MainEnumDBO,
)