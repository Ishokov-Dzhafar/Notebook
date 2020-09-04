package com.dzhafar.calendar.di

import com.dzhafar.calendar.data.repositories.CalendarRepositoryImpl
import com.dzhafar.calendar.domain.repositories.CalendarRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindNoteRepository(repository: CalendarRepositoryImpl): CalendarRepository
}