package com.dzhafar.core

import com.dzhafar.core_db_api.di.AppProvider
import com.dzhafar.core_db_api.di.DatabaseProvider
import com.dzhafar.core_db_impl.di.DaggerCoreDbComponent

object CoreProviderFactory {
    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerCoreDbComponent.builder().appProvider(appProvider).build()
    }
}