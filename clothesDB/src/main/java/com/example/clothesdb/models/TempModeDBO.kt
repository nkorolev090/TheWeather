package com.example.clothesdb.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TempModeDBO(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("low") val low: Double,
    @ColumnInfo("high") val high: Double,
    @ColumnInfo("main") val main: MainEnumDBO,
)