package com.example.listview

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.listview.databinding.ListItemBinding

/***
 * Essa classe view holder é a classe responsável por carregar um elemento person.
 */
class PersonViewHolder(
    private val listItemBinding: ListItemBinding
): ViewHolder(listItemBinding.root) {

    /**
     * essa função recebe um objeto pessoa e `seta` as informações no campo da view.
     */
    fun bind(person: Person?) {
        person?.let {it ->
            listItemBinding.name.text = it.name
            listItemBinding.phone.text = it.phone
        }
    }

}