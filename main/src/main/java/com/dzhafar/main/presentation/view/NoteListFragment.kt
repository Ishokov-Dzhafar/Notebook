package com.dzhafar.main.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dzhafar.core_db_api.di.AppWithFacade
import com.dzhafar.core_db_api.view_model.ViewModelFactory
import com.dzhafar.main.R
import com.dzhafar.main.databinding.FragmentNoteListBinding
import com.dzhafar.main.di.MainComponent
import com.dzhafar.main.presentation.MainActivity
import com.dzhafar.main.presentation.view.adapter.NoteListRVAdapter
import com.dzhafar.main.presentation.vm.NoteListVM
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [NoteListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteListFragment : Fragment(R.layout.fragment_note_list) {

    val viewModel: NoteListVM by viewModels { viewModelFactory }

    @Inject lateinit var viewModelFactory: ViewModelFactory

    lateinit var noteListAdapterRV: NoteListRVAdapter

    var binding: FragmentNoteListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteListAdapterRV = NoteListRVAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_list, container, false)
            val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            binding!!.noteList.layoutManager = llm
            binding!!.noteList.adapter = noteListAdapterRV
            setHasOptionsMenu(true)
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainComponent.create((requireActivity().application as AppWithFacade).getFacade()).inject(this)
        (activity as MainActivity).setSupportActionBar(toolbarView as Toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbarView.title = resources.getString(R.string.main_title)
    }

    override fun onResume() {
        super.onResume()
        viewModel.noteList.observe(viewLifecycleOwner, Observer {
            noteListAdapterRV.setData(it)
            Log.d("NOTE LIST FRAGMENT", it.last().text)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addNote ->  {
                Log.d("NOTE LIST FRAGMENT", "ADD NOTE")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
