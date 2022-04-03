package com.example.diplomayin

import android.app.Application
import com.example.data.di.apiModule
import com.example.data.di.newsDB
import com.example.data.di.repositoryModule
import com.example.diplomayin.di.viewModelModule
import com.example.domain.di.interactorModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }

    private val modules = listOf(
        viewModelModule,
        apiModule,
        repositoryModule,
        interactorModule,
        newsDB
    )
}