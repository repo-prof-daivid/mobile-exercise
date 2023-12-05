package com.example.networkconection.ui.pullrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkconection.databinding.ActivityPullRequestBinding
import com.example.networkconection.model.PullRequest
import com.example.networkconection.ui.pullrequest.adapter.PullRequestAdapter
import org.koin.android.ext.android.inject

class PullRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPullRequestBinding
    private val viewModel: PullRequestViewModel by inject()
    private lateinit var pullRequests: ArrayList<PullRequest>
    private lateinit var adapter: PullRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpList()
        setUpObservables()
        makeRequest()
    }

    private fun setUpObservables(){
        viewModel.pullRequests.observe(this) {
            pullRequests.clear()
            pullRequests.addAll(it)
            adapter.notifyItemRangeChanged(0, it.size)

            binding.rvPullRequest.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.feedbackMessage.visibility = View.GONE
        }

        viewModel.feedBackMessage.observe(this) {
            binding.rvPullRequest.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.feedbackMessage.visibility = View.VISIBLE
            binding.feedbackMessage.text = it
        }
    }

    private fun makeRequest() {
        val owner = intent.getStringExtra(OWNER) ?: "daividvleal"
        val repo = intent.getStringExtra(REPO) ?: "daividvleal"
        viewModel.getPullRequests(
            owner, repo
        )
    }


    private fun setUpList() {
        pullRequests = arrayListOf()
        adapter = PullRequestAdapter(
            pullRequests
        )
        binding.rvPullRequest.layoutManager = LinearLayoutManager(this)
        binding.rvPullRequest.adapter = adapter
    }

    companion object {
        const val OWNER = "owner"
        const val REPO = "repo"
    }

}