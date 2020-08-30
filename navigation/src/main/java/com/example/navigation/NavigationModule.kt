package com.example.navigation

import com.example.navigationapi.navigation.calendar.NavigateToCalendarMediator
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {
    @Provides
    fun getNavigationToCalendar(): NavigateToCalendarMediator = NavigateToCalendarMediatorImpl()
}