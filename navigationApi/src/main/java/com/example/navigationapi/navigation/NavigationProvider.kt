package com.example.navigationapi.navigation

import com.example.navigationapi.navigation.calendar.NavigateToCalendarMediator

interface NavigationProvider {
    fun navigateToCalendar(): NavigateToCalendarMediator
}