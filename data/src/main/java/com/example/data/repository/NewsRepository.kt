package com.example.data.repository

import com.example.core.ActionResult
import com.example.data.model.model.response.News
import com.example.data.model.model.room.MyNewsDataModel
import com.example.data.model.model.room.NewsDataModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsData(category:String):ActionResult<News>
    suspend fun getHealthNewsData():ActionResult<News>
    suspend fun getTechnologyNewsData():ActionResult<News>
    suspend fun getSearchedNews(title: String):ActionResult<News>
    suspend fun getSavedNews(): List<NewsDataModel>
    suspend fun insertNews(news:NewsDataModel)
    suspend fun deleteNews(news:NewsDataModel)
    suspend fun getMyNews(): List<MyNewsDataModel>
    suspend fun insertMyNews(news:MyNewsDataModel)
    suspend fun deleteMyNews(news:MyNewsDataModel)
    suspend fun updateMyNews(news:MyNewsDataModel)
}