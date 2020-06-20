package com.dzhafar.core_db_impl.di

import com.dzhafar.core_db_api.di.AppProvider
import com.dzhafar.core_db_api.di.DatabaseProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppProvider::class], modules = [DBModule::class])
interface CoreDbComponent : DatabaseProvider
