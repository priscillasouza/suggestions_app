package com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity

import androidx.room.Entity
import java.math.BigDecimal

@Entity(tableName = "activity_table")
data class ActivityEntity(
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: BigDecimal,
    val link: String,
    val key: String,
    val status: String
)