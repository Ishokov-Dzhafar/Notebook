package com.dzhafar.calendar.presentation.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.dzhafar.calendar.R
import com.dzhafar.calendar.domain.models.CalendarItem
import kotlinx.android.synthetic.main.calendar_active_item.view.number

class CalendarAdapter(private val context: Context) : BaseAdapter() {

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
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.calendar_active_item, parent, false)
            itemView.number.text = calendarItem.number.toString()
            itemView
        } else {
            convertView.number.text = calendarItem.number.toString()
            convertView
        }
    }

    override fun getItem(position: Int): CalendarItem = calendarItems[position]

    override fun getItemId(position: Int): Long =
        calendarItems.hashCode().toLong()

    override fun getCount(): Int = calendarItems.size
}