package com.example.data.dataservice

import com.example.data.model.model.response.News
import retrofit2.Response
import retrofit2.http.GET
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
    suspend fun getHealthNews(
        @Query("language") language: String = "en",
        @Query("apiKey") key: String = API_KEY,
        @Query("category") category: String = "health",
    ): Response<News>

    // Developers News
    @GET("top-headlines")
    suspend fun getTechnologyNews(
        @Query("language") language: String = "en",
        @Query("apiKey") key: String = API_KEY,
        @Query("category") category: String = "technology",
    ): Response<News>

    // Search for News
    @GET("everything")
    suspend fun getSearchedNews(
        @Query("q") title: String,
        @Query("language") language: String = "en",
        @Query("apiKey") key: String = API_KEY
    ): Response<News>

}