package com.dzhafar.calendar.domain.models

import com.dzhafar.calendar.domain.enums.EnumMonths

open class CalendarDate(
    open val day: Int,
    open val month: EnumMonths,
    open val year: Int
)