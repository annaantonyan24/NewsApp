package com.example.data.repository

import com.example.core.Result
import com.example.data.model.News

interface NewsRepository {

    suspend fun getAllNewsData():Result<News>
}