package com.example.clothesdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.clothesdb.models.ClothesTypeDBO
import com.example.clothesdb.models.TempModeDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface TempModeDao {
    @Query("SELECT * FROM tempMode")
    suspend fun getAll(): List<TempModeDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tempMode: TempModeDBO) : Long

    @Delete
    suspend fun remove(tempMode: TempModeDBO)

    @Query("DELETE FROM tempMode")
    suspend fun clean()
}