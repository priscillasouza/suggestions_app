package com.priscilla.suggestionsapp.activity_suggestion.domain.repository

import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import kotlinx.coroutines.flow.Flow

interface IActivityRepository {

    fun getActivity(): Flow<ActivityModel>

    suspend fun saveActivity(activityModel: ActivityModel)
}