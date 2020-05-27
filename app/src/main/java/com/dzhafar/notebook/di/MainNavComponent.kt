package com.dzhafar.notebook.di

import android.app.Application
import com.dzhafar.core_db_api.di.MainNavProvider
import com.dzhafar.core_db_api.navigation.note.NoteNavCommand
import com.dzhafar.notebook.MainApplication
import com.dzhafar.notebook.navigation.note.NoteNavCommandImpl
import dagger.Component

@Component(dependencies = [NoteNavCommand::class])
interface MainNavComponent: MainNavProvider {
    companion object {

        fun init(): MainNavComponent =
            DaggerMainNavComponent.builder()
                .noteNavCommand(NoteNavCommandImpl())
                .build()
    }

}