package com.dzhafar.calendar.presentation.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dzhafar.calendar.R
import com.dzhafar.calendar.domain.models.ActiveCalendarItem
import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.calendar.domain.models.DisableCalendarItem
import com.dzhafar.calendar.domain.models.EnableCalendarItem

class CalendarRecyclerViewAdapter(private val clickListener: (calendarItem: CalendarItem) -> Unit) :
    RecyclerView.Adapter<CalendarRecyclerViewAdapter.ViewHolder>() {

    private val calendarItems = mutableListOf<CalendarItem>()

    fun setItems(list: List<CalendarItem>) {
        calendarItems.clear()
        notifyDataSetChanged()
        calendarItems.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = when (viewType) {
            ENABLE -> R.layout.calendar_enable_item
            ACTIVE -> R.layout.calendar_active_item
            else -> R.layout.calendar_disable_item
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view, parent)
    }

    override fun getItemCount(): Int {
        return calendarItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(calendarItems[position], clickListener)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return when (calendarItems[position]) {
            is ActiveCalendarItem -> ACTIVE
            is DisableCalendarItem -> DISABLE
            is EnableCalendarItem -> ENABLE
        }
    }

    companion object {
        private const val ENABLE = 1
        private const val ACTIVE = 2
        private const val DISABLE = 3
        private const val NOTES_MAX_COUNT = 3
    }

    class ViewHolder(itemView: View, private val parent: ViewGroup) :
        RecyclerView.ViewHolder(itemView) {
        private val numberTV = itemView.findViewById<TextView>(R.id.number)
        private val container = itemView.findViewById<LinearLayout>(R.id.notesContainer)

        fun bind(calendarItem: CalendarItem, clickListener: (calendarItem: CalendarItem) -> Unit) {
            numberTV.text = calendarItem.day.toString()
            val inflater =
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            addNotesView(this, calendarItem, inflater, parent)
            itemView.setOnClickListener { clickListener(calendarItem) }
        }

        private fun addNotesView(
            viewHolder: ViewHolder,
            calendarItem: CalendarItem,
            inflater: LayoutInflater,
            parent: ViewGroup?
        ) {
            val container = viewHolder.container
            val lastIndex =
                if (calendarItem.notesTitle.size > NOTES_MAX_COUNT) {
                    NOTES_MAX_COUNT
                } else {
                    calendarItem.notesTitle.size
                }
            calendarItem.notesTitle.subList(0, lastIndex).forEach {
                val textView =
                    inflater.inflate(R.layout.note_textview_item, parent, false) as TextView
                textView.text = it
                container.addView(textView)
            }
        }
    }
}