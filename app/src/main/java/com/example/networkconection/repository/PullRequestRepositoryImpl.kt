package com.example.networkconection.repository

import com.example.networkconection.model.PullRequest
import com.example.networkconection.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestRepositoryImpl(
    private val service: ApiService
): PullRequestRepository {

    override fun getPullRequests(
        owner: String,
        repo: String,
        onSuccess: (List<PullRequest>) -> Unit,
        onFailure: (t: Throwable) -> Unit
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
                    onFailure(t)
                }

            }
        )

    }

}