package com.example.listview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.listview.databinding.ListItemBinding

/***
 * Essa classe adapter é a classe responsável por carregar os elementos da lista.
 */
class PersonListAdapter(
    private val layoutInflater: LayoutInflater,
    private val arrayListPersons: ArrayList<Person>
) : BaseAdapter() {

    /**
     * Devolver ao adapter a possibilidade de saber quantos elementos tem na lista.
     */
    override fun getCount(): Int = arrayListPersons.size

    /**
     * getItem para o adapter poder utilixar para retornar o elemento da posição.
     */
    override fun getItem(position: Int): Any = arrayListPersons[position]

    /**
     * Id do elementos da lista.
     */
    override fun getItemId(position: Int): Long = position.toLong()

    /**
     * Criaçào da view para o adapter poder saber o qual view ele deve mostrar na tela.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Separa o item da lista que queremos carregar
        // o as? Person é um safe cast para transformar o objeto que recebemos como any da função getItem em Person
        val person = getItem(position) as? Person
        // criar a view onde queremos mostrar as informações
        val listItemBinding = ListItemBinding.inflate(layoutInflater, parent, false)
        // criar um objeto view Holder que vai carregar as informações do objeto person na view
        val personViewHolder = PersonViewHolder(listItemBinding)
        // chamada da função que seta as informações e recebe o objeto pessoa
        personViewHolder.bind(person)
        // deviver ao adapter a view que deve ser carregada na lista
        return listItemBinding.root
    }
}