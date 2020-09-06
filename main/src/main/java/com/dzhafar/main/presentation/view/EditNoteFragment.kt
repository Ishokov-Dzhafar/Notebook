package com.dzhafar.main.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dzhafar.coreApi.di.AppWithFacade
import com.dzhafar.coreApi.viewModel.ViewModelFactory
import com.dzhafar.main.R
import com.dzhafar.main.databinding.EditNoteFragmentBinding
import com.dzhafar.main.di.MainComponent
import com.dzhafar.main.presentation.vm.EditNoteViewModel
import com.dzhafar.coreCommon.view.BaseFragment
import java.util.Date
import javax.inject.Inject

class EditNoteFragment : BaseFragment(R.layout.edit_note_fragment) {
    private val args: EditNoteFragmentArgs by navArgs()
    val viewModel: EditNoteViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var binding: EditNoteFragmentBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainComponent.create((requireActivity().application as AppWithFacade).getFacade())
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
                R.layout.edit_note_fragment,
                container,
                false
            )
            val toolbarView = binding!!.root.findViewById<Toolbar>(R.id.toolbarView)
            retainInstance = true
            initView()
            initObservables()
            initToolbar(toolbarView)
        }
        return binding!!.root
    }

    private fun initView() {
        setHasOptionsMenu(true)
        val noteModel = args.StringNoteModelArg
        binding!!.noteTitle.setText(noteModel.title)
        binding!!.noteText.setText(noteModel.text)
        val toolbarView = binding!!.root.findViewById<Toolbar>(R.id.toolbarView)
        toolbarView.inflateMenu(R.menu.create_note_menu)
        toolbarView.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.save -> {
                    viewModel.saveNote(
                        noteModel.copy(
                            title = binding!!.noteTitle.text.toString(),
                            text = binding!!.noteText.text.toString(),
                            date = Date().time
                        )
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun initObservables() {
        viewModel.updateNoteLD.observe(
            viewLifecycleOwner,
            Observer {
                findNavController().navigateUp()
            }
        )
    }
}