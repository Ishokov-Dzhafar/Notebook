package com.example.calendar.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
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
import kotlinx.android.synthetic.main.calendar_fragment.monthText
import kotlinx.android.synthetic.main.calendar_fragment.toolbarLayout
import kotlinx.android.synthetic.main.calendar_fragment.view.nextMonthBtn
import kotlinx.android.synthetic.main.calendar_fragment.view.previousMonthBtn
import kotlinx.android.synthetic.main.calendar_fragment.view.toolbarLayout
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
            val toolbarView = binding!!.root.findViewById<Toolbar>(R.id.toolbarView)
            toolbarView.title = getString(R.string.calendar)
            calendarAdapter = CalendarAdapter(requireContext())
            binding!!.root.nextMonthBtn.setOnClickListener {
                viewModel.nextMonth()
            }
            binding!!.root.previousMonthBtn.setOnClickListener {
                viewModel.previousMonth()
            }
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
        viewModel.month.observe(viewLifecycleOwner) {
            monthText.text = it.name
        }
    }
}