package com.dzhafar.navigation

import com.dzhafar.navigationapi.navigation.NavigationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface NavigationComponent : NavigationProvider