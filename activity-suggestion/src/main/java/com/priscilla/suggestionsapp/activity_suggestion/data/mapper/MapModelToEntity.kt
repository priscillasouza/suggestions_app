package com.priscilla.suggestionsapp.activity_suggestion.data.mapper

import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity.ActivityEntity
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity.Status

class MapModelToEntity {

    fun transform(activityModel: ActivityModel): ActivityEntity {
        return ActivityEntity(
            activity =  activityModel.activity,
            accessibility = activityModel.accessibility,
            type =  activityModel.type,
            participants =  activityModel.participants,
            price =  activityModel.price,
            link =  activityModel.link,
            key =  activityModel.key,
            status = activityModel.status?.name?.let {
                Status.valueOf(it)
            },
            startTime = activityModel.startTime,
            endTime = activityModel.endTime,
            spentTime = activityModel.spentTime
        )
    }
}