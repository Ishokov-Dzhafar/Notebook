@file:Suppress("Filename", "MatchingDeclarationName") // TODO удалить если добавятся еще классы
package com.dzhafar.navigation.calendar

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dzhafar.navigation.R
import com.dzhafar.navigationapi.navigation.calendar.NavigateToCalendarMediator
import javax.inject.Inject
class NavigateToCalendarMediatorImpl @Inject constructor() : NavigateToCalendarMediator {

    override fun navigate(fragment: Fragment) {
        val uri = Uri.parse(fragment.getString(R.string.navigate_to_calendar_deeplink))
        fragment.findNavController().navigate(uri)
    }
}