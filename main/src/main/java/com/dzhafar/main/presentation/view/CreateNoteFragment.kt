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
import com.dzhafar.core_db_api.di.AppWithFacade
import com.dzhafar.core_db_api.hideKeyboard
import com.dzhafar.core_db_api.view_model.ViewModelFactory
import com.dzhafar.main.R
import com.dzhafar.main.databinding.FragmentCreateNoteBinding
import com.dzhafar.main.di.MainComponent
import com.dzhafar.main.presentation.vm.CreateNoteVM
import kotlinx.android.synthetic.main.toolbar.toolbarView
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
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_note,
                container, false)
            val toolbarView = binding!!.root.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarView)
            setHasOptionsMenu(true)
            toolbarView.title = resources.getString(R.string.main_title)
            toolbarView.inflateMenu(R.menu.create_note_menu)
            toolbarView.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.save -> {
                        viewModel.createNoteLD.observe(viewLifecycleOwner, Observer {
                            hideKeyboard(requireActivity())
                            findNavController().popBackStack()
                            Log.d("CREATE NOTE FRAGMENT", "SAVE NOTE")
                        })
                        viewModel.createNote(binding!!.noteTitle.text.toString(),
                            binding!!.noteText.text.toString())
                        true
                    }
                    else -> false
                }
            }
        }
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainComponent.create((requireActivity().application as AppWithFacade).getFacade())
            .inject(this)
    }
}