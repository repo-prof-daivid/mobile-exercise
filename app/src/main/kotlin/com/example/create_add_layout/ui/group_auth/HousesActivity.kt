package com.example.create_add_layout.ui.group_auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.create_add_layout.R
import com.example.create_add_layout.databinding.ActivityHousesBinding
import com.example.create_add_layout.model.House
import com.example.create_add_layout.network.Network
import com.example.create_add_layout.ui.houses_recycler_view.HouseRecyclerViewAdapter

const val HOUSE = "house"

class HousesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHousesBinding
    private val houses = mutableListOf<House>()
    private lateinit var houseRecyclerViewAdapter: HouseRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHousesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpView()
        loadHouses()
    }

    private fun setUpView() {
        binding.apply {
            houseRecyclerViewAdapter = HouseRecyclerViewAdapter(houses, ::gotToHouse)
            housesRecyclerView.layoutManager = LinearLayoutManager(this@HousesActivity)
            housesRecyclerView.adapter = houseRecyclerViewAdapter
        }
    }

    private fun gotToHouse(house: House) {
        val intent = Intent(this@HousesActivity, InitialActivity::class.java)
        intent.putExtra(HOUSE, house)
        startActivity(intent)
    }

    private fun loadHouses() {
        Network.api.getHouses().enqueue(
            object : retrofit2.Callback<List<House>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: retrofit2.Call<List<House>>,
                    response: retrofit2.Response<List<House>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            houses.clear()
                            houses.addAll(it)
                        } ?: run {
                            Toast.makeText(
                                this@HousesActivity,
                                "Error loading houses",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        houseRecyclerViewAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(
                            this@HousesActivity,
                            "Error loading houses",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<List<House>>, t: Throwable) {
                    t.printStackTrace()
                }
            })

    }

}