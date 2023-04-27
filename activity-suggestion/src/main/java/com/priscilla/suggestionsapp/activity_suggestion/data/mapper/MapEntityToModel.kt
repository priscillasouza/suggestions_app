package com.priscilla.suggestionsapp.activity_suggestion.data.mapper

import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity.ActivityEntity
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.Status

class MapEntityToModel: IMapper<ActivityEntity, ActivityModel> {

    override fun transform(activityEntity: ActivityEntity): ActivityModel {
        return ActivityModel(
            activity = activityEntity.activity,
            accessibility = activityEntity.accessibility,
            type = activityEntity.type,
            participants = activityEntity.participants,
            price = activityEntity.price,
            link = activityEntity.link,
            key = activityEntity.key,
            status = activityEntity.status?.name?.let {
                Status.valueOf(it)
            },
            startTime = activityEntity.startTime,
            endTime = activityEntity.endTime,
            spentTime = activityEntity.spentTime
        )
    }
}