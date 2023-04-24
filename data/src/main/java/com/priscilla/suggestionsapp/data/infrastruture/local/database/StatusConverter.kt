package com.priscilla.suggestionsapp.data.infrastruture.local.database

import androidx.room.TypeConverter
import com.priscilla.suggestionsapp.data.infrastruture.local.database.activity.entity.Status

object StatusConverter {

    @TypeConverter
    @JvmStatic
    fun statusToString(status: Status?): String? {
        return try {
            status?.let {
                it.name
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToStatus(string: String?): Status? {
        return try {
            string?.let {
                Status.valueOf(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}