package com.example.data.dataservice

import com.example.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "b26b52df3c3a497d84cf0ff59b0537de"

interface NewsApiService {

    // All Live News
    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("language") language: String = "en",
        @Query("apiKey") key: String = API_KEY
    ): Response<News>

    // Popular News
    @GET("top-headlines")
    suspend fun getPopularNews(
        @Query ("sortBy") sortBy: String = "popularity",
        @Query("language") language: String = "en",
        @Query("apiKey") key: String = API_KEY
    ): Response<News>

    // Developers News
    @GET("everything")
    suspend fun getDevelopersNews(
        @Query("q") q: String = "developers",
        @Query("language") language: String = "en",
        @Query("apiKey") key: String = API_KEY
    ): Response<News>

    // Search for News
    @GET("everything")
    suspend fun getSearchedNews(
        @Query("q") title: String,
        @Query("language") language: String = "en",
        @Query("apiKey") key: String = API_KEY
    ): Response<News>

}