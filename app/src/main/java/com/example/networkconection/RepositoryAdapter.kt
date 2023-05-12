package com.example.networkconection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networkconection.databinding.GithubRepoListItemBinding

class RepositoryAdapter(
    private val repositorios: ArrayList<Repositorio>
) : RecyclerView.Adapter<RepositoryVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryVH {
        val binding =
            GithubRepoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryVH(binding)
    }

    override fun getItemCount(): Int = repositorios.size

    override fun onBindViewHolder(holder: RepositoryVH, position: Int) {
        holder.bind(repositorios[position])
    }
}