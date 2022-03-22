package com.example.data.repository

import com.example.core.ActionResult
import com.example.data.model.News

interface NewsRepository {

    suspend fun getAllNewsData():ActionResult<News>
    suspend fun getHealthNewsData():ActionResult<News>
    suspend fun getTechnologyNewsData():ActionResult<News>
    suspend fun getSearchedNews(title: String):ActionResult<News>
}