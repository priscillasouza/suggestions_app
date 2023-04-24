package com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model

import java.math.BigDecimal
import java.util.*

data class ActivityModel(
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: BigDecimal,
    val link: String,
    val key: String,
    var status: Status? = null,
    val startTime: Date? = null,
    val endTime: Date? = null,
)

enum class Status(val value: String) {
    STATUS_PROGRESS("andamento"),
    STATUS_GIV_UP("desistencia"),
    STATUS_COMPLETED("realizada")
}