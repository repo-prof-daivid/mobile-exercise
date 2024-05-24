package com.example.create_add_layout.group_auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.create_add_layout.EMAIL_REGEX
import com.example.create_add_layout.MainActivity
import com.example.create_add_layout.MainActivity.Companion.USER
import com.example.create_add_layout.R
import com.example.create_add_layout.User
import com.example.create_add_layout.databinding.ActivityInitialBinding
import com.example.create_add_layout.isFieldValid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InitialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitialBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            btnLogin.setOnClickListener {
                goToMainActivity()
            }

            btnSingUp.setOnClickListener {
                goToRegisterActivity()
            }
        }
    }

    private fun goToRegisterActivity() {
        val intent = Intent(this@InitialActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun goToMainActivity() {
        var result = true
        val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPwd.text.toString()
        if (name.isEmpty()) {
            Toast.makeText(
                this@InitialActivity,
                getString(R.string.field_name),
                Toast.LENGTH_LONG
            ).show()
            result = false
        }
        if (email.isFieldValid(Regex(EMAIL_REGEX))
                .not()
        ) {
            Toast.makeText(
                this@InitialActivity,
                getString(R.string.email_field),
                Toast.LENGTH_LONG
            ).show()
            result = false
        }
        if (result) {
            auth.signInWithEmailAndPassword(
                email,
                password
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    if (auth.currentUser?.uid != null) {
                        val intent = Intent(this@InitialActivity, MainActivity::class.java)
                        firestore.collection(USERS).document(auth.currentUser!!.uid)
                            .get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    val user = document.toObject(User::class.java)
                                    intent.putExtra(USER, user)
                                    finish()
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        this@InitialActivity,
                                        getString(R.string.login_error),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                    } else {
                        Toast.makeText(
                            this@InitialActivity,
                            getString(R.string.login_error),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@InitialActivity,
                        getString(R.string.login_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    companion object {
        const val USERS = "USERS"
    }

}