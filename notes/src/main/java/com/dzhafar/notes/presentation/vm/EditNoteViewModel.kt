package com.dzhafar.notes.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafar.notes.domain.interactors.NoteInteractor
import com.dzhafar.notes.domain.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(
    private val noteInteractor: NoteInteractor
) : ViewModel() {
    private val updateNoteMLD = MutableLiveData<Unit>()
    val updateNoteLD: LiveData<Unit> = updateNoteMLD

    fun saveNote(note: NoteModel) {
        viewModelScope.launch {
            noteInteractor.updateNote(note)
            withContext(Dispatchers.Main) {
                updateNoteMLD.value = Unit
            }
        }
    }
}