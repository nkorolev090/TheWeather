package com.example.clothesdb.models.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.clothesdb.models.TempModeDBO
import com.example.clothesdb.models.СlothesDBO

data class TempModeWithClothes (
    @Embedded val tempMode: TempModeDBO,
    @Relation(
        parentColumn = "tempModeId",
        entityColumn = "id"
    )
    val clothes: List<СlothesDBO>
)