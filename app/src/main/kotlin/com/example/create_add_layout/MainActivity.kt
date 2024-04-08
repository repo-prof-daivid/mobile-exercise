package com.example.create_add_layout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
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

    private fun setUpListeners() {
        binding.btnAddPerson.setOnClickListener {
            addContact()
        }
    }

    private fun addContact() {
        val name = binding.editTextName.text.toString()
        val phone = binding.editTextPhone.text.toString()
        val age = binding.editTextAge.text.toString()
            .takeIf {
                it.isFieldValid(Regex("^?\\d+(\\.\\d+)?\$"))
            }?.toInt() ?: 0
        val hobby = binding.editTextHobby.text.toString()
        val sex = binding.editTextSex.text.toString()
        val person = Person(name, phone, age, hobby, sex)
        if (isFormValid(person)) {
            contactList.add(person)
        }
    }

    private fun isFormValid(person: Person): Boolean =
        validateField(person.name, binding.editTextName, R.string.name)
                && validateField(person.phone, binding.editTextPhone, R.string.phone)
                && validateField(person.age, binding.editTextAge, R.string.age)
                && validateField(person.hobby, binding.editTextHobby, R.string.hobby)
                && validateField(person.sex, binding.editTextSex, R.string.sex)


    private fun validateField(
        text: String,
        view: AppCompatEditText,
        @StringRes errorField: Int
    ): Boolean {
        var isToAdd1 = true
        if (text.isEmpty()) {
            view.error =
                getString(R.string.message_field_required, getString(errorField))
            isToAdd1 = false
        }
        return isToAdd1
    }

    private fun validateField(
        age: Int,
        view: AppCompatEditText,
        @StringRes errorField: Int
    ): Boolean {
        var isToAdd1 = true
        if (age <= 0) {
            view.error =
                getString(R.string.message_field_required, getString(errorField))
            isToAdd1 = false
        }
        return isToAdd1
    }


}