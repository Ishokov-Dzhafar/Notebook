package com.dzhafar.core_db_api.di

import android.content.Context

interface AppProvider {
    fun provideContext(): Context
}