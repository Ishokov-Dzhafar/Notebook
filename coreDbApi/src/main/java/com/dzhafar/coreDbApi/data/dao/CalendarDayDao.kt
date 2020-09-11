package com.dzhafar.coreDbApi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.dzhafar.coreDbApi.data.entity.CalendarDayEntity

@Dao
interface CalendarDayDao {

    @Insert
    fun insertCalendarDay(calendarDayEntity: CalendarDayEntity): Long
}