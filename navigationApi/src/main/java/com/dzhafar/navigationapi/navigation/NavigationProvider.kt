package com.dzhafar.navigationapi.navigation

import com.dzhafar.navigationapi.navigation.calendar.NavigateToCalendarMediator

interface NavigationProvider {
    fun navigateToCalendar(): NavigateToCalendarMediator
}