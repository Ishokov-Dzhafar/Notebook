package com.dzhafar.calendar.domain.repositories

import com.dzhafar.calendar.domain.models.CalendarItemData
import com.dzhafar.calendar.domain.models.CalendarDate
import com.dzhafar.calendar.domain.models.CalendarItem
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {
    fun insertCalendarItem(calendarItem: CalendarItem): Flow<Long>
    fun fetchCalendarItems(startDate: CalendarDate, endDate: CalendarDate): Flow<List<CalendarItemData>>
}