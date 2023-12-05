package com.example.networkconection.di

import com.example.networkconection.network.ApiService
import com.example.networkconection.repository.PullRequestRepository
import com.example.networkconection.repository.RepositoryRepository
import com.example.networkconection.ui.pullrequest.PullRequestViewModel
import com.example.networkconection.ui.repositories.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> {
        val retrofit: Retrofit = get()
        retrofit.create(ApiService::class.java)
    }

}

val repositoryModules = module {

    factory<RepositoryRepository> {
        RepositoryRepository(get())
    }

    factory<PullRequestRepository> {
        PullRequestRepository(get())
    }

}

val viewModelModules = module {

    viewModel {
        PullRequestViewModel(get())
    }

    viewModel {
        RepositoryViewModel(get())
    }

}