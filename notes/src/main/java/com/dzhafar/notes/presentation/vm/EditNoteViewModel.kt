package com.dzhafar.notes.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafar.notes.domain.interactors.NoteInteractor
import com.dzhafar.notes.domain.models.NoteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(
    private val noteInteractor: NoteInteractor
) : ViewModel() {
    private val _updateNote = MutableLiveData<Unit>()
    val updateNote: LiveData<Unit> = _updateNote

    private val _noteModel = MutableLiveData<NoteModel>()
    val noteModel: LiveData<NoteModel> = _noteModel

    private val supervisorJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + supervisorJob)
    private lateinit var note: NoteModel

    fun init(noteId: Long) {
        coroutineScope.launch {
            note = noteInteractor.fetchNoteById(noteId)
            _noteModel.postValue(note)
        }
    }

    fun saveNote(title: String, text: String, date: Long) {
        viewModelScope.launch {
            noteInteractor.updateNote(note.copy(title = title, text = text, date = date))
            withContext(Dispatchers.Main) {
                _updateNote.value = Unit
            }
        }
    }
}