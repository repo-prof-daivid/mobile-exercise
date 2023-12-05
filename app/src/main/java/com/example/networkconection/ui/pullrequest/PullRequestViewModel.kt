package com.example.networkconection.ui.pullrequest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkconection.model.PullRequest
import com.example.networkconection.repository.PullRequestRepository

class PullRequestViewModel : ViewModel() {

    private val repository = PullRequestRepository()

    private val _pullRequests: MutableLiveData<ArrayList<PullRequest>> =
        MutableLiveData(arrayListOf())
    val pullRequests: LiveData<ArrayList<PullRequest>> = _pullRequests

    private val _feedBackMessage: MutableLiveData<String> =
        MutableLiveData(String())
    val feedBackMessage: LiveData<String> = _feedBackMessage


    fun getPullRequests(
        owner: String,
        repo: String
    ) {
        repository.getPullRequests(
            owner = owner,
            repo = repo,
            onSuccess = {
                if (it.isEmpty()) {
                    _feedBackMessage.postValue("Esse repo não contém pull requests!!!")
                } else {
                    _pullRequests.postValue(it as ArrayList<PullRequest>)
                }
            },
            onFailure = {
                _feedBackMessage.postValue("Deu ruim colega!!!")
            })
    }

}