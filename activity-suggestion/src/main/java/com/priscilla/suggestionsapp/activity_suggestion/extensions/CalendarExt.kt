package com.priscilla.suggestionsapp.activity_suggestion.extensions

import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_HOUR = "HH:mm"

fun Calendar.toString(stringFormat: String): String {
    val simpleDateFormat = SimpleDateFormat(stringFormat)
    val dateStringFormated = simpleDateFormat.format(this.time)
    return dateStringFormated
}

fun Date.toString(stringFormat: String): String {
    val simpleDateFormat = SimpleDateFormat(stringFormat)
    val dateStringFormated = simpleDateFormat.format(this.time)
    return dateStringFormated
}

fun Date.differStartTime(): String {
    val startDate = this
    val differDate = Date(startDate.time)
    return differDate.toString(FORMAT_HOUR)
}