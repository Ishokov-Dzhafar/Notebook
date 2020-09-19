package com.dzhafar.calendar.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dzhafar.calendar.R
import com.dzhafar.coreCommon.utils.getLocalDateFormat
import com.dzhafar.coreDbApi.data.entity.NoteEntity
import com.google.android.material.button.MaterialButton

class NotesAdapter(
    private val clickItemCallback: (item: NoteEntity) -> Unit,
    private val deleteItem: (item: NoteEntity, position: Int) -> Unit
) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    companion object {
        val TAG = this::class.java.simpleName
    }

    private var noteItems = mutableListOf<NoteEntity>()

    fun setItems(list: List<NoteEntity>) {
        val diffUtil = NoteListDiffUtil(noteItems, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        noteItems = list.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = R.layout.note_item
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(
            view,
            clickItemCallback,
            { item: NoteEntity, position: Int ->
                noteItems.removeAt(position)
                notifyItemRemoved(position)
                deleteItem(item, position)
            }
        )
    }

    override fun getItemCount(): Int {
        return noteItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noteItems[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int = position

    class ViewHolder(
        view: View,
        private val clickItemCallback: (item: NoteEntity) -> Unit,
        private val deleteItemCallback: (item: NoteEntity, position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val titleTV = itemView.findViewById<TextView>(R.id.titleTV)
        private val createDateTV = itemView.findViewById<TextView>(R.id.createDateTV)
        private val bodyTV = itemView.findViewById<TextView>(R.id.bodyTV)
        private val deleteBtn = itemView.findViewById<MaterialButton>(R.id.deleteBtn)
        fun bind(item: NoteEntity) {
            titleTV.text = item.title
            createDateTV.text = getLocalDateFormat(item.date)
            bodyTV.text = item.text
            deleteBtn.setOnClickListener {
                deleteItemCallback(item, adapterPosition)
            }
            itemView.setOnClickListener {
                clickItemCallback(item)
            }
        }
    }

    class NoteListDiffUtil(
        private val oldList: List<NoteEntity>,
        private val newList: List<NoteEntity>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].date == newList[newItemPosition].date &&
                oldList[oldItemPosition].text == newList[newItemPosition].text &&
                oldList[oldItemPosition].title == newList[newItemPosition].title
        }
    }
}