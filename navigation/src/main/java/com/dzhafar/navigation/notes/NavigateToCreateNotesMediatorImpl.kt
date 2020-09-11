package com.dzhafar.navigation.notes

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dzhafar.navigation.R
import com.dzhafar.navigationapi.navigation.notes.NavigateToCreateNotesMediator

class NavigateToCreateNotesMediatorImpl : NavigateToCreateNotesMediator {
    override fun navigate(fragment: Fragment, params: Long) {
        val fieldArg = fragment.getString(R.string.create_note_field)
        val uri = Uri.parse(
            fragment.getString(R.string.navigate_to_create_note)
                .replace("{$fieldArg}", params.toString())
        )
        fragment.findNavController().navigate(uri)
    }
}