package com.dzhafar.main.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dzhafar.main.R
import com.dzhafar.main.databinding.NoteListItemBinding
import com.dzhafar.main.domain.models.Note

class NoteListRVAdapter : RecyclerView.Adapter<NoteListRVAdapter.NoteListItemVH>() {
    var noteList: List<Note> = listOf()

    fun setData(list: List<Note>) {
        val diffUtil = NoteListDiffUtil(noteList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        noteList = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: NoteListItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.note_list_item, parent, false)
        return NoteListItemVH(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteListItemVH, position: Int) {
        holder.bind(noteList[position])
    }

    class NoteListItemVH(val binding: NoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Note) {
            binding.note = item
        }
    }

    class NoteListDiffUtil(private val oldList: List<Note>, private val newList: List<Note>)
        : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return  newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id &&
                    oldList[oldItemPosition].date == newList[newItemPosition].date &&
                    oldList[oldItemPosition].text == newList[newItemPosition].text &&
                    oldList[oldItemPosition].title == newList[newItemPosition].title
        }
    }
}
