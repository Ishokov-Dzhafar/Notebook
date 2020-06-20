package com.dzhafar.main.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzhafar.core_db_api.di.AppWithFacade
import com.dzhafar.core_db_api.view_model.ViewModelFactory
import com.dzhafar.main.R
import com.dzhafar.main.databinding.FragmentNoteListBinding
import com.dzhafar.main.di.MainComponent
import com.dzhafar.main.presentation.view.adapter.NoteListRVAdapter
import com.dzhafar.main.presentation.vm.NoteListVM
import kotlinx.android.synthetic.main.toolbar.toolbarView
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [NoteListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteListFragment : Fragment(R.layout.fragment_note_list) {

    val viewModel: NoteListVM by viewModels { viewModelFactory }

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val noteListAdapterRV: NoteListRVAdapter by lazy {
        NoteListRVAdapter()
    }

    var binding: FragmentNoteListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list,
                container, false)
            val llm = LinearLayoutManager(context)
            binding!!.noteList.layoutManager = llm
            binding!!.noteList.adapter = noteListAdapterRV
            setHasOptionsMenu(true)
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainComponent.create((requireActivity().application as AppWithFacade)
            .getFacade()).inject(this)
        toolbarView.title = resources.getString(R.string.main_title)
        toolbarView.inflateMenu(R.menu.note_list_menu)
        toolbarView.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addNote -> {
                    findNavController().navigate(R.id.action_noteListFragment_to_createNoteFragment)
                    Log.d("NOTE LIST FRAGMENT", "ADD NOTE")
                    true
                }
                else -> false
            }
        }

        viewModel.noteList.observe(viewLifecycleOwner, Observer {
            noteListAdapterRV.setData(it)
            noteListAdapterRV.notifyDataSetChanged()
            Log.d("NOTE LIST FRAGMENT", it.isEmpty().toString())
        })
    }
}
