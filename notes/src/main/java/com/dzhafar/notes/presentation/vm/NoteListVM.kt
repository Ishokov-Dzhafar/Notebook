package com.dzhafar.notes.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dzhafar.notes.domain.interactors.NoteInteractor
import com.dzhafar.notes.domain.models.NoteModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListVM @Inject constructor(private val noteInteractor: NoteInteractor) : ViewModel() {

    val noteModelList: LiveData<List<NoteModel>> = noteInteractor.getNoteList().asLiveData()

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch {
            noteInteractor.deleteNote(noteModel)
        }
    }
}