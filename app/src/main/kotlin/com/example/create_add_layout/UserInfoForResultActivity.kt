package com.example.create_add_layout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.create_add_layout.MainActivity.Companion.USER
import com.example.create_add_layout.databinding.ActivityUserInfoForResultBinding

class UserInfoForResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoForResultBinding
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserInfoForResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        user = intent.getExtra<User>(USER)
        binding.btnUpdate.setOnClickListener {
            val phone = binding.edtPhone.text.toString()
            if (phone.isNotEmpty()) {
                user?.phone =  phone
                setResult(
                    Activity.RESULT_OK,
                    Intent().putExtra(USER, user)
                )
            } else {
                setResult(
                    Activity.RESULT_CANCELED
                )
            }
            finish()
        }
    }
}