package com.priscilla.suggestionsapp.activity_suggestion.data.mapper

import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity.ActivityEntity

class MapModelToEntity {

    fun transform(activityModel: ActivityModel): ActivityEntity {
        return ActivityEntity(
            activity =  activityModel.activity,
            accessibility= activityModel.accessibility,
            type=  activityModel.type,
            participants=  activityModel.participants,
            price=  activityModel.price,
            link=  activityModel.link,
            key=  activityModel.key,
            status = activityModel.status,
            startTime = activityModel.startTime,
            endTime = activityModel.endTime
        )
    }
}