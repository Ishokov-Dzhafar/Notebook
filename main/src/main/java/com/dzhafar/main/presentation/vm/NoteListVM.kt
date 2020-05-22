package com.dzhafar.main.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dzhafar.main.domain.interactors.NoteInteractor
import com.dzhafar.main.domain.models.Note
import javax.inject.Inject

class NoteListVM @Inject constructor(noteInteractor: NoteInteractor) : ViewModel() {

    val noteList: LiveData<List<Note>> = noteInteractor.getNoteList().asLiveData()

}