package com.dzhafar.main.presentation.vm

import androidx.lifecycle.*
import com.dzhafar.core_db_api.di.MainNavProvider
import com.dzhafar.main.domain.interactors.NoteInteractor
import com.dzhafar.main.domain.models.Note
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CreateNoteVM @Inject constructor(private val noteInteractor: NoteInteractor, private val mainNavProvider: MainNavProvider) : ViewModel() {

    private val supervisorJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + supervisorJob)

    val createNoteLD = MutableLiveData<Long>()

    fun createNote(note: Note) {
        /*val a = viewModelScope.async {
            noteInteractor.createNote(note)
        }*/
        viewModelScope.launch {
            val rowId = noteInteractor.createNote(note)
            withContext(Dispatchers.Main) {
               createNoteLD.value = rowId
            }
        }
    }

}