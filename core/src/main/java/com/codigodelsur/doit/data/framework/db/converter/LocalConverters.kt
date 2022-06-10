package com.codigodelsur.doit.data.framework.db.converter

import androidx.room.TypeConverter
import java.util.*

class LocalConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}
