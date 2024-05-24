package com.example.create_add_layout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.create_add_layout.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val contactList = arrayListOf<Person>()
    private var user: User? = null
    private lateinit var fireStore: FirebaseFirestore
    private lateinit var adapter: PersonRecyclerViewAdapter

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
        fireStore = FirebaseFirestore.getInstance()
        setUpView()
        setUpListeners()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        adapter = PersonRecyclerViewAdapter(contactList)
        binding.contactList.layoutManager = GridLayoutManager(this, 1)
        binding.contactList.adapter = adapter
    }

    private fun setUpView() {
        user = intent.getExtra<User>(USER)
        user?.let {
            binding.txtWelcomeMessage.text = getString(R.string.welcome_message, user?.name)
            binding.txtUserEmail.text = getString(R.string.e_mail, user?.email)
            fireStore.collection(USERS).document(user?.uid!!).collection(CONTACT_LIST)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val person = document.toObject(Person::class.java)
                        contactList.add(person)
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.error_retrieving_data), Toast.LENGTH_LONG
                    ).show()
                }
                .addOnCompleteListener {
                    setUpRecyclerView()
                }
        } ?: run {
            binding.txtWelcomeMessage.isVisible = false
            binding.txtUserEmail.isVisible = false
        }
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult: ActivityResult? ->
            if (activityResult?.resultCode == Activity.RESULT_OK) {
                user = activityResult.data?.getExtra<User>(USER)
                user?.phone?.let {
                    binding.txtUserPhone.text = it
                    binding.txtUserPhone.isVisible = true
                } ?: run {
                    showError()
                }
            } else {
                showError()
            }
        }

    private fun showError() {
        Toast.makeText(
            this@MainActivity,
            getString(R.string.phone_not_informed), Toast.LENGTH_LONG
        ).show()
        binding.txtUserPhone.isVisible = false
    }

    private fun setUpListeners() {
        binding.btnAddPerson.setOnClickListener {
            addContact()
        }
        binding.btnUpdateUserInfo.setOnClickListener {
            val intentForResult = Intent(this@MainActivity, UserInfoForResultActivity::class.java)
            intentForResult.putExtra(USER, user)
            getContent.launch(intentForResult)
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
            fireStore.collection(USERS).document(user?.uid!!).collection(CONTACT_LIST)
                .add(person)
                .addOnSuccessListener {
                    contactList.add(person)
                }
                .addOnFailureListener {
                    Toast.makeText(
                        this@MainActivity,
                        it.message.toString(), Toast.LENGTH_LONG
                    ).show()
                }.addOnCompleteListener {
                    adapter?.notifyDataSetChanged()
                }
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

    companion object {
        const val USER = "USER"
        const val USERS = "USERS"
        const val CONTACT_LIST = "CONTACT_LIST"
    }


}