package com.example.holidays

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.holidays.databinding.HolidayCardBinding

class HolidayAdapter(private val items: MutableList<Model>): RecyclerView.Adapter<HolidayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayViewHolder {
        val binding = HolidayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolidayViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: HolidayViewHolder, position: Int) {
        val currentHoliday = items[position]
        holder.bind(currentHoliday)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
