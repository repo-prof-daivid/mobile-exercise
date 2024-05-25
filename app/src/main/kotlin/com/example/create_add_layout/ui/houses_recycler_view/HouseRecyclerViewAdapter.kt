package com.example.create_add_layout.ui.houses_recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.create_add_layout.databinding.HouseItemBinding
import com.example.create_add_layout.model.House

class HouseRecyclerViewAdapter(private val houses: List<House>, private val onClick: (House) -> Unit):
    RecyclerView.Adapter<HouseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val binding = HouseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        holder.bind(houses[position], onItemClicked = onClick)
    }

    override fun getItemCount(): Int = houses.size

}