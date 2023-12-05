package com.example.networkconection.ui.pullrequest.vh

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.networkconection.databinding.GithubPullListItemBinding
import com.example.networkconection.model.PullRequest
import com.squareup.picasso.Picasso

class PullRequestVH(private val binding: GithubPullListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(pullRequest: PullRequest) {
            binding.pullRequestTitle.text = pullRequest.title
            binding.btnGoToWeb.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.htmlUrl))
                binding.root.context.startActivity(browserIntent)
            }
            binding.repoOwnerUser.text = pullRequest.base.repo.owner.login
            binding.repoOwnerImage.clipToOutline = true
            Picasso.get().load(pullRequest.base.repo.owner.avatarUrl).into(binding.repoOwnerImage)
        }
}