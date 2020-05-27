package com.dzhafar.notebook.navigation.note

import com.dzhafar.notebook.R
import com.dzhafar.core_db_api.navigation.NavCommand
import com.dzhafar.core_db_api.navigation.note.NoteNavCommand
import javax.inject.Inject

class NoteNavCommandImpl @Inject constructor() :
    NoteNavCommand {
    override val toCreateNote: NavCommand =
        NavCommand(R.id.action_noteListFragment_to_createNoteFragment)
}