package com.priscilla.suggestionsapp.activity_suggestion.data.repository

import com.priscilla.suggestionsapp.activity_suggestion.data.mapper.MapModelToEntity
import com.priscilla.suggestionsapp.activity_suggestion.data.mapper.MapResponseToModel
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.IActivityRepository
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.ActivityDAO
import com.priscilla.suggestionsapp.data.infrastruture.remote.api.SuggestionsActivityAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ActivityRepositoryImpl(
    private var activityApi: SuggestionsActivityAPI,
    private var activityDao: ActivityDAO,
    private var mapResponseToModel: MapResponseToModel,
    private var mapModelToEntity: MapModelToEntity,
) : IActivityRepository {

    override fun getActivity(): Flow<ActivityModel> {
        return flow {
            activityApi.getActivity().let {
                emit(mapResponseToModel.transform(it))
            }
        }
    }

    override suspend fun saveActivity(activityModel: ActivityModel) {
        mapModelToEntity.transform(activityModel).also {
            activityDao.saveActivity(it)
        }
    }
}