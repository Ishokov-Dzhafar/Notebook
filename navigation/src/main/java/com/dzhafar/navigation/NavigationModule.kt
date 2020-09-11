package com.dzhafar.navigation

import com.dzhafar.navigation.calendar.NavigateToCalendarMediatorImpl
import com.dzhafar.navigation.notes.NavigateToCreateNotesMediatorImpl
import com.dzhafar.navigationapi.navigation.calendar.NavigateToCalendarMediator
import com.dzhafar.navigationapi.navigation.notes.NavigateToCreateNotesMediator
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {
    @Provides
    fun getNavigationToCalendar(): NavigateToCalendarMediator =
        NavigateToCalendarMediatorImpl()
    @Provides
    fun getNavigationToCreateNotes(): NavigateToCreateNotesMediator =
        NavigateToCreateNotesMediatorImpl()
}