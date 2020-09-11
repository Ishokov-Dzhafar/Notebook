package com.dzhafar.calendar.domain.repositories

import com.dzhafar.calendar.data.models.CalendarItemData
import com.dzhafar.calendar.domain.models.CalendarItem
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface CalendarRepository {
    fun getCalendarItemsByInterval(startDate: Date, endDate: Date): Flow<CalendarItemData>
    fun insertCalendarItem(calendarItem: CalendarItem): Flow<Long>
}