package com.example.networkconection.ui.pullrequest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networkconection.databinding.GithubPullListItemBinding
import com.example.networkconection.model.PullRequest
import com.example.networkconection.ui.pullrequest.vh.PullRequestVH

class PullRequestAdapter(
    private val pullRequests: ArrayList<PullRequest>
) : RecyclerView.Adapter<PullRequestVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestVH {
        val view =
            GithubPullListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PullRequestVH(view)
    }

    override fun getItemCount(): Int = pullRequests.size

    override fun onBindViewHolder(holder: PullRequestVH, position: Int) {
        holder.bind(pullRequest = pullRequests[position])
    }
}