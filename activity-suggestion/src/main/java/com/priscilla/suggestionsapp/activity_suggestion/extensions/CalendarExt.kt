package com.priscilla.suggestionsapp.activity_suggestion.extensions

import java.text.SimpleDateFormat
import java.util.*

const val FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'"
const val FORMAT_DATE_VIEW = "dd/MM/yyyy"
const val FORMAT_DATE_VIEW_SHORT = "dd MMMM"
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

private fun Date.differ(secondDate: Date): String {
    val firstDate = this
    val differDate = Date(secondDate.time - firstDate.time)
    return differDate.toString(FORMAT_DATE)
}

fun String.differTime(secondDateString: String?): String {
    val firstDate = converterStringToDate(this)
    val secondDate = converterStringToDate(secondDateString.toString())
    return secondDate?.let {
        firstDate?.differ(it)
    } ?: ""
}

private fun converterStringToDate(stringDate: String): Date? {
    val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(FORMAT_DATE)
    return stringDate.takeIf {
        it.isNotEmpty()
    }?.let {
        simpleDateFormat.parse(stringDate)
    }
}






