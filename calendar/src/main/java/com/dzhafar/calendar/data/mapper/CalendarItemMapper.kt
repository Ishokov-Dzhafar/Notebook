package com.dzhafar.calendar.data.mapper

import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.coreDbApi.data.entity.CalendarDayEntity

fun CalendarItem.mapToData() = CalendarDayEntity(
    id = id,
    year = year,
    month = month.mapToDataMonthEnum(),
    day = day
)