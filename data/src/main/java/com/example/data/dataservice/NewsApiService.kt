package com.example.data.dataservice

import com.example.data.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
//    @GET("news")
//    suspend fun getNews(
//        @Query("access_key") key: String = "85025b50dcb44477d3b96c365971f8bc",
//        @Query("languages") languages: String = "en"
//    ): Response<News>

    @GET("content/all/all.json")
    suspend fun getAllNews(
        @Query("api-key") key: String = "FbfTzoGKqg7CG43sCF30v1P6ASZ66SRs"
    ): Response<News>


}