package com.dzhafar.calendar.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.dzhafar.calendar.R
import com.dzhafar.calendar.di.CalendarComponent
import com.dzhafar.calendar.presentation.vm.DayFragmentViewModel
import com.dzhafar.coreApi.di.AppWithFacade
import com.dzhafar.coreApi.viewModel.ViewModelFactory
import com.dzhafar.coreCommon.view.BaseFragment
import javax.inject.Inject

class DayFragment : BaseFragment(R.layout.day_fragment) {

    private val fragmentViewModel: DayFragmentViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CalendarComponent.create((requireActivity().application as AppWithFacade).getFacade())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}