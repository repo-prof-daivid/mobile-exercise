package com.example.networkconection.ui.repositories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networkconection.model.Repository
import com.example.networkconection.databinding.GithubRepoListItemBinding
import com.example.networkconection.ui.repositories.vh.RepositoryVH

class RepositoryAdapter(
    private val repositories: ArrayList<Repository>
) : RecyclerView.Adapter<RepositoryVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryVH {
        val binding =
            GithubRepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryVH(binding)
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: RepositoryVH, position: Int) {
        holder.bind(repositories[position])
    }
}