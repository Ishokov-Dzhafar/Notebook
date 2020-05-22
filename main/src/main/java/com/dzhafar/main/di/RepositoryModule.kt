package com.dzhafar.main.di

import com.dzhafar.main.data.repository.NoteRepositoryImpl
import com.dzhafar.main.domain.repositories.NoteRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindNoteRepository(repository: NoteRepositoryImpl) : com.dzhafar.main.domain.repositories.NoteRepository
}