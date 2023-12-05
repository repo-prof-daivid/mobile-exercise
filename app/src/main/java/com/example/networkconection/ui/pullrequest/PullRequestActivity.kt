package com.example.networkconection.ui.pullrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.networkconection.R
import com.example.networkconection.databinding.ActivityPullRequestBinding

class PullRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPullRequestBinding
    private val viewModel = PullRequestViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val owner = intent.getStringExtra(OWNER) ?: "daividvleal"
        val repo = intent.getStringExtra(REPO) ?: "daividvleal"

        viewModel.pullRequests.observe(this) {
            binding.pulls.text = it
        }

        viewModel.getPullRequests(
            owner, repo
        )

    }

    companion object {
        const val OWNER = "owner"
        const val REPO = "repo"
    }
}