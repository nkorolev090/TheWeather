package com.example.clothesdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.clothesdb.dao.ClothesDao
import com.example.clothesdb.dao.ClothesTypeDao
import com.example.clothesdb.dao.TempModeDao
import com.example.clothesdb.models.ClothesDBO
import com.example.clothesdb.models.ClothesTypeDBO
import com.example.clothesdb.models.TempModeDBO

class ClothesDatabase internal constructor(private val database: ClothesRoomDatabase){
    val clothesDao: ClothesDao
        get() = database.clothesDao()
    val clothesTypeDao: ClothesTypeDao
        get() = database.clothesTypeDao()
    val tempModeDao: TempModeDao
        get() = database.tempModeDao()
}
@Database(entities = [ClothesDBO::class, TempModeDBO::class, ClothesTypeDBO::class], version = 2)
@TypeConverters(TypeConverter::class)
abstract class ClothesRoomDatabase : RoomDatabase() {

    abstract fun clothesDao(): ClothesDao
    abstract fun clothesTypeDao(): ClothesTypeDao
    abstract fun tempModeDao(): TempModeDao
    }
fun ClothesDatabase(applicationContext: Context): ClothesDatabase{
    val clothesRoomDatabase =
        Room.databaseBuilder(
            checkNotNull(applicationContext.applicationContext),
            ClothesRoomDatabase::class.java,
            "clothes"
        ).build()
    return ClothesDatabase(clothesRoomDatabase)
}