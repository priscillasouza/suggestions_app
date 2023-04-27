package com.priscilla.suggestionsapp.activity_suggestion.data.mapper

import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.data.infrastruture.remote.api.model.response.ActivityResponse

class MapResponseToModel {

    fun transform(response: ActivityResponse): ActivityModel {
        return ActivityModel(
            activity =  response.activity,
            accessibility =  response.accessibility,
            type =  response.type,
            participants =  response.participants,
            price =  response.price,
            link =  response.link,
            key =  response.key
        )
    }
}