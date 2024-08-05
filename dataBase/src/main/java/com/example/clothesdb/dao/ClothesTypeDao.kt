package com.example.clothesdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clothesdb.models.ClothesDBO
import com.example.clothesdb.models.ClothesTypeDBO
import com.example.weatherdb.models.WeatherDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ClothesTypeDao {
    @Query("SELECT * FROM clothesType")
    suspend fun getAll(): List<ClothesTypeDBO>

    @Query("SELECT * FROM clothesType WHERE id = :id")
    suspend fun getFromId(id: Long): List<ClothesTypeDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clothesType: ClothesTypeDBO) : Long

    @Delete
    suspend fun remove(clothesType: ClothesTypeDBO)

    @Query("DELETE FROM clothesType")
    suspend fun clean()
}