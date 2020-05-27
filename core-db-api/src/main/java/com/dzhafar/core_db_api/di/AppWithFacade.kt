package com.dzhafar.core_db_api.di

interface AppWithFacade {
    fun getFacade(): ProvidersFacade
    fun getMainNavProvider(): MainNavProvider
}