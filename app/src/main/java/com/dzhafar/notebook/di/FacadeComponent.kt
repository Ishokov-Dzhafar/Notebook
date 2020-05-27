package com.dzhafar.notebook.di

import android.app.Application
import com.dzhafar.core.CoreProviderFactory
import com.dzhafar.core_db_api.di.AppProvider
import com.dzhafar.core_db_api.di.DatabaseProvider
import com.dzhafar.core_db_api.di.ProvidersFacade
import com.dzhafar.core_db_api.navigation.note.NoteNavCommand
import com.dzhafar.notebook.MainApplication
import com.dzhafar.notebook.navigation.note.NoteNavCommandImpl
import dagger.Component

@Component(dependencies = [AppProvider::class, DatabaseProvider::class])
interface FacadeComponent: ProvidersFacade {
    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .databaseProvider(CoreProviderFactory.createDatabaseBuilder(AppComponent.create(application)))
                .build()
    }

    fun inject(mainApplication: MainApplication)
}