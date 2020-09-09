package com.dzhafar.coreDbApi.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CALENDAR_DAY)
data class CalendarDayEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "day") val day: Int
)