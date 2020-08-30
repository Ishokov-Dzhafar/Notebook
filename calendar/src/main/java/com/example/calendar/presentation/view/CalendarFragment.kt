package com.example.calendar.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.dzhafar.coreDbApi.di.AppWithFacade
import com.dzhafar.coreDbApi.viewModel.ViewModelFactory
import com.example.calendar.R
import com.example.calendar.databinding.CalendarFragmentBinding
import com.example.calendar.di.CalendarComponent
import com.example.calendar.presentation.view.adapters.CalendarAdapter
import com.example.calendar.presentation.vm.CalendarFragmentViewModel
import com.example.coreCommon.view.BaseFragment
import kotlinx.android.synthetic.main.calendar_fragment.calendarGridView
import javax.inject.Inject

class CalendarFragment : BaseFragment(R.layout.calendar_fragment) {
    val viewModel: CalendarFragmentViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var calendarAdapter: CalendarAdapter

    private var binding: CalendarFragmentBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CalendarComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.calendar_fragment,
                container,
                false
            )
            calendarAdapter = CalendarAdapter(requireContext())
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(calendarGridView) {
            adapter = calendarAdapter
        }
        viewModel.calendarItems.observe(viewLifecycleOwner) {
            calendarAdapter.updateItems(calendarItems = it)
        }
    }
}