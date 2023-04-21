package com.priscilla.suggestionsapp.data.infrastruture.remote.api.model.response

import java.math.BigDecimal

data class ActivityResponse(
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: BigDecimal,
    val link: String,
    val key: String
)