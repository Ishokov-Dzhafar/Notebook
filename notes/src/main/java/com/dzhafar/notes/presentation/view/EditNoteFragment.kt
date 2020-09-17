package com.dzhafar.notes.presentation.view

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
import com.dzhafar.notes.R
import com.dzhafar.notes.databinding.EditNoteFragmentBinding
import com.dzhafar.notes.di.MainComponent
import com.dzhafar.notes.presentation.vm.EditNoteViewModel
import com.dzhafar.coreCommon.view.BaseFragment
import com.dzhafar.navigationapi.navigation.notes.NavigateToEditNoteMediator
import com.dzhafar.notes.domain.models.NoteModel
import com.google.gson.Gson
import java.util.Date
import javax.inject.Inject

class EditNoteFragment : BaseFragment(R.layout.edit_note_fragment) {
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
            initArgs()
        }
        return binding!!.root
    }

    private fun initView() {
        setHasOptionsMenu(true)
        val toolbarView = binding!!.root.findViewById<Toolbar>(R.id.toolbarView)
        toolbarView.inflateMenu(R.menu.create_note_menu)
        toolbarView.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.save -> {
                    viewModel.saveNote(
                        title = binding!!.noteTitle.text.toString(),
                        text = binding!!.noteText.text.toString(),
                        date = Date().time
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun initArgs() {
        arguments?.let {
            val modelKey = getString(R.string.note_id_arg)
            val deeplinkKey = getString(R.string.edit_note_field)
            if (it.getLong(modelKey) != 0L) {
                val modelId = it.getLong(modelKey)
                viewModel.init(modelId)
            } else if (it.getString(deeplinkKey) != null) {
                val params = it.getString(deeplinkKey)
                val model = Gson().fromJson(
                    params,
                    NavigateToEditNoteMediator.Params::class.java
                )
                viewModel.init(model.noteId)
            }
        }
    }

    private fun initObservables() {
        viewModel.updateNote.observe(
            viewLifecycleOwner,
            Observer {
                findNavController().navigateUp()
            }
        )
        viewModel.noteModel.observe(
            viewLifecycleOwner,
            Observer {
                binding!!.noteTitle.setText(it.title)
                binding!!.noteText.setText(it.text)
            }
        )
    }
}