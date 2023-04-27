package com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity(tableName = "activity_table")
data class ActivityEntity(
    val activity: String,
    val accessibility: Double,
    val type: String,
    val participants: Int,
    val price: BigDecimal,
    val link: String,
    @PrimaryKey
    val key: String,
    val status: Status? = null,
    val startTime: Date? = null,
    val endTime: Date? = null,
    val spentTime: Date? = null
)

enum class Status(val value: String) {
    STATUS_PROGRESS("andamento"),
    STATUS_GIV_UP("desistencia"),
    STATUS_FINISHED("realizada")
}