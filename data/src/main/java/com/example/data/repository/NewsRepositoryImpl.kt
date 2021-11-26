package com.example.data.repository

import com.example.core.Result
import com.example.data.dataservice.NewsApiService
import com.example.data.model.News
import com.example.data.util.analyzeResponse
import com.example.data.util.makeApiCall

class NewsRepositoryImpl(private val newsApiService: NewsApiService) : NewsRepository {

    override suspend fun getAllNewsData(): Result<News> =
        makeApiCall({
            analyzeResponse(newsApiService.getAllNews())
        })
}