package com.priscilla.suggestionsapp.data.infrastruture.remote.api

import com.priscilla.suggestionsapp.data.infrastruture.remote.api.model.response.ActivityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SuggestionsActivityAPI {

    @GET("activity")
    suspend fun getActivity(): ActivityResponse

    @GET("activity")
    suspend fun getType(@Query("type") type:String): ActivityResponse
}