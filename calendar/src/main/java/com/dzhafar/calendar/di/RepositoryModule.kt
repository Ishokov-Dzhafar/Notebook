package com.dzhafar.calendar.di

import com.dzhafar.calendar.data.repositories.CalendarRepositoryImpl
import com.dzhafar.calendar.data.repositories.NoteRepositoryImpl
import com.dzhafar.calendar.domain.repositories.CalendarRepository
import com.dzhafar.calendar.domain.repositories.NoteRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindCalendarRepository(repository: CalendarRepositoryImpl): CalendarRepository

    @Binds
    fun bindNoteRepository(repository: NoteRepositoryImpl): NoteRepository
}