package com.example.data.repository

import com.example.core.ActionResult
import com.example.data.dataservice.NewsApiService
import com.example.data.model.model.response.News
import com.example.data.util.analyzeResponse
import com.example.data.util.makeApiCall

class NewsRepositoryImpl(private val newsApiService: NewsApiService) : NewsRepository {

    override suspend fun getAllNewsData(): ActionResult<News> =
        makeApiCall({
            analyzeResponse(newsApiService.getAllNews())
        })

    override suspend fun getHealthNewsData(): ActionResult<News> =
        makeApiCall({
            analyzeResponse(newsApiService.getHealthNews())
        })

    override suspend fun getTechnologyNewsData(): ActionResult<News> =
        makeApiCall({
            analyzeResponse(newsApiService.getTechnologyNews())
        })

    override suspend fun getSearchedNews(title: String): ActionResult<News> =
        makeApiCall({
            analyzeResponse(newsApiService.getSearchedNews(title))
        })

}