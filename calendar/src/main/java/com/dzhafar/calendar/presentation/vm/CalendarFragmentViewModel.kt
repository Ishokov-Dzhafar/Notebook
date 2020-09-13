package com.dzhafar.calendar.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dzhafar.calendar.domain.enums.EnumMonths
import com.dzhafar.calendar.domain.interactors.CalendarInteractor
import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.coreCommon.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

class CalendarFragmentViewModel @Inject constructor(
    private val calendarInteractor: CalendarInteractor
) : ViewModel() {

    companion object {
        private const val countOfMonth = 12
    }

    private val _calendarItems = MutableLiveData<List<CalendarItem>>()
    val calendarItems: LiveData<List<CalendarItem>> get() = _calendarItems
    private val _openDay = SingleLiveEvent<Long>()
    val openDay: LiveData<Long> = _openDay
    private val currentDate = Calendar.getInstance()
    private val visibleDate = Calendar.getInstance()
    private val _month = MutableLiveData<Pair<EnumMonths, Int>>()
    val month: LiveData<Pair<EnumMonths, Int>> get() = _month

    private val supervisorJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + supervisorJob)

    init {
        getCalendar()
    }

    private fun getCalendar() {
        updateCalendarData()
    }

    fun nextMonth() {
        val nextMonth = visibleDate.get(Calendar.MONTH) + 1
        if (nextMonth == countOfMonth) {
            visibleDate.set(Calendar.MONTH, 0)
            visibleDate.set(Calendar.YEAR, visibleDate.get(Calendar.YEAR) + 1)
        } else {
            visibleDate.set(Calendar.MONTH, nextMonth)
        }
        updateCalendarData()
    }

    private fun updateCalendarData() {
        coroutineScope.launch {
            calendarInteractor.getCalendar(currentDate.time, visibleDate.time)
                .collect { calendarList ->
                    val idMonth = visibleDate.get(Calendar.MONTH)
                    val pair = Pair(
                        EnumMonths.values().find {
                            it.id == idMonth
                        }!!,
                        visibleDate.get(Calendar.YEAR)
                    )
                    _month.postValue(pair)
                    _calendarItems.postValue(calendarList)
                }
        }
    }

    fun previousMonth() {
        val previousMonth = visibleDate.get(Calendar.MONTH) - 1
        if (previousMonth == -1) {
            visibleDate.set(Calendar.MONTH, countOfMonth - 1)
            visibleDate.set(Calendar.YEAR, visibleDate.get(Calendar.YEAR) - 1)
        } else {
            visibleDate.set(Calendar.MONTH, previousMonth)
        }
        updateCalendarData()
    }

    fun openCalendarItem(calendarItem: CalendarItem) {
        if (calendarItem.id != null) {
            _openDay.postValue(calendarItem.id)
        } else {
            coroutineScope.launch {
                calendarInteractor.insertCalendarItem(calendarItem).collect {
                    withContext(Dispatchers.Main) {
                        Log.d("TAAAAG", "AAAAAAAAA$it")
                        _openDay.postValue(it)
                    }
                }
            }
        }
    }
}