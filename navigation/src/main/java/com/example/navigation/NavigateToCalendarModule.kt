package com.example.navigation

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationapi.navigation.calendar.NavigateToCalendarMediator
import javax.inject.Inject

class NavigateToCalendarMediatorImpl @Inject constructor() : NavigateToCalendarMediator {

    override fun navigate(fragment: Fragment) {
        val uri = Uri.parse("Notebook://calendar/grid")
        fragment.findNavController().navigate(uri)
    }
}