package com.dzhafar.notes.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzhafar.coreCommon.view.BaseFragment
import com.dzhafar.notes.R
import com.dzhafar.notes.databinding.FragmentNoteListBinding
import com.dzhafar.notes.di.MainComponent
import com.dzhafar.notes.presentation.view.adapter.NoteListRVAdapter
import com.dzhafar.notes.presentation.vm.NoteListVM
import com.dzhafar.navigationapi.navigation.calendar.NavigateToCalendarMediator
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [NoteListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteListFragment : BaseFragment(R.layout.fragment_note_list) {

    @Inject
    lateinit var viewModelFactory: com.dzhafar.coreApi.viewModel.ViewModelFactory

    @Inject
    lateinit var navigateToCalendar: NavigateToCalendarMediator

    private val viewModel: NoteListVM by viewModels { viewModelFactory }

    private lateinit var noteListAdapterRV: NoteListRVAdapter

    var binding: FragmentNoteListBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainComponent.create(
            (requireActivity().application as com.dzhafar.coreApi.di.AppWithFacade)
                .getFacade()
        ).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_note_list,
                container,
                false
            )
            noteListAdapterRV = NoteListRVAdapter(
                clickItemCallback = {
                    val args = EditNoteFragmentArgs(it)
                    findNavController().navigate(
                        R.id.action_noteListFragment_to_editNoteFragment,
                        args.toBundle()
                    )
                },
                deleteItem = {
                    viewModel.deleteNote(it)
                }
            )
            val llm = LinearLayoutManager(context)
            binding!!.noteList.layoutManager = llm
            binding!!.noteList.adapter = noteListAdapterRV
            setHasOptionsMenu(true)
            val toolbarView = binding!!.root.findViewById<Toolbar>(R.id.toolbarView)
            toolbarView.inflateMenu(R.menu.note_list_menu)
            toolbarView.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.addNote -> {
                        findNavController()
                            .navigate(R.id.action_noteListFragment_to_createNoteFragment)
                        true
                    }
                    R.id.calendar -> {
                        navigateToCalendar.navigate(this)
                        true
                    }
                    else -> false
                }
            }
            initToolbar(toolbarView)
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        viewModel.noteModelList.observe(
            viewLifecycleOwner,
            Observer {
                noteListAdapterRV.setData(it)
                noteListAdapterRV.notifyDataSetChanged()
            }
        )
    }
}
