package com.priscilla.suggestionsapp.data.infrastruture.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.ActivityDAO
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity.ActivityEntity

const val DB_NAME = "activity_db"

@Database(
    entities = [ActivityEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(BigDecimalConverter::class, DateConverter::class, StatusConverter::class)
abstract class SuggestionsActivityDatabase : RoomDatabase() {

    abstract fun activityDao(): ActivityDAO
}