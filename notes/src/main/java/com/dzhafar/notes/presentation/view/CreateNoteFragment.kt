package com.dzhafar.notes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dzhafar.coreCommon.view.BaseFragment
import com.dzhafar.coreCommon.hideKeyboard
import com.dzhafar.notes.R
import com.dzhafar.notes.databinding.FragmentCreateNoteBinding
import com.dzhafar.notes.di.MainComponent
import com.dzhafar.notes.presentation.vm.CreateNoteVM
import javax.inject.Inject

class CreateNoteFragment : BaseFragment(R.layout.fragment_create_note) {
    val viewModel: CreateNoteVM by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: com.dzhafar.coreApi.viewModel.ViewModelFactory

    var binding: FragmentCreateNoteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_create_note,
                container, false
            )
            val toolbarView = binding!!.root.findViewById<Toolbar>(R.id.toolbarView)
            setHasOptionsMenu(true)
            toolbarView.inflateMenu(R.menu.create_note_menu)
            toolbarView.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.save -> {
                        viewModel.createNoteLD.observe(
                            viewLifecycleOwner,
                            Observer {
                                hideKeyboard(requireActivity())
                                findNavController().popBackStack()
                            }
                        )
                        viewModel.createNote(
                            binding!!.noteTitle.text.toString(),
                            binding!!.noteText.text.toString()
                        )
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
        MainComponent.create((requireActivity().application as com.dzhafar.coreApi.di.AppWithFacade).getFacade())
            .inject(this)
    }
}