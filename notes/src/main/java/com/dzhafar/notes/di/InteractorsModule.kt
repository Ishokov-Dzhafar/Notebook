package com.dzhafar.notes.di

import com.dzhafar.notes.domain.interactors.NoteInteractor
import com.dzhafar.notes.domain.interactors.NoteInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorsModule {
    @Binds
    fun bindNoteInteractor(interactor: NoteInteractorImpl): NoteInteractor
}