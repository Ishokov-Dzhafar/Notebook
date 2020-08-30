package com.example.navigation

import com.dzhafar.coreDbApi.di.AppProvider
import com.example.navigationapi.navigation.NavigationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent : NavigationProvider