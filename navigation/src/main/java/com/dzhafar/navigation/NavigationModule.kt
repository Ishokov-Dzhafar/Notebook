package com.dzhafar.navigation

import com.dzhafar.navigationapi.navigation.calendar.NavigateToCalendarMediator
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {
    @Provides
    fun getNavigationToCalendar(): NavigateToCalendarMediator = NavigateToCalendarMediatorImpl()
}