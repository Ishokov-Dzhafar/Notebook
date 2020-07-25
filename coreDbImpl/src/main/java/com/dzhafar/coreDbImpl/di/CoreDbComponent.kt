package com.dzhafar.coreDbImpl.di

import com.dzhafar.coreDbApi.di.AppProvider
import com.dzhafar.coreDbApi.di.DatabaseProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppProvider::class], modules = [DBModule::class])
interface CoreDbComponent : DatabaseProvider
