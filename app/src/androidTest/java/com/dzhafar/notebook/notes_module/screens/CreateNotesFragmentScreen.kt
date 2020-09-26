package com.dzhafar.notebook.notes_module.screens

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.dzhafar.notes.R

class CreateNotesFragmentScreen : Screen<CreateNotesFragmentScreen>() {
    val titleEditText = KEditText {
        withId(R.id.noteTitle)
    }
    val bodyEditText = KEditText {
        withId(R.id.noteText)
    }
    val saveBtn = KButton {
        withId(R.id.save)
    }
}