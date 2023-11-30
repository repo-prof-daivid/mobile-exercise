package com.example.networkconection.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkconection.model.Repository
import com.example.networkconection.repository.RepositoryRepository

class RepositoryViewModel : ViewModel() {

    private val repositoryRepository = RepositoryRepository()

    private val _repositories: MutableLiveData<ArrayList<Repository>> =
        MutableLiveData(arrayListOf())
    val repositories: LiveData<ArrayList<Repository>> = _repositories

    private val _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _empty: MutableLiveData<Boolean> = MutableLiveData(false)
    val empty: LiveData<Boolean> = _empty

    fun getRepositories(userName: String = "daividvleal") {
        _loading.postValue(true)
        repositoryRepository.getRepositories(
            userName = userName,
            onSuccess = {
                if (it.isEmpty()) {
                    _loading.postValue(false)
                    _error.postValue(true)
                } else {
                    _loading.postValue(false)
                    _repositories.postValue(it as ArrayList<Repository>)
                }
            },
            onFailure = {
                _loading.postValue(false)
                _error.postValue(true)
            }
        )
    }
}