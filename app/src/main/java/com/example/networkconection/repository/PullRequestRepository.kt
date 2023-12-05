package com.example.networkconection.repository

import com.example.networkconection.model.PullRequest
import com.example.networkconection.network.Network.service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestRepository {

    fun getPullRequests(
        owner: String,
        repo: String,
        onSuccess: (List<PullRequest>) -> Unit,
        onFailure: () -> Unit
    ){
        val response = service.listPullRequest(owner, repo)
        response.enqueue(
            object : Callback<List<PullRequest>> {
                override fun onResponse(
                    call: Call<List<PullRequest>>,
                    response: Response<List<PullRequest>>
                ) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                }

                override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                    onFailure()
                }

            }
        )

    }

}