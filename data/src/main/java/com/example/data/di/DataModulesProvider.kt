package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.database.AppDatabase
import com.example.data.database.NewsDao
import com.example.data.dataservice.NewsApiService
import com.example.data.repository.NewsRepository
import com.example.data.repository.NewsRepositoryImpl
import com.example.data.util.Constants
import com.example.data.util.HeaderInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single { GsonBuilder().create() }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .apply {
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
            }
            .build()
    }

    single<NewsApiService> { get<Retrofit>().create(NewsApiService::class.java) }
}

val repositoryModule = module {
    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }
}

val newsDB = module {
    fun provideDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "NEWSDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: AppDatabase): NewsDao {
        return dataBase.newsDao
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}
