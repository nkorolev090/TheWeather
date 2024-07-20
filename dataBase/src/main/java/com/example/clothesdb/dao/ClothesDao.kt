package com.example.clothesdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.clothesdb.models.ClothesDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface ClothesDao {

    @Query("SELECT * FROM clothes")
    suspend fun getAll(): List<ClothesDBO>

    @Query("SELECT * FROM clothes")
    fun observeAll(): Flow<List<ClothesDBO>>

    @Insert
    suspend fun insert(articles: List<ClothesDBO>)

    @Delete
    suspend fun remove(articles: List<ClothesDBO>)

    @Query("DELETE FROM clothes")
    suspend fun clean()
}