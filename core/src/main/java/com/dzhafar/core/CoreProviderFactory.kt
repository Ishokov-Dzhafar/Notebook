package com.dzhafar.core

import com.dzhafar.coreDbApi.di.AppProvider
import com.dzhafar.coreDbApi.di.DatabaseProvider
import com.dzhafar.coreDbImpl.di.DaggerCoreDbComponent
import com.dzhafar.navigation.DaggerNavigationComponent
import com.dzhafar.navigationapi.navigation.NavigationProvider

object CoreProviderFactory {
    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerCoreDbComponent.builder().appProvider(appProvider).build()
    }

    fun createNavigation(appProvider: AppProvider): NavigationProvider {
        return DaggerNavigationComponent.builder().build()
    }
}