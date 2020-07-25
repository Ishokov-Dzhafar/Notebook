package com.dzhafar.coreDbApi.di

import android.content.Context

interface AppProvider {
    fun provideContext(): Context
}