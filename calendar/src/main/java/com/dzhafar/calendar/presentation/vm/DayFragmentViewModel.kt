package com.dzhafar.calendar.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dzhafar.coreCommon.utils.SingleLiveEvent
import javax.inject.Inject

class DayFragmentViewModel @Inject constructor() : ViewModel() {

    var dayId: Long? = null
    private val _createNote = SingleLiveEvent<Long>()
    val createNote: LiveData<Long> = _createNote

    private val _error = SingleLiveEvent<String>()
    val error: LiveData<String> = _error

    fun init(dayId: Long) {
        this.dayId = dayId
    }

    fun createNote() {
        _createNote.postValue(dayId)
    }

    fun handleError(error: Throwable) {
        _error.postValue(error.message)
    }
}