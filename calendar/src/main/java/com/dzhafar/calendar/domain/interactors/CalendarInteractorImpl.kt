package com.dzhafar.calendar.domain.interactors

import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.calendar.domain.usecases.GetCalendarItemsUseCase
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class CalendarInteractorImpl @Inject constructor(
    private val getCalendarItemsUseCase: GetCalendarItemsUseCase
) : CalendarInteractor {
    override fun getCalendar(currentDate: Date, visibleDate: Date): Flow<List<CalendarItem>> {
        return getCalendarItemsUseCase.execute(GetCalendarItemsUseCase.Params(currentDate, visibleDate))
    }

}