package com.example.networkconection.ui.repositories

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkconection.model.Repository
import com.example.networkconection.databinding.ActivityRepositoryBinding
import com.example.networkconection.ui.repositories.adapter.RepositoryAdapter

class RepositoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepositoryBinding
    private lateinit var repositories: ArrayList<Repository>
    private lateinit var adapter: RepositoryAdapter
    private val viewModel = RepositoryViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpList()
        setUpObservables()
        binding.btnLoadGihut.setOnClickListener {
            binding.userName.text?.toString()?.takeIf {
                it.isNotBlank() && it.isNotEmpty()
            }?.let {
                viewModel.getRepositories(userName = it)
            } ?: kotlin.run {
                viewModel.getRepositories()
            }
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

    private fun setUpObservables() {
        viewModel.repositories.observe(
            this
        ) {
            repositories.clear()
            repositories.addAll(it)
            adapter.notifyDataSetChanged()
            changeElementsVisibility(rvVisibilityMessageVisibility = View.VISIBLE)
        }

        viewModel.error.observe(this) {
            if (it) {
                changeElementsVisibility(errorMessageVisibility = View.VISIBLE)
            }
        }

        viewModel.loading.observe(this) {
            if (it) {
                changeElementsVisibility(progressBarVisibility = View.VISIBLE)
            }
        }

        viewModel.empty.observe(this) {
            if (it) {
                changeElementsVisibility(emptyMessageVisibility = View.VISIBLE)
            }
        }
    }

    private fun setUpList() {
        repositories = arrayListOf()
        adapter = RepositoryAdapter(
            repositories
        )
        binding.rvRepositories.layoutManager = LinearLayoutManager(this)
        binding.rvRepositories.adapter = adapter
    }

}