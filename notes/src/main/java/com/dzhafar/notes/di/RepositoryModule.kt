package com.dzhafar.notes.di

import com.dzhafar.notes.data.repository.NoteRepositoryImpl
import com.dzhafar.notes.domain.repositories.NoteRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindNoteRepository(repository: NoteRepositoryImpl): NoteRepository
}