package com.dzhafar.calendar.data.mapper

import com.dzhafar.calendar.domain.models.CalendarItemData
import com.dzhafar.coreDbApi.data.models.CalendarItemModel

fun CalendarItemModel.mapToDomain() = CalendarItemData(
    id = calendarDayEntity.id!!,
    day = calendarDayEntity.day,
    month = calendarDayEntity.month.mapToDomain(),
    year = calendarDayEntity.year,
    notesTitle = notes.map { it.text }
)