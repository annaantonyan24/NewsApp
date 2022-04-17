package com.example.data.repository

import com.example.core.ActionResult
import com.example.data.database.NewsDao
import com.example.data.dataservice.NewsApiService
import com.example.data.model.model.response.News
import com.example.data.model.model.room.NewsDataModel
import com.example.data.util.analyzeResponse
import com.example.data.util.makeApiCall
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApiService: NewsApiService,
    private val newsDao: NewsDao
) : NewsRepository {

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

    override suspend fun getSavedNews(): List<NewsDataModel> = newsDao.getAllNews()
    override suspend fun insertNews(news: NewsDataModel) = newsDao.insertData(news)
    override suspend fun deleteNews(news: NewsDataModel) = newsDao.deleteData(news)

}