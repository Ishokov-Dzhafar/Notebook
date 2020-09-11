package com.dzhafar.calendar.presentation.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.dzhafar.calendar.R
import com.dzhafar.calendar.domain.models.ActiveCalendarItem
import com.dzhafar.calendar.domain.models.CalendarItem
import com.dzhafar.calendar.domain.models.DisableCalendarItem
import com.dzhafar.calendar.domain.models.EnableCalendarItem
import kotlinx.android.synthetic.main.calendar_active_item.view.number

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
        return if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
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
            itemView.number.text = calendarItem.day.toString()
            itemView.setOnClickListener { clickListener(calendarItem) }
            itemView
        } else {
            convertView.number.text = calendarItem.day.toString()
            convertView.setOnClickListener { clickListener(calendarItem) }
            convertView
        }
    }

    override fun getItem(position: Int): CalendarItem = calendarItems[position]

    override fun getItemId(position: Int): Long =
        calendarItems.hashCode().toLong()

    override fun getCount(): Int = calendarItems.size
}