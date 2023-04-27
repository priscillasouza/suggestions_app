package com.priscilla.suggestionsapp.data.infrastruture.local.database

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

object DateConverter {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")

    @TypeConverter
    @JvmStatic
    fun dateToString(date: Date?): String? {
        return try {
            date?.let {
                simpleDateFormat.format(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToDate(string: String?): Date? {
        return try {
            string?.let {
                simpleDateFormat.parse(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}