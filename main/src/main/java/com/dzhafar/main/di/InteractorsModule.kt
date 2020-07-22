package com.dzhafar.main.di

import com.dzhafar.main.domain.interactors.NoteInteractor
import com.dzhafar.main.domain.interactors.NoteInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorsModule {
    @Binds
    fun bindNoteInteractor(interactor: NoteInteractorImpl): NoteInteractor
}