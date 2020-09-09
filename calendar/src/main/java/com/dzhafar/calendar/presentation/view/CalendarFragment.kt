package com.dzhafar.calendar.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.dzhafar.coreApi.di.AppWithFacade
import com.dzhafar.coreApi.viewModel.ViewModelFactory
import com.dzhafar.calendar.R
import com.dzhafar.calendar.databinding.CalendarFragmentBinding
import com.dzhafar.calendar.di.CalendarComponent
import com.dzhafar.calendar.domain.enums.EnumMonths
import com.dzhafar.calendar.presentation.view.adapters.CalendarAdapter
import com.dzhafar.calendar.presentation.vm.CalendarFragmentViewModel
import com.dzhafar.coreCommon.view.BaseFragment
import kotlinx.android.synthetic.main.calendar_fragment.calendarGridView
import kotlinx.android.synthetic.main.calendar_fragment.monthText
import kotlinx.android.synthetic.main.calendar_fragment.view.nextMonthBtn
import kotlinx.android.synthetic.main.calendar_fragment.view.previousMonthBtn
import javax.inject.Inject

class CalendarFragment : BaseFragment(R.layout.calendar_fragment) {
    private val viewModel: CalendarFragmentViewModel by viewModels { viewModelFactory }

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
            initToolbar(toolbarView)
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
            monthText.text = getString(
                when (it.first) {
                    EnumMonths.JANUARY -> R.string.january
                    EnumMonths.FEBRUARY -> R.string.february
                    EnumMonths.MARCH -> R.string.march
                    EnumMonths.APRIL -> R.string.april
                    EnumMonths.MAY -> R.string.may
                    EnumMonths.JUNE -> R.string.june
                    EnumMonths.JULY -> R.string.july
                    EnumMonths.AUGUST -> R.string.august
                    EnumMonths.SEPTEMBER -> R.string.september
                    EnumMonths.OCTOBER -> R.string.october
                    EnumMonths.NOVEMBER -> R.string.november
                    EnumMonths.DECEMBER -> R.string.december
                }
            ).plus(" ${it.second}")
        }
    }
}