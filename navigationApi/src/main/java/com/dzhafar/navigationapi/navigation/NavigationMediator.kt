package com.dzhafar.navigationapi.navigation

import androidx.fragment.app.Fragment

interface NavigationMediatorParams<Params> {
    fun navigate(fragment: Fragment, params: Params)
}

interface NavigationMediator {
    fun navigate(fragment: Fragment)
}