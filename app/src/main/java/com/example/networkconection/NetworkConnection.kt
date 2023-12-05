package com.example.networkconection

import android.app.Application
import com.example.networkconection.di.networkModule
import com.example.networkconection.di.repositoryModules
import com.example.networkconection.di.viewModelModules
import org.koin.core.context.startKoin

class NetworkConnection: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    repositoryModules,
                    networkModule,
                    viewModelModules
                )
            )
        }
    }

}