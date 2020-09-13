package com.dzhafar.calendar.domain.usecases

import com.dzhafar.calendar.domain.enums.EnumDayOfWeek
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.FRIDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.MONDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.SATURDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.SUNDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.THURSDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.TUESDAY
import com.dzhafar.calendar.domain.enums.EnumDayOfWeek.WEDNESDAY
import com.dzhafar.calendar.domain.enums.EnumMonths
import com.dzhafar.calendar.domain.models.ActiveCalendarItem
import com.dzhafar.calendar.domain.models.CalendarDate
import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.calendar.domain.models.DisableCalendarItem
import com.dzhafar.calendar.domain.models.EnableCalendarItem
import com.dzhafar.calendar.domain.repositories.CalendarRepository
import com.dzhafar.coreCommon.usecase.base.FlowUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class GetCalendarItemsUseCase @Inject constructor(
    private val getDayOfWeekFromDateUseCase: GetDayOfWeekFromDateUseCase,
    private val calendarRepository: CalendarRepository
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
            val finishDateDayOfWeek =
                ((dayOfWeek.id + months[month]!! - 1) % countOfDaysInWeek).let { id ->
                    EnumDayOfWeek.values().find { it.id == id }!!
                }
            val countToMondayFromCurrent = countToMondayFrom(finishDateDayOfWeek)
            val calendarItems = mutableListOf<CalendarItem>()
            val countCurrentMonth = months[month]!!
            val countPreviousMonth =
                if (month == januaryIndex) months[decemberIndex]!! else months[month - 1]!!

            val monthEnum = EnumMonths.values().find { it.id == month } ?: EnumMonths.JANUARY

            calendarItems.addAll(
                getPreviousMonthDays(
                    countFromMondayToCurrent,
                    countPreviousMonth,
                    monthEnum,
                    year
                )
            )

            calendarItems.addAll(
                getEnableMonthDays(countCurrentMonth, currentDay, monthEnum, year)
            )
            calendarItems.addAll(
                getNextMonthDays(countToMondayFromCurrent, calendarItems, monthEnum, year)
            )
            calendarItems
        }
    }

    private suspend fun getNextMonthDays(
        countToMondayFromCurrent: Int,
        calendarItems: MutableList<CalendarItem>,
        monthEnum: EnumMonths,
        year: Int
    ): List<CalendarItem> {
        val result = mutableListOf<CalendarItem>()
        val startDate = CalendarDate(
            1,
            monthEnum,
            year
        )
        val endDate = CalendarDate(
            countToMondayFromCurrent,
            monthEnum,
            year
        )
        val listFromDB = calendarRepository.fetchCalendarItems(startDate, endDate).map { list ->
            list.map {
                DisableCalendarItem(
                    it.notesTitle,
                    it.day,
                    monthEnum,
                    year,
                    it.id
                )
            }
        }.single()
        if (countToMondayFromCurrent != 0) {
            for (i in 1..countToMondayFromCurrent)
                result.add(
                    listFromDB.find { it.day == i } ?: DisableCalendarItem(
                        listOf(),
                        i,
                        monthEnum,
                        year,
                        null
                    )
                )
        }
        val size = calendarItems.size + result.size
        if (size < visibleDaysCount) {
            for (i in 1..visibleDaysCount - size)
                result.add(
                    listFromDB.find { it.day == i } ?: DisableCalendarItem(
                        listOf(),
                        countToMondayFromCurrent + i,
                        monthEnum,
                        year,
                        null
                    )
                )
        }
        return result
    }

    private suspend fun getEnableMonthDays(
        countCurrentMonth: Int,
        currentDay: Int,
        monthEnum: EnumMonths,
        year: Int
    ): List<CalendarItem> {
        val result = mutableListOf<CalendarItem>()
        val startDate = CalendarDate(
            1,
            monthEnum,
            year
        )
        val endDate = CalendarDate(
            countCurrentMonth,
            monthEnum,
            year
        )
        val listFromDB = calendarRepository.fetchCalendarItems(startDate, endDate).map { list ->
            list.map {
                if (it.day == currentDay) EnableCalendarItem(
                    it.notesTitle,
                    it.day,
                    monthEnum,
                    year,
                    it.id
                ) else ActiveCalendarItem(
                    it.notesTitle,
                    it.day,
                    monthEnum,
                    year,
                    it.id
                )
            }
        }.single()
        for (i in 1..countCurrentMonth)
            result.add(
                listFromDB.find { it.day == i } ?: if (i == currentDay) EnableCalendarItem(
                    listOf(), i, monthEnum, year, null
                ) else ActiveCalendarItem(listOf(), i, monthEnum, year, null)
            )
        return result
    }

    private suspend fun getPreviousMonthDays(
        countFromMondayToCurrent: Int,
        countPreviousMonth: Int,
        monthEnum: EnumMonths,
        year: Int
    ): List<DisableCalendarItem> {
        val result = mutableListOf<DisableCalendarItem>()
        if (countFromMondayToCurrent != 0) {
            val startDate = CalendarDate(
                countPreviousMonth - (countFromMondayToCurrent - 1),
                monthEnum,
                year
            )
            val endDate = CalendarDate(
                countPreviousMonth,
                monthEnum,
                year
            )
            val listFromDB = calendarRepository.fetchCalendarItems(startDate, endDate).map { list ->
                list.map {
                    DisableCalendarItem(
                        it.notesTitle,
                        it.day,
                        monthEnum,
                        year,
                        it.id
                    )
                }
            }.single()
            for (i in countFromMondayToCurrent - 1 downTo 0)
                result.add(
                    listFromDB.find { it.day == countPreviousMonth - i } ?: DisableCalendarItem(
                        listOf(),
                        countPreviousMonth - i,
                        monthEnum,
                        year,
                        null
                    )
                )
        }
        return result
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