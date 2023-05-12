package com.example.networkconection

import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.networkconection.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repositorios: ArrayList<Repositorio>
    private lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpList()
        binding.btnLoadGihut.setOnClickListener {
            makeRequest()
        }
    }

    private fun changeElementsVisibility(
        welcomeMessageVisibility: Int = View.GONE,
        errorMessageVisibility: Int = View.GONE,
        emptyMessageVisibility: Int = View.GONE,
        rvVisibilityMessageVisibility: Int = View.GONE,
        progressBarVisibility: Int = View.GONE
    ) {
        binding.welcomeMessage.visibility = welcomeMessageVisibility
        binding.erroMessage.visibility = errorMessageVisibility
        binding.emptyMessage.visibility = emptyMessageVisibility
        binding.rvRepositories.visibility = rvVisibilityMessageVisibility
        binding.progressBar.visibility = progressBarVisibility
    }


    private fun makeRequest() {
        // create retrofit object
        val instance = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // create service using Interface that has the request methods
        val service = instance.create(ApiService::class.java)
        changeElementsVisibility(progressBarVisibility = View.VISIBLE)
        // build the call
        val reponse: Call<List<Repositorio>> = service.listRepos("daividvleal")
        // make the call
        reponse.enqueue(object : Callback<List<Repositorio>> {

            override fun onResponse(
                call: Call<List<Repositorio>>,
                response: Response<List<Repositorio>>
            ) {
                if (response.code() == 200) {
                    response.body()?.let {
                        if (it.isEmpty()) {
                            changeElementsVisibility(emptyMessageVisibility = View.VISIBLE)
                        } else {
                            repositorios.addAll(it)
                            adapter.notifyItemRangeChanged(0, it.size)
                            changeElementsVisibility(rvVisibilityMessageVisibility = View.VISIBLE)
                        }
                    } ?: run {
                        changeElementsVisibility(errorMessageVisibility = View.VISIBLE)
                    }
                }
            }

            override fun onFailure(
                call: Call<List<Repositorio>>,
                t: Throwable
            ) {
                t.printStackTrace()
                changeElementsVisibility(errorMessageVisibility = View.VISIBLE)
            }

        })
    }

    private fun setUpList() {
        repositorios = arrayListOf()
        adapter = RepositoryAdapter(
            repositorios
        )
        binding.rvRepositories.layoutManager = LinearLayoutManager(this)
        binding.rvRepositories.adapter = adapter
    }

}