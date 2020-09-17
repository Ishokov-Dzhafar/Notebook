package com.dzhafar.navigation

import com.dzhafar.navigation.calendar.NavigateToCalendarMediatorImpl
import com.dzhafar.navigation.notes.NavigateToCreateNotesMediatorImpl
import com.dzhafar.navigation.notes.NavigateToEditNoteMediatorImpl
import com.dzhafar.navigationapi.navigation.calendar.NavigateToCalendarMediator
import com.dzhafar.navigationapi.navigation.notes.NavigateToCreateNotesMediator
import com.dzhafar.navigationapi.navigation.notes.NavigateToEditNoteMediator
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
    @Provides
    fun getNavigationToEditNote(): NavigateToEditNoteMediator =
        NavigateToEditNoteMediatorImpl()
}