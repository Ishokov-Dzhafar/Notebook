package com.example.calendar.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendar.domain.interactors.CalendarInteractor
import com.example.calendar.domain.models.CalendarItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class CalendarFragmentViewModel @Inject constructor(
    private val calendarInteractor: CalendarInteractor
) : ViewModel() {

    private val _calendarItems = MutableLiveData<List<CalendarItem>>()
    val calendarItems: LiveData<List<CalendarItem>> get() = _calendarItems
    init {
        getCalendar()
    }
    private fun getCalendar() {
        val calendar = Calendar.getInstance()
        viewModelScope.launch {
            //val data = calendarInteractor.getCalendar(calendar.time)
            calendarInteractor.getCalendar(calendar.time).collect {
                _calendarItems.postValue(it)
            }
            //_calendarItems.value = data
        }
    }
}