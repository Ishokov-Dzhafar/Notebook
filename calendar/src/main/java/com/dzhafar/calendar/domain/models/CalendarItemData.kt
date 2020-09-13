package com.dzhafar.calendar.domain.models

import com.dzhafar.calendar.domain.enums.EnumMonths

data class CalendarItemData(
    override val day: Int,
    override val month: EnumMonths,
    override val year: Int,
    val id: Long,
    val notesTitle: List<String>
) : CalendarDate(day, month, year)