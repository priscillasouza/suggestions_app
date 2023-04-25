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

fun Date.differ(): String {
    val firstDate = this
    val differDate = Date(firstDate.time)
    return differDate.toString(FORMAT_HOUR)
}