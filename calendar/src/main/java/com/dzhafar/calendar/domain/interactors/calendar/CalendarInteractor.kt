package com.dzhafar.calendar.domain.interactors.calendar

import com.dzhafar.calendar.domain.models.CalendarItem
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface CalendarInteractor {
    fun getCalendar(currentDate: Date, visibleDate: Date): Flow<List<CalendarItem>>
    fun insertCalendarItem(calendarItem: CalendarItem): Flow<Long>
}