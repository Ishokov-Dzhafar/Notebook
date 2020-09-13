package com.dzhafar.coreDbApi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.TypeConverters
import com.dzhafar.coreDbApi.data.converters.MonthEnumConverter
import com.dzhafar.coreDbApi.data.entity.CALENDAR_DAY
import com.dzhafar.coreDbApi.data.entity.CalendarDayEntity
import com.dzhafar.coreDbApi.data.entity.NOTE
import com.dzhafar.coreDbApi.data.enums.MonthEnums
import com.dzhafar.coreDbApi.data.models.CalendarDateModel
import com.dzhafar.coreDbApi.data.models.CalendarItemModel

@Dao
interface CalendarDayDao {

    @Insert
    fun insertCalendarDay(calendarDayEntity: CalendarDayEntity): Long

    @TypeConverters(MonthEnumConverter::class)
    @Suppress("LongParameterList")
    @Transaction
    @Query(
        """SELECT *
        FROM $CALENDAR_DAY 
        WHERE day >= :startDateDay AND day <= :endDateDay AND
        month >= :startDateMonth AND month <= :endDateMonth AND
        year >= :startDateYear AND year <= :endDateYear"""
    )
    fun fetchCalendarItems(
        startDateDay: Int,
        startDateMonth: MonthEnums,
        startDateYear: Int,
        endDateDay: Int,
        endDateMonth: MonthEnums,
        endDateYear: Int
    ): List<CalendarItemModel>

    /*"""SELECT id, day, month, year, (
            SELECT text
            FROM $NOTE
            WHERE dayId = id
        ) as notes
        FROM $CALENDAR_DAY
        WHERE day >= :startDateDay AND day <= :endDateDay AND
        month >= :startDateMonth AND month <= :endDateMonth AND
        month >= :startDateYear AND month <= :endDateYear"""*/
}