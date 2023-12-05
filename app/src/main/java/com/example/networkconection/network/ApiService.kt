package com.example.networkconection.network

import com.example.networkconection.model.PullRequest
import com.example.networkconection.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repository>>

    @GET("/repos/{owner}/{repo}/pulls")
    fun listPullRequest(@Path("owner") owner: String, @Path("repo") repo: String): Call<List<PullRequest>>
}