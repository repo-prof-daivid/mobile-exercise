package com.example.networkconection.ui.repositories.vh

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.networkconection.R
import com.example.networkconection.model.Repository
import com.example.networkconection.databinding.GithubRepoListItemBinding
import com.squareup.picasso.Picasso


class RepositoryVH(private val binding: GithubRepoListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(repository: Repository){
        binding.repositoryTitle.text = binding.root.context.getString(R.string.title, repository.name)
        repository.description.let {
            binding.repositoryDescription.text = binding.root.context.getString(R.string.description, repository.description)
        }
        binding.repoOwnerUser.text = binding.root.context.getString(R.string.created_by, repository.owner.login)
        binding.repoOwnerImage.clipToOutline = true
        Picasso.get().load(repository.owner.avatarUrl).into(binding.repoOwnerImage)
        binding.btnGoToWeb.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(repository.link))
            binding.root.context.startActivity(browserIntent)
        }
    }

}