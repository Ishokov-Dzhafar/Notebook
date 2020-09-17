package com.dzhafar.navigationapi.navigation

import com.dzhafar.navigationapi.navigation.calendar.NavigateToCalendarMediator
import com.dzhafar.navigationapi.navigation.notes.NavigateToCreateNotesMediator
import com.dzhafar.navigationapi.navigation.notes.NavigateToEditNoteMediator

interface NavigationProvider {
    fun navigateToCalendar(): NavigateToCalendarMediator
    fun navigateToCreateNotes(): NavigateToCreateNotesMediator
    fun navigateToEditNote(): NavigateToEditNoteMediator
}