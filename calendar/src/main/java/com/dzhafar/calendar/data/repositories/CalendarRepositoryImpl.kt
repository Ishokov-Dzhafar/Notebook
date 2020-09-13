package com.dzhafar.calendar.data.repositories

import com.dzhafar.calendar.data.mapper.mapToData
import com.dzhafar.calendar.data.mapper.mapToDataMonthEnum
import com.dzhafar.calendar.data.mapper.mapToDomain
import com.dzhafar.calendar.domain.models.CalendarDate
import com.dzhafar.coreDbApi.di.DBApi
import com.dzhafar.calendar.domain.models.CalendarItemData
import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.calendar.domain.repositories.CalendarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val db: DBApi
) : CalendarRepository {

    override fun insertCalendarItem(calendarItem: CalendarItem): Flow<Long> =
        flow {
            emit(db.calendarDayDao().insertCalendarDay(calendarItem.mapToData()))
        }

    override fun fetchCalendarItems(
        startDate: CalendarDate,
        endDate: CalendarDate
    ): Flow<List<CalendarItemData>> =
        flow {
            val result = db.calendarDayDao().fetchCalendarItems(
                startDate.day,
                startDate.month.mapToDataMonthEnum(),
                startDate.year,
                endDate.day,
                endDate.month.mapToDataMonthEnum(),
                endDate.year
            ).map {
                it.mapToDomain()
            }
            emit(result)
        }
}