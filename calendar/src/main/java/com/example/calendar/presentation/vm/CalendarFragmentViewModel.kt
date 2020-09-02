package com.example.calendar.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendar.domain.enums.EnumMonths
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
    private val currentDate = Calendar.getInstance()
    private val visibleDate = Calendar.getInstance()
    private val _month = MutableLiveData<EnumMonths>()
    val month: LiveData<EnumMonths> get() = _month

    init {
        getCalendar()
    }

    private fun getCalendar() {
        updateCalendarData()
    }

    fun nextMonth() {
        val nextMonth = visibleDate.get(Calendar.MONTH) + 1
        if (nextMonth == 12) {
            visibleDate.set(Calendar.MONTH, 0)
            visibleDate.set(Calendar.YEAR, visibleDate.get(Calendar.YEAR) + 1)
        } else {
            visibleDate.set(Calendar.MONTH, nextMonth)
        }
        updateCalendarData()
    }

    private fun updateCalendarData() {
        viewModelScope.launch {
            calendarInteractor.getCalendar(currentDate.time, visibleDate.time)
                .collect { calendarList ->
                    val idMonth = visibleDate.get(Calendar.MONTH)
                    _month.postValue(EnumMonths.values().find { it.id == idMonth })
                    _calendarItems.postValue(calendarList)
                }
        }
    }

    fun previousMonth() {
        val previousMonth = visibleDate.get(Calendar.MONTH) - 1
        if (previousMonth == -1) {
            visibleDate.set(Calendar.MONTH, 11)
            visibleDate.set(Calendar.YEAR, visibleDate.get(Calendar.YEAR) - 1)
        } else {
            visibleDate.set(Calendar.MONTH, previousMonth)
        }
        updateCalendarData()
    }
}