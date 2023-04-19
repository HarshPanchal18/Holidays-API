package com.example.holidays

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.holidays.databinding.HolidayCardBinding

class HolidayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = HolidayCardBinding.bind(itemView)

    fun bind(model: Model) {
        binding.name.text = model.name
        binding.localName.text = model.localName
        binding.types.text = model.types.toString()
        binding.date.text = model.date
        binding.countryCode.text = model.countryCode
    }
}
