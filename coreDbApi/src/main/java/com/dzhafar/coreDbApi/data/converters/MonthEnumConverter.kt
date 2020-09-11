package com.dzhafar.coreDbApi.data.converters

import androidx.room.TypeConverter
import com.dzhafar.coreDbApi.data.enums.MonthEnums

object MonthEnumConverter {
    @TypeConverter
    @JvmStatic
    fun indexFromEnumTypeDuties(type: MonthEnums): Int = type.id

    @TypeConverter
    @JvmStatic
    fun enumTypeDutiesFromIndex(id: Int): MonthEnums = MonthEnums.values().find { it.id == id } ?: MonthEnums.JANUARY
}