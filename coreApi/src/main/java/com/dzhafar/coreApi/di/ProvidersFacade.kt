package com.dzhafar.coreApi.di

import com.dzhafar.coreDbApi.di.DatabaseProvider
import com.dzhafar.navigationapi.navigation.NavigationProvider

interface ProvidersFacade : AppProvider, DatabaseProvider, NavigationProvider