package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var persons: ArrayList<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpList()
        setUpAddNewListElment()
    }

    private fun setUpAddNewListElment() {
        TODO("Crie a função que adiciona um novo elmento na lista e atualiza a lista.")
    }

    /**
     * Configuração inicial da lista
     */
    private fun setUpList() {
        persons = createInitialDataSet()
        val adapter = PersonListAdapter(
            layoutInflater,
            persons
        )
        binding.listViewPersons.adapter = adapter
    }



    /**
     * Criação de um dataset inicial para a lista comçar carregada.
     */
    private fun createInitialDataSet(): ArrayList<Person> = arrayListOf(
        Person("Ana", "1111-1111"),
        Person("Bianca", "2222-2222"),
        Person("Carla", "3333-3333"),
        Person("Daniel", "4444-4444"),
        Person("Elaine", "5555-5555"),
        Person("Fernando", "6666-6666"),
        Person("Gabriela", "7777-7777"),
        Person("Henrique", "8888-8888"),
        Person("Isabela", "9999-9999"),
        Person("João", "1010-1010"),
        Person("Kátia", "1112-1112"),
        Person("Lucas", "1212-1212"),
        Person("Maria", "1313-1313"),
        Person("Natalia", "1414-1414"),
        Person("Oliver", "1515-1515"),
        Person("Paula", "1616-1616"),
        Person("Quiteria", "1717-1717"),
        Person("Ricardo", "1818-1818"),
        Person("Sabrina", "1919-1919"),
        Person("Thiago", "2020-2020")
    )
}