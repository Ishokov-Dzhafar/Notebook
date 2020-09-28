package com.dzhafar.notebook.notes_module.screens

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.text.KTextView
import com.dzhafar.notes.R
import org.hamcrest.Matcher

class NoteItem(parent: Matcher<View>) : KRecyclerItem<NoteItem>(parent) {
    val title: KTextView = KTextView(parent) { withId(R.id.title) }
    val body: KTextView = KTextView(parent) { withId(R.id.text) }
}