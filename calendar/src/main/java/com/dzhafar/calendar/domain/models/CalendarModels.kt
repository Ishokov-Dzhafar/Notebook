package com.dzhafar.calendar.domain.models

import com.dzhafar.calendar.domain.enums.EnumMonths

sealed class CalendarItem(
    open val notesTitle: List<String>,
    open val day: Int,
    open val month: EnumMonths,
    open val year: Int,
    open val id: Long?
)

data class ActiveCalendarItem(
    override val notesTitle: List<String>,
    override val day: Int,
    override val month: EnumMonths,
    override val year: Int,
    override val id: Long?
) : CalendarItem(notesTitle, day, month, year, id)

data class DisableCalendarItem(
    override val notesTitle: List<String>,
    override val day: Int,
    override val month: EnumMonths,
    override val year: Int,
    override val id: Long?
) : CalendarItem(notesTitle, day, month, year, id)

data class EnableCalendarItem(
    override val notesTitle: List<String>,
    override val day: Int,
    override val month: EnumMonths,
    override val year: Int,
    override val id: Long?
) : CalendarItem(notesTitle, day, month, year, id)
