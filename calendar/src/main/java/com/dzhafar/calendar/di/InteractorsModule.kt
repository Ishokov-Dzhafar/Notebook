package com.dzhafar.calendar.di

import com.dzhafar.calendar.domain.interactors.CalendarInteractor
import com.dzhafar.calendar.domain.interactors.CalendarInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorsModule {
    @Binds
    fun bindNoteInteractor(interactor: CalendarInteractorImpl): CalendarInteractor
}