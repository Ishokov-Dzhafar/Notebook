package com.dzhafar.main.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafar.main.domain.interactors.NoteInteractor
import com.dzhafar.main.domain.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class CreateNoteVM @Inject constructor(private val noteInteractor: NoteInteractor) : ViewModel() {

    private val createNoteMLD = MutableLiveData<Unit>()
    val createNoteLD: LiveData<Unit> = createNoteMLD

    fun createNote(title: String, text: String) {
        val note = NoteModel(
            text = text,
            date = Date().time,
            title = title,
            id = null,
            dayId = null
        )
        viewModelScope.launch {
            noteInteractor.createNote(note)
            withContext(Dispatchers.Main) {
                createNoteMLD.value = Unit
            }
        }
    }
}