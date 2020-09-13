package com.dzhafar.calendar.presentation.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.dzhafar.calendar.R
import com.dzhafar.calendar.domain.models.ActiveCalendarItem
import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.calendar.domain.models.DisableCalendarItem
import com.dzhafar.calendar.domain.models.EnableCalendarItem

class CalendarAdapter(
    private val context: Context,
    private val clickListener: (calendarItem: CalendarItem) -> Unit
) : BaseAdapter() {

    private val calendarItems = mutableListOf<CalendarItem>()

    fun updateItems(calendarItems: List<CalendarItem>) {
        this.calendarItems.clear()
        this.calendarItems.addAll(calendarItems)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        val calendarItem = calendarItems[position]
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewHolder: ViewHolder
        if (convertView == null) {
            itemView = when (calendarItem) {
                is ActiveCalendarItem -> inflater.inflate(
                    R.layout.calendar_active_item,
                    parent,
                    false
                )
                is DisableCalendarItem -> inflater.inflate(
                    R.layout.calendar_disable_item,
                    parent,
                    false
                )
                is EnableCalendarItem -> inflater.inflate(
                    R.layout.calendar_enable_item,
                    parent,
                    false
                )
            }
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            itemView = convertView
        }
        viewHolder.numberTV.text = calendarItem.day.toString()
        addNotesView(viewHolder, calendarItem, inflater, parent)
        viewHolder.view.setOnClickListener { clickListener(calendarItem) }
        return itemView
    }

    private fun addNotesView(
        viewHolder: ViewHolder,
        calendarItem: CalendarItem,
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) {
        val container = viewHolder.container
        val lastIndex =
            if (calendarItem.notesTitle.size > NOTES_MAX_COUNT) NOTES_MAX_COUNT else calendarItem.notesTitle.size
        calendarItem.notesTitle.subList(0, lastIndex).forEach {
            val textView = inflater.inflate(R.layout.note_textview_item, parent, false) as TextView
            textView.text = it
            container.addView(textView)
        }
        container.addView(TextView(context))
    }

    override fun getItem(position: Int): CalendarItem = calendarItems[position]

    override fun getItemId(position: Int): Long =
        position.toLong()

    override fun getCount(): Int = calendarItems.size

    companion object {
        const val NOTES_MAX_COUNT = 3
    }

    class ViewHolder(public val view: View) {
        val numberTV = view.findViewById<TextView>(R.id.number)
        val container = view.findViewById<LinearLayout>(R.id.notesContainer)
    }
}