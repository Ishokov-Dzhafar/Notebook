package com.dzhafar.navigationapi.navigation

import com.dzhafar.navigationapi.navigation.calendar.NavigateToCalendarMediator
import com.dzhafar.navigationapi.navigation.notes.NavigateToCreateNotesMediator

interface NavigationProvider {
    fun navigateToCalendar(): NavigateToCalendarMediator
    fun navigateToCreateNotes(): NavigateToCreateNotesMediator
}