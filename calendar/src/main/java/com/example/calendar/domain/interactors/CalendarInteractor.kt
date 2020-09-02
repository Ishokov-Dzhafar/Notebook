package com.example.calendar.domain.interactors

import com.example.calendar.domain.models.CalendarItem
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface CalendarInteractor {
    fun getCalendar(currentDate: Date, visibleDate: Date): Flow<List<CalendarItem>>
}