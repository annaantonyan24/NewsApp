package com.example.data.dataservice

import com.example.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("language") language: String = "en",
        @Query("apiKey") key: String = "b26b52df3c3a497d84cf0ff59b0537de"
    ): Response<News>

}