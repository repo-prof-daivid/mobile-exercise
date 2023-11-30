package com.example.networkconection.repository

import com.example.networkconection.model.Repository
import com.example.networkconection.network.Network.service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryRepository {

    fun getRepositories(
        userName: String,
        onSuccess: (List<Repository>) -> Unit,
        onFailure: () -> Unit
    ){
        val response: Call<List<Repository>> = service.listRepos(userName)
        // make the call
        response.enqueue(object : Callback<List<Repository>> {

            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                if (response.code() == 200) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: run {
                        onFailure()
                    }
                }
            }

            override fun onFailure(
                call: Call<List<Repository>>,
                t: Throwable
            ) {
                t.printStackTrace()
                onFailure()
            }

        })
    }

}