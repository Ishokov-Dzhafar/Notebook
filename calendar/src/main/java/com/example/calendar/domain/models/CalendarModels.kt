package com.example.calendar.domain.models

sealed class CalendarItem(
    open val notesTitle: List<String>,
    open val number: Int
)

data class ActiveCalendarItem(
    override val notesTitle: List<String>,
    override val number: Int
) : CalendarItem(notesTitle, number)


data class DisableCalendarItem(
    override val notesTitle: List<String>,
    override val number: Int
) : CalendarItem(notesTitle, number)


data class EnableCalendarItem(
    override val notesTitle: List<String>,
    override val number: Int
) : CalendarItem(notesTitle, number)
