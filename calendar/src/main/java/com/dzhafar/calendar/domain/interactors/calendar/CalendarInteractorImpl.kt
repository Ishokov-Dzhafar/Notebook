package com.dzhafar.calendar.domain.interactors.calendar

import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.calendar.domain.repositories.CalendarRepository
import com.dzhafar.calendar.domain.usecases.GetCalendarItemsUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class CalendarInteractorImpl @Inject constructor(
    private val getCalendarItemsUseCase: GetCalendarItemsUseCase,
    private val calendarRepository: CalendarRepository
) : CalendarInteractor {
    @FlowPreview
    override fun getCalendar(currentDate: Date, visibleDate: Date): Flow<List<CalendarItem>> {
        return getCalendarItemsUseCase.execute(GetCalendarItemsUseCase.Params(currentDate, visibleDate))
    }

    override fun insertCalendarItem(calendarItem: CalendarItem): Flow<Long> =
        calendarRepository.insertCalendarItem(calendarItem)
}