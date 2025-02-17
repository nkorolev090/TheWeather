package com.example.clothesdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clothesdb.models.ClothesDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ClothesDao {

    @Query("SELECT * FROM clothes")
    suspend fun getAll(): List<ClothesDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clothes: ClothesDBO) : Long

    @Delete
    suspend fun remove(clothes: ClothesDBO)

    @Query("DELETE FROM clothes")
    suspend fun clean()
}