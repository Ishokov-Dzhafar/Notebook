package com.dzhafar.navigation.notes

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dzhafar.navigation.R
import com.dzhafar.navigationapi.navigation.notes.NavigateToCreateNotesMediator
import com.dzhafar.navigationapi.navigation.notes.NavigateToEditNoteMediator

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

class NavigateToEditNoteMediatorImpl : NavigateToEditNoteMediator {
    override fun navigate(fragment: Fragment, params: NavigateToEditNoteMediator.Params) {
        val fieldArg = fragment.getString(R.string.edit_note_field)
        val uri = Uri.parse(
            fragment.getString(R.string.navigate_to_edit_note)
                .replace("{$fieldArg}", params.toString())
        )
        fragment.findNavController().navigate(uri)
    }
}