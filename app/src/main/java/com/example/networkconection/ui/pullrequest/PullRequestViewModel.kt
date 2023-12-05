package com.example.networkconection.ui.pullrequest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkconection.model.PullRequest
import com.example.networkconection.repository.PullRequestRepository

class PullRequestViewModel : ViewModel() {

    private val repository = PullRequestRepository()

    private val _pullRequests: MutableLiveData<String> =
        MutableLiveData(String())
    val pullRequests: LiveData<String> = _pullRequests


    fun getPullRequests(
        owner: String,
        repo: String
    ) {
        repository.getPullRequests(
            owner,
            repo,
            {
                _pullRequests.postValue(it.toString())
                Log.d("RESPONSE", it.toString())
            },
            {
                Log.e("RESPONSE", "DEU RUIM!")
            })
    }

}