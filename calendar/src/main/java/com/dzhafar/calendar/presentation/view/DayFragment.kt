package com.dzhafar.calendar.presentation.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dzhafar.calendar.R
import com.dzhafar.calendar.di.CalendarComponent
import com.dzhafar.calendar.presentation.view.adapters.NotesAdapter
import com.dzhafar.calendar.presentation.vm.DayFragmentViewModel
import com.dzhafar.coreApi.di.AppWithFacade
import com.dzhafar.coreApi.viewModel.ViewModelFactory
import com.dzhafar.coreCommon.view.BaseFragment
import com.dzhafar.navigationapi.navigation.notes.NavigateToCreateNotesMediator
import com.dzhafar.navigationapi.navigation.notes.NavigateToEditNoteMediator
import kotlinx.android.synthetic.main.day_fragment.notesList
import javax.inject.Inject

class DayFragment : BaseFragment(R.layout.day_fragment) {

    companion object {
        const val ANIMATION_DURATION = 500L
    }

    private val viewModel: DayFragmentViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigateToCreateNotesMediator: NavigateToCreateNotesMediator

    @Inject
    lateinit var navigateToEditNoteMediator: NavigateToEditNoteMediator

    private val notesAdapter: NotesAdapter = NotesAdapter(
        clickItemCallback = {
            navigateToEditNoteMediator.navigate(
                this,
                NavigateToEditNoteMediator.Params(it.id!!, it.dayId!!)
            )
        },
        deleteItem = { item, position ->
            Handler().postDelayed(
                {
                    viewModel.deleteNote(item)
                },
                ANIMATION_DURATION
            )
        }
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CalendarComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbarView)
        toolbar.inflateMenu(R.menu.day_fragment_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addNote -> {
                    viewModel.createNote()
                    true
                }
                else -> false
            }
        }
        with(notesList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = notesAdapter
            itemAnimator?.addDuration = ANIMATION_DURATION
        }
        initToolbar(toolbar)
        initObservable()
        initArg()
    }

    private fun initArg() {
        runCatching {
            requireArguments().getLong(getString(R.string.day_id))
        }.onSuccess {
            viewModel.init(it)
        }.onFailure {
            viewModel.handleError(it)
        }
    }

    private fun initObservable() {
        viewModel.error.observe(
            viewLifecycleOwner,
            Observer {
                showErrorDialog(it)
            }
        )
        viewModel.createNote.observe(
            viewLifecycleOwner,
            Observer {
                navigateToCreateNotesMediator.navigate(this, it)
            }
        )
        viewModel.notes.observe(
            viewLifecycleOwner,
            Observer {
                notesAdapter.setItems(it)
            }
        )
    }
}