package com.example.networkconection

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.networkconection.databinding.GithubRepoListItemBinding
import com.squareup.picasso.Picasso

class RepositoryVH(private val binding: GithubRepoListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(repositorio: Repositorio){
        binding.repositoryTitle.text = binding.root.context.getString(R.string.title, repositorio.name)
        repositorio.description?.let {
            binding.repositoryDescription.text = binding.root.context.getString(R.string.description, repositorio.description)
        } ?: run {
            binding.repositoryDescription.visibility = View.GONE
        }
        binding.repoOwnerUser.text = binding.root.context.getString(R.string.created_by, repositorio.owner.login)
        Picasso.get().load(repositorio.owner.avatarUrl).into(binding.repoOwnerImage);
    }
}