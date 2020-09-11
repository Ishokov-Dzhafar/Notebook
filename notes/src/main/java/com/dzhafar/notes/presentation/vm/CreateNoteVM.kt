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
import java.util.Date
import javax.inject.Inject

class CreateNoteVM @Inject constructor(private val noteInteractor: NoteInteractor) : ViewModel() {

    private val createNoteMLD = MutableLiveData<Unit>()
    val createNoteLD: LiveData<Unit> = createNoteMLD

    private var dayId: Long? = null

    fun createNote(title: String, text: String) {
        val note = NoteModel(
            text = text,
            date = Date().time,
            title = title,
            id = null,
            dayId = dayId
        )
        viewModelScope.launch {
            noteInteractor.createNote(note)
            withContext(Dispatchers.Main) {
                createNoteMLD.value = Unit
            }
        }
    }

    fun saveDayId(dayId: Long) {
        this.dayId = dayId
    }
}