package com.priscilla.suggestionsapp.data.infrastruture.local.database.activity

import androidx.room.*
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity.ActivityEntity

@Dao
interface ActivityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveActivity(activity: ActivityEntity)
}