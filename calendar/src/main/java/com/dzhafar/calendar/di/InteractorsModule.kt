package com.dzhafar.calendar.di

import com.dzhafar.calendar.domain.interactors.calendar.CalendarInteractor
import com.dzhafar.calendar.domain.interactors.calendar.CalendarInteractorImpl
import com.dzhafar.calendar.domain.interactors.day.DayInteractor
import com.dzhafar.calendar.domain.interactors.day.DayInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorsModule {
    @Binds
    fun bindCalendarInteractor(interactor: CalendarInteractorImpl): CalendarInteractor

    @Binds
    fun bindDayInteractor(interactor: DayInteractorImpl): DayInteractor
}