package com.example.data.repository

import com.example.core.ActionResult
import com.example.data.dataservice.NewsApiService
import com.example.data.model.News
import com.example.data.util.analyzeResponse
import com.example.data.util.makeApiCall

class NewsRepositoryImpl(private val newsApiService: NewsApiService) : NewsRepository {

    override suspend fun getAllNewsData(): ActionResult<News> =
        makeApiCall({
            analyzeResponse(newsApiService.getAllNews())
        })

    override suspend fun getPopularNewsData(): ActionResult<News> =
        makeApiCall({
            analyzeResponse(newsApiService.getPopularNews())
        })


}