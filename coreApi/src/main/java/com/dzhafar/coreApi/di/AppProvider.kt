package com.dzhafar.coreApi.di

import android.content.Context

interface AppProvider {
    fun provideContext(): Context
}