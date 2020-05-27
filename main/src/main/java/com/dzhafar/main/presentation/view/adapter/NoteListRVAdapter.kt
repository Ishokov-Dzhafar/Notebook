package com.dzhafar.main.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dzhafar.main.R
import com.dzhafar.main.databinding.NoteListItemBinding
import com.dzhafar.main.domain.models.Note


class NoteListRVAdapter: RecyclerView.Adapter<NoteListRVAdapter.NoteListItemVH>() {

    var noteList: MutableList<Note> = mutableListOf()

    fun setData(list: List<Note>) {
        noteList.clear()
        noteList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: NoteListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.note_list_item, parent, false)
        return NoteListItemVH(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteListItemVH, position: Int) {
        holder.bind(noteList[position])
    }

    inner class NoteListItemVH(val binding: NoteListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Note) {
            binding.note = item
        }
    }
}

