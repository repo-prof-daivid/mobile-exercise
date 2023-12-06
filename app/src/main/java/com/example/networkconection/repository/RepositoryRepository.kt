package com.example.networkconection.repository

import com.example.networkconection.model.Repository

interface RepositoryRepository {

    fun  getRepositories(
        userName: String,
        onSuccess: (List<Repository>) -> Unit,
        onFailure: () -> Unit
    )
}