package com.dzhafar.notebook.di

import android.app.Application
import com.dzhafar.core.CoreProviderFactory
import com.dzhafar.coreDbApi.di.AppProvider
import com.dzhafar.coreDbApi.di.DatabaseProvider
import com.dzhafar.coreDbApi.di.ProvidersFacade
import com.dzhafar.notebook.MainApplication
import com.dzhafar.navigationapi.navigation.NavigationProvider
import dagger.Component

@Component(dependencies = [AppProvider::class, DatabaseProvider::class, NavigationProvider::class])
interface FacadeComponent : ProvidersFacade {
    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .databaseProvider(
                    CoreProviderFactory
                        .createDatabaseBuilder(AppComponent.create(application))
                )
                .navigationProvider(CoreProviderFactory.createNavigation(AppComponent.create(application)))
                .build()
    }

    fun inject(mainApplication: MainApplication)
}