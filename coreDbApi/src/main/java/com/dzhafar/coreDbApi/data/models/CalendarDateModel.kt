package com.dzhafar.coreDbApi.data.models

import com.dzhafar.coreDbApi.data.enums.MonthEnums

open class CalendarDateModel(
    open val day: Int,
    open val month: MonthEnums,
    open val year: Int
)