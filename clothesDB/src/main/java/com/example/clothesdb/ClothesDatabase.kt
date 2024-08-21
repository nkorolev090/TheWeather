package com.example.clothesdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clothesdb.dao.ClothesDao
import com.example.clothesdb.models.ClothesDBO
import com.example.clothesdb.models.ClothesTypeDBO
import com.example.clothesdb.models.TempModeDBO

@Database(entities = [ClothesDBO::class, TempModeDBO::class, ClothesTypeDBO::class], version = 1)
abstract class ClothesDatabase : RoomDatabase() {

    abstract fun clothesDao(): ClothesDao

    }
fun ClothesDatabase(applicationContext: Context): ClothesDatabase{
        return Room.databaseBuilder(
            checkNotNull(applicationContext.applicationContext),
            ClothesDatabase::class.java,
            "clothes"
        ).build()
}