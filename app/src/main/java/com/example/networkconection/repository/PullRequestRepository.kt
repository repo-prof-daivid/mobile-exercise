package com.example.networkconection.repository

import com.example.networkconection.model.PullRequest

interface PullRequestRepository {

    fun getPullRequests(
        owner: String,
        repo: String,
        onSuccess: (List<PullRequest>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    )

}