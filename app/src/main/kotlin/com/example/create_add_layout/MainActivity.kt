package com.example.create_add_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.create_add_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val contactList = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpListeners()
    }

    private fun setUpListeners(){
        binding.btnAddPerson.setOnClickListener {
            addContact()
        }
    }

    private fun addContact() {
        val name = binding.editTextPersonName.text.toString()
        val phone = binding.editTextPersonPhone.text.toString()
        var isToAdd = true
        if (name.isEmpty()) {
            binding.editTextPersonName.error =
                getString(R.string.message_field_required, getString(R.string.name))
            isToAdd = false
        }
        if (phone.isEmpty()) {
            binding.editTextPersonPhone.error =
                getString(R.string.message_field_required, getString(R.string.phone))
            isToAdd = false
        }
        if (isToAdd) {
            contactList.add(Person(name, phone))
        }
    }


}