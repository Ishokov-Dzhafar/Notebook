package com.dzhafar.core

import com.dzhafar.coreDbApi.di.AppProvider
import com.dzhafar.coreDbApi.di.DatabaseProvider
import com.dzhafar.coreDbImpl.di.DaggerCoreDbComponent

object CoreProviderFactory {
    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerCoreDbComponent.builder().appProvider(appProvider).build()
    }
}