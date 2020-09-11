package com.dzhafar.calendar.data.repositories

import android.util.Log
import com.dzhafar.calendar.data.mapper.mapToData
import com.dzhafar.coreDbApi.di.DBApi
import com.dzhafar.calendar.data.models.CalendarItemData
import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.calendar.domain.repositories.CalendarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val db: DBApi
) : CalendarRepository {

    override fun getCalendarItemsByInterval(
        startDate: Date,
        endDate: Date
    ): Flow<CalendarItemData> {
        TODO("Not yet implemented")
    }

    override fun insertCalendarItem(calendarItem: CalendarItem): Flow<Long> =
        flow {
            emit(db.calendarDayDao().insertCalendarDay(calendarItem.mapToData()))
        }
}