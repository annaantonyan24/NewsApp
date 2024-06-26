package com.example.data.repository

import com.example.core.ActionResult
import com.example.data.database.MyNewsDao
import com.example.data.database.NewsDao
import com.example.data.dataservice.NewsApiService
import com.example.data.model.model.response.News
import com.example.data.model.model.room.MyNewsDataModel
import com.example.data.model.model.room.NewsDataModel
import com.example.data.util.analyzeResponse
import com.example.data.util.makeApiCall
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApiService: NewsApiService,
    private val newsDao: NewsDao,
    private val myNewsDao: MyNewsDao
) : NewsRepository {

    override suspend fun getNewsData(category: String): ActionResult<News> =
        makeApiCall({
            analyzeResponse(newsApiService.getNews(category = category))
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

    override suspend fun getMyNews(): List<MyNewsDataModel> = myNewsDao.getMyNews()
    override suspend fun insertMyNews(news: MyNewsDataModel) = myNewsDao.insertMyNews(news)
    override suspend fun deleteMyNews(news: MyNewsDataModel) = myNewsDao.deleteMyNews(news)
    override suspend fun updateMyNews(news: MyNewsDataModel) = myNewsDao.updateMyNews(news)

}