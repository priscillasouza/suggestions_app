package com.priscilla.suggestionsapp.data.infrastruture.local.database.activity

import androidx.room.*
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity.ActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveActivity(activityEntity: ActivityEntity)

    @Update
    fun update(activityEntity: ActivityEntity)

    @Query("SELECT * FROM activity_table WHERE status = 'STATUS_PROGRESS'")
    fun getProgressActivity(): Flow<List<ActivityEntity>>

    @Query("SELECT * FROM activity_table WHERE status = 'STATUS_FINISHED'")
    fun getFinishedActivity(): Flow<List<ActivityEntity>>
}