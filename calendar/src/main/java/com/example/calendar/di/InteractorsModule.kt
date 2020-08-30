package com.example.calendar.di

import com.example.calendar.domain.interactors.CalendarInteractor
import com.example.calendar.domain.interactors.CalendarInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorsModule {
    @Binds
    fun bindNoteInteractor(interactor: CalendarInteractorImpl): CalendarInteractor
}