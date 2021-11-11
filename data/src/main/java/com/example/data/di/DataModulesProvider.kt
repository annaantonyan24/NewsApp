package com.example.data.di

import com.example.data.util.Constants
import com.google.gson.GsonBuilder
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



            }
            .build()
    }
}