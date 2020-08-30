package com.example.navigationapi.navigation

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

interface NavigationMediatorParams<Params> {
    fun navigate(context: Context, params: Params)
}

interface NavigationMediator {
    fun navigate(fragment: Fragment)
}