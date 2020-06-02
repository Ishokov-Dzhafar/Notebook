package com.dzhafar.main.presentation.vm

import androidx.lifecycle.*
import com.dzhafar.main.domain.interactors.NoteInteractor
import com.dzhafar.main.domain.models.Note
import kotlinx.coroutines.*
import javax.inject.Inject

class CreateNoteVM @Inject constructor(private val noteInteractor: NoteInteractor) : ViewModel() {

    val createNoteLD = MutableLiveData<Long>()

    fun createNote(note: Note) {
        viewModelScope.launch {
            val rowId = noteInteractor.createNote(note)
            withContext(Dispatchers.Main) {
               createNoteLD.value = rowId
            }
        }
    }

}