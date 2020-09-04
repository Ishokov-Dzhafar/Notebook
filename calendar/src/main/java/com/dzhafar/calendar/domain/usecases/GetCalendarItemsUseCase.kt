package com.dzhafar.calendar.domain.usecases

import com.dzhafar.calendar.domain.enums.EnumDayOfWeek
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.FRIDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.MONDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.SATURDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.SUNDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.THURSDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.TUESDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.WEDNESDAY
import com.dzhafar.calendar.domain.models.ActiveCalendarItem
import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.calendar.domain.models.DisableCalendarItem
import com.dzhafar.calendar.domain.models.EnableCalendarItem
import com.dzhafar.coreCommon.usecase.base.FlowUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class GetCalendarItemsUseCase @Inject constructor(
    private val getDayOfWeekFromDateUseCase: GetDayOfWeekFromDateUseCase
) : FlowUseCase<List<CalendarItem>, GetCalendarItemsUseCase.Params> {

    companion object {
        private const val januaryIndex = 0
        private const val decemberIndex = 11
        private const val visibleDaysCount = 42
        private const val countOfDaysInWeek = 7
    }

    @FlowPreview
    override fun execute(params: Params): Flow<List<CalendarItem>> {
        return flow {
            val date = getFirstDayOfMonth(params.visibleDate)
            emit(date)
        }.flatMapConcat { date ->
            getDayOfWeekFromDateUseCase.execute(GetDayOfWeekFromDateUseCase.Params(date))
        }.map { dayOfWeek ->
            val currentDay = Calendar.getInstance()
                .apply { time = params.currentDate }
                .get(Calendar.DAY_OF_MONTH)
            val calendar = Calendar.getInstance().apply { time = params.visibleDate }
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val months = getCountOfDayInMonths(year)
            val countFromMondayToCurrent = countFromMondayTo(dayOfWeek)
            val finishDateDayOfWeek = ((dayOfWeek.id + months[month]!! - 1) % countOfDaysInWeek).let { id ->
                EnumDayOfWeek.values().find { it.id == id }!!
            }
            val countToMondayFromCurrent = countToMondayFrom(finishDateDayOfWeek)
            val calendarItems = mutableListOf<CalendarItem>()
            val countCurrentMonth = months[month]!!
            val countPreviousMonth =
                if (month == januaryIndex) months[decemberIndex]!! else months[month - 1]!!

            if (countFromMondayToCurrent != 0) {
                for (i in countFromMondayToCurrent - 1 downTo 0)
                    calendarItems.add(DisableCalendarItem(listOf(), countPreviousMonth - i))
            }

            for (i in 1..countCurrentMonth)
                calendarItems.add(
                    if (i == currentDay) EnableCalendarItem(
                        listOf(),
                        i
                    ) else ActiveCalendarItem(listOf(), i)
                )

            if (countToMondayFromCurrent != 0) {
                for (i in 1..countToMondayFromCurrent)
                    calendarItems.add(DisableCalendarItem(listOf(), i))
            }

            if (calendarItems.size < visibleDaysCount) {
                for (i in 1..visibleDaysCount - calendarItems.size)
                    calendarItems.add(DisableCalendarItem(listOf(), countToMondayFromCurrent + i))
            }
            calendarItems
        }
    }

    private fun getFirstDayOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance().apply {
            time = date
        }
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        return Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, 1)
        }.time
    }

    @Suppress("MagicNumber")
    private fun getCountOfDayInMonths(year: Int): HashMap<Int, Int> =
        hashMapOf(
            0 to 31,
            1 to if (year % 4 == 0) 29 else 28,
            2 to 31,
            3 to 30,
            4 to 31,
            5 to 30,
            6 to 31,
            7 to 31,
            8 to 30,
            9 to 31,
            10 to 30,
            11 to 31
        )

    @Suppress("MagicNumber")
    private fun countFromMondayTo(dayOfWeek: EnumDayOfWeek): Int =
        when (dayOfWeek) {
            SUNDAY -> 6
            MONDAY -> 0
            TUESDAY -> 1
            WEDNESDAY -> 2
            THURSDAY -> 3
            FRIDAY -> 4
            SATURDAY -> 5
        }

    @Suppress("MagicNumber")
    private fun countToMondayFrom(dayOfWeek: EnumDayOfWeek): Int =
        when (dayOfWeek) {
            SUNDAY -> 0
            MONDAY -> 6
            TUESDAY -> 5
            WEDNESDAY -> 4
            THURSDAY -> 3
            FRIDAY -> 2
            SATURDAY -> 1
        }

    data class Params(val currentDate: Date, val visibleDate: Date)
}