package com.priscilla.suggestionsapp.activity_suggestion.domain.repository

import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import kotlinx.coroutines.flow.Flow

interface IActivityRepository {

    fun getActivity(): Flow<ActivityModel>

    fun getTypeForActivity(type: String): Flow<ActivityModel>

    suspend fun saveActivity(activityModel: ActivityModel)

    suspend fun updateActivity(activityModel: ActivityModel)

    fun getProgressAcitivity(): Flow<List<ActivityModel>>

    fun getFinishedAcitivity(): Flow<List<ActivityModel>>
}