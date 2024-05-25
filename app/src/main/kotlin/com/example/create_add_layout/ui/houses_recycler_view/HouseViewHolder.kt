package com.example.create_add_layout.ui.houses_recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.example.create_add_layout.databinding.HouseItemBinding
import com.example.create_add_layout.model.House

class HouseViewHolder(private val houseItemBinding: HouseItemBinding) :
    RecyclerView.ViewHolder(houseItemBinding.root) {
    fun bind(house: House, onItemClicked: (House) -> Unit) {
        houseItemBinding.apply {
            root.setOnClickListener { onItemClicked(house) }

            name.text = house.name
            animal.text = house.animal
            element.text = house.element
            founder.text = house.founder
            ghost.text = house.ghost
            houseColours.text = house.houseColours
            commonRoom.text = house.commonRoom

            traits.text = house.traits.joinToString { it.name }
            heads.text = house.heads.joinToString { "${it.firstName} ${it.lastName}" }
        }
    }
}