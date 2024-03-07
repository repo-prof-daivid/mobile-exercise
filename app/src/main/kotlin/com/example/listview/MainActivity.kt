package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val contactList = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setUpList()

        Log.e("LOG TYPE:", "ERROR MESSAGE")
        Log.d("LOG TYPE:", "DEBUG MESSAGE")
        Log.w("LOG TYPE:", "WARNING MESSAGE")
        Log.i("LOG TYPE:", "INFORMATION MESSAGE")
        Log.v("LOG TYPE:", "VERBOSE MESSAGE")
        //setUpAddNewListElment()
    }

    /*
    private fun setUpAddNewListElment() {
        TODO("Descomente a função e crie a função
         que adiciona um novo elmento na lista e atualiza a lista.")
    }*/


}