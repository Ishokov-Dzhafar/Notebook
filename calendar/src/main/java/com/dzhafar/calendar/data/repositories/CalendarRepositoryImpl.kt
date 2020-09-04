package com.dzhafar.calendar.data.repositories

import com.dzhafar.coreDbApi.di.DBApi
import com.dzhafar.calendar.data.models.CalendarItemData
import com.dzhafar.calendar.domain.repositories.CalendarRepository
import kotlinx.coroutines.flow.Flow
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

}