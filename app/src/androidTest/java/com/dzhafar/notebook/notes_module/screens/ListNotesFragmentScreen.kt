package com.dzhafar.notebook.notes_module.screens

import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.dzhafar.notes.R

class ListNotesFragmentScreen : Screen<ListNotesFragmentScreen>() {
    val menuAddBtn = KButton {
        withId(R.id.addNote)
    }
    val noteList = KRecyclerView(
        {
            withId(R.id.noteList)
        },
        itemTypeBuilder = {
            itemType(::NoteItem)
        }
    )
}
