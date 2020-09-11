package com.dzhafar.coreDbApi.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dzhafar.coreDbApi.data.converters.MonthEnumConverter
import com.dzhafar.coreDbApi.data.enums.MonthEnums

@Entity(tableName = CALENDAR_DAY)
@TypeConverters(MonthEnumConverter::class)
data class CalendarDayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "month") val month: MonthEnums,
    @ColumnInfo(name = "day") val day: Int
)