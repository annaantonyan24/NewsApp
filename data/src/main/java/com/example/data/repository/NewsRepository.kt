package com.example.data.repository

import com.example.core.ActionResult
import com.example.data.model.News

interface NewsRepository {

    suspend fun getAllNewsData():ActionResult<News>
    suspend fun getPopularNewsData():ActionResult<News>
    suspend fun getDevelopersNewsData():ActionResult<News>
}