package com.dzhafar.calendar.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzhafar.calendar.domain.interactors.day.DayInteractor
import com.dzhafar.coreCommon.handleErrors
import com.dzhafar.coreCommon.utils.SingleLiveEvent
import com.dzhafar.coreDbApi.data.entity.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DayFragmentViewModel @Inject constructor(
    private val dayInteractor: DayInteractor
) : ViewModel() {

    var dayId: Long? = null
    private val _createNote = SingleLiveEvent<Long>()
    val createNote: LiveData<Long> = _createNote

    private val _notes = MutableLiveData<List<NoteEntity>>()
    val notes: LiveData<List<NoteEntity>> = _notes

    private val _error = SingleLiveEvent<String>()
    val error: LiveData<String> = _error

    private val supervisorJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + supervisorJob)

    fun init(dayId: Long) {
        this.dayId = dayId
        coroutineScope.launch {
            dayInteractor.fetchNotesByDayId(dayId).handleErrors {
                handleError(it)
            }.collect {
                withContext(Dispatchers.Main) {
                    _notes.postValue(it)
                }
            }
        }
    }

    fun createNote() {
        _createNote.postValue(dayId)
    }

    fun deleteNote(noteEntity: NoteEntity) {
        coroutineScope.launch {
            dayInteractor.deleteNote(noteEntity)
        }
    }

    fun handleError(error: Throwable) {
        _error.postValue(error.message)
    }
}