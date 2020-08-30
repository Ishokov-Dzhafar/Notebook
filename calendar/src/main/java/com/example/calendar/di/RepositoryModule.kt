package com.example.calendar.di

import com.example.calendar.data.repositories.CalendarRepositoryImpl
import com.example.calendar.domain.repositories.CalendarRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindNoteRepository(repository: CalendarRepositoryImpl): CalendarRepository
}