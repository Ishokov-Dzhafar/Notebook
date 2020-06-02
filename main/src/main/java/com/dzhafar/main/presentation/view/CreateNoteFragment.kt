package com.dzhafar.main.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dzhafar.core_db_api.di.AppWithFacade
import com.dzhafar.core_db_api.hideKeyboard
import com.dzhafar.core_db_api.view_model.ViewModelFactory
import com.dzhafar.main.R
import com.dzhafar.main.databinding.FragmentCreateNoteBinding
import com.dzhafar.main.di.MainComponent
import com.dzhafar.main.domain.models.Note
import com.dzhafar.main.presentation.MainActivity
import com.dzhafar.main.presentation.vm.CreateNoteVM
import kotlinx.android.synthetic.main.toolbar.*
import androidx.lifecycle.Observer
import java.util.*
import javax.inject.Inject

class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {
    val viewModel: CreateNoteVM by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var binding: FragmentCreateNoteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_note, container, false)
        }
        setHasOptionsMenu(true)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainComponent.create((requireActivity().application as AppWithFacade).getFacade()).inject(this)
        (activity as MainActivity).setSupportActionBar(toolbarView as Toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbarView.title = resources.getString(R.string.main_title)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save ->  {
                val note = Note(
                    text = binding!!.noteText.text.toString(),
                    date = Date().time,
                    title = binding!!.noteTitle.text.toString(),
                    id = null
                )
                viewModel.createNoteLD.observe(viewLifecycleOwner, Observer {
                    hideKeyboard(requireActivity())
                    findNavController().popBackStack()
                    Log.d("CREATE NOTE FRAGMENT", "SAVE NOTE")
                })
                viewModel.createNote(note)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}