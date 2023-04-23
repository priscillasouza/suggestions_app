package com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model

import java.math.BigDecimal

data class ActivityModel(
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: BigDecimal,
    val link: String,
    val key: String,
    val status: String? = null
)